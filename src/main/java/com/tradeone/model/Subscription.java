package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "subscription")
@Data
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
  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;
  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;
  @ManyToOne
  @JoinColumn(name = "investor_wallet_id")
  private InvestorWallet investorWallet;

}
