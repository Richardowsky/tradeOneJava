package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import com.tradeone.photo.Picture;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "trader")
@Data
public class Trader {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "nick_name", nullable = false)
  private String nickName;
  @Column(name = "first_name", nullable = false)
  private String firstName;
  @Column(name = "last_name", nullable = false)
  private String lastName;
  @Column(name = "password", nullable = false)
  private String password;
  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "country", nullable = false)
  private String country;
  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;
  @Column(name = "registration_date", nullable = false)
  private LocalDate registrationDate;
  @OneToOne
  @JoinColumn(name = "trader_wallet_id")
  private TraderWallet traderWallet;

  private String filename;
  @ElementCollection(targetClass = Risk.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "trader_risk", joinColumns = @JoinColumn(name = "trader_id"))
  @Enumerated(EnumType.STRING)
  private Set<Risk> risks;
  @Column(name = "trader_profit")
  private int profit;
  @Column(name = "investors_quantity")
  private int investorsQuantity;
  @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Rialto> rialtos;


}
