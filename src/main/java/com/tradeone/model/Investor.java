package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.Collection;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "investor")

public class Investor implements UserDetails {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @NotBlank(message = "Username cannot be empty")
  @Column(name = "nick_name", nullable = false)
  private String username;
  @NotBlank(message = "Firstname cannot be empty")
  @Column(name = "first_name", nullable = false)
  private String firstName;
  @NotBlank(message = "Lastname cannot be empty")
  @Column(name = "last_name", nullable = false)
  private String lastName;
  @NotBlank(message = "Password cannot be empty")
  @Column(name = "password", nullable = false)
  private String password;
  @Email(message = "Email is not correct")
  @NotBlank(message = "Email cannot be empty")
  @Column(name = "email")
  private String email;
  private String activationCode;
  @Column(name = "country")
  private String country;
  @Column(name = "birth_date")
  private LocalDate birthDate;
  @Column(name = "registration_date")
  private LocalDate registrationDate;
  @OneToOne
  @JoinColumn(name = "investor_wallet_id")
  private InvestorWallet investorWallet;
  private boolean active;
  @Column(name = "api_key")
  private String apiKey;
  @Column(name = "secret_key")
  private String secretKey;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;

  @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<WalletBITT> walletBITTs;

  public Set<WalletBITT> getWalletBITTs() {
    return walletBITTs;
  }

  public void setWalletBITTs(Set<WalletBITT> walletBITTs) {
    this.walletBITTs = walletBITTs;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
  }

  public String getPassword() {
    return password;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getActivationCode() {
    return activationCode;
  }

  public void setActivationCode(String activationCode) {
    this.activationCode = activationCode;
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

  public InvestorWallet getInvestorWallet() {
    return investorWallet;
  }

  public void setInvestorWallet(InvestorWallet investorWallet) {
    this.investorWallet = investorWallet;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Set<Role> getRoles() {
    return roles;
  }
}
