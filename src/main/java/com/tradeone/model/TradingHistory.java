package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class TradingHistory {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "trader_id")
  private Trader trader;
  @Column(name ="date")
  private LocalDate transactionDate;
  @Column(name ="pair")
  private String pair;
  @Column(name = "price")
  private double price;
  @Column(name ="rialto")
  private String rialto;
  @Column(name ="type")
  private String type;

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
