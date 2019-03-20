package com.tradeone.controller;

import com.tradeone.domain.InvestorDto;
import com.tradeone.domain.TraderDto;
import com.tradeone.model.Investor;
import com.tradeone.service.InvestorService;
import com.tradeone.service.TraderService;
import info.blockchain.api.APIException;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("registration")
public class RegistrationController {

  @Value("${upload.path}")
  private String uploadPath;

  @Autowired
  private InvestorService investorService;
  @Autowired
  private TraderService traderService;


  @RequestMapping(value = "/investor", method = RequestMethod.POST)
  public void createInvestor(@RequestBody InvestorDto investorDto)
      throws APIException, IOException {
//    saveFile(investorDto,file);
    investorService.addNewInvestor(investorDto);
  }

  @RequestMapping(value = "/trader", method = RequestMethod.POST)
  public void createTrader(@RequestBody TraderDto traderDto)
      throws IOException {
//    saveTraderFile(traderDto, file);
    traderService.addNewTrader(traderDto);
  }

//  private void saveFile(InvestorDto investorDto, @RequestParam("file") MultipartFile file) throws IOException {
//    if (file != null && !file.getOriginalFilename().isEmpty()) {
//      File uploadDir = new File(uploadPath);
//
//      if (!uploadDir.exists()) {
//        uploadDir.mkdir();
//      }
//
//      String uuidFile = UUID.randomUUID().toString();
//      String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//      file.transferTo(new File(uploadPath + "/" + resultFilename));
//
//      investorDto.setFilename(resultFilename);
//    }
//  }
//  private void saveTraderFile(TraderDto traderDto, @RequestParam("file") MultipartFile file) throws IOException {
//    if (file != null && !file.getOriginalFilename().isEmpty()) {
//      File uploadDir = new File(uploadPath);
//
//      if (!uploadDir.exists()) {
//        uploadDir.mkdir();
//      }
//
//      String uuidFile = UUID.randomUUID().toString();
//      String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//      file.transferTo(new File(uploadPath + "/" + resultFilename));
//
//      traderDto.setFilename(resultFilename);
//    }
  }


