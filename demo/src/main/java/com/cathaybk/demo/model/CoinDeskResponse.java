package com.cathaybk.demo.model;

import java.util.Map;

public class CoinDeskResponse {
	
    private CoinDeskTime time;
    private Map<String, Bpi> bpi;
    
    public CoinDeskResponse() {
    }

    public CoinDeskResponse(CoinDeskTime time, Map<String, Bpi> bpi) {
        this.time = time;
        this.bpi = bpi;
    }

    public CoinDeskTime getTime() {
        return time;
    }

    public void setTime(CoinDeskTime time) {
        this.time = time;
    }

    public Map<String, Bpi> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, Bpi> bpi) {
        this.bpi = bpi;
    }

    // CoinDeskTime 類別 (內部類別，處理時間)
    public static class CoinDeskTime {
        private String updated;
        
        public CoinDeskTime() {
        }
        
        public CoinDeskTime(String updated) {
            this.updated = updated;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }

    // Bpi 類別
    public static class Bpi {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        private float rateFloat;
        
        public Bpi() {
        }

        public Bpi(String code, String symbol, String rate, String description, float rateFloat) {
            this.code = code;
            this.symbol = symbol;
            this.rate = rate;
            this.description = description;
            this.rateFloat = rateFloat;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public float getRateFloat() {
            return rateFloat;
        }

        public void setRateFloat(float rateFloat) {
            this.rateFloat = rateFloat;
        }
    }
}

