package com.cathaybk.demo.model;

import java.util.List;

public class CoinDeskInfo {
	
	
    private String updatedTime;
    private List<CurrencyInfo> currencies;

    public CoinDeskInfo(String updatedTime, List<CurrencyInfo> currencies) {
        this.updatedTime = updatedTime;
        this.currencies = currencies;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public List<CurrencyInfo> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyInfo> currencies) {
        this.currencies = currencies;
    }

    // 幣別資訊類
    public static class CurrencyInfo {
        private String code;
        private String name;
        private String rate;

        public CurrencyInfo(String code, String name, String rate) {
            this.code = code;
            this.name = name;
            this.rate = rate;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }
    }
}