package com.microseservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    // Transform method in SQL Query
    CurrencyExchange findByFromAndTo(String from, String to);

}
