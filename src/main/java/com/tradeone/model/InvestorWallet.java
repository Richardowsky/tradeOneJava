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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "investor_wallet")

public class InvestorWallet {

  private final static String URL = "http://127.0.0.1:3000/";
  private final static String API = "some api key from blockchain";
  private final static String ID = "some id from from blockchain";
  private final static String PASS = "some password from blockchain";


  private static Wallet wallet = new Wallet(URL, API, ID, PASS);

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "address")
  private String address;
  @Column(name = "free_coins")
  private long freeCoins;
  @Column(name = "bitt_coins")
  private double freeBITT;

  @OneToMany(mappedBy = "investorWallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Subscription> subscriptions;
  @OneToMany(mappedBy = "investorWallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Transaction> transactions;

  public double getFreeBITT() {
    return freeBITT;
  }

  public void setFreeBITT(double freeBITT) {
    this.freeBITT = freeBITT;
  }

  public static String getURL() {
    return URL;
  }

  public static String getAPI() {
    return API;
  }

  public static String getID() {
    return ID;
  }

  public static String getPASS() {
    return PASS;
  }

  public static void setWallet(Wallet wallet) {
    InvestorWallet.wallet = wallet;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public long getFreeCoins() {
    return freeCoins;
  }

  public void setFreeCoins(long freeCoins) {
    this.freeCoins = freeCoins;
  }

  public Set<Subscription> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(Set<Subscription> subscriptions) {
    this.subscriptions = subscriptions;
  }

  public Set<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(Set<Transaction> transactions) {
    this.transactions = transactions;
  }

  public Wallet getWallet() {
    return wallet;
  }

}
