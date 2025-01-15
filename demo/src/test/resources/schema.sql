CREATE TABLE IF NOT exists currencydb (
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