package com.jumiapay.cardscheme.controller;

import com.jumiapay.cardscheme.dto.StatsDTO;
import com.jumiapay.cardscheme.dto.VerifyResponseDTO;
import com.jumiapay.cardscheme.service.CardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
        StatsDTO response = cardDetailsService.findRequestCount((start - 1), limit);
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
