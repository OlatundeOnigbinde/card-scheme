/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumiapay.cardscheme.controller;

import com.jumiapay.cardscheme.dto.Payload;
import com.jumiapay.cardscheme.dto.StatsDTO;
import com.jumiapay.cardscheme.dto.VerifyResponseDTO;
import com.jumiapay.cardscheme.model.RequestStatistics;
import com.jumiapay.cardscheme.repositories.RequestsStatsRepository;
import com.jumiapay.cardscheme.service.CardDetailsService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Olatunde
 */
@RestController
@RequestMapping("/card-scheme")
public class CardSchemeController {

    private static final Logger logger = Logger.getLogger(CardSchemeController.class.getName());

//    @Value("${card.verifier.path}")
//    private String cardVerifierPath;
//    @Autowired
//    RequestsStatsRepository requestsRepository;
//    @Autowired
//    VerifyCardService verifyCardService;
    @Autowired
    CardDetailsService cardDetailsService;

    @GetMapping(path = "/verify/{card-no}")
    public ResponseEntity<VerifyResponseDTO> verifyApi(@PathVariable(name = "card-no") String cardNo) {
        VerifyResponseDTO response = cardDetailsService.verifyCard(cardNo);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping(path = "/stats")
    public ResponseEntity<StatsDTO> cardStatsApi(@RequestParam("start") int start, @RequestParam("limit") int limit) {
//        StatsDTO response = new StatsDTO();
        StatsDTO response = cardDetailsService.findRequestCount((start - 1), limit);
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);//notFound().build();
        }
//        logger.log(Level.INFO, "total elements: {0}", items.getTotalElements());
//        logger.log(Level.INFO, "total pages: {0}", items.getTotalPages());
//        response.setSuccess(true);
//        response.setStart(start);
//        response.setLimit(limit);
//        response.setSize(items.getTotalElements());
//        items.getContent().stream().forEach((x) -> {
//            logger.log(Level.INFO, "card number: {0}", x.getCardNumber());
//            logger.log(Level.INFO, "count: {0}", x.getCount());
//            response.getPayload().put(x.getCardNumber(), "" + x.getCount());
//        });
//        items.getContent().stream().map(x -> response.getPayload().put(x.getCardNumber(), "" + x.getCount()));
        return ResponseEntity.ok(response);
    }

}
