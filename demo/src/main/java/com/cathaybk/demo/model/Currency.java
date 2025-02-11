package com.cathaybk.demo.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "currencydb")
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CODE")
    private String code;  // 幣別代碼
    
	@Column(name = "NAME")
    private String name;  // 幣別中文名稱
    
	@Column(name = "RATE")
    private String rate;  // 匯率
    
	@Column(name = "SYMBOL")
    private String symbol;  // 圖示
    
	@Column(name = "DESCRIPTION")
    private String description;  // 說明
    
	@Column(name = "RATEFLOAT")
    private String rateFloat;  // 匯率浮點
	
	@Column(name = "CREATEDATE")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createDate;  // 建立時間
    
    @Column(name = "UPDATEDATE")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updateDate;  // 更新時間

    @PrePersist
    protected void onCreate() {
        if (this.createDate == null) {
            this.createDate = LocalDateTime.now();
        }
        if (this.updateDate == null) {
            this.updateDate = LocalDateTime.now();
        }
    }
    
    public Currency() {
    }

	public Currency(Long id, String code, String name, String rate, String symbol, String description, String rateFloat,
			LocalDateTime createDate, LocalDateTime updateDate) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.rate = rate;
		this.symbol = symbol;
		this.description = description;
		this.rateFloat = rateFloat;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
   
    

}
