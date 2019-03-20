package com.tradeone.service;

import com.tradeone.domain.AppResponse;
import com.tradeone.domain.InvestorDto;
import com.tradeone.domain.TraderDtoOut;
import com.tradeone.domain.TransactionDto;
import com.tradeone.exception.AppException;
import com.tradeone.model.Investor;
import com.tradeone.model.InvestorWallet;
import com.tradeone.model.Role;
import com.tradeone.model.Trader;
import com.tradeone.model.TraderWallet;
import com.tradeone.model.Transaction;
import com.tradeone.model.TransactionRole;
import com.tradeone.repository.InvestorRepository;
import com.tradeone.repository.InvestorWalletRepository;
import com.tradeone.repository.TraderRepository;
import info.blockchain.api.APIException;
import info.blockchain.api.wallet.Wallet;
import info.blockchain.api.wallet.entity.Address;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class InvestorService implements UserDetailsService {

  @Autowired
  private InvestorRepository investorRepository;
  @Autowired
  private InvestorWalletRepository investorWalletRepository;
  @Autowired
  private TraderRepository traderRepository;

  private final double satoshi = 0.00000001;


  public void addNewInvestor(InvestorDto investorDto) throws APIException, IOException {
    Investor investor = new Investor();

    String username = investorDto.getUsername();
    String firstName = investorDto.getFirstName();
    String lastName = investorDto.getLastName();
    String password = investorDto.getPassword();
    String email = investorDto.getEmail();
    String userPhoto = investorDto.getFilename();

    int year = investorDto.getYear();
    int month = investorDto.getMonth();
    int day = investorDto.getDay();
    LocalDate birthDate = LocalDate.of(year, month, day);
    String country = investorDto.getCountry();
    LocalDate registrationDate = LocalDate.now();

    Optional<Investor> allByLogin = investorRepository.getAllByUsername(username);
    if (allByLogin.isPresent()) {
      throw new AppException("Investor already exist!!!");
    }

    investor.setActive(true);
    investor.setRoles(Collections.singleton(Role.USER));
    investor.setUsername(username);
    investor.setFirstName(firstName);
    investor.setLastName(lastName);
    investor.setPassword(password);
    investor.setEmail(email);
    investor.setFilename(userPhoto);
    investor.setBirthDate(birthDate);
    investor.setCountry(country);
    investor.setRegistrationDate(registrationDate);

    InvestorWallet investorWallet = new InvestorWallet();
    Wallet wallet = investorWallet.getWallet();
    Address address = wallet.newAddress(username);
    investorWallet.setAddress(address.getAddress());
    investor.setInvestorWallet(investorWallet);
    investorWalletRepository.save(investorWallet);

    investorRepository.save(investor);
  }


  public AppResponse getInvestorWalletInfo(long id) throws APIException, IOException {
    Investor investor = getInvestor(id);
    InvestorWallet investorWallet = investor.getInvestorWallet();
    Wallet wallet = investorWallet.getWallet();
    String investorAddress = investorWallet.getAddress();
    Address address = wallet.getAddress(investorAddress);
    long balance = address.getBalance();

    investorWallet.setFreeCoins(balance);
    investorWalletRepository.save(investorWallet);

    double balanceInBitcoin = balance * satoshi;
    AppResponse appResponse = new AppResponse();
    appResponse.put("balance", balanceInBitcoin);
    return appResponse;
  }

  public AppResponse getInvestorWalletAddress(long id) throws APIException, IOException {
    Investor investor = getInvestor(id);
    InvestorWallet investorWallet = investor.getInvestorWallet();
    Wallet wallet = investorWallet.getWallet();
    String investorAddress = investorWallet.getAddress();
    Address address = wallet.getAddress(investorAddress);
    String addressAddress = address.getAddress();

    AppResponse appResponse = new AppResponse();
    appResponse.put("address", addressAddress);

    return appResponse;
  }

  public void SendCoinsToTrader(long investorId, long traderId, long coins, long fee)
      throws APIException, IOException {
    Investor investor = getInvestor(investorId);
    InvestorWallet investorWallet = investor.getInvestorWallet();
    Trader trader = getTrader(traderId);
    TraderWallet traderWallet = trader.getTraderWallet();
    String traderWalletAddress = traderWallet.getAddress();
    Wallet wallet = investorWallet.getWallet();
    String investorAddress = investorWallet.getAddress();
    wallet.send(traderWalletAddress, coins, investorAddress, fee);
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


  public Investor getInvestor(Long investorId) {
    Optional<Investor> byId = investorRepository.getById(investorId);
    return byId.orElse(null);
  }

  public Trader getTrader(Long traderId) {
    Optional<Trader> byId = traderRepository.getById(traderId);
    return byId.orElse(null);
  }

  public Investor getByNickname(String nickname) {
    Optional<Investor> byNickname = investorRepository.getAllByUsername(nickname);
    return byNickname.orElse(null);
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return getByNickname(s);
  }


//  public AppResponse getTransactions(long investorId) {
//    Investor investor = getInvestor(investorId);
//    InvestorWallet investorWallet = investor.getInvestorWallet();
//    Set<Transaction> transactions = investorWallet.getTransactions();
//    List<TransactionDto> collect = transactions.stream()
//        .map(p -> new TransactionDto(p))
//        .collect(Collectors.toList());
//    AppResponse appResponse = new AppResponse();
//    appResponse.put("transactions", collect);
//    return appResponse;
//  }
  }
