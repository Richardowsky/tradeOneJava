package com.tradeone.domain;

import lombok.Data;

@Data
public class InvestorDto {
  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private String email;
  private String filename;
  private int year;
  private int month;
  private int day;
//  private LocalDate birthDate;
  private String country;

}
