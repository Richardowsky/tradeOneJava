package com.tradeone.repository;

import com.tradeone.model.Investor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {

  Optional<Investor> getById(Long userId);

  Optional<Investor> getAllByNickName(String login);
}
