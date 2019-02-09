/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumiapay.cardscheme;

import com.jumiapay.cardscheme.controller.CardSchemeController;
import com.jumiapay.cardscheme.dto.PayloadDTO;
import com.jumiapay.cardscheme.dto.StatsDTO;
import com.jumiapay.cardscheme.dto.VerifyResponseDTO;
import com.jumiapay.cardscheme.service.CardDetailsService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Olatunde
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CardSchemeController.class)
public class CardSchemeRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CardDetailsService cardDetailsService;

    @Test
    public void givenCardStats_whenCardStatsApi_thenReturnJson()
            throws Exception {

        StatsDTO statsDTO = new StatsDTO();

        statsDTO.setSuccess(true);
        statsDTO.setStart(0);
        statsDTO.setLimit(3);
        statsDTO.setSize(3);
        statsDTO.getPayload().put("514585", "1");
        statsDTO.getPayload().put("539983", "5");
        statsDTO.getPayload().put("555555", "2");

        given(cardDetailsService.findRequestCount(0, 3)).willReturn(statsDTO);
        mvc.perform(get("/card-scheme/stats?start=1&limit=3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", Matchers.is(3)))
                .andExpect(jsonPath("$.success", Matchers.is(true)));
    }

    @Test
    public void givenCardNo_whenVerifyApi_thenReturnJson()
            throws Exception {

        VerifyResponseDTO verifyDTO = new VerifyResponseDTO();

        verifyDTO.setSuccess(true);
        PayloadDTO p = new PayloadDTO("mastercard", "debit", "GUARANTY TRUST BANK");
        verifyDTO.setPayload(p);

        given(cardDetailsService.verifyCard("539983")).willReturn(verifyDTO);
        mvc.perform(get("/card-scheme/verify/{card-no}", "539983")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload.type", Matchers.is("debit")))
                .andExpect(jsonPath("$.success", Matchers.is(true)));
    }
}
