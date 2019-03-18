package com.tradeone.domain;

import lombok.Data;

@Data
public class SubscriptionDto {

  private long traderId;
  private int stopLoss;
  private int days;
  private long coins;

}
