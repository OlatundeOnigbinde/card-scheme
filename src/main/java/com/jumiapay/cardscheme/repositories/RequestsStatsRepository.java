package com.jumiapay.cardscheme.repositories;

import com.jumiapay.cardscheme.model.RequestStatistics;
import com.jumiapay.cardscheme.model.CardDetailsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Olatunde
 */
@Repository
public interface RequestsStatsRepository extends JpaRepository<CardDetailsEntity, Long> {

    @Query("SELECT "
            + "    new com.jumiapay.cardscheme.model.RequestStatistics(v.cardNumber, COUNT(v)) "
            + "FROM "
            + "    CardDetailsEntity v "
            + "GROUP BY "
            + "    v.cardNumber "
            + "ORDER BY "
            + "    COUNT(v) DESC"
    )
    Page<RequestStatistics> findRequestCount(Pageable pageable);

    CardDetailsEntity findTop1ByCardNumber(String cardNumber);
}
