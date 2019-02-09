package com.jumiapay.cardscheme.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olatunde
 */
@Getter
@Setter
public class CardDetailsDTO {

    private String scheme;
    private String type;
    private Map<String, String> bank = new HashMap();
}
