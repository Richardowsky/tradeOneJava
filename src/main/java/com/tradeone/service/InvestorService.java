package com.tradeone.service;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.AssetBalance;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.event.OrderTradeUpdateEvent;
import com.binance.api.client.exception.BinanceApiException;
import com.tradeone.domain.AppResponse;
import com.tradeone.domain.BinanceApi;
import com.tradeone.domain.InvestorDto;
import com.tradeone.domain.TraderDtoOut;
import com.tradeone.domain.TradingHistoryDtoOut;
import com.tradeone.domain.WalletBITTDto;
import com.tradeone.exception.AppException;
import com.tradeone.model.Investor;
import com.tradeone.model.InvestorWallet;
import com.tradeone.model.Rialto;
import com.tradeone.model.Role;
import com.tradeone.model.Subscription;
import com.tradeone.model.Trader;
import com.tradeone.model.TraderWallet;
import com.tradeone.model.TradingHistory;
import com.tradeone.model.WalletBITT;
import com.tradeone.repository.InvestorRepository;
import com.tradeone.repository.InvestorWalletRepository;
import com.tradeone.repository.RialtoRepository;
import com.tradeone.repository.SubscriptionRepository;
import com.tradeone.repository.TraderRepository;
import com.tradeone.repository.TradingHistoryRepository;
import com.tradeone.repository.WalletBITTRepository;
import info.blockchain.api.APIException;
import info.blockchain.api.wallet.Wallet;
import info.blockchain.api.wallet.entity.Address;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class InvestorService implements UserDetailsService {

  @Autowired
  private InvestorRepository investorRepository;
  @Autowired
  private InvestorWalletRepository investorWalletRepository;
  @Autowired
  private TraderRepository traderRepository;
  @Autowired
  private MailSender mailSender;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private TradingHistoryRepository tradingHistoryRepository;
  @Autowired
  private WalletBITTRepository walletBITTRepository;
  @Autowired
  private RialtoRepository rialtoRepository;
  @Autowired
  private SubscriptionRepository subscriptionRepository;


  @Value("${hostname}")
  private String hostname;
  private final double satoshi = 0.00000001;
  private static final long blockchainFee = 500;
  private static final String eventMail = "someMail@gmail.com";


  public boolean addNewInvestor(InvestorDto investorDto) throws APIException, IOException {

    Investor investor = new Investor();

    String username = investorDto.getUsername();
    String firstName = investorDto.getFirstName();
    String lastName = investorDto.getLastName();
    int year = investorDto.getYear();
    int month = investorDto.getMonth();
    int day = investorDto.getDay();
    String password = investorDto.getPassword();
    String email = investorDto.getEmail();
    LocalDate birthDate = LocalDate.of(year, month, day);
    String country = investorDto.getCountry();
    LocalDate registrationDate = LocalDate.now();

    Optional<Investor> allByLogin = investorRepository.getAllByUsername(username);
    Optional<Investor> allByEmail = investorRepository.getAllByEmail(email);
    if (allByLogin.isPresent() || allByEmail.isPresent()) {
      throw new AppException("Investor already exist!!!");
    }

    investor.setActive(true);
    investor.setRoles(Collections.singleton(Role.USER));
    investor.setUsername(username);
    investor.setFirstName(firstName);
    investor.setLastName(lastName);
    investor.setPassword(passwordEncoder.encode(password));
    investor.setEmail(email);
    // investor.setFilename(userPhoto);
    investor.setBirthDate(birthDate);
    investor.setCountry(country);
    investor.setRegistrationDate(registrationDate);
    investor.setActivationCode(UUID.randomUUID().toString());
    if (!StringUtils.isEmpty(email)) {
      String message = String.format(
          "Hello, %s %s! \n" +
              "Welcome to TradeOne. Please, visit next link: http://%s/activate/%s",
          firstName, lastName,
          hostname,
          investor.getActivationCode()
      );

      mailSender.send(email, "Activation code", message);
    }

    InvestorWallet investorWallet = new InvestorWallet();
    Wallet wallet = investorWallet.getWallet();
    Address address = wallet.newAddress(username);
    investorWallet.setAddress(address.getAddress());
    investor.setInvestorWallet(investorWallet);
    investorWalletRepository.save(investorWallet);
    investorRepository.save(investor);

    String eventMessage = String.format("New investor - %s %s!", firstName, lastName);
    mailSender.send(eventMail, "New Investor", eventMessage);

    return true;
  }

  public void setApiBinance(Investor investor, BinanceApi binanceApi) {
    investor.setApiKey(binanceApi.getApiKey());
    investor.setSecretKey(binanceApi.getSecretKey());
    investorRepository.save(investor);
  }

  public AppResponse getApiInfo(Investor investor) {
    String apiKey = investor.getApiKey();
    String secretKey = investor.getSecretKey();
    BinanceApiClientFactory investorFactory = BinanceApiClientFactory
        .newInstance(apiKey, secretKey);
    BinanceApiRestClient investorClient = investorFactory.newRestClient();
    Account investorClientAccount = investorClient.getAccount();
    String btc = investorClientAccount.getAssetBalance("BTC").getFree();

    AppResponse appResponse = new AppResponse();
    appResponse.put("apiKey", apiKey);
    appResponse.put("balance", btc);

    return appResponse;
  }


  public AppResponse getInvestorWalletInfo(Investor investor) throws APIException, IOException {
    InvestorWallet investorWallet = investor.getInvestorWallet();
    Wallet wallet = investorWallet.getWallet();
    String investorAddress = investorWallet.getAddress();
    Address address = wallet.getAddress(investorAddress);
    long balance = address.getBalance();
    long copyBalance = 0;
    double profit = 0;

    Set<Subscription> subscriptions = investorWallet.getSubscriptions();
    for (Subscription subscription : subscriptions) {
      copyBalance += subscription.getCoins();
      profit += (subscription.getCoins() * subscription.getProfit()) / 100;
    }

    investorWallet.setFreeCoins(balance);
    investorWalletRepository.save(investorWallet);

    double balanceInBitcoin = balance * satoshi;
    AppResponse appResponse = new AppResponse();
    appResponse.put("balance", balanceInBitcoin);
    appResponse.put("copyBalance", copyBalance);
    appResponse.put("profit", profit);

    return appResponse;
  }

  public AppResponse getInvestorWalletAddress(Investor investor) throws APIException, IOException {
    InvestorWallet investorWallet = investor.getInvestorWallet();
    Wallet wallet = investorWallet.getWallet();
    String investorAddress = investorWallet.getAddress();
    Address address = wallet.getAddress(investorAddress);
    String addressAddress = address.getAddress();

    AppResponse appResponse = new AppResponse();
    appResponse.put("address", investorAddress);

    return appResponse;
  }

  public AppResponse getWalletBITTAddress() {
    WalletBITT walletBITT = new WalletBITT();
    // String walletAddress = walletBITT.walletAddress();
    String addressBITT = walletBITT.getAddressBITT();
    AppResponse appResponse = new AppResponse();
    appResponse.put("addressBITT", addressBITT);
    return appResponse;
  }


  public AppResponse getBalanceBITT(WalletBITTDto walletBITTDto, Investor investor) {
    long decimal = 1000000;
    WalletBITT walletBITT = new WalletBITT();
    String address = walletBITTDto.getResponseAddress();
    String hash = walletBITTDto.getResponseHash();
    long bitt = (walletBITTDto.getResponseBitt() / decimal) / decimal;

    walletBITT.setInvestor(investor);
    walletBITT.setAddress(address);
    walletBITT.setHash(hash);
    walletBITT.setBitt(bitt);

    Optional<WalletBITT> allByTraderAndAndInvestorWallet = walletBITTRepository
        .getAllByHash(hash);
    if (allByTraderAndAndInvestorWallet.isPresent()) {
      throw new AppException("Hash already exist!!!");
    }
    InvestorWallet investorWallet = investor.getInvestorWallet();
    investorWallet.setFreeBITT(investorWallet.getFreeBITT() + bitt);
    walletBITTRepository.save(walletBITT);
    investorWalletRepository.save(investorWallet);
    AppResponse appResponse = new AppResponse();
    appResponse.put("balanceBITT", investorWallet.getFreeBITT());
    return appResponse;
  }


  public void SendCoinsToTrader(long investorId, long traderId, long coins, long fee)
      throws APIException, IOException {
    Investor investor = getInvestor(investorId);
    String firstName = investor.getFirstName();
    String lastName = investor.getLastName();
    InvestorWallet investorWallet = investor.getInvestorWallet();
    Trader trader = getTrader(traderId);
    String traderFirstName = trader.getFirstName();
    String traderLastName = trader.getLastName();
    TraderWallet traderWallet = trader.getTraderWallet();
    String traderWalletAddress = traderWallet.getAddress();
    Wallet wallet = investorWallet.getWallet();
    String investorAddress = investorWallet.getAddress();

    wallet.send(traderWalletAddress, coins, investorAddress, fee);
    String eventMessage = String
        .format("New subscription, investor - %s %s, subscribe to trader - %s %s", firstName,
            lastName, traderFirstName, traderLastName);
    mailSender.send(eventMail, "New Subscription", eventMessage);
  }

  public void withdrawCoins(String withdrawAddress, long coins, Investor investor)
      throws APIException, IOException {
    InvestorWallet investorWallet = investor.getInvestorWallet();
    Wallet wallet = investorWallet.getWallet();
    String investorAddress = investorWallet.getAddress();
    wallet.send(withdrawAddress, coins, investorAddress, blockchainFee);

  }

  public AppResponse getTraders() {
    List<Trader> traders = traderRepository.findAll();
    List<TraderDtoOut> collect = traders.stream()
        .map(p -> new TraderDtoOut(p))
        .collect(Collectors.toList());
    AppResponse appResponse = new AppResponse();
    appResponse.put("traders", collect);
    return appResponse;
  }


  public AppResponse getTradingHistory(Investor investor) {
    InvestorWallet investorWallet = investor.getInvestorWallet();
    Set<Subscription> subscriptions = investorWallet.getSubscriptions();
    List<TradingHistory> history = new LinkedList<TradingHistory>();

    for (Subscription subscription : subscriptions) {
      Trader trader = subscription.getTrader();
      Long id = trader.getId();

      List<TradingHistory> historyList = tradingHistoryRepository.getByTraderId(id);
      int size = historyList.size();
      for (int i = 0; i < size; i++) {
        TradingHistory tradingHistory = historyList.get(i);
        if (history.contains(tradingHistory)) {
          break;
        }
        history.add(tradingHistory);

        if (tradingHistory.getTransactionDate().toEpochDay() < subscription.getStartDate()
            .toEpochDay()) {
          history.remove(tradingHistory);
        }
      }
    }

    List<TradingHistoryDtoOut> collect = history.stream()
        .map(p -> new TradingHistoryDtoOut(p))
        .collect(Collectors.toList());
    AppResponse appResponse = new AppResponse();
    appResponse.put("history", collect);
    return appResponse;
  }


  public Investor getInvestor(Long investorId) {
    Optional<Investor> byId = investorRepository.getById(investorId);
    return byId.orElse(null);
  }

  public Trader getTrader(Long traderId) {
    Optional<Trader> byId = traderRepository.getById(traderId);
    return byId.orElse(null);
  }

  public Investor getByUsername(String username) {
    Optional<Investor> byNickname = investorRepository.getAllByUsername(username);
    return byNickname.orElse(null);
  }

  public Investor getByEmail(String email) {
    Optional<Investor> byEmail = investorRepository.getAllByEmail(email);
    return byEmail.orElse(null);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.contains("@")) {
      return getByEmail(username);
    } else {
      return getByUsername(username);
    }
  }

  public boolean activateUser(String code) {
    Investor user = investorRepository.findByActivationCode(code);

    if (user == null) {
      return false;
    }
    user.setActivationCode(null);
    investorRepository.save(user);
    return true;
  }

  public AppResponse getCredentials(Investor investor) {
    AppResponse appResponse = new AppResponse();
    appResponse.put("firstname", investor.getFirstName());
    appResponse.put("lastname", investor.getLastName());
    appResponse.put("username", investor.getUsername());
    return appResponse;
  }


  public AppResponse getInvestorWalletBITT(Investor investor) {
    InvestorWallet investorWallet = investor.getInvestorWallet();
    double freeBITT = investorWallet.getFreeBITT();
    AppResponse appResponse = new AppResponse();
    appResponse.put("bitt", freeBITT);
    return appResponse;

  }

  @Async
  public void apiStart() {
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
    long traderId = 10;
    Trader trader = traderRepository.getOneById(traderId);
    String traderApiKey = trader.getApiKey();
    String traderSecretKey = trader.getSecretKey();
    BinanceApiClientFactory factory = BinanceApiClientFactory
        .newInstance(traderApiKey, traderSecretKey);
    BinanceApiRestClient client = factory.newRestClient();
    double traderBalance = 0.005;
    double percent = 1;
    String listenKey = client.startUserDataStream();
    BinanceApiWebSocketClient webSocketClient = factory.newWebSocketClient();
    webSocketClient.onUserDataUpdateEvent(listenKey, response -> {
      OrderTradeUpdateEvent orderTradeUpdateEvent = response.getOrderTradeUpdateEvent();

      if (orderTradeUpdateEvent.getExecutionType().toString().equals("TRADE")) {
        System.out.println(orderTradeUpdateEvent);
        Set<Investor> investors = new HashSet<>();
        List<Subscription> subscriptions = subscriptionRepository.findByTrader(trader);
        for (Subscription subscription : subscriptions) {
          InvestorWallet investorWallet = subscription.getInvestorWallet();
          Investor investor = investorRepository.getByInvestorWallet(investorWallet);
          investors.add(investor);
        }
        String symbol = orderTradeUpdateEvent.getSymbol();
        //String originalQuantity = orderTradeUpdateEvent.getOriginalQuantity();
        double traderPrice = Double
            .parseDouble(orderTradeUpdateEvent.getPriceOfLastFilledTrade());
        double traderQuantity = Double
            .parseDouble(orderTradeUpdateEvent.getOriginalQuantity());
        double traderTradeBTC = traderPrice * traderQuantity;
        double traderPersentDeal = (traderTradeBTC / traderBalance);
        if (traderPersentDeal > percent) {
          traderPersentDeal = percent;
        }
        try {
          for (Investor investor : investors) {
            String investorApiKey = investor.getApiKey();
            String investorSecretKey = investor.getSecretKey();
            InvestorWallet investorWallet = investor.getInvestorWallet();
            double investorBalance = 0;

            for (Subscription subscription : subscriptions) {
              if (subscription.getInvestorWallet().getAddress()
                  .equals(investorWallet.getAddress())) {
                investorBalance += subscription.getCoins() * satoshi;
              }
            }
            double investorTradeBTC = investorBalance * traderPersentDeal;
            if (investorTradeBTC > investorBalance) {
              investorTradeBTC = investorBalance;
            }
            BinanceApiClientFactory investorFactory = BinanceApiClientFactory
                .newInstance(investorApiKey, investorSecretKey);
            BinanceApiRestClient investorClient = investorFactory.newRestClient();
            Account investorAccount = investorClient.getAccount();
            double investorQuantity = (investorTradeBTC / traderPrice);
            char[] format = getChars(symbol);
            String formatSymbol = new String(format);
            AssetBalance assetBalance = investorAccount.getAssetBalance(formatSymbol);
            double investorFreeQuantity = Double.parseDouble(assetBalance.getFree());

            if (investorQuantity > investorFreeQuantity) {
              investorQuantity = investorFreeQuantity;
            }
            if (orderTradeUpdateEvent.getSide().toString().equals("BUY")) {
              double investorBTC = Double
                  .parseDouble(investorAccount.getAssetBalance("BTC").getFree());
              if (investorTradeBTC > investorBTC) {
                investorTradeBTC = investorBTC;
              }
              double investorQuantityBuy = (investorTradeBTC / traderPrice);
              apiBinanceBuy(symbol, investorClient, investorQuantityBuy);
            } else if (orderTradeUpdateEvent.getSide().toString().equals("SELL")) {
              apiBinanceSell(symbol, investorClient, investorQuantity);
            }
          }
        } catch (BinanceApiException e) {
          System.out.println(e.getError().getCode());
          System.out.println(e.getError().getMsg());
        } finally {
          saveHistory(trader, orderTradeUpdateEvent);
          saveRialto(trader, orderTradeUpdateEvent);
        }
      } else {
        System.out.println(orderTradeUpdateEvent);
      }
    });
    System.out.println("Waiting for events...");
    client.keepAliveUserDataStream(listenKey);

    ses.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        client.closeUserDataStream(listenKey);
        System.out.println("End...");
        apiStart();
        ses.shutdown();
      }
    }, 1, 1, TimeUnit.MINUTES);
  }

  private char[] getChars(String symbol) {
    int length = symbol.length();
    char[] chars = symbol.toCharArray();
    char[] format = new char[length - 3];
    for (int i = 0; i < length - 3; i++) {
      format[i] = chars[i];
    }
    return format;
  }

  private void apiBinanceSell(String symbol, BinanceApiRestClient investorClient,
      double investorQuantity) {
    if (symbol.equals("POLYBTC") || symbol.equals("XRPBTC")
        || symbol.equals("RVNBTC") || symbol.equals("EOSBTC") || symbol.equals("ADABTC")
        || symbol.equals("TNBBTC")) {
      String quantitySell = Double.toString((long) investorQuantity);
      NewOrderResponse newOrderResponse = investorClient
          .newOrder(NewOrder.marketSell(symbol, quantitySell));
      System.out.println(newOrderResponse);
    } else {
      DecimalFormat df = new DecimalFormat("#.######");
      if (symbol.equals("ETHBTC") || symbol.equals("BCHABCBTC") || symbol
          .equals("ZECBTC") || symbol.equals("DASHBTC")) {
        df = new DecimalFormat("#.###");
      } else if (symbol.equals("BNBBTC") || symbol.equals("LTCBTC") || symbol
          .equals("ETCBTC") || symbol
          .equals("NEOBTC")) {
        df = new DecimalFormat("#.##");
      }
      df.setRoundingMode(RoundingMode.CEILING);
      String formatQuantity = df.format(investorQuantity);
      NewOrderResponse newOrderResponse = investorClient
          .newOrder(NewOrder.marketSell(symbol, formatQuantity));
      System.out.println(newOrderResponse);
    }
  }

  private void apiBinanceBuy(String symbol, BinanceApiRestClient investorClient,
      double investorQuantityBuy) {
    if (symbol.equals("EOSBTC") || symbol.equals("XRPBTC")
        || symbol
        .equals("RVNBTC") || symbol.equals("POLYBTC") || symbol.equals("ADABTC")
        || symbol
        .equals("TNBBTC")) {
      String quantityBuy = Double.toString((long) investorQuantityBuy);
      NewOrderResponse newOrderResponse = investorClient
          .newOrder(NewOrder.marketBuy(symbol, quantityBuy));
      System.out.println(newOrderResponse);
    } else {
      DecimalFormat df = new DecimalFormat("#.######");
      if (symbol.equals("ZECBTC") || symbol.equals("BCHABCBTC") || symbol
          .equals("ETHBTC") || symbol.equals("DASHBTC")) {
        df = new DecimalFormat("#.###");
      } else if (symbol.equals("BNBBTC") || symbol.equals("LTCBTC") || symbol
          .equals("NEOBTC") || symbol
          .equals("ETCBTC")) {
        df = new DecimalFormat("#.##");
      }
      df.setRoundingMode(RoundingMode.CEILING);
      String formatQuantity = df.format(investorQuantityBuy);
      NewOrderResponse newOrderResponse = investorClient
          .newOrder(NewOrder.marketBuy(symbol, formatQuantity));
      System.out.println(newOrderResponse);
    }
  }

  private void saveRialto(Trader trader, OrderTradeUpdateEvent orderTradeUpdateEvent) {
    Rialto rialto = rialtoRepository.getByTrader(trader);
    rialto.setOrdersByDay(rialto.getOrdersByDay() + 1);
    rialto.setBalance(rialto.getBalance() + (long) (
        (Double.parseDouble(orderTradeUpdateEvent.getOriginalQuantity()) * Double
            .parseDouble(orderTradeUpdateEvent.getPriceOfLastFilledTrade())) / satoshi));
    rialtoRepository.save(rialto);
  }

  private void saveHistory(Trader trader, OrderTradeUpdateEvent orderTradeUpdateEvent) {
    TradingHistory tradingHistory = new TradingHistory();
    tradingHistory.setTrader(trader);
    tradingHistory.setPair(orderTradeUpdateEvent.getSymbol());
    tradingHistory.setTransactionDate(LocalDate.now());
    tradingHistory
        .setPrice(Double.parseDouble(orderTradeUpdateEvent.getPriceOfLastFilledTrade()));
    tradingHistory.setRialto("Binance");
    tradingHistory.setType(orderTradeUpdateEvent.getSide().toString());
    tradingHistoryRepository.save(tradingHistory);
  }

  public boolean validationName(String username) {
    Optional<Investor> allByLogin = investorRepository.getAllByUsername(username);
    if (allByLogin.isPresent()) {
      return false;
    } else {
      return true;
    }
  }

  public boolean validationEmail(String email) {
    Optional<Investor> allByEmail = investorRepository.getAllByEmail(email);
    if (allByEmail.isPresent()) {
      return false;
    } else {
      return true;
    }
  }
}
