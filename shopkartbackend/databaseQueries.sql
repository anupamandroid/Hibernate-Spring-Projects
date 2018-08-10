SELECT * FROM shopkart.category;
SELECT * FROM shopkart.user_detail;
SELECT * FROM shopkart.product;

DESC shopkart.category;
DESC shopkart.user_detail;
DESC shopkart.product;

USE shopkart;

CREATE TABLE Category (

		id int(10) NOT NULL AUTO_INCREMENT,
		name VARCHAR(50),
		description VARCHAR(255),
		image_url VARCHAR(50),
		is_active BOOLEAN,
		
		CONSTRAINT pk_category_id PRIMARY KEY (id)

);


CREATE TABLE user_detail (
	id int(10),
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number VARCHAR(15),	
	CONSTRAINT pk_user_id PRIMARY KEY(id)
);


CREATE TABLE product (
	id int(10) NOT NULL auto_increment,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id)
);

ALTER TABLE product MODIFY COLUMN ID INT;
ALTER TABLE product DROP PRIMARY KEY;
ALTER TABLE product ADD PRIMARY KEY (id);

ALTER TABLE product MODIFY COLUMN ID INT AUTO_INCREMENT;
ALTER TABLE category MODIFY COLUMN ID INT AUTO_INCREMENT;
ALTER TABLE user_detail MODIFY COLUMN ID INT AUTO_INCREMENT;



INSERT INTO category (name, description,image_url,is_active) VALUES ('Laptop', 'This is description for Laptop category!', 'CAT_1.png', true);
INSERT INTO category (id, name, description,image_url,is_active) VALUES (1, 'Television', 'This is description for Television category!', 'CAT_2.png', true);
INSERT INTO category (id, name, description,image_url,is_active) VALUES (2,'Mobile', 'This is description for Mobile category!', 'CAT_3.png', true);

INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Virat', 'Kohli', 'ADMIN', true, '$2a$06$ORtBskA2g5Wg0HDgRE5ZsOQNDHUZSdpJqJ2.PGXv0mKyEvLnKP7SW', 'vk@gmail.com', '8888888888');
INSERT INTO user_detail 
(id, first_name, last_name, role, enabled, password, email, contact_number) 
VALUES (1, 'Ravindra', 'Jadeja', 'SUPPLIER', true, '$2a$06$bzYMivkRjSxTK2LPD8W4te6jjJa795OwJR1Of5n95myFsu3hgUnm6', 'rj@gmail.com', '9999999999');
INSERT INTO user_detail 
(id, first_name, last_name, role, enabled, password, email, contact_number) 
VALUES (2, 'Ravichandra', 'Ashwin', 'SUPPLIER', true, '$2a$06$i1dLNlXj2uY.UBIb9kUcAOxCigGHUZRKBtpRlmNtL5xtgD6bcVNOK', 'ra@gmail.com', '7777777777');
INSERT INTO user_detail 
(id, first_name, last_name, role, enabled, password, email, contact_number) 
VALUES (3, 'Khozema', 'Nullwala', 'USER', true, '$2a$06$4mvvyO0h7vnUiKV57IW3oudNEaKPpH1xVSdbie1k6Ni2jfjwwminq', 'kn@gmail.com', '7777777777');


INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (1, 'PRDABC123DEFX', 'iPhone 5s', 'apple', 'This is one of the best phone available in the market right now!', 18000, 5, true, 3, 2, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (2, 'PRDDEF123DEFX', 'Samsung s7', 'samsung', 'A smart phone by samsung!', 32000, 2, true, 3, 3, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (3, 'PRDPQR123WGTX', 'Google Pixel', 'google', 'This is one of the best android smart phone available in the market right now!', 57000, 5, true, 3, 2, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (4, 'PRDMNO123PQRX', ' Macbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 54000, 3, true, 1, 2, 0, 0 );
INSERT INTO product (id, code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES (5, 'PRDABCXYZDEFX', 'Dell Latitude E6510', 'dell', 'This is one of the best laptop series from dell that can be used!', 48000, 5, true, 1, 3, 0, 0 );