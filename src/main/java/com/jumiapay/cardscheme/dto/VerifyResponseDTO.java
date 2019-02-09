package com.jumiapay.cardscheme.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olatunde
 */
@Getter
@Setter
public class VerifyResponseDTO {

    private boolean success;

    private PayloadDTO payload;

}
