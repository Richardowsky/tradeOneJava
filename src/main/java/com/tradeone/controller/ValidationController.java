package com.tradeone.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.tradeone.domain.AppResponse;
import com.tradeone.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class ValidationController {
  @Autowired
  private InvestorService investorService;

  @RequestMapping(value = "validationName", method = POST)
  public boolean validationUsename(@RequestBody AppResponse response) {
    Object username = response.get("username");
    return investorService.validationName(username.toString());
  }

  @RequestMapping(value = "validationEmail", method = POST)
  public boolean validationEmail(@RequestBody AppResponse response) {
    Object email = response.get("email");
    return investorService.validationEmail(email.toString());
  }
}
