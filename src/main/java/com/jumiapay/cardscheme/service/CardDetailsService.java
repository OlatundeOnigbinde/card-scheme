/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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