package com.cathaybk.demo.model;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CurrencyRepositoryTest {
	
	@Autowired
	private CurrencyRepository currencyRepository;


}
