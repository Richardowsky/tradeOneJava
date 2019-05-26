package com.tradeone;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
@EnableAsync
public class TradeoneApplication {

  @RequestMapping(value = {"/", "/about", "/info"}, method = GET)
  String home() {
    return "index";
  }

  public static void main(String[] args) {
    SpringApplication.run(TradeoneApplication.class, args);

  }
}


