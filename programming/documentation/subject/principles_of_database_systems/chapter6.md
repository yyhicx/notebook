# 数据库安全与保护

1.  [数据库完整性](#数据库完整性)
2.  [触发器](#触发器)
3.  [安全性与访问控制](#安全性与访问控制)
4.  [事务与并发控制](#事务与并发控制)
5.  [备份与恢复](#备份与恢复)

## 数据库完整性

完整性约束的作用对象：

*   列级约束：对于列的类型、取值范围、精度等的约束。
*   元组约束：指元组各个字段之间的相互约束，比如开始日期小于结束日期。
*   表级约束：指若干元组、关系之间的联系的的约束。

实体完整性：

*   主键用primary key添加约束：`primary key(field_name),`。
*   候选键用unique key进行约束：`unique key key_name(field_name),`。

参照完整性：指两个表之间的关系。

*   外键用foreign key进行约束：`foreign key(field_name) references other_table_name(other_field_name) on delete restrict on update restrict,`。

用户自定义完整性：

*   非空：`id int not null auto_increment,`。
*   检查约束：`check(age>=18 and age<=60),`。

更新完整性约束：

*   `alter table table_name add constraint constraint_name primary key(field_name);`。
*   `alter table table_name add constraint constraint_name unique key(field_name);`。
*   `alter table table_name add constraint constraint_name foreign key(field_name) references other_table_name(other_field_name);`。

删除完整性约束：

*   `alter table table_name drop foreign key field_name;`。
*   `alter table table_name drop primary key;`。
*   `alter table table_name drop field_name;`。

## 触发器

触发器：保护表数据的数据库对象，当指定的表发生Insert，Update，Delete时触发，进行相关的动作。

创建触发器：`create trigger trigger_name trigger_time trigger_event on table_name for each row trigger_statement;`。

*   trigger_time: before，after。
*   trigger_event: insert，update，delete。

```sql
create trigger test_add after insert on test for each row set @x=NEW.id;
create trigger test_del after delete on test for each row set @y=OLD.id;
```

删除触发器：`drop trigger trigger_name;`。

## 安全性与访问控制

创建不同的用户，授予不同的权限，使用数据库中数据。创建、修改或删除账户时，需要拥有权限。

创建用户账户：`create user 'user_name'@'localhost' identified by 'password';`。

删除用户账户：`drop user 'user_name'@'localhost';`。

注意：不要删除root用户。

修改用户名称：`rename user 'old_user_name'@'localhost' to 'new_user_name'@'localhost';`。

修改用户密码：`set password for 'user_name'@'localhost' = 'new_password';`。

查看用户信息：`select user, password from mysql.user;`。

注意：高版本中没有password字段，使用authentication_string字段代替。

账户权限管理：

*   新的SQL用户不允许访问属于其他SQL用户的表，也不能立即创建自己的表，它必须被授权。可以授予的权限有以下几组：列权限，表权限，数据库权限和用户权限。
*   授予用户特权：`grant privilege_name on *.* to 'user_name'@'localhost' with grant option;`。
    *   privilege_name：all，create，drop，insert，select，update。
    *   with grant option：表示允许用户将自己的特权授予给其他用户
*   撤销用户特权：`revoke privilege_naem on *.* from 'user_name'@'localhost';`。

## 事务与并发控制

事务（Transaction）是用户定义的一个数据库操作序列，这些操作要么全做，要么全不做，是一个不可分割的工作单位。

事务与程序的区别：在关系数据库中，一个事务可以是一条SQL语句，一组SQL语句或整个程序。一个应用程序通常包含多个事务。事务是恢复和并发控制的基本单位。

事务的ACID特性：

*   原子性（Atomicity）：一组更新操作是原子不可分的。
*   一致性（Consistency）：事务必须满足数据库的完整性约束，数据库由一个一致性状态转变到另一个一致性状态。
*   隔离性（Isolation）：隔离性要求事务是彼此独立的、隔离的。
*   持续性（Durability）：也称为永久性，是指一个事务一旦提交，它对数据库中的数据的改变就应该是永久的。

并发操作引起的问题：丢失更新，不可重复读，读“脏”数据。

封锁：最常用的并发控制技术，基本思想是：需要时，事务通过向系统请求对它所希望的数据对象加锁，以确保它不被非预期改变。事务一直占有获得的锁直到结束时释放。

锁：一个锁实质上就是允许或阻止一个事务对一个数据对象的存取特权。锁的类型：排他锁（X锁），共享锁（S锁）。

封锁的粒度：通常以粒度来描述封锁的数据单元的大小。DBMS可以决定不同粒度的锁。由最底层的数据元素到最高层的整个数据库，粒度越细，并发性就越大，但软件复杂度和系统开销也就越大。

封锁的级别：

*   0级封锁：封锁的事务不重写其他非0级封锁事务的未提交的更新数据。这种状态实际上实用价值不大。
*   1级封锁：防止丢失更新的发生。
*   2级封锁：防止丢失更新的发生，防止读“脏”数据。
*   3级封锁：防止丢失更新的发生，防止不可重复读，防止读“脏”数据。

活锁和死锁：活锁是级别低的事务无法执行。死锁是两个以上事务循环等待被同组中另一个事务锁住的数据单元的情形。

## 备份与恢复

数据库中的数据丢失或被破坏的可能由以下的原因：硬件故障，软件故障，病毒，误操作，自然灾害，盗窃。

生成备份数据：`select *** into outfile path fields terminated by ',' optionally enclosed by '"' lines terminated by '?';`。

导入备份数据：`load data infile path into table table_name fields terminated by ',' optionally enclosed by '"' lines terminated by '?';`。

```sql
select * from table_name into outfile 'd:/1.txt' fields terminated by ',' optionally enclosed by '"' lines terminated by '?';

load data infile 'd:/1.txt' into table table_name fields terminated by ',' optionally enclosed by '"' lines terminated by '?';
```
