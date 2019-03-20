package com.tradeone.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.tradeone.domain.AppResponse;
import com.tradeone.domain.SubscriptionDto;
import com.tradeone.service.InvestorService;
import com.tradeone.service.SubscriptionService;
import info.blockchain.api.APIException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investor")
public class InvestorController {

  @Autowired
  private InvestorService investorService;
  @Autowired
  private SubscriptionService subscriptionService;


  @RequestMapping(value = "/balance/{id}", method = GET)
  public AppResponse getBalance(@PathVariable long id
  ) throws APIException, IOException {
    return investorService.getInvestorWalletInfo(id);
  }


  @RequestMapping(value = "/address/{id}", method = GET)
  public AppResponse getInvestorWallet(@PathVariable long id
  ) throws APIException, IOException {
    return investorService.getInvestorWalletAddress(id);
  }

  @RequestMapping(value = "/traders", method = GET)
  public AppResponse getAllTraders() {
    return investorService.getTraders();
  }

  @RequestMapping(value = "/subscriptions/{id}", method = GET)
  public AppResponse getAllSubscriptions(
      @PathVariable long id) {
    return subscriptionService.getSubscriptions(id);
  }

  //  @RequestMapping(value = "/send", method = POST)
//  public void sendToTrader(
//      @RequestParam long investorId,
//      @RequestParam long traderId,
//      @RequestParam long amount,
//      @RequestParam long fee
//      ) throws APIException, IOException {
//    investorService.SendCoinsToTrader(investorId, traderId,amount,fee);
//  }


  @RequestMapping(value = "/subscription/{id}", method = POST)
  public String subscriptionOnTrader(@RequestBody SubscriptionDto subscriptionDto,
      @PathVariable long id) throws APIException, IOException {
    subscriptionService.addNewSubscription(subscriptionDto, id);

    return "all ok";
  }

//  @RequestMapping(value = "/transactions/{id}", method = GET)
//  public AppResponse getTransactions(@PathVariable long id) {
//    return investorService.getTransactions(id);
//  }

  @RequestMapping(value = "/cabinet", method = GET)
  public String cabinet() {
    return "cabinet.html";
  }
}






