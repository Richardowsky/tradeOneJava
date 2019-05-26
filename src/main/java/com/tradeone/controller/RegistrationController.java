package com.tradeone.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.tradeone.domain.InvestorDto;
import com.tradeone.service.InvestorService;
import info.blockchain.api.APIException;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class RegistrationController {

  @Autowired
  private InvestorService investorService;

  @Bean
  public String apiStart() {
    investorService.apiStart();
    return "redirect:/";
  }

  @RequestMapping(value = "registration", method = GET)
  public String testOne(HttpServletResponse response) {
    return "redirect:/cabinet.html";
  }


  @RequestMapping(value = "registration", method = RequestMethod.POST)
  public String createInvestor(@RequestBody InvestorDto investorDto)
      throws APIException, IOException {
    investorService.addNewInvestor(investorDto);
    return "forward:/index.html";
  }


  @GetMapping("/activate/{code}")
  public String activate(Model model, @PathVariable String code) {
    boolean isActivated = investorService.activateUser(code);

    if (isActivated) {
      model.addAttribute("messageType", "success");
      model.addAttribute("message", "User successfully activated");
    } else {
      model.addAttribute("messageType", "danger");
      model.addAttribute("message", "Activation code is not found!");
    }

    return "/";
  }
}





