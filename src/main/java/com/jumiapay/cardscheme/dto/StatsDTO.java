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
public class StatsDTO {

    private boolean success;
    private int start;
    private int limit;
    private long size;
    private Map<String, String> payload = new HashMap();
}
