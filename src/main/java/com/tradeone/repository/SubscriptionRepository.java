package com.tradeone.repository;

import com.tradeone.model.InvestorWallet;
import com.tradeone.model.Subscription;
import com.tradeone.model.Trader;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  List<Subscription> findByInvestorWallet(InvestorWallet investorWallet);

  List<Subscription> findByTrader(Trader trader);


  Optional<Subscription> getAllByTraderAndInvestorWallet(Trader trader, InvestorWallet investorWallet);
}
