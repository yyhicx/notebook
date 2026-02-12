drop database if exists `springboot_example`;

create database `springboot_example` character set utf8mb4 collate utf8mb4_unicode_ci;

use `springboot_example`;

drop table if exists `user_info`;

create table `user_info` (
  `id` int(11) not null auto_increment comment '用户ID',
  `username` varchar(50) default null comment '用户名',
  `password` varchar(100) default null comment '密码',
  `email` varchar(100) default null comment '邮箱',
  `phone` varchar(20) default null comment '手机号',
  `status` tinyint(4) default '1' comment '用户状态 1-正常 0-禁用',
  `created_time` datetime default current_timestamp comment '创建时间',
  `updated_time` datetime default current_timestamp on update current_timestamp comment '更新时间',
  `is_deleted` tinyint(1) default '0' comment '是否删除 0-未删除 1-已删除',
  primary key (`id`)
) engine = innodb default charset = utf8mb4 comment = '用户信息';

insert into `user_info` (`username`, `password`, `email`, `phone`)
values
  ('admin', '123456', 'admin@localhost', '13800138000'),
  ('user1', '654321', 'user1@localhost', '13800138001'),
  ('user2', '123456', 'user2@localhost', '13800138002');

update `user_info` set `is_deleted` = 1 where `username`='user1';
