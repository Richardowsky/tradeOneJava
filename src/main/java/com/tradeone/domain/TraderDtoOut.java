package com.tradeone.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.tradeone.model.Rialto;
import com.tradeone.model.Trader;
import java.util.Set;


public class TraderDtoOut {
  @JsonProperty(value = "id")
  private long id;
  @JsonProperty(value = "firstname")
  private String firstName;
  @JsonProperty(value = "lastname")
  private String lastName;
  @JsonProperty(value = "risk")
  private String risks;
  @JsonProperty(value = "profit")
  private double profit;
  @JsonProperty(value = "available")
  private double available;
  @JsonProperty(value = "investors")
  private int investorsQuantity;
  @JsonProperty(value = "rialtos")
  private Set<Rialto> rialtos;
  @JsonProperty(value = "minDays")
  private int minDays;


  public TraderDtoOut() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public double getProfit() {
    return profit;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRisks() {
    return risks;
  }

  public void setRisks(String risks) {
    this.risks = risks;
  }

  public int getMinDays() {
    return minDays;
  }

  public void setMinDays(int minDays) {
    this.minDays = minDays;
  }

  public void setProfit(int profit) {
    this.profit = profit;
  }

  public int getInvestorsQuantity() {
    return investorsQuantity;
  }

  public void setInvestorsQuantity(int investorsQuantity) {
    this.investorsQuantity = investorsQuantity;
  }

  public Set<Rialto> getRialtos() {
    return rialtos;
  }

  public void setRialtos(Set<Rialto> rialtos) {
    this.rialtos = rialtos;
  }

  public void setProfit(double profit) {
    this.profit = profit;
  }

  public double getAvailable() {
    return available;
  }

  public void setAvailable(double available) {
    this.available = available;
  }

  public TraderDtoOut(Trader trader) {
    this.id = trader.getId();
    this.firstName = trader.getFirstName();
    this.lastName = trader.getLastName();
    this.risks = trader.getRisks();
    this.profit = trader.getProfit();
    this.investorsQuantity = trader.getInvestorsQuantity();
    this.rialtos = trader.getRialtos();
    this.minDays = trader.getMinDays();
    this.available = trader.getAvailable();

  }
}

