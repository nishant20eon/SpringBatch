CREATE TABLE IF NOT EXISTS Products (
	productId INT PRIMARY KEY,
	title VARCHAR(200),
	description VARCHAR(255),
	price VARCHAR(10),
	discount VARCHAR(2),
	discounted_price VARCHAR(10)
);