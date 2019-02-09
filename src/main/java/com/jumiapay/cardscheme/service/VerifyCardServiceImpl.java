/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumiapay.cardscheme.service;

import com.jumiapay.cardscheme.dto.CardDetailsDTO;
import com.jumiapay.cardscheme.dto.Payload;
import com.jumiapay.cardscheme.dto.VerifyResponseDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Olatunde
 */
@Service
public class VerifyCardServiceImpl implements VerifyCardService {

    @org.springframework.beans.factory.annotation.Value("${card.verifier.path}")
    private String cardVerifierPath;

    private static final Logger logger = Logger.getLogger(VerifyCardService.class.getName());
    private final RestTemplate restTemplate;

    public VerifyCardServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public VerifyResponseDTO fetchCardDetails(String cardNumber) {
        VerifyResponseDTO response = new VerifyResponseDTO();
        try {
            CardDetailsDTO obj = this.restTemplate.getForObject(cardVerifierPath + cardNumber, CardDetailsDTO.class);
            if (obj != null) {
                response.setSuccess(true);
                String bankName = (obj.getBank() != null && obj.getBank().containsKey("name")) ? obj.getBank().get("name") : "";
                Payload p = new Payload(obj.getScheme(), obj.getType(), bankName);
                response.setPayload(p);
                return response;
            }
        } catch (org.springframework.web.client.HttpClientErrorException e) {
            logger.log(Level.SEVERE, "Error occurred {0}", e.getMessage());
        }

        response.setSuccess(false);
        return response;
    }
}
