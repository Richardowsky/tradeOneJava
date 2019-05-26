package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subscription")

public class Subscription {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "trader_id")
  private Trader trader;
  @Column(name = "stop_loss", nullable = false)
  private int stopLoss;
  @Column(name = "days_for_trading")
  private int days;
  @Column(name = "coins")
  private long coins;
  @Column(name = "fee")
  private long fee;
  @Column(name = "profit")
  private double profit;
  @Column(name = "bitt_fee")
  private double bittFee;
  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;
  @Column(name = "end_date")
  private LocalDate endDate;
  @ManyToOne
  @JoinColumn(name = "investor_wallet_id")
  private InvestorWallet investorWallet;
  @Column(name = "type")
  private String type;

  public double getBittFee() {
    return bittFee;
  }

  public void setBittFee(double bittFee) {
    this.bittFee = bittFee;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public void setDays(int days) {
    this.days = days;
  }

  public long getCoins() {
    return coins;
  }

  public void setCoins(long coins) {
    this.coins = coins;
  }

  public long getFee() {
    return fee;
  }

  public void setFee(long fee) {
    this.fee = fee;
  }

  public double getProfit() {
    return profit;
  }

  public void setProfit(double profit) {
    this.profit = profit;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public InvestorWallet getInvestorWallet() {
    return investorWallet;
  }

  public void setInvestorWallet(InvestorWallet investorWallet) {
    this.investorWallet = investorWallet;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
