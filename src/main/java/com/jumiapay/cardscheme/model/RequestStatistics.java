package com.jumiapay.cardscheme.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olatunde
 */
@Getter
@Setter
public class RequestStatistics {

    private String cardNumber;
    private Long count;

    public RequestStatistics(String cardNumber, Long count) {
        this.cardNumber = cardNumber;
        this.count = count;
    }
}
