package com.tradeone.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.File;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

@Entity
@Table(name = "investor_walletBITT")
public class WalletBITT {

  private final static File file = new File(
      "path/to/your/file/");
  private final static String password = "lusenko16";
  private final static String addressBITT = "your wallet address";

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "investor_address")
  private String address;
  @Column(name = "trx_hash")
  private String hash;
  @Column(name = "bitt")
  private long bitt;
  @ManyToOne
  @JoinColumn(name = "investor_id")
  private Investor investor;

  public String walletAddress() throws Exception {
    Credentials credentials = WalletUtils.loadCredentials(password, file);
    return credentials.getAddress();
  }

  public String getAddressBITT(){
    return addressBITT;
  }

  public Investor getInvestor() {
    return investor;
  }

  public void setInvestor(Investor investor) {
    this.investor = investor;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public long getBitt() {
    return bitt;
  }

  public void setBitt(long bitt) {
    this.bitt = bitt;
  }
}
