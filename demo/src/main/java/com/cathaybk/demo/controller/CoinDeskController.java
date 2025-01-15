package com.cathaybk.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cathaybk.demo.model.CoinDeskInfo;
import com.cathaybk.demo.model.CoinDeskInfo.CurrencyInfo;
import com.cathaybk.demo.model.CoinDeskResponse;
import com.cathaybk.demo.service.CoinDeskService;

@RestController
public class CoinDeskController {

    @Autowired
    private CoinDeskService coinDeskService;

    @GetMapping("/api/coinDeskNew")
    public CoinDeskInfo getCoinDeskInfo() {
        // 獲取 CoinDesk API 資料
        CoinDeskResponse response = coinDeskService.getCoinDeskData();
        
        // 格式化時間
        String formattedTime = coinDeskService.formatTime(response.getTime().getUpdated());

        // 創建新的幣別資訊
        List<CurrencyInfo> currencyInfoList = coinDeskService.createCurrencyInfo(response.getBpi());

        // 回傳新 API 資料
        return new CoinDeskInfo(formattedTime, currencyInfoList);
    }


    @GetMapping("/api/coinDeskJson")
    public String getCoinDeskJson() {
        // 獲取 CoinDesk API 資料
        String response = coinDeskService.getCoinDeskJson();

        return response;
    }
    
}
