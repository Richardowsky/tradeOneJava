package com.tradeone.domain;

public class SubscriptionDto {

  private long traderId;
  private int stopLoss;
  private int days;
  private long coins;
  private  long fee;
  private double bittFee;
  private String type;

  public double getBittFee() {
    return bittFee;
  }

  public void setBittFee(double bittFee) {
    this.bittFee = bittFee;
  }

  public long getTraderId() {
    return traderId;
  }

  public void setTraderId(long traderId) {
    this.traderId = traderId;
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

  public void setDays(int days) {
    this.days = days;
  }

  public long getFee() {
    return fee;
  }

  public void setFee(long fee) {
    this.fee = fee;
  }

  public long getCoins() {
    return coins;
  }

  public void setCoins(long coins) {
    this.coins = coins;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
