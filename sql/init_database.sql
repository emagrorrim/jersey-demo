DROP DATABASE IF EXISTS SHOP_DATABASE;
CREATE DATABASE SHOP_DATABASE;

USE SHOP_DATABASE;

DROP TABLE IF EXISTS  ITEMS;
CREATE TABLE  ITEMS (
    id int not null primary key AUTO_INCREMENT,
    name varchar(100) not null,
    price int not null,
    category_id int not null,
    available int not null
);
DROP TABLE IF EXISTS  CATEGORIES;
CREATE TABLE  CATEGORIES (
    id int not null primary key AUTO_INCREMENT,
    name varchar(100) not null
);

INSERT INTO CATEGORIES(name) VALUES("cosmetics");
INSERT INTO CATEGORIES(name) VALUES("bag");
INSERT INTO ITEMS(name, price, available, category_id) VALUES("TF-lipstick", 380, 0, 1);
INSERT INTO ITEMS(name, price, available, category_id) VALUES("Givenchy-lipstick", 320, 0, 1);
INSERT INTO ITEMS(name, price, available, category_id) VALUES("Dior-lipstick", 300, 0, 1);
INSERT INTO ITEMS(name, price, available, category_id) VALUES("LV", 10000, 0, 2);
INSERT INTO ITEMS(name, price, available, category_id) VALUES("Chanel", 15000, 0, 2);
INSERT INTO ITEMS(name, price, available, category_id) VALUES("YSL", 5000, 0, 2);

SELECT * FROM SHOP_DATABASE.ITEMS;
SELECT * FROM SHOP_DATABASE.CATEGORIES;