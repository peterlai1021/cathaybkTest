package com.cathaybk.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cathaybk.demo.model.Currency;
import com.cathaybk.demo.model.CurrencyRepository;

@Service
public class CurrencyService {

	@Autowired
    private CurrencyRepository currencyRepository;
	
	public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
	
	public Optional<Currency> getCurrenciesById(Long id) {
        return currencyRepository.findById(id);
    }
	
	public Currency createCurrency(Currency currency) {
		
		String htmlString = currency.getSymbol();
    	String convertedString = StringEscapeUtils.unescapeHtml4(htmlString);
    	
    	currency.setSymbol(convertedString);
		
        return currencyRepository.save(currency);
    }
	
	
	public void deleteCurrencyById(Long id) {
        if (!currencyRepository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        currencyRepository.deleteById(id);
    }
	
}
