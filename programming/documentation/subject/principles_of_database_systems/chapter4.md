# SQL与关系数据库基本操作

1.  [SQL概述](#sql概述)
2.  [MySQL预备知识](#mysql预备知识)
3.  [数据定义](#数据定义)
4.  [数据更新](#数据更新)
5.  [数据查询](#数据查询)
6.  [视图](#视图)

## SQL概述

结构化查询语言（Structured Query Language）：是一种特殊目的的编程语言，是一种数据库查询和程序设计语言，用于存取数据以及查询、更新和管理关系数据库系统。

## MySQL预备知识

LAMP和WAMP：

*   LAMP：Linux + Apache + MySQL + PHP/Perl/Python。
*   WAMP：Windows + Apache + MySQL + PHP/Perl/Python。

类型：

*   数值类型：INT，FLOAT，DOUBLE，DECIMAL。
*   日期和时间类型：DATE，(YYYY-MM-DD)，YEAR，DATETIME，TIME，TIMESTAMP。
*   字符串类型：CHAR，VARCHAR，TEXT。

常量：

*   字符串常量（本人用的MySQL不支持N）：'Hello'，N'你好'。
*   数值常量：129，-1.39，101.5E5。
*   日期时间常量：'1999-05-14'，'12:30:53'。
*   布尔值：true和false。
*   NULL。

变量：

*   用户变量：@a。
*   系统变量：@@admin_address，@@admin_port。

运算符：

*   算术运算符：+，-，*，/，%。
*   位运算符：&，|，^，～，>>，<<。
*   比较运算符：=，!=，<，>，<=，>=。
*   逻辑运算符：not，or，and，xor。
*   表达式就是常量、变量、列名、复杂计算、运算符和函数的组合。
*   内置函数：abs，sqrt，count，ascii，char，noew，year，month，day，cast，user，version。
*   内置聚合函数：count，max，min，sum，avg。

## 数据定义

数据库：

*   创建数据库：`create database [if not exists] db_name;`。
*   使用数据库：`use db_name;`。
*   修改数据库：`alter database db_name;`。
*   删除数据库：`drop database [if exists] db_name;`。
*   查看数据库：`show databases;`。

表：

*   创建表：`create table [if not exists] table_name;`。
*   删除表：`drop table [if exists] table_name;`。
*   查看表：`show tables;`。
*   查看表结构：`show columns from table_name;`。
*   添加字段：`alter table table_name add field_name type;`。
*   修改字段：`alter table table_name change old_field_name new_field_name type;`。
*   删除字段：`alter table table_name drop field_name;`。
*   重命名表：`alter table table_name1 rename to table_name2;`。

索引：

*   按照用途划分：
    *   普通索引（index）。
    *   唯一性索引（unique）。
    *   主键（primary key）。
    *   全文索引（fulltext，引擎为MyISAM）。
    *   聚簇索引（引擎为InnoDB）。
*   按照列级划分：
    *   单列索引。
    *   组合索引。
*   创建索引：`create index index_name on table_name(field_name [asc / desc])`。
*   查看索引：`show index from student;`。
*   删除索引：`drop index index_name on table_name;`。

```sql
create table student
  (
  sno char(11) not null,
  sname char(100) not null,
  sage int,
  primary key(sno)
  );
create table course
  (
  id int not null auto_increment,
  cname char(50) not null,
  primary key(id),
  index(cname)
  );
```

## 数据更新

插入数据：`insert into table_name(field_name1, field_name2 ...) values (value1, value2 ...)`。

删除数据：`delete from table_name where field_name=value;`。

修改数据：`update table_name set field_name2=value2 where field_name1=value1`。

## 数据查询

查询部分列：`select field_name1 as name1, field_name2 as name2, ... from table_name;`。

查询所有列：`select * from table_name;`。

查询部分列并添加额外信息：`select field_name1 as name1, field_name2 as name2, ..., cast when field_name1 < value1 then value11 when field_name1 = value1 then value22 ... end field_name from table_name;`。

连接：交叉连接（无条件），内连接，外连接（左连接和右连接）。

*   `select * from table_name1 [left / right ] join table_name2 on table_name1.field_name=table_name2.field_name;`。
*   `select * from table_name1, table_name2 where table_name1.field_name=table_name2.field_name;`。

where子句：

*   `select * from table_name where field_name[> / = / < / ...]value`。
*   `select * from table_name where field_name [not] between value1 and value2;`。
*   `select * from table_name where field_name in (value1, value2, ...)`。
*   `select * from table_name where field_name like '%value__';`。
    *   %表示代替任意字符任意次数，_表示单个字符仅一次。
*   `select * from table_name where field_name is [not] null;`。

group by子句：`select sum(field_name1 * field_name2) as field_name from table_name group by field_name3;`。

having子句：`select sum(field_name1 * field_name2) as field_name from table_name group by field_name3 having field_name[> / = / < / ...]value;`。

limit子句：`select * from table_name limit start_value, num;`。

```sql
# 简单查询
select student_name as 姓名, sex as 性别, year(now()) - year(birthday) as 年龄 from table_student;

select sno as 编号, sage as 年龄, cast when sage < 18 then '青年' when sage>= 18 and sage <= 50 then '中年' else '老年' end '状态' from table_name;

# 连接
select table_name1.*, table_name2.*  from table_name1 join table_name2;

select * from table_name1 join table_name2 on table_name1.gno=table_name2.gno;

select * from table_name1 left join table_name2 on table_name1.gno=table_name2.gno;

select * from table_name1 right join table_name2 on table_name1.gno=table_name2.gno;

select * from table_name1, table_name2 where table_name1.gno=table_name2.gno;

# where 子句
select * from table_name where num between 130 and 200;

select * from table_name1 where pno in (select distinct pno from table_name2);

# 其他子句
select sum(price*num) as sales, gno from table_name group by gno having sales>300 order by gno desc;

select * from table_name limit 3, 8; --以0下标开始计数，从3下标开始，共8个记录
```

## 视图

视图是从一个或多个表导出的表。视图一经定义以后，就可以像表一样被查询，修改，删除和更新。表占用物理存储空间而视图不占用物理存储空间，视图只是逻辑概念的存在，表可以及时对它进行修改，但视图只能用创建的方法来进行修改。

创建视图：`create [or replace] view view_name as select field_name from table_name;`。

删除视图：`drop view [if exists] view_name`。

修改视图：`alter view view_name(field_name1, ...) as select field_name1, ... from table_name;`。

查看完全定义：`show create view view_name;`。
