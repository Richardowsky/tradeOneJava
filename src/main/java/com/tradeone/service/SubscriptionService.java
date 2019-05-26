package com.tradeone.service;


import com.tradeone.domain.AppResponse;
import com.tradeone.domain.SubscriptionDto;
import com.tradeone.domain.SubscriptionDtoOut;
import com.tradeone.exception.AppException;
import com.tradeone.model.Investor;
import com.tradeone.model.InvestorWallet;
import com.tradeone.model.Subscription;
import com.tradeone.model.Trader;
import com.tradeone.repository.InvestorWalletRepository;
import com.tradeone.repository.SubscriptionRepository;
import com.tradeone.repository.TraderRepository;
import info.blockchain.api.APIException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

  @Autowired
  private SubscriptionRepository subscriptionRepository;
  @Autowired
  private TraderRepository traderRepository;
  @Autowired
  private InvestorService investorService;
  @Autowired
  private MailSender mailSender;
  @Autowired
  private InvestorWalletRepository investorWalletRepository;

  private static final long blockchainFee = 500;
  private static final String eventMail = "someMail@gmail.com";

  public void deleteSubscription(long id, Investor investor) {
    String firstName = investor.getFirstName();
    String lastName = investor.getLastName();
    Trader trader = traderRepository.getOneById(id);
    String traderFirstName = trader.getFirstName();
    String traderLastName = trader.getLastName();
    String eventMessage = String
        .format("Invesor - %s %s want to delete subscription on trader - id %s, %s %s", firstName,
            lastName, id, traderFirstName, traderLastName);
    mailSender.send(eventMail, "Delete Subscription", eventMessage);

  }

  public AppResponse addNewSubscription(SubscriptionDto subscriptionDto, Investor investor)
      throws APIException, IOException {
    AppResponse appResponse = new AppResponse();

    Subscription subscription = new Subscription();

    long traderId = subscriptionDto.getTraderId();
    int stopLoss = subscriptionDto.getStopLoss();
    int days = subscriptionDto.getDays();
    long coins = subscriptionDto.getCoins();
    LocalDate startSubscriptionDate = LocalDate.now();
    long fee = subscriptionDto.getFee();
    double bittFee = subscriptionDto.getBittFee();
    String type = subscriptionDto.getType();
    long total = 0;
    if (type.equals("TradeOne")) {
      total = (coins + fee) - blockchainFee;
    } else if (type.equals("Binance")) {
      total = fee - blockchainFee;
    }
    Trader trader = traderRepository.getOneById(traderId);
    long investorId = investor.getId();
    InvestorWallet investorWallet = investor.getInvestorWallet();
    Optional<Subscription> allByTraderAndAndInvestorWallet = subscriptionRepository
        .getAllByTraderAndInvestorWallet(trader, investorWallet);
    if (!allByTraderAndAndInvestorWallet.isPresent()) {
      trader.setInvestorsQuantity(trader.getInvestorsQuantity() + 1);
    }
    if (allByTraderAndAndInvestorWallet.isPresent() && type.equals("Binance")) {
      appResponse.put("response", "Subscription already exist!!!");
      throw new AppException("Subscription already exist!!!");
    }
    subscription.setTrader(trader);
    subscription.setStopLoss(stopLoss);
    subscription.setDays(days);
    subscription.setCoins(coins);
    subscription.setStartDate(startSubscriptionDate);
    subscription.setEndDate(startSubscriptionDate.plusDays((long) days));
    subscription.setInvestorWallet(investorWallet);
    subscription.setBittFee(bittFee);
    subscription.setType(type);
    if (bittFee == 0) {
      subscription.setFee(fee - blockchainFee);
    } else {
      subscription.setFee(fee);
    }
    investorWallet.setFreeBITT(investorWallet.getFreeBITT() - bittFee);
    if (type.equals("TradeOne") || bittFee == 0) {
      investorService.SendCoinsToTrader(investorId, traderId, total, blockchainFee);
    }
    investorWalletRepository.save(investorWallet);
    investorWalletRepository.flush();
    subscriptionRepository.save(subscription);
    appResponse.put("response", "Subscription ok!!!");
    return appResponse;
  }


  public AppResponse getSubscriptions(Investor investor) {
    InvestorWallet investorWallet = investor.getInvestorWallet();
    List<Subscription> subscriptions = subscriptionRepository.findByInvestorWallet(investorWallet);
    List<SubscriptionDtoOut> collect = subscriptions.stream()
        .map(p -> new SubscriptionDtoOut(p))
        .collect(Collectors.toList());
    AppResponse appResponse = new AppResponse();
    appResponse.put("subscriptions", collect);
    return appResponse;
  }


}
