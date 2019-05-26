package com.tradeone.domain;

import com.tradeone.model.Subscription;
import java.util.Set;


public class InvestorWalletDto {

  private String address;
  private long freeCoins;
  private long onTradeCoins;
  private Set<Subscription> subscriptions;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public long getFreeCoins() {
    return freeCoins;
  }

  public void setFreeCoins(long freeCoins) {
    this.freeCoins = freeCoins;
  }

  public long getOnTradeCoins() {
    return onTradeCoins;
  }

  public void setOnTradeCoins(long onTradeCoins) {
    this.onTradeCoins = onTradeCoins;
  }

  public Set<Subscription> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(Set<Subscription> subscriptions) {
    this.subscriptions = subscriptions;
  }
}
