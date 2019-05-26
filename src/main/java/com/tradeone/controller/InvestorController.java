package com.tradeone.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.tradeone.domain.AppResponse;
import com.tradeone.domain.BinanceApi;
import com.tradeone.domain.SubscriptionDto;
import com.tradeone.domain.WalletBITTDto;
import com.tradeone.domain.WithdrawDto;
import com.tradeone.model.Investor;
import com.tradeone.service.InvestorService;
import com.tradeone.service.SubscriptionService;
import info.blockchain.api.APIException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investor")
public class InvestorController {

  @Autowired
  private InvestorService investorService;
  @Autowired
  private SubscriptionService subscriptionService;

  @RequestMapping(value = "/connectApi", method = POST)
  public void connectApi(@RequestBody BinanceApi binanceApi,
      @AuthenticationPrincipal Investor investor) {
    investorService.setApiBinance(investor, binanceApi);
  }


  @RequestMapping(value = "/apiBinance", method = GET)
  public AppResponse getApiInfo(@AuthenticationPrincipal Investor investor) {
    return investorService.getApiInfo(investor);
  }

  @RequestMapping(value = "/balance", method = GET)
  public AppResponse getBalance(@AuthenticationPrincipal Investor investor
  ) throws APIException, IOException {
    return investorService.getInvestorWalletInfo(investor);
  }

  @RequestMapping(value = "/balanceBITT", method = GET)
  public AppResponse getBalanceBITT(@AuthenticationPrincipal Investor investor) {
    return investorService.getInvestorWalletBITT(investor);
  }

  @RequestMapping(value = "/getTransactionBITT", method = POST)
  public AppResponse getBalanceBITT(@RequestBody WalletBITTDto walletBITTDto,
      @AuthenticationPrincipal Investor investor) {
    return investorService.getBalanceBITT(walletBITTDto, investor);
  }

  @RequestMapping(value = "/cred", method = GET)
  public AppResponse getCredentials(@AuthenticationPrincipal Investor investor) {
    return investorService.getCredentials(investor);
  }

  @RequestMapping(value = "/address", method = GET)
  public AppResponse getInvestorWallet(@AuthenticationPrincipal Investor investor
  ) throws APIException, IOException {
    return investorService.getInvestorWalletAddress(investor);
  }

  @RequestMapping(value = "/addressBITT", method = GET)
  public AppResponse getBITTAddress() throws Exception {
    return investorService.getWalletBITTAddress();
  }

  @RequestMapping(value = "/traders", method = GET)
  public AppResponse getAllTraders() {
    return investorService.getTraders();
  }

  @RequestMapping(value = "/history", method = GET)
  public AppResponse getHistory(@AuthenticationPrincipal Investor investor) {
    return investorService.getTradingHistory(investor);
  }

  @RequestMapping(value = "/subscriptions", method = GET)
  public AppResponse getAllSubscriptions(
      @AuthenticationPrincipal Investor investor) {
    return subscriptionService.getSubscriptions(investor);
  }


  @RequestMapping(value = "/subscription", method = POST)
  public String subscriptionOnTrader(@RequestBody SubscriptionDto subscriptionDto,
      @AuthenticationPrincipal Investor investor) throws APIException, IOException {
    subscriptionService.addNewSubscription(subscriptionDto, investor);

    return "all ok";
  }

  @RequestMapping(value = "/withdraw", method = POST)
  public void withdrawCoins(@RequestBody WithdrawDto withdrawDto,
      @AuthenticationPrincipal Investor investor) throws APIException, IOException {
    String address = withdrawDto.getAddress();
    long coins = withdrawDto.getCoins();
    investorService.withdrawCoins(address, coins, investor);
  }

  @RequestMapping(value = "/deleteSubscription/{id}", method = POST)
  public void deleteSubscription(@PathVariable long id,
      @AuthenticationPrincipal Investor investor) {
    subscriptionService.deleteSubscription(id, investor);
  }

  @RequestMapping(value = "/cabinet", method = GET)
  public String cabinet() {
    return "cabinet.html";
  }
}






