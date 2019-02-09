package com.jumiapay.cardscheme;

import com.jumiapay.cardscheme.model.CardDetailsEntity;
import com.jumiapay.cardscheme.repositories.RequestsStatsRepository;
import com.jumiapay.cardscheme.service.CardDetailsService;
import com.jumiapay.cardscheme.service.CardDetailsServiceImpl;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
public class CardSchemeApplicationTests {

//    @Autowired
//    private TestEntityManager entityManager;

//    @Autowired
//    RequestsStatsRepository requestsRepository;

//    @Autowired
//    private CardDetailsService cardDetailsService;
//
//    @MockBean
//    private RequestsStatsRepository requestsStatsRepository;

    @Test
    public void contextLoads() {
    }

//    @Test
//    public void whenFindByCardNumber_thenReturnCardDetails() {
//        // given
//        CardDetailsEntity cardScheme = new CardDetailsEntity();
//        cardScheme.setBank("GUARANTY TRUST BANK");
//        cardScheme.setCardNumber("539983");
//        cardScheme.setCardScheme("mastercard");
//        cardScheme.setCardType("debit");
//        cardScheme.setRequestDate(new Date());
//        entityManager.persist(cardScheme);
//        entityManager.flush();
////
////        // when
//        CardDetailsEntity found = requestsRepository.findTop1ByCardNumber("539983");
//
//        // then
//        assertThat(found.getCardNumber())
//                .isEqualTo("539983");
//    }

//    @TestConfiguration
//    static class CardDetailsServiceImplTestContextConfiguration {
//
//        @Bean
//        public CardDetailsService cardDetailsService() {
//            return new CardDetailsServiceImpl();
//        }
//    }
//
//    @Before
//    public void setUp() {
//        CardDetailsEntity cardScheme = new CardDetailsEntity();
//        cardScheme.setBank("GUARANTY TRUST BANK");
//        cardScheme.setCardNumber("539983");
//        cardScheme.setCardScheme("mastercard");
//        cardScheme.setCardType("debit");
//        cardScheme.setRequestDate(new Date());
//
//        Mockito.when(requestsRepository.findTop1ByCardNumber(cardScheme.getCardNumber()))
//                .thenReturn(cardScheme);
//    }

}
