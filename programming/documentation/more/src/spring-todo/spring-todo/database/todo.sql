create database if not exists dbforspring;

use dbforspring;

create table if not exists todos (
  id INT NOT NULL AUTO_INCREMENT,
  description VARCHAR(100) NOT NULL,
  completed BOOLEAN NOT NULL DEFAULT false,
  PRIMARY KEY (id)
);

insert into todos (description, completed)
values
  ('Learn Vue 3', false),
  ('Learn Vite', true),
  ('Learn Element Plus', true),
  ('Learn JavaWeb', true),
  ('Learn JavaSSM', true),
  ('Learn HTML', false),
  ('Learn CSS', true),
  ('Learn JavaScript', true),
  ('Learn C', false),
  ('Learn C++', false),
  ('Learn C#', false),
  ('Learn Python', true),
  ('Learn Ruby', false),
  ('Learn Go', true),
  ('Learn Dart', true);
