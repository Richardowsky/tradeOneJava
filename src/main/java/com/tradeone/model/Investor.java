package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import com.tradeone.photo.Picture;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "investor")
@Data
public class Investor {

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
  @Column(name = "country")
  private String country;
  @Column(name = "birth_date")
  private LocalDate birthDate;
  @Column(name = "registration_date")
  private LocalDate registrationDate;
  @OneToOne
  @JoinColumn(name = "investor_wallet_id")
  private InvestorWallet investorWallet;

  private String filename;
}
