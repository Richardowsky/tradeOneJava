package com.tradeone.domain;

import com.tradeone.model.Transaction;
import com.tradeone.model.TransactionRole;
import java.time.LocalDate;
import java.util.Set;

public class TransactionDto {

  private long coins;
  private LocalDate transactionDate;
  private Set<TransactionRole> transactionRoles;

  public TransactionDto() {
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

  public TransactionDto(Transaction transaction) {
    this.transactionDate = transaction.getTransactionDate();
    this.transactionRoles = transaction.getTransactionRoles();
    this.coins = transaction.getCoins();


  }
}



