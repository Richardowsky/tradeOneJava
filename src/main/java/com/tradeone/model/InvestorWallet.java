package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import info.blockchain.api.wallet.Wallet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "investor_wallet")
@Data
public class InvestorWallet {

  private final static String URL = "http://localhost:3000/";
  private final static String API = "551c03a1-1597-4262-9e0b-0a855aaf6e65";
  private final static String ID = "86b32748-b8a9-4339-9bbe-324dbd5687a4";
  private final static String PASS = "4164654312dfsfsgda";


  private static Wallet wallet = new Wallet(URL, API, ID, PASS);

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "address")
  private String address;
  @Column(name = "free_coins")
  private long freeCoins;
  @Column(name = "onTrade_coins")
  private long onTradeCoins;
  @OneToMany(mappedBy = "investorWallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Subscription> subscriptions;
  @OneToMany(mappedBy = "investorWallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Transaction> transactions;



  public Wallet getWallet() {
    return wallet;
  }

}
