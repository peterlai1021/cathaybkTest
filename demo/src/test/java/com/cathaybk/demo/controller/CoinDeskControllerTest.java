package com.cathaybk.demo.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cathaybk.demo.model.CoinDeskResponse;
import com.cathaybk.demo.model.CoinDeskResponse.Bpi;
import com.cathaybk.demo.model.CoinDeskResponse.CoinDeskTime;
import com.cathaybk.demo.service.CoinDeskService;

@SpringBootTest
@AutoConfigureMockMvc
class CoinDeskControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Mock
    private CoinDeskService coinDeskService;

    @InjectMocks
    private CoinDeskController coinDeskController;

	@Test
	void testGetCoinDeskInfo() throws Exception {
		
		
        Bpi usdBpi = new Bpi("USD", "&#36;", "97895.687", "United States Dollar", (float)97895.687);
        
        Map<String, Bpi> bpiMap = new HashMap<>();
        bpiMap.put("USD", usdBpi);
        
        CoinDeskResponse coinDeskResponse = new CoinDeskResponse();
        coinDeskResponse.setTime(new CoinDeskTime("2025-01-13T10:00:00Z"));
        coinDeskResponse.setBpi(bpiMap);
        
		when(coinDeskService.getCoinDeskData()).thenReturn(coinDeskResponse);

        mockMvc.perform(get("/api/coinDeskNew"))
            .andExpect(status().isOk());
		
	}

	@Test
	void testGetCoinDeskJson() throws Exception {
		
		mockMvc.perform(get("/api/coinDeskJson"))
        .andExpect(status().isOk());
		
	}

}
