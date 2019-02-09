package com.jumiapay.cardscheme.service;

import com.jumiapay.cardscheme.dto.StatsDTO;
import com.jumiapay.cardscheme.dto.VerifyResponseDTO;

/**
 *
 * @author Olatunde
 */

public interface CardDetailsService {

    
    public VerifyResponseDTO verifyCard(String cardNo);
    
    public StatsDTO findRequestCount(int start, int limit);
}