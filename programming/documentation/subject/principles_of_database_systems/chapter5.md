# 数据库编程

1.  [存储过程](#存储过程)
2.  [存储函数](#存储函数)

## 存储过程

存储过程：是一组为了完成某项特定功能的SQL语句集，其实质上就是一段存储在数据库中的代码，它可以由声明式SQL语句和过程式SQL语句组成。

存储过程的优点：

*   提高运行速度。
*   增强了SQL的功能和灵活性。
*   可以降低网络的通信量。
*   减轻了程序编写的工作量。
*   间接实现安全控制功能。

定界符：`delimiter $$`。

创建存储过程：

```sql
delimiter $$
create procedure procedure_name ({in /out / inout} param_name type, ...)
begin
  /* statements */
end $$
```

创建和使用变量：

```sql
begin
  declare a int [default 0];
  [set a = 10;]
  [select field_name into a from table_name where field_name='001';]
  /* statements */
end $$
```

语句：

```sql
if field_name1=value1 then
  select value1;
elseif field_name1=value2 then
  select value2;
else
  select value3;
end if;

case field
  when value1 then
    select value1;
  when value2 then
    select value2;
  else
    select value3;
end case;

delimiter $$
create procedure test_while (in in_count int)
begin
  declare count int default 0;
  declare sum int default 0;
  while count < in_count do
    set sum = sum + count;
    set count = count + 1;
  end while;
  select sum;
end $$
delimiter ;
```

游标：

```sql
create table student(
  stu_id int not null auto_increment,
  stu_name varchar(20) not null,
  stu_sex varchar(2),
  stu_age int,
  primary key(stu_id)
) default charset=utf8;

insert into student(stu_name, stu_sex, stu_age) values
('小明', '男', 20),
('小花', '女', 19),
('大赤', '男', 20),
('可乐', '男', 19),
('盈盈', '女', 19);

delimiter $$
create procedure p1()
begin
  declare id int;
  declare name varchar(100) character set utf8;
  declare done int default 0;
  -- declare cursor
  declare mc cursor for select stu_id, stu_name from student where stu_age>19;
  declare continue handler for not found set done=1;
  -- open cursor
  open mc;
  -- fetch results
  fetch mc into id, name;
  select id, name;
  -- close cursor
  close mc;
end $$
delimiter ;

delimiter $$
create procedure p2()
begin
  declare id, age, total int;
  declare name, sex varchar(20) character set utf8;
  declare done int default 0;
  declare mc cursor for select stu_id, stu_name, stu_sex, stu_age from student where stu_age>19;
  declare continue handler for not found set done=1;
  -- open cursor
  open mc;
  -- initialize variable
  set total=0;
  while done != 1 do
    fetch mc into id, name, sex, age;
    if done != 1 then
      set total = total + 1;
    end if;
  end while;
  -- close cursor
  close mc;
  select total;
end $$
delimiter ;
```

调用存储过程：

```sql
call procedure_name(param_name, ...);
```

删除存储过程：

```sql
drop procedure [if exists] procedure_name;
```

## 存储函数

创建存储函数：

```sql
delimiter $$
create function function_name(param_name type, ...)
returns type
deterministic
begin
  insert into table_name(field_name) values (name);
  return(name);
end $$
```

调用存储函数：

```sql
select function_name(param_name, ...);
```

删除存储函数：

```sql
drop function [if exists] function_name;
```
