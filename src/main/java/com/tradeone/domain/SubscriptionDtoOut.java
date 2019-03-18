package com.tradeone.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tradeone.model.Subscription;
import com.tradeone.model.Trader;
import lombok.Data;

@Data
public class SubscriptionDtoOut {

  @JsonProperty(value = "trader")
  private Trader trader;
  @JsonProperty(value = "stopLoss")
  private int stopLoss;
  @JsonProperty(value = "days")
  private int days;
  @JsonProperty(value = "coins")
  private long coins;

  public SubscriptionDtoOut(Subscription subscription) {
    this.trader = subscription.getTrader();
    this.stopLoss = subscription.getStopLoss();
    this.days = subscription.getDays();
    this.coins = subscription.getCoins();
  }
}
