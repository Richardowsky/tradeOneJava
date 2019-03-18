package com.tradeone.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.tradeone.model.Rialto;
import com.tradeone.model.Risk;
import com.tradeone.model.Trader;
import java.util.Set;
import lombok.Data;

@Data
public class TraderDtoOut {

  @JsonProperty(value = "firstname")
  private String firstName;
  @JsonProperty(value = "lastname")
  private String lastName;
  @JsonProperty(value = "risk")
  private Set<Risk> risks;
  @JsonProperty(value = "profit")
  private int profit;
  @JsonProperty(value = "investors")
  private int investorsQuantity;
  @JsonProperty(value = "rialtos")
  private Set<Rialto> rialtos;


  public TraderDtoOut() {
  }

  public TraderDtoOut(Trader trader) {
    this.firstName = trader.getFirstName();
    this.lastName = trader.getLastName();
    this.risks = trader.getRisks();
    this.profit = trader.getProfit();
    this.investorsQuantity = trader.getInvestorsQuantity();
    this.rialtos = trader.getRialtos();

  }
}

