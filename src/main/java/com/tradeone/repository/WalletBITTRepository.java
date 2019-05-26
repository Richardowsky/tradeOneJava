package com.tradeone.repository;

import com.tradeone.model.WalletBITT;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletBITTRepository extends JpaRepository<WalletBITT, Long> {


  Optional<WalletBITT> getAllByHash(String hash);
}
