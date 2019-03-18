package com.tradeone.service;

import com.tradeone.domain.TraderDto;
import com.tradeone.exception.AppException;
import com.tradeone.model.Trader;
import com.tradeone.repository.TraderRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderService {

  @Autowired
  private TraderRepository traderRepository;


  public void addNewTrader(TraderDto traderDto) {
    Trader trader = new Trader();

    String nickname = traderDto.getNickName();
    String firstName = traderDto.getFirstName();
    String lastName = traderDto.getLastName();
    String password = traderDto.getPassword();
    String email = traderDto.getEmail();
String userPhoto = traderDto.getFilename();

    int year = traderDto.getYear();
    int month = traderDto.getMonth();
    int day = traderDto.getDay();
    LocalDate birthDate = LocalDate.of(year,month,day);
    String country = traderDto.getCountry();
    LocalDate registrationDate = LocalDate.now();

    Optional<Trader> allByLogin = traderRepository.getAllByNickName(nickname);
    if (allByLogin.isPresent()) {
      throw new AppException("Trader already exist!!!");
    }
    trader.setNickName(nickname);
    trader.setFirstName(firstName);
    trader.setLastName(lastName);
    trader.setPassword(password);
    trader.setEmail(email);
  trader.setFilename(userPhoto);
    trader.setBirthDate(birthDate);
    trader.setCountry(country);
    trader.setRegistrationDate(registrationDate);

    traderRepository.save(trader);
  }

  public Trader getTrader(Long traderId) {
    Optional<Trader> byId = traderRepository.getById(traderId);
    return byId.orElse(null);
  }
}
