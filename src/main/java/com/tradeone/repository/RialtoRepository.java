package com.tradeone.repository;

import com.tradeone.model.Rialto;
import com.tradeone.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RialtoRepository extends JpaRepository<Rialto, Long> {

  Rialto getByTrader(Trader trader);
}
