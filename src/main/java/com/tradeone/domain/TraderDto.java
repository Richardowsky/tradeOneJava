package com.tradeone.domain;

import com.tradeone.photo.Picture;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TraderDto {

  private String nickName;
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
