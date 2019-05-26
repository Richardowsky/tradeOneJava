package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
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

@Entity
@Table(name = "trader")

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
  @Column(name = "trader_risk")
  private String risks;
  @Column(name = "trader_profit")
  private double profit;
  @Column(name = "available")
  private double available;
  @Column(name = "trader_minimal_days")
  private int minDays;
  @Column(name = "investors_quantity")
  private int investorsQuantity;
  @OneToMany(mappedBy = "trader", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Rialto> rialtos;

  @Column(name = "api_key")
  private String apiKey;
  @Column(name = "secret_key")
  private String secretKey;

  public double getAvailable() {
    return available;
  }

  public void setAvailable(double available) {
    this.available = available;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getRisks() {
    return risks;
  }

  public void setRisks(String risks) {
    this.risks = risks;
  }

  public int getMinDays() {
    return minDays;
  }

  public void setMinDays(int minDays) {
    this.minDays = minDays;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }

  public TraderWallet getTraderWallet() {
    return traderWallet;
  }

  public void setTraderWallet(TraderWallet traderWallet) {
    this.traderWallet = traderWallet;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public double getProfit() {
    return profit;
  }

  public void setProfit(double profit) {
    this.profit = profit;
  }

  public int getInvestorsQuantity() {
    return investorsQuantity;
  }

  public void setInvestorsQuantity(int investorsQuantity) {
    this.investorsQuantity = investorsQuantity;
  }

  public Set<Rialto> getRialtos() {
    return rialtos;
  }

  public void setRialtos(Set<Rialto> rialtos) {
    this.rialtos = rialtos;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }
}
