package com.tradeone.domain;

import com.tradeone.model.Subscription;
import java.util.Set;
import lombok.Data;

@Data
public class InvestorWalletDto {

  private String address;
  private long freeCoins;
  private long onTradeCoins;
  private Set<Subscription> subscriptions;
}
