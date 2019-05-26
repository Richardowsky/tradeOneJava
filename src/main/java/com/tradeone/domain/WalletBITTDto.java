package com.tradeone.domain;

public class WalletBITTDto {

  private String responseHash;
  private String responseAddress;
  private long responseBitt;

  public String getResponseHash() {
    return responseHash;
  }

  public void setResponseHash(String responseHash) {
    this.responseHash = responseHash;
  }

  public String getResponseAddress() {
    return responseAddress;
  }

  public void setResponseAddress(String responseAddress) {
    this.responseAddress = responseAddress;
  }

  public long getResponseBitt() {
    return responseBitt;
  }

  public void setResponseBitt(long responseBitt) {
    this.responseBitt = responseBitt;
  }
}
