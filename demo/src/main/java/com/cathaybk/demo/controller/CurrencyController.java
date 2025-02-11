package com.cathaybk.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cathaybk.demo.model.Currency;
import com.cathaybk.demo.model.CurrencyRepository;
import com.cathaybk.demo.service.CurrencyService;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/currencies/list", method = RequestMethod.GET)
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }
    
    @RequestMapping(value = "/currencies/data/{id}", method = RequestMethod.GET)
    public Optional<Currency> getCurrenciesById(@PathVariable Long id) {
        return currencyService.getCurrenciesById(id);
    }

    @RequestMapping(value = "/currencies/create", method = RequestMethod.POST)
    public Currency createCurrency(@RequestBody Currency currency) {
    	
        return currencyService.createCurrency(currency);
    }

    @RequestMapping(value = "/currencies/update/{id}", method = RequestMethod.GET)
    public Currency updateCurrency(@PathVariable Long id, @RequestBody Currency currency) throws Exception {

    	Optional<Currency> OptionalCurrency = currencyService.getCurrenciesById(id);
    	
    	Currency existingCurrency = null;
    	
    	if (OptionalCurrency.isPresent()) {
    	
    	existingCurrency = OptionalCurrency.get();
    	
    	existingCurrency.setCode(currency.getCode());
    	existingCurrency.setName(currency.getName());
    	existingCurrency.setRate(currency.getRate());
    	existingCurrency.setSymbol(currency.getSymbol());
    	existingCurrency.setDescription(currency.getDescription());
    	existingCurrency.setRateFloat(currency.getRateFloat());
    	existingCurrency.setUpdateDate(LocalDateTime.now());
    	
    	currencyService.createCurrency(existingCurrency);
    	
    	} else {
    	    throw new Exception("Currency not found with id: " + id);
    	}
    	
    	return existingCurrency;
    }

    @RequestMapping(value = "/currencies/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) throws Exception {
        
    	Optional<Currency> OptionalCurrency = currencyService.getCurrenciesById(id);
    	
    	if (OptionalCurrency.isPresent()) {
    	
    		currencyService.deleteCurrencyById(id);
    		
    		return ResponseEntity.noContent().build();
    		
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    	
    }
}