package com.cathaybk.demo.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.cathaybk.demo.model.CoinDeskResponse;

@SpringBootTest
class CoinDeskServiceTest {
	
	@Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CoinDeskService coinDeskService;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        // 初始化 MockRestServiceServer
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

	@Test
    void testGetCoinDeskData() {

		String jsonResponse = "{"
                + "\"time\": {\"updated\": \"Jan 13, 2025 10:00:00 UTC\"},"
                + "\"bpi\": {"
                + "\"USD\": {\"code\": \"USD\", \"symbol\": \"&#36;\", \"rate\": \"97895.687\", \"description\": \"United States Dollar\", \"rate_float\": 97895.687}"
                + "}}";

        mockServer.expect(requestTo("https://api.coindesk.com/v1/bpi/currentprice.json"))
                  .andRespond(withSuccess(jsonResponse, org.springframework.http.MediaType.APPLICATION_JSON));

        CoinDeskResponse response = coinDeskService.getCoinDeskData();

        //判斷時間格式是否為yyyy/MM/dd HH:mm:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formattedTime = coinDeskService.formatTime(response.getTime().getUpdated());
        LocalDateTime date = LocalDateTime.parse(formattedTime, formatter);
        
        assertNotNull(response);
        System.out.println("時間: " + response.getTime().getUpdated());
        assertNotNull(date);
        assertNotNull(response.getBpi());
        System.out.println("Code: " + response.getBpi().get("USD").getCode());
        System.out.println("Rate: " + response.getBpi().get("USD").getRate());
    }

	@Test
	void testGetCoinDeskJson() {
		
		String jsonResponse = "{"
                + "\"time\": {\"updated\": \"Jan 13, 2025 10:00:00 UTC\"},"
                + "\"bpi\": {"
                + "\"USD\": {\"code\": \"USD\", \"symbol\": \"&#36;\", \"rate\": \"97895.687\", \"description\": \"United States Dollar\", \"rate_float\": 97895.687}"
                + "}}";

        mockServer.expect(requestTo("https://api.coindesk.com/v1/bpi/currentprice.json"))
                  .andRespond(withSuccess(jsonResponse, org.springframework.http.MediaType.APPLICATION_JSON));
		
        String response = coinDeskService.getCoinDeskJson();
		
        System.out.println("coindeskAPI資料: " + response);
        
        
	}

}
