package com.jumiapay.cardscheme.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olatunde
 */
@Entity
@Table(name = "card_details")
@Getter
@Setter
public class CardDetailsEntity implements Serializable {

    private static final long serialVersionUID = 7401548380514451401L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_no")
    private String cardNumber;

    @Column(name = "scheme")
    private String cardScheme;

    @Column(name = "card_type")
    private String cardType;
    
    @Column(name = "bank")
    private String bank;

    @Column(name = "request_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
}
