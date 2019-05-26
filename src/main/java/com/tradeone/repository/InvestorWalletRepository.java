package com.tradeone.repository;

import com.tradeone.model.InvestorWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorWalletRepository extends JpaRepository<InvestorWallet, Long> {

  InvestorWallet getById(long id);
}
