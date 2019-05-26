package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")

public class Transaction {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "serverWallet_id")
  private InvestorWallet investorWallet;
  @Column(name = "coins", nullable = false)
  private long coins;
  @Column(name ="date")
  private LocalDate transactionDate;
  @ElementCollection(targetClass = TransactionRole.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "transaction_role", joinColumns = @JoinColumn(name = "transaction_id"))
  @Enumerated(EnumType.STRING)
  private Set<TransactionRole> transactionRoles;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public InvestorWallet getInvestorWallet() {
    return investorWallet;
  }

  public void setInvestorWallet(InvestorWallet investorWallet) {
    this.investorWallet = investorWallet;
  }

  public long getCoins() {
    return coins;
  }

  public void setCoins(long coins) {
    this.coins = coins;
  }

  public LocalDate getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(LocalDate transactionDate) {
    this.transactionDate = transactionDate;
  }

  public Set<TransactionRole> getTransactionRoles() {
    return transactionRoles;
  }

  public void setTransactionRoles(Set<TransactionRole> transactionRoles) {
    this.transactionRoles = transactionRoles;
  }
}
