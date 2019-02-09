package com.jumiapay.cardscheme.service;

import com.jumiapay.cardscheme.dto.VerifyResponseDTO;

/**
 *
 * @author Olatunde
 */

public interface VerifyCardService {

       public VerifyResponseDTO fetchCardDetails(String cardNumber);
}
