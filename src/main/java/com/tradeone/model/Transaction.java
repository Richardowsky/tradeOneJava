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
import lombok.Data;

@Entity
@Table(name = "transaction")
@Data
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




}
