package com.tradeone.model;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rialto")

public class Rialto {


  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "tradingBalance", nullable = false)
  private long balance ;
  @Column(name = "profit", nullable = false)
  private double profit;
  @Column(name = "ordersByDay", nullable = false)
  private int ordersByDay;
  @ManyToOne
  @JoinColumn(name = "trader_id")
  private Trader trader;



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getBalance() {
    return balance;
  }

  public void setBalance(long balance) {
    this.balance = balance;
  }

  public double getProfit() {
    return profit;
  }

  public void setProfit(int profit) {
    this.profit = profit;
  }

  public int getOrdersByDay() {
    return ordersByDay;
  }

  public void setOrdersByDay(int ordersByDay) {
    this.ordersByDay = ordersByDay;
  }
}

