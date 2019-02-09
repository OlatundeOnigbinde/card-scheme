package com.jumiapay.cardscheme.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olatunde
 */
@Getter
@Setter
public class PayloadDTO {
    private String scheme;
    private String type;
    private String bank;

    public PayloadDTO() {
    }
    
    public PayloadDTO(String scheme, String type, String bank) {
        this.scheme = scheme;
        this.type = type;
        this.bank = bank;
    }
        
}
