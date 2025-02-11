package com.cathaybk.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cathaybk.demo.model.CoinDeskResponse;
import com.cathaybk.demo.model.CoinDeskInfo.CurrencyInfo;

@Service
public class CoinDeskService {

    private final RestTemplate restTemplate = new RestTemplate();
    
    public CoinDeskResponse getCoinDeskData() {
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        return restTemplate.getForObject(url, CoinDeskResponse.class);
    }
    
    public String getCoinDeskJson() {
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
    
    // 時間格式化
    public String formatTime(String updated) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss z", Locale.ENGLISH);
            Date date = inputFormat.parse(updated);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return outputFormat.format(date);
        } catch (Exception e) {
        	e.printStackTrace();
            return "Invalid Time";
        }
    }
    
    // 創建幣別資訊，這裡根據代碼返回中文名稱
    public List<CurrencyInfo> createCurrencyInfo(Map<String, CoinDeskResponse.Bpi> bpi) {
        return bpi.values().stream()
                .map(b -> new CurrencyInfo(b.getCode(), getCurrencyName(b.getCode()), b.getRate()))
                .collect(Collectors.toList());
    }
    
    // 根據貨幣代碼返回中文名稱
    private String getCurrencyName(String code) {
        switch (code) {
            case "USD":
                return "美金";
            case "GBP":
                return "英鎊";
            case "EUR":
                return "歐元";
            default:
                return "未知貨幣";
        }
    }
    
}

