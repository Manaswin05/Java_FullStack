CREATE DATABASE ebooks;
USE ebooks;

CREATE TABLE workshop (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    book_title VARCHAR(100),	
    book_author VARCHAR(100),
    book_price DOUBLE,
    quantity int
);

-- Adding generic test data
INSERT INTO workshop (book_title, book_author, book_price, quantity) VALUES ('Jungle Book','Rudyard Kipling',540.0,3);
INSERT INTO workshop (book_title, book_author, book_price, quantity) VALUES ('2 States','Chetan Bhagat',120.0,5);
INSERT INTO workshop (book_title, book_author, book_price, quantity) VALUES ('Black Myth Wukong','Chinese Unreal',360.0,4);
INSERT INTO workshop (book_title, book_author, book_price, quantity) VALUES ("Gulliver's Travels",'Robert Woodsworst',120.0,4);
select * from workshop;