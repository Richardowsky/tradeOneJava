package com.tradeone.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tradeone.model.Subscription;
import com.tradeone.model.Trader;
import java.time.LocalDate;

public class SubscriptionDtoOut {

  @JsonProperty(value = "trader")
  private Trader trader;
  @JsonProperty(value = "stopLoss")
  private int stopLoss;
  @JsonProperty(value = "days")
  private int days;
  @JsonProperty(value = "coins")
  private long coins;
  @JsonProperty(value = "profit")
  private double profit;
  @JsonProperty(value = "endDate")
  private LocalDate endDate;
  @JsonProperty(value = "type")
  private String type;

  public Trader getTrader() {
    return trader;
  }

  public void setTrader(Trader trader) {
    this.trader = trader;
  }

  public int getStopLoss() {
    return stopLoss;
  }

  public void setStopLoss(int stopLoss) {
    this.stopLoss = stopLoss;
  }

  public int getDays() {
    return days;
  }

  public double getProfit() {
    return profit;
  }

  public void setProfit(double profit) {
    this.profit = profit;
  }

  public void setDays(int days) {
    this.days = days;
  }

  public long getCoins() {
    return coins;
  }

  public void setCoins(long coins) {
    this.coins = coins;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public SubscriptionDtoOut(Subscription subscription) {
    this.trader = subscription.getTrader();
    this.stopLoss = subscription.getStopLoss();
    this.days = subscription.getDays();
    this.coins = subscription.getCoins();
    this.profit = subscription.getProfit();
    this.endDate = subscription.getEndDate();
    this.type = subscription.getType();
  }
}
