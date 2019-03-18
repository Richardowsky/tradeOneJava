package com.tradeone.service;


import com.tradeone.domain.AppResponse;
import com.tradeone.domain.SubscriptionDto;
import com.tradeone.domain.SubscriptionDtoOut;
import com.tradeone.exception.AppException;
import com.tradeone.model.Investor;
import com.tradeone.model.InvestorWallet;
import com.tradeone.model.Subscription;
import com.tradeone.model.Trader;
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


  public void addNewSubscription(SubscriptionDto subscriptionDto, long id)
      throws APIException, IOException {
    Subscription subscription = new Subscription();

    long traderId = subscriptionDto.getTraderId();
    int stopLoss = subscriptionDto.getStopLoss();
    int days = subscriptionDto.getDays();
    long coins = subscriptionDto.getCoins();
    LocalDate startSubscriptionDate = LocalDate.now();
    long fee = (long) 300;
    Investor investor = investorService.getInvestor(id);
    Trader trader = traderRepository.getOneById(traderId);
    Long investorId = investor.getId();
    InvestorWallet investorWallet = investor.getInvestorWallet();

    Optional<Subscription> allByTraderAndAndInvestorWallet = subscriptionRepository
        .getAllByTraderAndInvestorWallet(trader, investorWallet);
    if (allByTraderAndAndInvestorWallet.isPresent()) {
      throw new AppException("Subscription already exist!!!");
    }

    subscription.setTrader(trader);
    subscription.setStopLoss(stopLoss);
    subscription.setDays(days);
    subscription.setCoins(coins);
    subscription.setStartDate(startSubscriptionDate);
    subscription.setInvestorWallet(investorWallet);

    investorService.SendCoinsToTrader(investorId, traderId, coins, fee);

    subscriptionRepository.save(subscription);
  }

  public AppResponse getSubscriptions(long id) {
    Investor investor = investorService.getInvestor(id);
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
