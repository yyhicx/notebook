create database if not exists dbforspring;

use dbforspring;

create table if not exists customers (
  customer_id INT NOT NULL AUTO_INCREMENT,
  customer_name VARCHAR(50) NOT NULL,
  PRIMARY KEY (customer_id)
);

insert into customers (customer_name) values ('c01');
