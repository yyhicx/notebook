create database if not exists dbforspring;

use dbforspring;

create table if not exists orders (
  order_id INT NOT NULL AUTO_INCREMENT,
  order_name VARCHAR(50) NOT NULL,
  customer_id INT NOT NULL,
  PRIMARY KEY (order_id)
);

insert into orders (order_name, customer_id)
values
  ('o01', 1),
  ('o02', 1),
  ('o03', 1);
