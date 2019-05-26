package com.tradeone.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tradeone.model.Trader;
import com.tradeone.model.TradingHistory;
import java.time.LocalDate;

public class TradingHistoryDtoOut {

  @JsonProperty(value = "id")
  private long id;

  @JsonProperty(value = "firstName")
  private String firstName;
  @JsonProperty(value = "lastName")
  private String lastName;
  @JsonProperty(value = "date")
  private LocalDate transactionDate;
  @JsonProperty(value = "pair")
  private String pair;
  @JsonProperty(value = "price")
  private double price;
  @JsonProperty(value = "rialto")
  private String rialto;
  @JsonProperty(value = "type")
  private String type;

  public TradingHistoryDtoOut() {
  }

  public TradingHistoryDtoOut(TradingHistory tradingHistory) {
    this.id = tradingHistory.getId();
    Trader trader = tradingHistory.getTrader();
    this.firstName = trader.getFirstName();
    this.lastName = trader.getLastName();
    this.transactionDate = tradingHistory.getTransactionDate();
    this.pair = tradingHistory.getPair();
    this.price = tradingHistory.getPrice();
    this.rialto = tradingHistory.getRialto();
    this.type = tradingHistory.getType();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public LocalDate getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(LocalDate transactionDate) {
    this.transactionDate = transactionDate;
  }

  public String getPair() {
    return pair;
  }

  public void setPair(String pair) {
    this.pair = pair;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getRialto() {
    return rialto;
  }

  public void setRialto(String rialto) {
    this.rialto = rialto;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
