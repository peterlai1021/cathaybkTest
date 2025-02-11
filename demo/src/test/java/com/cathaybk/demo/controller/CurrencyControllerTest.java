package com.cathaybk.demo.controller;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cathaybk.demo.model.Currency;
import com.cathaybk.demo.service.CurrencyService;


@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private CurrencyService currencyService;
	

	@Test
	public void testGetCurrenciesById() throws Exception {
	    
	    Long id = 1L;
	    Currency currency = new Currency();
	    currency.setId(id);

	    when(currencyService.getCurrenciesById(id)).thenReturn(Optional.of(currency));

	    mockMvc.perform(get("/currencies/data/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
	    
	    verify(currencyService, times(1)).getCurrenciesById(id);
	}

	@Test
	public void testCreateCurrency() throws Exception {
		
	    Currency currency = new Currency();
	    currency.setCode("USD");
	    currency.setRate("96167.555");
	    currency.setDescription("United States Dollar");
		
	    when(currencyService.createCurrency(currency)).thenReturn(currency);

        mockMvc.perform(post("/currencies/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"code\": \"USD\", \"rate\": \"96167.555\", \"description\": \"United States Dollar\"}"))
                .andExpect(status().isOk());
		
	}

	@Test
	void testUpdateCurrency() throws Exception {
		
		Long id = 1L;
		
		Currency currency = new Currency();
		currency.setId(id);
	    currency.setCode("USD");
	    currency.setRate("96167.555");
	    currency.setDescription("United States Dollar");
	    
	    when(currencyService.getCurrenciesById(id)).thenReturn(Optional.of(currency));
		
	    when(currencyService.createCurrency(currency)).thenReturn(currency);

        mockMvc.perform(get("/currencies/update/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"code\": \"USD\", \"rate\": \"96167.555\", \"description\": \"United States Dollar\"}"))
                .andExpect(status().isOk());
        
        verify(currencyService, times(1)).createCurrency(currency);
        
	}

	@Test
	void testDeleteCurrencySuccess() throws Exception {
		
		Long id = 1L;
		
		Currency currency = new Currency();
		currency.setId(id);
	    currency.setCode("USD");
	    currency.setRate("96167.555");
	    currency.setDescription("United States Dollar");
		
	    when(currencyService.getCurrenciesById(id)).thenReturn(Optional.of(currency));
		
        mockMvc.perform(get("/currencies/delete/{id}", id))
                .andExpect(status().isNoContent());
        
        verify(currencyService, times(1)).deleteCurrencyById(id);
		
	}
	
	@Test
	void testDeleteCurrencyNotFound() throws Exception {
		
		Long id = 1L;
		
		Currency currency = new Currency();
		currency.setId(id);
	    currency.setCode("USD");
	    currency.setRate("96167.555");
	    currency.setDescription("United States Dollar");
		
	    when(currencyService.getCurrenciesById(id)).thenReturn(Optional.empty());
		
        mockMvc.perform(get("/currencies/delete/{id}", id))
                .andExpect(status().isNotFound());
        
        verify(currencyService, never()).deleteCurrencyById(id);
		
	}
	

}
