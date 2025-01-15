資料庫初始化SQL:
1.建立table
CREATE TABLE currencydb (
    id BIGINT  PRIMARY KEY,
    code VARCHAR(255),
	symbol VARCHAR(255),
	rate VARCHAR(255),
    description VARCHAR(255),
	rateFloat FLOAT,
    name VARCHAR(255),
	createDate TIMESTAMP,
    updateDate TIMESTAMP
);

2.手動新增測試資料
INSERT INTO currencydb (id, code, symbol, rate, description, rateFloat, name, createDate, updateDate)
VALUES 
    ('1', 'USD', '&#36;', '1.00', 'US Dollar', 1.00, '美金', '2025-01-13 10:00:00', '2025-01-13 10:02:00'),
	('2', 'EUR', '&euro;', '0.92', 'Euro', 0.92, '歐元', '2025-01-14 12:00:00', '2025-01-13 12:02:00'),
    ('3', 'GBP', '&pound;', '0.82', 'British Pound Sterling', 0.82, '英鎊', '2025-01-14 13:00:00', '2025-01-13 13:02:00');
	
	

H2資料庫:
http://localhost:8080/h2-console



1.幣別資料表 CRUD 等維護功能的 API

查詢全部資料:
GET http://localhost:8080/currencies/list

查詢ID:
GET http://localhost:8080/currencies/data/1 (1是ID可以更換)

新增資料:
POST http://localhost:8080/currencies/create
(資料輸入用JSON)
{
    "code": "USD",
	"name": "美金一",
	"symbol": "&#36;",
	"rate": "96,167.555",
	"description": "United States Dollar",
	"rateFloat": 96167.5549
}

修改資料:
GET http://localhost:8080/currencies/update/1 (1是ID可以更換)
(資料輸入用JSON)
{
    "code": "USD2",
	"name": "美金二",
	"symbol": "&#36;",
	"rate": "95,637.56",
	"description": "United States Dollar2",
	"rateFloat": 95637.555
}

2.
呼叫 coindesk 的 API
GET http://localhost:8080/api/coinDeskJson

3.
呼叫 coindesk 的 API,並進行資料轉換,組成新 API
GET http://localhost:8080/api/coinDeskNew