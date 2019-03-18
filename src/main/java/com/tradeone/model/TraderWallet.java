package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "trader_wallet")
@Data
public class TraderWallet {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "address")
  private String address;

}
