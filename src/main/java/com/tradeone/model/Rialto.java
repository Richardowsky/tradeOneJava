package com.tradeone.model;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "rialto")
@Data
public class Rialto {


  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "tradingBalance", nullable = false)
  private double balance ;
  @Column(name = "profit", nullable = false)
  private int profit;
  @Column(name = "ordersByDay", nullable = false)
  private int ordersByDay;



}

