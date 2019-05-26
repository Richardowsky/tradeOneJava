package com.tradeone.repository;

import com.tradeone.model.Investor;
import com.tradeone.model.InvestorWallet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {

  Optional<Investor> getById(Long userId);

  Optional<Investor> getAllByUsername(String username);

  Investor findByActivationCode(String code);

  Optional<Investor> getAllByEmail(String email);

  Investor getOneById(long id);

  Investor getByInvestorWallet(InvestorWallet investorWallet);
}
