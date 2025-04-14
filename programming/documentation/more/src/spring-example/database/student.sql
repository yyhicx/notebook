create database if not exists dbforspring;

use dbforspring;

create table if not exists students (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  gender VARCHAR(10) NOT NULL,
  age INT,
  class VARCHAR(50),
  PRIMARY KEY (id)
);

insert into students (name, gender, age, class)
values
  ('张三', '男', 15, '高中一班'),
  ('李四', '男', 16, '高中二班'),
  ('王五', '女', 17, '高中一班'),
  ('赵六', '女', 18, '高中三班'),
  ('刘七', '男', 16, '高中二班'),
  ('陈八', '女', 14, '高中一班'),
  ('杨九', '男', 18, '高中三班'),
  ('吴十', '男', 16, '高中二班');
