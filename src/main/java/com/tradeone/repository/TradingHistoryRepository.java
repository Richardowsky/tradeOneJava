package com.tradeone.repository;

import com.tradeone.model.TradingHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradingHistoryRepository extends JpaRepository<TradingHistory, Long> {

  List<TradingHistory> getByTraderId(long id);

}
