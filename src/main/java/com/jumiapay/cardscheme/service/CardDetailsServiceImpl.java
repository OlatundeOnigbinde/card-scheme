/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumiapay.cardscheme.service;

import com.jumiapay.cardscheme.dto.Payload;
import com.jumiapay.cardscheme.dto.StatsDTO;
import com.jumiapay.cardscheme.dto.VerifyResponseDTO;
import com.jumiapay.cardscheme.model.CardDetailsEntity;
import com.jumiapay.cardscheme.model.RequestStatistics;
import com.jumiapay.cardscheme.repositories.RequestsStatsRepository;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olatunde
 */
@Service
public class CardDetailsServiceImpl implements CardDetailsService {

    private static final Logger logger = Logger.getLogger(CardDetailsServiceImpl.class.getName());

    @Autowired
    RequestsStatsRepository requestsRepository;

    @Autowired
    VerifyCardService verifyCardService;

    @Override
    public VerifyResponseDTO verifyCard(String cardNo) {
        VerifyResponseDTO response;
        CardDetailsEntity cardDetails = requestsRepository.findTop1ByCardNumber(cardNo);
        if (cardDetails != null) {
            CardDetailsEntity newEntry = new CardDetailsEntity();
            newEntry.setCardNumber(cardDetails.getCardNumber());
            newEntry.setRequestDate(new Date());
            newEntry.setBank(cardDetails.getBank());
            newEntry.setCardScheme(cardDetails.getCardScheme());
            newEntry.setCardType(cardDetails.getCardType());
            requestsRepository.save(newEntry);

            response = new VerifyResponseDTO();
            response.setSuccess(true);
            Payload p = new Payload(cardDetails.getCardScheme(), cardDetails.getCardType(), cardDetails.getBank());
            response.setPayload(p);
            return response;
        }
        response = verifyCardService.fetchCardDetails(cardNo);
        if (response.isSuccess()) {
            CardDetailsEntity firstEntry = new CardDetailsEntity();
            firstEntry.setCardNumber(cardNo);
            firstEntry.setRequestDate(new Date());
            firstEntry.setBank(response.getPayload().getBank());
            firstEntry.setCardScheme(response.getPayload().getScheme());
            firstEntry.setCardType(response.getPayload().getType());
            requestsRepository.save(firstEntry);
            return response;
        }
        return response;
    }

    @Override
    public StatsDTO findRequestCount(int start, int limit) {
//        return requestsRepository.findRequestCount(pageable);
        StatsDTO response = new StatsDTO();
        Page<RequestStatistics> items = requestsRepository.findRequestCount(PageRequest.of(start, limit));
        if (items.isEmpty()) {
            response.setSuccess(false);
            return response;//notFound().build();
        }
        logger.log(Level.INFO, "total elements: {0}", items.getTotalElements());
        logger.log(Level.INFO, "total pages: {0}", items.getTotalPages());
        response.setSuccess(true);
        response.setStart(start);
        response.setLimit(limit);
        response.setSize(items.getTotalElements());
        items.getContent().stream().forEach((x) -> {
            logger.log(Level.INFO, "card number: {0}", x.getCardNumber());
            logger.log(Level.INFO, "count: {0}", x.getCount());
            response.getPayload().put(x.getCardNumber(), "" + x.getCount());
        });
        return response;
    }
}
