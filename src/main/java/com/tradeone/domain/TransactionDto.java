package com.tradeone.domain;

import com.tradeone.model.Transaction;
import com.tradeone.model.TransactionRole;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

@Data
public class TransactionDto {

  private long coins;
  private LocalDate transactionDate;
  private Set<TransactionRole> transactionRoles;

  public TransactionDto() {
  }

  public TransactionDto(Transaction transaction) {
    this.transactionDate = transaction.getTransactionDate();
    this.transactionRoles = transaction.getTransactionRoles();
    this.coins = transaction.getCoins();


  }
}



