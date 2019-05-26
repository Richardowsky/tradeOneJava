package com.tradeone.repository;

import com.tradeone.model.Trader;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {

  Optional<Trader> getById(Long userId);

  Trader getOneById(Long userId);
}
