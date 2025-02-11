package com.cathaybk.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cathaybk.demo.model.Currency;
import com.cathaybk.demo.model.CurrencyRepository;

@SpringBootTest
@Transactional
class CurrencyServiceTest {
	
	@Mock
    private CurrencyRepository currencyRepository;
	
	@InjectMocks
    private CurrencyService currencyService;
	
	private Currency currency;
	
	private Long existingCurrencyId = 1L;
    private Long nonExistingCurrencyId = 999L;
	
	@BeforeEach
    void setUp() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		currency = new Currency(1L, "USD","美金","1.00","$","US Dollar","1.0",LocalDateTime.parse("2025-01-13 10:00:00", formatter),LocalDateTime.parse("2025-01-13 10:02:00", formatter));
    }

	@Test
	void testGetCurrenciesById() {
		
		
		Long id = 1L;

        when(currencyRepository.findById(id)).thenReturn(Optional.of(currency));

        Optional<Currency> optionalCurrency = currencyRepository.findById(id);

        Currency currencyData = optionalCurrency.get();
        
        assertNotNull(currencyData);
        assertEquals("USD", currencyData.getCode());
        assertEquals("US Dollar", currencyData.getDescription());
        
        System.out.println("currencyData.getCode(): " + currencyData.getCode());
        System.out.println("currencyData.getDescription(): " + currencyData.getDescription());
		
        
        verify(currencyRepository, times(1)).findById(id);
		
	}

	@Test
	void testCreateCurrency() {
		
		when(currencyRepository.save(currency)).thenReturn(currency);
		
		Currency savedCurrency = currencyService.createCurrency(currency);
		
		assertEquals("$", savedCurrency.getSymbol(), "Symbol should be correctly unescaped.");
		
		assertNotNull(savedCurrency);
        assertEquals("USD", savedCurrency.getCode());
        assertEquals("US Dollar", savedCurrency.getDescription());
        
        System.out.println("savedCurrency.getCode(): " + savedCurrency.getCode());
        System.out.println("savedCurrency.getDescription(): " + savedCurrency.getDescription());
		
    	verify(currencyRepository, times(1)).save(currency);
		
	}

	@Test
    void testDeleteCurrencyByIdWhenCurrencyExists() {
       
		when(currencyRepository.existsById(existingCurrencyId)).thenReturn(true);

        currencyService.deleteCurrencyById(existingCurrencyId);

        verify(currencyRepository, times(1)).deleteById(existingCurrencyId);
    }
	
	@Test
    void testDeleteCurrencyByIdWhenCurrencyDoesNotExist() {

		when(currencyRepository.existsById(nonExistingCurrencyId)).thenReturn(false);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            currencyService.deleteCurrencyById(nonExistingCurrencyId);
        });

        assertEquals("User with id " + nonExistingCurrencyId + " not found", thrown.getMessage());

        verify(currencyRepository, never()).deleteById(nonExistingCurrencyId);
    }

}
