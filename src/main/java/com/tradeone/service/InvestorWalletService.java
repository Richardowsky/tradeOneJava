package com.tradeone.service;


import com.tradeone.repository.InvestorRepository;
import com.tradeone.repository.InvestorWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestorWalletService {

  @Autowired
  private InvestorWalletRepository investorWalletRepository;
  @Autowired
  private InvestorRepository investorRepository;



}
