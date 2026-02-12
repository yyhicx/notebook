# Java Web

## Java Web介绍

什么是Java Web？

*   Web：全球互联网，也称为万维网（WWW），是用于浏览、发布和分享数据的应用。
*   JavaWeb：用Java技术来解决相关Web互联网领域的技术栈。
*   通过使用网页、JavaWeb程序和数据库，实现互联网应用。其中网页呈现数据，JavaWeb程序用于逻辑处理，数据库存储和管理数据。

## 数据库

数据库相关概念：

*   数据库（DataBase，简称DB）：存储数据的仓库，其中保存的数据是有组织的进行存储。
*   数据库管理系统（Database Management System，简称DBMS）：数据库的管理系统，负责数据库的创建、管理、维护。

MySQL数据模型：

*   关系型数据库：
    *   关系型数据库是建立关系模型基础上的数据库，简单来说，关系型数据库是由多张能互相连接的二维表组成的数据库。
    *   优点：
        1.  都是使用表结构，格式一致，易于维护。
        2.  使用通用的SQL语言操作，使用方便，可用于复杂查询。
        3.  数据存储在磁盘中，安全。
*   多个客户端通过JavaWeb程序访问数据库管理系统，数据库管理系统管理多个数据库，每个数据库管理多张表，每张表管理多行数据，每行数据管理多列数据。

结构化查询语言（Structured Query Language，简称SQL）：

*   一门操作关系型数据库的编程语言。
*   定义操作所有关系型数据库的统一标准。
*   对于同一需求，每一种数据库操作的方式可能存在一些不一样的地方，我们称为“方言”。即有的语法这个数据库可以执行，而其他数据库不能执行。

SQL语法：

*   SQL语句可以单行或多行书写，以分号结尾。
*   MySQL数据库的SQL语句不区分大小写，关键字建议使用大写。
*   注释：
    *   单行注释：`-- 注释内容`或`# 注释内容`（MySQL特有）。
    *   多行注释：`/* 注释内容 */`。
*   SQL中数据类型：
    *   整数：
        *   tinyint：1字节，范围-128~127或0~255，用于存储极小整数值。
        *   smallint：2字节，范围-32768~32767或0~65535，用于存储较小的整数值。
        *   mediumint：3字节，范围-8388608~8388607或0~16777215，用于存储中等的整数值。
        *   int/integer：4字节，范围-2147483648~2147483647或0~4294967295，用于存储较大的整数值，例如主键ID、数量、年龄等。
        *   bigint：8字节，用于存储超大的整数值，例如订单号、大数量统计。
    *   浮点数：
        *   float：4字节，用于存储单精度浮点数。
        *   double：8字节，用于存储双精度浮点数。
        *   decimal：用于存储高精度的十进制数，例如需要精确计算的银行、财务等场景。
    *   日期时间：
        *   date：3字节，用于存储日期，例如生日、活动日期等。
        *   time：3字节，用于存储时间。
        *   year：1字节，用于存储年份。
        *   datetime：8字节，用于存储日期和时间，例如订单时间、日志时间等。
        *   timestamp：4字节，时间戳，用于存储日期和时间，例如创建时间、最后修改时间等。
    *   字符串：
        *   char：0~255字符，用于存储固定长度的字符串，例如手机号、国家代码等。
        *   varchar：0~65535字符，用于存储可变长字符串，例如用户名、密码、地址等。
        *   text：最大65535字符，用于存储标准文本数据，例如文章、简介等。
        *   longtext：最大4294967295字符，用于存储超长文本数据。
    *   二进制：
        *   binary：0~255字节，用于存储固定长度二进制数据。
        *   varbinary：0~65535字节，用于存储可变长度进制数据。
        *   blob：最大65535字节，用于存储标准二进制大对象。
        *   longblob：最大4294967295字节，用于存储超大二进制对象。
    *   枚举：enum，用于存储枚举值，例如性别、状态等。
    *   集合：set，用于存储多个枚举值，例如颜色、权限等。
    *   JSON：JSON类型，用于存储JSON数据。
    *   注意：虽然MySQL中二进制类型可以存储图片、音频、视频等文件，但是性能很差，推荐使用varchar类型保存这些文件存储在文件系统中的路径，然后通过路径访问文件。
*   SQL分类：
    *   DDL（Data Definition Language）：数据定义语言，用来定义数据库对象，如：数据库、表、字段等。
        *   数据库的相关操作：

            ```sql
            # 查询所有数据库
            show databases;

            # 创建数据库
            create database database_name;
            create database if not exists database_name;

            # 删除数据库
            drop database database_name;
            drop database if exists database_name;

            # 使用数据库
            use database_name;

            # 查看当前正在使用的数据库
            select database();

            # 在 MySQL 8.0 中，推荐使用 utf8mb4_0900_ai_ci 排序规则
            create database database_name character set utf8mb4 collate utf8mb4_0900_ai_ci;

            # 在 MySQL 5.7 中，使用 utf8mb4_general_ci 排序规则，性能较快，但准确性较差
            create database database_name character set utf8mb4 collate utf8mb4_general_ci;

            # 在 MySQL 5.7 中，使用 utf8mb4_unicode_ci 排序规则，性能较慢，但准确性较高
            create database database_name character set utf8mb4 collate utf8mb4_unicode_ci;
            ```

        *   表的相关操作：

            ```sql
            # 查询当前数据库下的所有表
            show tables;

            # 查询表的结构
            desc table_name;

            # 创建表
            create table table_name (
              field_name1 field_type1 field_property1,
              field_name2 field_type2 field_property2,
              ...
              field_nameN field_typeN field_propertyN
            );

            # 创建表的示例
            create table students (
              id int primary key auto_increment,
              name varchar(20) not null,
              gender char(1),
              birthday date,
              score double(5, 2),  # 总长度为 5，保留 2 位小数，例如 345.67
              email varchar(64),
              tel varchar(15),
              status enum('在读', '毕业') default '在读',
              create_time timestamp default current_timestamp
            );

            # 删除表
            drop table table_name;
            drop table if exists table_name;

            # 修改表名
            alter table old_table_name rename to new_table_name;

            # 添加一列
            alter table table_name add column new_field_name new_field_type new_field_property;

            # 修改数据类型
            alter table table_name modify column field_name new_field_type new_field_property;

            # 修改列名和数据类型
            alter table table_name change column old_field_name new_field_name new_field_type new_field_property;

            # 删除一列
            alter table table_name drop column field_name;
            ```

    *   DML（Data Manipulation Language）：数据操作语言，用来对数据库中表的数据进行增删改（没有查）。
        *   添加数据：

            ```sql
            # 给指定列添加数据
            insert into table_name (field_name1, field_name2, ...) values (value1, value2, ...);

            # 给全部列添加数据
            insert into table_name values (value1, value2, ...);

            # 给指定的列批量添加数据
            insert into table_name (field_name1, field_name2, ...) values (value1, value2, ...), (value3, value4, ...), ...;

            # 给全部列批量添加数据
            insert into table_name values (value1, value2, ...), (value3, value4, ...), ...;
            ```

        *   修改数据：

            ```sql
            # 修改指定列的数据
            update table_name set field_name1 = value1, field_name2 = value2, ... where condition;

            # 修改全部列的数据
            update table_name set field_name1 = value1, field_name2 = value2, ...;
            ```

        *   删除数据：

            ```sql
            # 删除指定列的数据
            delete from table_name where condition;

            # 删除全部列的数据
            delete from table_name;
            ```

    *   DQL（Data Query Language）：数据查询语言，用来查询数据库中的表的数据。
        *   查询语法：

            ```sql
            select field_name1, field_name2, ... from table_name where condition group by field_name1, field_name2, ... having condition order by field_name1, field_name2, ... limit number;
            ```

        *   五大查询：
            *   基础查询：

                ```sql
                # 查询多个字段
                select field_name1, field_name2, ... from table_name;

                # 查询所有字段
                select * from table_name;

                # 查询多个字段并去除重复记录
                select distinct field_name1, field_name2, ... from table_name;

                # 使用 as 给字段起别名
                select field_name1 as alias_name1, field_name2 as alias_name2, ... from table_name;
                ```

            *   条件查询：
                *   条件查询语法：

                    ```sql
                    select field_name1, field_name2, ... from table_name where condition;
                    ```

                *   条件查询示例：

                    ```sql
                    # 查询年龄大于 20 岁的学生信息
                    select * from students where age > 20;

                    # 查询年龄大于等于 20 岁的学生信息
                    select * from students where age >= 20;

                    # 查询年龄大于等于 20 岁并且年龄小于等于 30 岁的学生信息
                    select * from students where age >= 20 and age <= 30;
                    select * from students where age between 20 and 30;

                    # 查询入学日期在 1998-09-01 到 1999-09-01 之间的学生信息
                    select * from students where hire_date between '1998-09-01' and '1999-09-01';

                    # 查询年龄等于 18 岁的学生信息
                    select * from students where age = 18;

                    # 查询年龄不等于 18 岁的学生信息
                    select * from students where age != 18;
                    select * from students where age <> 18;

                    # 查询年龄等于 18 岁或者年龄等于 20 岁或者年龄等于 22 岁的学生信息
                    select * from students where age = 18 or age = 20 or age = 22;
                    select * from students where age in (18, 20, 22);

                    # 查询英语成绩为 null 的学生信息
                    # 和 null 值进行比较需要使用 is 或者 is not
                    select * from students where english is null;

                    # 模糊查询使用 like 关键字
                    # 通配符：% 表示任意个数的字符，_ 表示任意一个字符
                    # 查询姓马的学生信息
                    select * from students where name like '马%';

                    # 查询姓名中第二个字是花的学生信息
                    select * from students where name like '_花%';

                    # 查询姓名中包含德的学生信息
                    select * from students where name like '%德%';
                    ```

            *   排序查询：
                *   排序查询语法：

                    ```sql
                    # asc 表示升序，desc 表示降序，默认采用升序
                    # 如果有多个排序条件，当前面的条件值一样时，才会根据第二条件进行排序
                    select field_name1, field_name2, ... from table_name order by field_name1 [asc|desc], field_name2 [asc|desc], ...;
                    ```

                *   排序查询示例：

                    ```sql
                    # 查询学生信息，按照年龄升序排序
                    select * from students order by age asc;

                    # 查询学生信息，按照数学成绩降序排序
                    select * from students order by math desc;

                    # 查询学生信息，按照数学成绩降序排序，如果数学成绩一样，再按照英语成绩升序排序
                    select * from students order by math desc, english asc;
                    ```

            *   分组查询：
                *   聚合函数：将一列数据作为一个整体，进行纵向计算。
                    *   聚合函数语法：

                        ```sql
                        # null 值不参与所有聚合函数的计算
                        select aggregate_function(column_name) from table_name;
                        ```

                    *   聚合函数使用示例：

                        ```sql
                        # 统计班级一共有多少个学生
                        select count(id) from students;

                        # 查询数学成绩的最高分
                        select max(math) from students;

                        # 查询数学成绩的最低分
                        select min(math) from students;

                        # 查询数学成绩的总和
                        select sum(math) from students;

                        # 查询数学成绩的平均分
                        select avg(math) from students;
                        ```

                *   分组查询语法：

                    ```sql
                    # 分组之后，查询的字段为聚合函数和分组字段，查询其他字段无任何意义
                    # where 是分组之前进行限定，不满足 where 条件的行不参与分组，而 having 是分组之后对结果进行过滤
                    # where 不能对聚合函数进行判断，having 可以对聚合函数进行判断
                    # 执行顺序：where -> aggregate_function -> having
                    select field_name1, field_name2, ... from table_name [where condition] group by field_name1, field_name2, ... [having condition];
                    ```

                *   分组查询示例：

                    ```sql
                    # 查询男同学和女同学各自的数学平均分
                    select gender, avg(math) from students group by gender;

                    # 查询男同学和女同学各自的数学平均分，以及各自人数
                    select gender, avg(math), count(*) from students group by gender;

                    # 查询男同学和女同学各自的数学平均分，以及各自人数，同时分数低于 70 分的不参与统计
                    select gender, avg(math), count(*) from students where math >= 70 group by gender;

                    # 查询男同学和女同学各自的数学平均分，以及各自人数，同时分数低于 70 分的不参与统计，并且分组之后人数大于 2 个
                    select gender, avg(math), count(*) from students where math >= 70 group by gender having count(*) > 2;
                    ```

            *   分页查询：
                *   分页查询语法：

                    ```sql
                    # 起始索引 start 从 0 开始
                    # 计算公式：起始索引 = (当前页码 - 1) * 每页显示的记录数
                    # limit 是 MySQL 数据库的方言，Oracle 数据库的分页查询使用 rownumber，SQL Server 数据库的分页查询使用 top
                    select field_name1, field_name2, ... from table_name limit start, count;
                    ```

                *   分页查询示例：

                    ```sql
                    # 从 0 开始，查询 3 条数据
                    select * from students limit 0, 3;

                    # 每页显示 3 条数据，查询第 1 页数据
                    select * from students limit 0, 3;

                    # 每页显示 3 条数据，查询第 2 页数据
                    select * from students limit 3, 3;

                    # 每页显示 3 条数据，查询第 3 页数据
                    select * from students limit 6, 3;
                    ```

    *   DCL（Data Control Language）：数据控制语言，用来对数据库中用户进行管理、权限进行控制。
        *   用户管理：
            *   用户管理语法：

                ```sql
                # 创建用户
                create user 'username'@'hostname' identified by 'password';

                # 修改用户密码
                alter user 'username'@'hostname' identified by 'new_password';

                # 删除用户
                drop user 'username'@'hostname';
                ```

            *   用户管理示例：

                ```sql
                # 创建本地访问用户
                create user 'dev_user'@'localhost' identified by 'dev_password';

                # 创建任意 IP 访问用户
                create user 'remote_user'@'%' identified by 'remote_password';

                # 修改用户密码
                alter user 'dev_user'@'localhost' identified by 'new_dev_password';

                # 删除用户
                drop user 'remote_user'@'%';
                ```

        *   权限控制：
            *   权限控制语法：

                ```sql
                # 授予权限
                grant permission_name1, permission_name2, ... on database_name.table_name to 'username'@'hostname';

                # 撤销权限
                revoke permission_name1, permission_name2, ... on database_name.table_name from 'username'@'hostname';

                # 查看权限
                show grants for 'username'@'hostname';
                ```

            *   常用权限类型：
                *   all privileges：所有权限（慎用）。
                *   select：查询数据。
                *   insert：插入数据。
                *   update：更新数据。
                *   delete：删除数据。
                *   create：创建数据库或表。
                *   drop：删除数据库或表。
                *   alter：修改表结构。
                *   index：创建或删除索引。
                *   execute：执行存储过程。
            *   权限控制示例：

                ```sql
                # 授予 school_db 所有表的查询权限
                grant select on school_db.* to 'report_user'@'localhost';

                # 授予 students 表的完整操作权限
                grant select, insert, update, delete on school_db.students to 'admin_user'@'%';

                # 撤销 students 表的删除权限
                revoke delete on school_db.students from 'admin_user'@'%';

                # 查看用户权限
                show grants for 'report_user'@'localhost';
                ```

约束：约束是作用于表中列上的规则，用于限制加入表中的数据；约束的存在保证了数据的正确性、有效性和完整性。

*   约束的分类：
    *   not null：非空约束，保证列中所有数据不能有null值。
    *   unique：唯一约束，保证列中所有数据都是唯一的，不能有重复值。
    *   primary key：主键约束，保证列中数据是唯一的，不能有重复值，并且不能为null。
    *   check：检查约束，保证列中的数据满足指定的条件。
    *   default：默认约束，保存数据时未指定值则采用默认值。
    *   foreign key：外键约束，外键用来让两个表的数据之间建立链接，保证数据的一致性和完整性。
    *   注意：MySQL中不支持检查约束。
*   约束使用示例：

    ```sql
    create table employees (
      id int primary key auto_increment,
      ename varchar(50) not null,
      joindate date not null,
      salary double(7, 2) not null,
      bonus double(7, 2) default 0.0,
      dept_id int not null,
      # 给 dept_id 和 departments.id 之间建立名为 fk_emp_dept 的外键约束
      constraint fk_emp_dept foreign key (dept_id) references departments(id)
    );

    create table departments (
      id int primary key auto_increment,
      dname varchar(50) not null unique,
      address varchar(100) not null
    );
    ```

数据库设计：

*   软件的研发步骤：需求分析、设计、编码、测试、安装部署。

    ![软件开发步骤](resources/software_development_steps.png)

*   数据库设计概念：
    *   数据库设计就是根据业务系统的具体需求，结合我们所选用的DBMS，为这个业务系统构造出最优的数据存储模型。
    *   建立数据库中的表结构以及表与表之间的关联关系的过程。
*   数据库设计的步骤：
    *   需求分析：需要设计哪些表，表中有哪些字段，表和表之间有哪些关系。
    *   逻辑分析：通过ER图对数据库进行逻辑建模，不需要考虑我们所选用的数据库管理系统。
    *   物理设计：根据数据库自身的特点把逻辑设计转换成物理设计。
    *   维护设计：对新的需求进行建表，对已建好的表进行优化。
*   表关系：
    *   一对一：在任意一方加入外键约束，关联另一方主键，同时设置外键唯一；一对一的关系多用于表拆分，将一个实体中经常使用的字段放一张表，不经常使用的字段放另一张表，用于提升查询性能。

        ```sql
        # 用户表和用户详情表
        create table users (
          id int primary key auto_increment,
          uname varchar(20) not null,
          age int not null,
          gender char(1) not null
        );

        create table user_details (
          id int primary key auto_increment,
          uid int not null unique,
          address varchar(50) not null,
          education varchar(20) not null,
          status varchar(20) not null,
          constraint fk_user_detail_user foreign key (uid) references users (id)
        );
        ```

    *   一对多（多对一）：通过外键约束建立一对多关系。

        ```sql
        # 一个部门有多个员工
        create table departments (
          id int primary key auto_increment,
          dname varchar(20) not null unique,
          address varchar(20) not null
        );

        create table employees (
          id int primary key auto_increment,
          ename varchar(20) not null,
          age int not null,
          dept_id int not null,
          constraint fk_employee_department foreign key (dept_id) references departments (id)
        );
        ```

    *   多对多：通过建立第三张中间表，中间表至少包含两个外键，分别关联两方主键，用来建立多对多关系。

        ```sql
        # 一个商品对应多个订单，一个订单包含多个商品
        create table products (
          id int primary key auto_increment,
          pname varchar(20) not null,
          price decimal(10,2) not null
        );

        # 如果表名为 order，那么需要使用反引号转义保留字，例如 `order`
        create table orders (
          id int primary key auto_increment,
          odate datetime not null,
          total decimal(10,2) not null,
          payment_type enum('微信', '支付宝') not null,
          status enum('待付款', '待发货', '待收货', '待评价', '已完成') not null
        );

        create table product_order (
          id int primary key auto_increment,
          product_id int not null,
          order_id int not null
          constraint fk_product_order_product foreign key (product_id) references products (id),
          constraint fk_product_order_order foreign key (order_id) references orders (id)
        );
        ```

多表查询：

*   笛卡尔积：取多表中所有行进行组合运算后的结果集。如果两个表的行数分别为M和N，则笛卡尔积的结果集行数为M*N。这种方式下，结果集可能非常庞大，且缺乏任何实际意义，不建议用于大规模数据查询，通常不会在实际生产环境中使用。

    ```txt
    商品表：products

    pid     pname       price
    1       Apple       150.00
    2       Banana      80.00
    3       Orange      120.00

    订单表：orders

    oid     odate           pid     quantity    total   
    101     2023-01-01      1       2           300.00
    102     2023-01-02      2       3           240.00
    103     2023-01-03      3       1           120.00
    104     2023-01-04      1       1           150.00
    ```

    ```sql
    # 例如现在有 3 个商品，4 个订单，最终会显示 12 行数据
    # 且大量数据没有很好的关联性，不建议直接用笛卡尔积进行查询
    select * from products, orders;
    ```

    ```txt
    结果集

    pid     pname       price       oid     odate           pid     quantity    total
    1       Apple       150.00      101     2023-01-01      1       2           300.00
    1       Apple       150.00      102     2023-01-02      2       3           240.00
    1       Apple       150.00      103     2023-01-03      3       1           120.00
    1       Apple       150.00      104     2023-01-04      1       1           150.00
    2       Banana      80.00       101     2023-01-01      1       2           300.00
    2       Banana      80.00       102     2023-01-02      2       3           240.00
    2       Banana      80.00       103     2023-01-03      3       1           120.00
    2       Banana      80.00       104     2023-01-04      1       1           150.00
    3       Orange      120.00      101     2023-01-01      1       2           300.00
    3       Orange      120.00      102     2023-01-02      2       3           240.00
    3       Orange      120.00      103     2023-01-03      3       1           120.00
    3       Orange      120.00      104     2023-01-04      1       1           150.00
    ```

*   连接查询：将多个表按特定条件（通常是某个列的相等性）连接起来，获取符合条件的记录。
    *   内连接：返回两个表中满足连接条件的记录。如果某一表中的记录在另一表中没有匹配项，则该记录不会出现在查询结果中。

        ```txt
        商品表：products

        pid     pname       price
        1       Apple       150.00
        2       Banana      80.00
        3       Orange      120.00

        订单表：orders

        oid     odate           pid     quantity    total  
        101     2023-01-01      1       2           300.00
        102     2023-01-02      2       3           240.00
        103     2023-01-03      3       1           120.00
        104     2023-01-04      1       1           150.00
        ```

        ```sql
        # 隐式内连接
        select * from products p, orders o where p.pid = o.pid;

        # 显式内连接
        select * from products p inner join orders o on p.pid = o.pid;
        ```

        ```txt
        结果集

        pid     pname       price       oid     odate           pid     quantity    total          
        1       Apple       150.00      101     2023-01-01      1       2           300.00
        2       Banana      80.00       102     2023-01-02      2       3           240.00
        3       Orange      120.00      103     2023-01-03      3       1           120.00
        1       Apple       150.00      104     2023-01-04      1       1           150.00
        ```

    *   外连接：返回符合连接条件的记录和不符合条件的记录，其中不符合条件的记录会使用null填充。
        *   左外连接：返回左表中的所有记录，即使右表中没有匹配的记录，右表对应列会返回null。

            ```txt
            商品表：products

            pid     pname       price
            1       Apple       150.00
            2       Banana      80.00
            3       Orange      120.00

            订单表：orders

            oid     odate           pid     quantity    total  
            101     2023-01-01      1       2           300.00
            102     2023-01-02      2       3           240.00
            103     2023-01-03      null    1           120.00
            104     2023-01-04      null    1           150.00
            ```

            ```sql
            select * from products p left join orders o on p.pid = o.pid;
            ```

            ```txt
            结果集

            pid     pname       price       oid     odate           pid     quantity    total          
            1       Apple       150.00      101     2023-01-01      1       2           300.00
            2       Banana      80.00       102     2023-01-02      2       3           240.00
            3       Orange      120.00      null    null            null    null        null
            ```

        *   右外连接：返回右表中的所有记录，即使左表中没有匹配的记录，左表对应列会返回null。

            ```txt
            商品表：products

            pid     pname       price
            1       Apple       150.00
            2       Banana      80.00
            3       Orange      120.00

            订单表：orders

            oid     odate           pid     quantity    total  
            101     2023-01-01      1       2           300.00
            102     2023-01-02      2       3           240.00
            103     2023-01-03      3       1           120.00
            104     2023-01-04      4       1           150.00
            ```

            ```sql
            select * from products p right join orders o on p.pid = o.pid;
            ```

            ```txt
            结果集

            pid     pname       price       oid     odate           pid     quantity    total
            1       Apple       150.00      101     2023-01-01      1       2           300.00
            2       Banana      80.00       102     2023-01-02      2       3           240.00
            3       Orange      120.00      103     2023-01-03      3       1           120.00
            null    null        null        104     2023-01-04      4       1           150.00
            ```

        *   全外连接：返回左表和右表中的所有记录，即使没有匹配的记录，对应列会返回null。

            ```txt
            商品表：products

            pid     pname       price
            1       Apple       150.00
            2       Banana      80.00
            3       Orange      120.00
            4       Grape       90.00

            订单表：orders

            oid     odate           pid     quantity    total  
            101     2023-01-01      1       2           300.00
            102     2023-01-02      2       3           240.00
            103     2023-01-03      3       1           120.00
            104     2023-01-04      null    1           150.00
            ```

            ```sql
            # MySQL 8.0 以前
            select * from products p left join orders o on p.pid = o.pid
            union
            select * from products p right join orders o on p.pid = o.pid;

            # MySQL 8.0 及以后
            select * from products p full outer join orders o on p.pid = o.pid;
            ```

            ```txt
            结果集

            pid     pname       price       oid     odate           pid     quantity    total
            1       Apple       150.00      101     2023-01-01      1       2           300.00
            2       Banana      80.00       102     2023-01-02      2       3           240.00
            3       Orange      120.00      103     2023-01-03      3       1           120.00
            4       Grape       90.00       null    null            null    null        null
            null    null        null        104     2023-01-04      null    1           150.00
            ```

*   子查询：也称为嵌套查询或内部查询，是指一个select语句嵌套在另一个SQL语句（select、insert、update、delete）内部的查询。
    *   数据库引擎会先执行子查询，得到一个中间结果集，然后这个中间结果集被递给外层查询使用。
    *   子查询根据查询结果不同、作用不同分为：
        *   单行单列：

            ```sql
            # 找出工资高于公司平均工资的所有员工
            # select avg(salary) from employees 返回的是一个确定的值
            select * from employees where salary > (select avg(salary) from employees);
            ```

        *   多行单列：

            ```sql
            # 找出部门在北京的所有员工
            # select id from departments where location = '北京' 返回的是多个值
            select * from employees where dept_id in (select id from departments where location = '北京');
            ```

        *   多行多列：主要用在from子句中，作为一个派生表。

            ```sql
            # 将部门表和员工表进行关联，找出每个员工及其所属部门
            # select id, name from departments 和 select id, name from employees 返回的都是
            select * from (select id, name from departments) as d, (select id, name from employees) as e where d.id = e.dept_id;
            ```

*   示例：
    *   表结构和原始数据：

        ```sql
        drop table if exists employees;
        drop table if exists departments;
        drop table if exists jobs;
        drop table if exists salarygrades;

        -- 部门表
        create table departments (
          id int primary key auto_increment,
          dname varchar(50) not null,
          location varchar(50) not null
        );

        -- 职位表
        create table jobs (
          id int primary key auto_increment,
          jname varchar(20) not null,
          description varchar(100) not null
        );

        -- 员工表
        create table employees (
          id int primary key auto_increment,
          ename varchar(20) not null,
          joindate date not null,
          salary decimal(10,2) not null,
          bonus decimal(10,2) default 0,
          dept_id int,
          job_id int,
          constraint fk_emp_dept foreign key (dept_id) references departments (id),
          constraint fk_emp_job foreign key (job_id) references jobs (id)
        );

        -- 导入部门数据
        INSERT INTO departments (dname, location) VALUES
        ('研发部', '北京'),
        ('销售部', '上海'),
        ('行政部', '广州'),
        ('财务部', '深圳');

        -- 导入职位数据
        INSERT INTO jobs (jname, description) VALUES
        ('技术总监', '负责技术团队管理和技术决策'),
        ('高级工程师', '负责核心模块开发和系统架构'),
        ('销售经理', '负责销售团队管理和客户关系维护'),
        ('销售代表', '负责产品销售和客户开发'),
        ('财务主管', '负责财务报表和预算管理'),
        ('行政专员', '负责日常行政事务和办公管理');

        -- 导入员工数据
        INSERT INTO employees (ename, joindate, salary, bonus, dept_id, job_id) VALUES
        -- 研发部
        ('张三', '2019-03-15', 25000.00, 20000.00, 1, 1),
        ('李四', '2020-07-22', 18000.00, 15000.00, 1, 2),
        ('王五', '2021-11-05', 15000.00, 12000.00, 1, 2),
        ('赵六', '2022-02-18', 12000.00, 8000.00, 1, 2),
        -- 销售部
        ('钱七', '2018-05-30', 22000.00, 30000.00, 2, 3),
        ('孙八', '2019-09-12', 16000.00, 25000.00, 2, 4),
        ('周九', '2020-12-03', 14000.00, 18000.00, 2, 4),
        ('吴十', '2021-04-25', 13000.00, 15000.00, 2, 4),
        ('郑十一', '2023-01-15', 11000.00, 10000.00, 2, 4),
        -- 行政部
        ('王芳', '2017-08-19', 16000.00, 5000.00, 3, 6),
        ('李娜', '2020-03-22', 13000.00, 3000.00, 3, 6),
        ('陈静', '2022-06-11', 10000.00, 2000.00, 3, 6),
        ('刘婷', '2023-03-08', 9000.00, 1500.00, 3, 6),
        -- 财务部
        ('杨明', '2018-11-07', 20000.00, 10000.00, 4, 5),
        ('黄伟', '2019-04-16', 17000.00, 8000.00, 4, 5),
        ('朱琳', '2021-08-29', 15000.00, 6000.00, 4, 5),
        ('徐强', '2022-10-12', 13000.00, 5000.00, 4, 5),
        ('高敏', '2023-02-28', 11000.00, 3000.00, 4, 5),
        ('林峰', '2021-05-17', 14000.00, 4000.00, 4, 5),
        ('罗娟', '2022-09-03', 12000.00, 3500.00, 4, 5);
        ```

    *   执行：

        ```sql
        -- 查询所有员工信息，包括员工编号、员工姓名、工资、职务名称和职务描述
        select e.id, e.ename, e.salary, j.jname, j.description from employees e, jobs j where e.job_id = j.id;

        -- 查询员工编号、员工姓名、工资、职务名称、职务描述、部门名称和部门位置
        select e.id, e.ename, e.salary, j.jname, j.description, d.dname, d.location from employees e, jobs j, departments d where e.job_id = j.id and e.dept_id = d.id;

        -- 查询部门编号、部门名称、部门位置和部门人数
        select d.id, d.dname, d.location, count(*) as num from employees e, departments d where e.dept_id = d.id group by d.id;
        ```

事务：

*   事务简介：
    *   数据库的事务是一种机制，一个操作序列，包含了一组数据库操作命令。
    *   事务把所有的命令作为一个整体一起向系统提交或撤销操作请求，即这一组数据库命令要么同时成功，要么同时失败。
    *   事务是一个不可分割的工作逻辑单元。
*   查询事务的默认提交方式：

    ```sql
    -- 默认是自动提交事务，值为 1
    select @@autocommit;

    -- 修改事务的提交方式为手动提交
    set @@autocommit = 0;
    ```

*   事务操作：开启事务、提交事务、回滚事务、查询事务。

    ```sql
    -- ========== 准备工作 ==========
    -- 1. 删除表
    drop table if exists transactions;
    drop table if exists bank_accounts;

    -- 2. 创建银行账户表
    create table bank_accounts (
      account_id int primary key auto_increment,
      account_name varchar(50) not null,
      balance decimal(10,2) not null default 0.00,  -- 账户余额
      last_transaction timestamp null -- 最后一次交易时间
    );

    -- 3. 创建交易记录表
    create table transactions (
      transaction_id int primary key auto_increment,
      from_account int not null,
      to_account int not null,
      amount decimal(10,2) not null,  -- 交易金额
      transaction_time timestamp default current_timestamp,
      status enum('pending', 'completed', 'failed') default 'pending',  -- 交易状态：待处理、完成、失败
      foreign key (from_account) references bank_accounts(account_id),
      foreign key (to_account) references bank_accounts(account_id)
    );

    -- 4. 插入初始账户数据
    insert into bank_accounts (account_name, balance) values
    ('张三', 10000.00),
    ('李四', 5000.00),
    ('王五', 20000.00);

    -- 5. 查看初始数据
    select * from bank_accounts;

    -- ========== 事务演示开始 ==========
    -- 场景 1：成功转账事务
    select '===== 开始成功转账事务 =====' as log;

    -- 1. 开启事务
    start transaction;  -- 或者 begin;

    -- 2. 插入交易记录
    insert into transactions (from_account, to_account, amount, status)
    values (
      (select account_id from bank_accounts where account_name = '张三'),
      (select account_id from bank_accounts where account_name = '李四'),
      1000.00,
      'pending'
    );

    -- 3. 执行转账操作
    update bank_accounts set balance = balance - 1000.00 where account_name = '张三';
    update bank_accounts set balance = balance + 1000.00 where account_name = '李四';

    -- 4. 更新交易状态
    update transactions set status = 'completed' where status = 'pending'
    and from_account = (select account_id from bank_accounts where account_name = '张三');

    -- 5. 查询当前事务状态
    select '当前事务状态：' as info,
    (select count(*) from information_schema.innodb_trx
    where trx_mysql_thread_id = connection_id()) as active_transactions;

    -- 6. 查询事务中的临时数据
    select '事务内账户余额：' as info;
    select * from bank_accounts;

    select '事务内交易记录：' as info;
    select * from transactions;

    -- 7. 提交事务
    commit;

    select '===== 事务已提交 =====' as log;

    -- 场景 2：失败转账事务（余额不足）
    select '===== 开始失败转账事务 =====' as log;

    -- 1. 开启事务
    start transaction;

    -- 2. 插入交易记录
    insert into transactions (from_account, to_account, amount, status)
    values (
      (select account_id from bank_accounts where account_name = '李四'),
      (select account_id from bank_accounts where account_name = '王五'),
      10000.00,
      'pending' 
    );

    -- 3. 执行转账操作
    update bank_accounts set balance = balance - 10000.00 where account_name = '李四';

    -- 检查余额是否足够（在实际应用开发中，应在业务代码中检查）
    set @current_balance = (select balance from bank_accounts where account_name = '李四');

    select concat('转账后李四余额：', @current_balance) as balance_check;
    select '余额不足，请观察上面的负数，现在回滚事务' as action;

    -- 4. 回滚事务，撤销所有更改
    rollback;

    -- 5. 查看回滚后的数据
    select '回滚后账户余额：' as info;
    select * from bank_accounts;

    select '交易记录状态：' as info;
    select * from transactions;
    ```

*   事务四大特征：
    *   原子性：事务是不可分割的最小操作单位，要么同时成功，要么同时失败。
    *   一致性：事务完成后，必须使所有的数据都保持一致状态。
    *   隔离性：多个事务之间，操作的可见性。
    *   持久性：事务一旦提交或回滚，它对数据库中的数据的改变就是永久的。

JDBC：

*   JDBC简介：
    *   JDBC（Java DataBase Connectivity，Java数据库连接）是使用Java语言操作关系型数据库的一套API。
    *   JDBC的本质：
        *   由官方（Sun公司）定义的一套操作所有关系型数据库的规则，即接口。
        *   各个数据库厂商去实现这套接口，提供数据库驱动jar包。
        *   我们可以使用这套接口（JDBC）编程，真正执行的代码是驱动jar包中的实现类。
    *   JDBC的好处：
        *   各数据库厂商使用相同的接口，Java代码不需要针对不同数据库分别开发。
        *   可随时替换底层数据库，访问数据库的Java代码基本不变。
*   JDBC快速入门：
    *   导入依赖：在项目中创建lib文件夹，导入mysql-connector-java.jar包，最后在IDEA中右键jar包以`Add as Library...`添加到项目中。
    *   示例：

        ```java
        public class Main {
          public static void main(String[] args) throws ClassNotFoundException, SQLException {
            // 注册驱动，从 MySQL 5 之后的驱动包可以省略注册驱动这一步
            // 老版本
            Class.forName("com.mysql.jdbc.Driver");
            // 新版本
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // 获取连接
            String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=UTC";  // useSSL=false 禁用安全连接方式，解决警告提示
            String user = "root";
            String password = "123456";
            Connection conn = DriverManager.getConnection(url, user, password);

            // 定义 SQL 语句
            String sql = "update account set money = 500 where id = 1";

            // 获取执行 SQL 对象
            Statement stmt = conn.createStatement();

            // 执行 SQL
            int count = stmt.executeUpdate(sql);

            // 处理返回结果，count 表示执行 SQL 后影响的行数
            System.out.println(count);

            // 释放资源
            stmt.close();
            conn.close();
          }
        }
        ```

*   JDBC API详解：
    *   DriverManager（驱动管理类）：用于注册驱动，以及获取数据库连接。
        *   为什么`mysql-connector-java-5.x.x.jar`以前的版本中通过`Class.forName("com.mysql.jdbc.Driver")`也可以获取数据库连接？因为Driver类的静态代码块中会通过`DriverManager.registerDriver(new Driver())`注册。

            ```java
            public class Driver extends NonRegisteringDriver implements java.sql.Driver {
              public Driver() throws SQLException {
              }

              static {
                try {
                  DriverManager.registerDriver(new Driver());
                } catch (SQLException var1) {
                  throw new RuntimeException("Can't register driver!");
                }
              }
            }
            ```

        *   为什么`mysql-connector-java-5.x.x.jar`以后的版本可以不写`Class.forName("com.mysql.cj.jdbc.Driver")`获取数据库连接？因为`mysql-connector-java-5.x.x.jar/META-INF/services/java.sql.Driver`文件中写入了`com.mysql.cj.jdbc.Driver`，会自动完成注册。
    *   Connection（数据库连接对象）：用于获取执行SQL的对象，以及管理事务。
    *   Statement（执行SQL的对象）：用于执行SQL语句。
        *   通过`executeUpdate(sql)`执行DML语句，会返回受影响的行数。
        *   通过`executeUpdate(sql)`执行DDL语句，执行成功也可能返回0。
        *   通过`executeQuery(sql)`执行DQL语句，返回ResultSet结果集对象。
    *   ResultSet（结果集对象）：用于获取查询结果。
    *   PreparedStatement（预编译SQL对象）：用于执行SQL语句。预编译SQL对象，性能更高（默认关闭需要开启）；对敏感字符进行转义，防止SQL注入。

    ```java
    public class Main {
      public static void main(String[] args) {
        // useSSL=false 禁用安全连接方式，解决警告提示
        // useServerPrepStmts=true 开启预编译
        // charsetEncoding=utf8mb4 设置字符集，防止中文乱码
        // serverTimezone=UTC 设置时区
        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false&useServerPrepStmts=true&charsetEncoding=utf8mb4&serverTimezone=UTC";
        String username = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
          String sql1 = "update accounts set money = 500 where id = 1";
          String sql2 = "update accounts set money = 1000 where id = 2";
          String sql3 = "select * from accounts";
          String sql4 = "select * from users where username = ? and password = ?";

          // 开启事务
          conn.setAutoCommit(false);

          try (
              Statement stmt = conn.createStatement();
              PreparedStatement pstmt = conn.prepareStatement(sql4);
          ) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            int count1 = stmt.executeUpdate(sql1);
            if (count1 > 0) {
              System.out.println("Update success!");
            }
            int count2 = stmt.executeUpdate(sql2);
            if (count2 > 0) {
              System.out.println("Update success!");
            }

            try (ResultSet rs1 = stmt.executeQuery(sql3)) {
              while (rs1.next()) {
                System.out.println(rs1.getInt("id") + " " + rs1.getDouble("money"));
              }
            }

            try (ResultSet rs2 = pstmt.executeQuery()) {
              if (rs2.next()) {
                System.out.println("Login success!");
              } else {
                System.out.println("Login failed!");
              }
            }

            // 提交事务
            conn.commit();
          } catch (SQLException e) {
            // 回滚事务
            conn.rollback();

            e.printStackTrace();
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    ```

*   数据库连接池：
    *   数据库连接池是个容器，负责分配、管理数据库连接（Connection）。它允许应用程序重复使用一个现有的数据库连接，而不是再重新建立一个。释放空闲时间超过最大空闲时间的数据库连接来避免因为没有释放数据库连接而引起的数据库连接遗漏。
    *   好处：资源重用；提升系统响应速度；避免数据库连接遗漏。
    *   数据库连接实现：DataSource（标准接口），由官方（Sun公司）提供的数据库连接池标准接口，由第三方组织实现此接口。常见数据库连接池实现：DBCP、C3P0、Druid等等。
    *   Druid快速入门：
        *   导入druid.jar依赖。
        *   编写druid.properties配置文件：

            ```properties
            # 数据库连接配置
            driverClassName=com.mysql.cj.jdbc.Driver
            url=jdbc:mysql://localhost:3306/testdb?useSSL=false&useServerPrepStmts=true&charsetEncoding=utf8mb4&serverTimezone=UTC
            username=root
            password=123456

            # 连接池配置
            # 初始化连接池数量
            initialSize=5
            # 最大连接数
            maxActive=10
            # 最大等待时间
            maxWait=3000
            ```

        *   示例：

            ```java
            public class Main {
              public static void main(String[] args) {
                Connection conn = null;

                try {
                  // 加载配置文件
                  // druid.properties 文件需要放在 src/main/resources 目录下
                  Properties prop = new Properties();
                  try (InputStream is = Main.class.getClassLoader().getResourceAsStream("druid.properties")) {
                    if (is == null) {
                      throw new RuntimeException("Can't find druid.properties!");
                    }
                    prop.load(is);
                  }

                  // 获取连接池对象
                  DataSource ds = DruidDataSourceFactory.createDataSource(prop);

                  // 获取数据库连接
                  try (Connection connection = ds.getConnection()) {
                    conn = connection;
                    System.out.println(conn);
                  }
                } catch (RuntimeException e) {
                  e.printStackTrace();
                } catch (Exception e) {
                  e.printStackTrace();
                } finally {
                  if (conn != null) {
                    try {
                      if (!conn.isClosed()) {
                        conn.close();
                      }
                    } catch (Exception e) {
                      e.printStackTrace();
                    }
                  }
                }
              }
            }
            ```

## Maven

Maven简介：

*   Maven是专门用于管理和构建Java项目的工具，提供一套了标准化的项目结构、提供了一套标准化的构建流程（编译、测试、打包、发布）、提供了一套依赖管理机制。
*   Maven项目的标准结构：所有IDE创建Maven项目都基于此结构。
    *   单模块项目：

        ```txt
        maven-project            # 项目名称
        |-- src                  # 源代码和测试代码目录
        |   |-- main             # 源代码目录
        |   |   |-- java         # 源代码 Java 文件目录
        |   |   |-- resources    # 源代码配置和资源文件目录
        |   |   |-- webapp       # Web 项目目录
        |   |-- test             # 测试代码目录
        |       |-- java         # 测试代码 Java 文件目录
        |       |-- resources    # 测试代码配置和资源文件目录
        |-- pom.xml              # 项目描述文件
        ```

    *   多模块项目：

        ```txt
        maven-project            # 项目名称
        |-- module1              # 模块 1
        |   |-- src              
        |   |   |-- main           
        |   |   |   |-- java       
        |   |   |   |-- resources  
        |   |   |   |-- webapp      
        |   |   |-- test          
        |   |       |-- java      
        |   |       |-- resources  
        |   |-- pom.xml          # 子模块描述文件 1
        |-- module2              # 模块 2
        |   |-- src                  
        |   |   |-- main             
        |   |   |   |-- java         
        |   |   |   |-- resources
        |   |   |   |-- webapp      
        |   |   |-- test            
        |   |       |-- java        
        |   |       |-- resources   
        |   |-- pom.xml          # 子模块描述文件 2
        |-- pom.xml              # 项目描述文件
        ```

*   Maven命令：
    *   mvn clean：清理目标（target）目录。
    *   mvn compile：编译源代码。
    *   mvn test：运行测试代码。
    *   mvn package：打包项目。
    *   mvn install：安装项目到本地仓库。
    *   mvn deploy：将项目发布到远程仓库。
    *   mvn spring-boot:run：运行SpringBoot项目。
    *   mvn -P dev spring-boot:run：运行SpringBoot项目，并指定运行环境为开发环境。
    *   mvn -P prod clean package：使用生产环境配置打包项目。
*   项目描述文件示例（pom.xml）：

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
            http://maven.apache.org/xsd/maven-4.0.0.xsd">

      <!-- 项目坐标 - 唯一标识符 -->
      <modelVersion>4.0.0</modelVersion>
      <groupId>com.example</groupId>      <!-- 组织或公司域名反写 -->
      <artifactId>my-project</artifactId> <!-- 项目名称 -->
      <version>1.0.0-SNAPSHOT</version>   <!-- 版本号，SNAPSHOT 表示开发版本 -->
      <packaging>jar</packaging>          <!-- 打包方式：jar、war、pom 等 -->

      <!-- 项目名称和描述 -->
      <name>My Project</name>
      <description>My Project Description</description>
      <url>http://www.example.com</url>

      <!-- 属性定义 - 可在 POM 中任何地方使用 ${property.name} 引用 -->
      <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
        <spring.boot.version>3.5.3</spring.boot.version>
        <junit.version>5.10.1</junit.version>
        <db.host>localhost</db.host>
        <db.port>3306</db.port>
        <db.name>testdb</db.name>
      </properties>

      <!-- 依赖管理 - 定义版本但不引入依赖 -->
      <dependencyManagement>
        <dependencies>
          <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring.boot.version}</version>
            <type>pom</type>
            <scope>import</scope>  <!-- 导入 Spring Boot 的依赖管理 -->
          </dependency>
        </dependencies>
      </dependencyManagement>

      <!-- 项目依赖 -->
      <dependencies>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
          <!-- 版本由 dependencyManagement 控制，无需在此指定 -->
        </dependency>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>  <!-- test 说明该依赖项仅在测试时加载 -->
        </dependency>
        <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>8.0.29</version>  <!-- 未在 dependencyManagement 中管理的需指定版本 -->
        </dependency>
        <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <version>1.18.24</version>
          <scope>provided</scope>  <!-- provided 说明该依赖项不需要在运行时加载 -->
        </dependency>
        <dependency>
          <groupId>org.junit.jupiter</groupId>
          <artifactId>junit-jupiter</artifactId>
          <version>${junit.version}</version>
          <scope>test</scope>
        </dependency>
        <dependency>
          <groupId>org.apache.commons</groupId>
          <artifactId>commons-lang3</artifactId>
          <version>3.12.0</version>
        </dependency>
      </dependencies>

      <!-- 构建配置 -->
      <build>
        <!-- 插件管理 - 定义插件的版本但不引入插件 -->
        <pluginManagement>
          <plugins>
            <plugin>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-maven-plugin</artifactId>
              <version>${spring.boot.version}</version>
            </plugin>
          </plugins>
        </pluginManagement>

        <!-- 使用的插件 -->
        <plugins>
          <!-- 编译器插件 -->
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.10.1</version>
            <!-- 配置项 - 配置该插件的 -->
            <configuration>
              <source>${java.version}</source>
              <target>${java.version}</target>
              <encoding>${project.build.sourceEncoding}</encoding>
              <!-- 注解处理器路径 - 定义在编译过程中需要使用的注解处理器，确保这些处理器在编译时可用，例如 Lombok、MapStruct 等 -->
              <annotationProcessorPaths>
                <path>
                  <groupId>org.projectlombok</groupId>
                  <artifactId>lombok</artifactId>
                  <version>1.18.24</version>
                </path>
              </annotationProcessorPaths>
            </configuration>
          </plugin>
          <!-- 资源处理插件 -->
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-resources-plugin</artifactId>
            <version>3.2.0</version>
            <configuration>
              <encoding>${project.build.sourceEncoding}</encoding>
            </configuration>
          </plugin>
          <!-- SpringBoot 插件 - 用于打包可执行 jar 包 -->
          <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
              <!-- 主类 - 项目的入口类 -->
              <mainClass>com.example.Application</mainClass>
              <!-- 排除项 - 告诉 SpringBoot 忽略某些依赖项，这些依赖项在运行时不需要加载 -->
              <excludes>
                <exclude>
                  <groupId>org.projectlombok</groupId>
                  <artifactId>lombok</artifactId>
                </exclude>
              </excludes>
            </configuration>
            <!-- 执行项 - 告诉 Maven 在执行时执行哪些目标 -->
            <executions>
              <execution>
                <!-- 目标项 - 要执行的目标 -->
                <goals>
                  <goal>repackage</goal>  <!-- 重新打包为可执行jar -->
                </goals>
              </execution>
            </executions>
          </plugin>
          <!-- 单元测试插件 -->
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.22.2</version>
            <configuration>
              <!-- 包含项 -->
              <includes>
                <include>**/*Test.java</include>  <!-- 包含的测试类 -->
              </includes>
            </configuration>
          </plugin>
        </plugins>

        <!-- 资源文件配置 -->
        <resources>
          <resource>
            <directory>src/main/resources</directory>
            <!-- 是否过滤资源文件 - 会将资源文件中的占位符替换为对应的值，例如 application.properties 文件中的 database.url=jdbc:mysql://${db.host}:${db.port}/${db.name} 会被当前 pom.xml 文件中 properties 属性下的 db.host、db.port、db.name 值替换 -->
            <filtering>true</filtering>
            <includes>
              <include>**/*.properties</include>
              <include>**/*.xml</include>
            </includes>
          </resource>
        </resources>

        <!-- 测试资源文件配置 -->
        <testResources>
          <testResource>
            <directory>src/test/resources</directory>
            <filtering>true</filtering>
          </testResource>
        </testResources>
      </build>

      <!-- 报告配置 - 生成项目报告的配置，这些报告提供了项目的各种统计信息、文档和质量指标 -->
      <reporting>
        <plugins>
          <!-- 单元测试报告 - 测试通过率统计、测试执行时间、失败的测试用例详情等 -->
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-report-plugin</artifactId>
            <version>2.22.2</version>
          </plugin>
          <!-- 生成项目的 Java API 文档 - 从源代码注释生成 HTML 文档、包含类、方法、参数的详细说明等 -->
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-javadoc-plugin</artifactId>
            <version>3.4.0</version>
          </plugin>
        </plugins>
      </reporting>

      <!-- 环境配置 -->
      <profiles>
        <!-- 开发环境配置 -->
        <profile>
          <id>dev</id>  <!-- 环境标识 -->
          <properties>
            <env>development</env>  <!-- 环境名称 -->
          </properties>
          <activation>
            <activeByDefault>true</activeByDefault>  <!-- 默认激活 -->
          </activation>
        </profile>
        <!-- 测试环境配置 -->
        <profile>
          <id>test</id>
          <properties>
            <env>test</env>
          </properties>
        </profile>
        <!-- 生产环境配置 -->
        <profile>
          <id>prod</id>
          <properties>
            <env>production</env>
          </properties>
          <build>
            <plugins>
              <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                  <compilerArgs>
                    <arg>-Xlint:all</arg>  <!-- 生产环境启用所有警告 -->
                  </compilerArgs>
                </configuration>
              </plugin>
            </plugins>
          </build>
        </profile>
      </profiles>

      <!-- 许可证信息 -->
      <licenses>
        <license>
          <name>Apache License, Version 2.0</name>
          <url>https://www.apache.org/licenses/LICENSE-2.0.txt</url>
          <distribution>repo</distribution>
        </license>
      </licenses>

      <!-- 开发者信息 -->
      <developers>
        <developer>
          <id>developer1</id>
          <name>xish</name>
          <email>xish@example.com</email>
          <organization>Example Inc.</organization>
          <roles>
            <role>Architect</role>
            <role>Developer</role>
          </roles>
        </developer>
      </developers>

      <!-- SCM（源代码管理）配置 - 描述项目的源代码管理信息，例如 Git 仓库地址 -->
      <scm>
        <connection>scm:git:https://github.com/example/my-project.git</connection>
        <developerConnection>scm:git:https://github.com/example/my-project.git</developerConnection>
        <url>https://github.com/example/my-project</url>
      </scm>

      <!-- 发布管理 - 发布项目到远程仓库时的配置 -->
      <distributionManagement>
        <!-- 正式版本仓库 -->
        <repository>
          <id>nexus-releases</id>
          <name>Nexus Release Repository</name>
          <url>http://nexus.example.com/repository/maven-releases/</url>
        </repository>
        <!-- 快照版本仓库 -->
        <snapshotRepository>
          <id>nexus-snapshots</id>
          <name>Nexus Snapshot Repository</name>
          <url>http://nexus.example.com/repository/maven-snapshots/</url>
        </snapshotRepository>
      </distributionManagement>
    </project>
    ```

## MyBatis

MyBatis简介：

*   MyBatis是一个持久层框架，它对JDBC进行了封装，提供了一组接口，简化了JDBC操作。
*   JavaEE具有三层结构：表现层、业务层和持久层。
    *   表现层：用于显示数据、接收用户输入的数据。
    *   业务层：处理业务逻辑，调用持久层。
    *   持久层：负责与数据库进行交互，执行SQL语句，返回执行结果。
*   MyBatis中参数匹配通过ParamNameResolver类来实现，它负责解析Mapper接口方法中的参数并将其转换为MyBatis可以使用的形式。
    *   参数命名规范：

        ```java
        User getUserByIdAndName(@Param("id") Integer id, @Param("name") String name);
        ```

        ```xml
        <select id="getUserByIdAndName" resultType="User">
          select * from users where id = #{id} and name = #{name};
        </select>
        ```

    *   单个参数处理：

        ```java
        // 单个基本类型参数
        User getUserById(Integer id);  // XML 中可以直接使用 #{id}

        // 单个 JavaBean 参数
        int addUser(User user);  // XML 中可以使用 ${name}, ${password}

        // 单个 Map 参数
        List<User> getUsersByMap(Map<String, Object> params);
        ```

    *   多个参数处理：

        ```java
        // 多个参数必须使用 @Param 注解，否则 MyBatis 会使用默认名称（param1，param2...）
        List<User> getUsersByConditions(
          @Param("name") String name,
          @Param("minAge") Integer minAge,
          @Param("maxAge") Integer maxAge
        );
        ```

*   注意：
    *   当有多个参数时，始终使用@Param注解为多个参数命名。
    *   当参数过多时，使用JavaBean代替多个参数。
    *   在业务层进行参数校验，而非依赖MyBatis。
    *   启用MyBatis自带的日志功能，以便调试错误。
*   示例：
    *   创建数据库表：

        ```sql
        drop database if exists testdb;
        create database testdb;

        use testdb;

        drop table if exists users;
        create table users(
          id int primary key auto_increment,
          username varchar(20) not null,
          password varchar(20) not null,
          gender char(1) not null,
          company_name varchar(30) not null,
          company_address varchar(50) not null
        );

        insert into users values (1, "张三", "123456", "男", "阿里巴巴", "杭州市滨江区网商路699号");
        insert into users values (2, "李四", "456789", "女", "华为", "深圳市龙岗区冲之大道深圳华为坂田园区");
        insert into users values (3, "王五", "147258", "男", "京东", "北京市亦庄经济开发区科创十一街18号院");
        ```

    *   项目结构：

        ```txt
        maven-project
        |-- src
        |   |-- main
        |   |   |-- java
        |   |   |   |-- com.example
        |   |   |   |   |-- mapper
        |   |   |   |   |   |-- UserMapper.java
        |   |   |   |   |-- pojo
        |   |   |   |   |   |-- User.java
        |   |   |   |   |-- Main.java
        |   |   |-- resources
        |   |       |-- com.example.mapper
        |   |       |   |-- UserMapper.xml
        |   |       |-- logback.xml
        |   |       |-- mybatis-config.xml
        |   |-- test
        |       |-- java
        |           |-- com.example.test
        |               |-- TestMyBatis.java
        |-- pom.xml
        ```

    *   向pom.xml中添加依赖：

        ```xml
        <!-- MyBatis 依赖 -->
        <dependency>
          <groupId>org.mybatis</groupId>
          <artifactId>mybatis</artifactId>
          <version>3.5.6</version>
        </dependency>
        <!-- MySQL 驱动 -->
        <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>8.0.22</version>
        </dependency>
        <!-- JUnit 测试 -->
        <dependency>
          <groupId>org.junit.jupiter</groupId>
          <artifactId>junit-jupiter-api</artifactId>
          <version>5.7.0</version>
          <scope>test</scope>
        </dependency>
        <!-- Logback 日志 -->
        <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-api</artifactId>
          <version>1.7.20</version>
        </dependency>
        <dependency>
          <groupId>ch.qos.logback</groupId>
          <artifactId>logback-classic</artifactId>
          <version>1.2.3</version>
          <scope>compile</scope>
        </dependency>
        <dependency>
          <groupId>ch.qos.logback</groupId>
          <artifactId>logback-core</artifactId>
          <version>1.2.3</version>
        </dependency>
        ```

    *   编写Logback配置文件（logback.xml）：

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <configuration>
          <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
              <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
            </encoder>
          </appender>

          <logger name="com.example" level="DEBUG" additivity="false">
            <appender-ref ref="Console" />
          </logger>
        </configuration>
        ```

    *   编写MyBatis配置文件（mybatis-config.xml）：

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
        <configuration>
          <typeAliases>
            <!-- 配置某个类的别名 -->
            <!-- <typeAlias alias="user" type="com.example.pojo.User" /> -->
            <!-- 批量配置某个包下所有类的别名 -->
            <package name="com.example.pojo" />
          </typeAliases>

          <environments default="development">
            <environment id="development">
              <transactionManager type="JDBC" />
              <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver" />
                <property name="url" value="jdbc:mysql://localhost:3306/testdb?useSSL=false&amp;serverTimezone=Asia/Shanghai" />
                <property name="username" value="root" />
                <property name="password" value="123456" />
              </dataSource>
            </environment>
          </environments>

          <mappers>
            <!-- 加载某个映射文件 -->
            <!-- <mapper resource="com/example/mapper/UserMapper.xml" /> -->
            <!-- 加载某个包下的所有映射文件 -->
            <package name="com.example.mapper" />
          </mappers>
        </configuration>
        ```

    *   编写User实体类：

        ```java
        public class User {
          private Integer id;
          private String username;
          private String password;
          private String gender;
          private String companyName;
          private String companyAddress;

          public Integer getId() {
            return id;
          }

          public void setId(Integer id) {
            this.id = id;
          }

          public String getUsername() {
            return username;
          }

          public void setUsername(String username) {
            this.username = username;
          }

          public String getPassword() {
            return password;
          }

          public void setPassword(String password) {
            this.password = password;
          }

          public String getGender() {
            return gender;
          }

          public void setGender(String gender) {
            this.gender = gender;
          }

          public String getCompanyName() {
            return companyName;
          }

          public void setCompanyName(String companyName) {
            this.companyName = companyName;
          }

          public String getCompanyAddress() {
            return companyAddress;
          }

          public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
          }

          @Override
          public String toString() {
            return "User{" +
                "id=" + id + ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                '}';
          }
        }
        ```

    *   创建UserMapper接口：

        ```java
        public interface UserMapper {
          List<User> selectAllUsers();

          User selectUserById(Integer id);

          /**
           * 使用注解来映射简单语句会使代码显得更加简洁，但对于稍微复杂一点的语句，
           * Java注解不仅力不从心，还会让你本就复杂的 SQL 语句显得混乱不堪，因此
           * 对于复杂的操作还是使用 XML 来映射，不推荐使用注解来映射
           * @Select 查询 @Insert 插入 @Update 修改 @Delete 删除
           */
          @Select("select * from users where id = #{id}")
          User selectUserByIdWithAnnotation(Integer id);

          List<User> selectUserByCondition1(@Param("username") String username,
              @Param("companyName") String companyName,
              @Param("companyAddress") String companyAddress);

          List<User> selectUserByCondition2(User user);

          List<User> selectUserByCondition3(Map<String, Object> params);

          List<User> selectUserByCondition4(User user);

          List<User> selectUserByCondition5(User user);

          void addUser(User user);

          int addUserAndReturnId(User user);

          void updateUser1(User user);

          void updateUser2(User user);

          void deleteUserById(Integer id);

          void deleteUserByIds(@Param("ids") List<Integer> ids);
        }
        ```

    *   创建UserMapper.xml文件：

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
        <mapper namespace="com.example.mapper.UserMapper">
          <!-- 定义 sql 片段 -->
          <sql id="userColumns">
            id, username, password, gender, company_name as companyName, company_address as companyAddress
          </sql>

          <!-- 定义 resultMap -->
          <resultMap id="userResultMap" type="user">
            <!--
              id：完成主键映射
                column：数据库字段名
                property：实体类属性名
              result：完成普通字段映射
            -->
            <!-- <id column="user_id" property="userId" /> -->
            <result column="company_name" property="companyName" />
            <result column="company_address" property="companyAddress" />
          </resultMap>

          <!-- 查询所有用户信息 1：会出现部分数据为 null 的情况 -->
          <!--
          <select id="selectAllUsers" resultType="user">
            select * from users;
          </select>
          -->

          <!-- 查询所有用户信息 2：使用 sql 片段解决 null 值问题 -->
          <!--
          <select id="selectAllUsers" resultType="user">
            select <include refid="userColumns" /> from users;
          </select>
          -->

          <!-- 查询所有用户信息 3：使用 resultMap 解决 null 值问题，推荐 -->
          <select id="selectAllUsers" resultMap="userResultMap">
            select * from users;
          </select>

          <!-- 根据 id 查询用户信息 -->
          <select id="selectUserById" resultMap="userResultMap">
            <!--
              #{}：表示参数占位符，会替换成 '?'，防止 SQL 注入攻击，参数
              传递时使用 #{}
              ${}：表示参数占位符，拼接 SQL，会存在 SQL 注入问题，但是
              有时候表名或字段名不固定时，必须使用 ${}，如：${tableName}
            -->
            select * from users where id = #{id};
          </select>

          <!-- 多条件查询 1：使用散装参数，会因为某个参数为 null 导致 SQL 错误 -->
          <select id="selectUserByCondition1" resultMap="userResultMap">
            select * from users where
            username = #{username}
            and company_name like #{companyName}
            and company_address like #{companyAddress};
          </select>

          <!-- 多条件查询 2：使用对象参数，会因为某个参数为 null 导致 SQL 错误 -->
          <select id="selectUserByCondition2" resultMap="userResultMap">
            select * from users where
            username = #{username}
            and company_name like #{companyName}
            and company_address like #{companyAddress};
          </select>

          <!-- 多条件查询 3：使用 Map 参数，会因为某个参数为 null 导致 SQL 错误 -->
          <select id="selectUserByCondition3" resultMap="userResultMap">
            select * from users where
            username = #{username}
            and company_name like #{companyName}
            and company_address like #{companyAddress};
          </select>

          <!-- 多条件查询 4：使用动态 SQL 实现多条件查询，推荐 -->
          <select id="selectUserByCondition4" resultMap="userResultMap">
            select * from users
            <!-- where：防止 if 条件都不成立或者 and、or 逻辑错误 -->
            <where>
              <!-- if：用于判断参数是否有值，使用 test 属性进行条件判断 -->
              <if test="username != null and username != ''">
                and username = #{username}
              </if>
              <if test="companyName != null and companyName != ''">
                and company_name like #{companyName}
              </if>
              <if test="companyAddress != null and companyAddress != ''">
                and company_address like #{companyAddress}
              </if>
            </where>
          </select>

          <!-- 多条件查询 5：使用动态 SQL 实现多个条件但是只有一个条件成立的查询，推荐 -->
          <select id="selectUserByCondition5" resultMap="userResultMap">
            select * from users
            <where>
              <!-- choose 相当于 Java 语言中的 switch -->
              <choose>
                <!-- when 相当于 Java 语言中的 case -->
                <when test="username != null and username != ''">
                  username = #{username}
                </when>
                <when test="companyName != null and companyName != ''">
                  company_name like #{companyName}
                </when>
                <when test="companyAddress != null and companyAddress != ''">
                  company_address like #{companyAddress}
                </when>
                <!-- otherwise 相当于 Java 语言中的 default -->
                <otherwise>
                  1=1
                </otherwise>
              </choose>
            </where>
          </select>

          <!-- 添加 -->
          <insert id="addUser">
            insert into users(username, password, gender, company_name, company_address)
            values(#{username}, #{password}, #{gender}, #{companyName}, #{companyAddress});
          </insert>

          <!-- 添加后获取自增主键 -->
          <insert id="addUserAndReturnId" useGeneratedKeys="true" keyProperty="id">
            insert into users(username, password, gender, company_name, company_address)
            values(#{username}, #{password}, #{gender}, #{companyName}, #{companyAddress});
          </insert>

          <!-- 修改全部字段 -->
          <update id="updateUser1">
            update users set
            username = #{username},
            password = #{password},
            gender = #{gender},
            company_name = #{companyName},
            company_address = #{companyAddress}
            where id = #{id};
          </update>

          <!-- 修改部分字段 -->
          <update id="updateUser2">
            update users
            <set>
              <if test="username != null and username != ''">
                username = #{username},
              </if>
              <if test="password != null and password != ''">
                password = #{password},
              </if>
              <if test="gender != null and gender != ''">
                gender = #{gender},
              </if>
              <if test="companyName != null and companyName != ''">
                company_name = #{companyName},
              </if>
              <if test="companyAddress != null and companyAddress != ''">
                company_address = #{companyAddress},
              </if>
            </set>
            where id = #{id};
          </update>

          <!-- 删除一个 -->
          <delete id="deleteUserById">
            delete from users where id = #{id};
          </delete>

          <!-- 删除多个 -->
          <delete id="deleteUserByIds">
            delete from users where id in
            <foreach item="id" collection="ids" separator="," open="(" close=")">
              #{id}
            </foreach>
          </delete>
        </mapper>
        ```

    *   创建主程序：

        ```java
        public class Main {
          // 将 SqlSessionFactory 声明为静态变量，避免重复创建
          private static SqlSessionFactory sqlSessionFactory;

          // 添加日志记录器
          private static final Logger logger = LoggerFactory.getLogger(Main.class);
          
          // 静态初始化块，在类加载时初始化 SqlSessionFactory
          static {
            try {
              String resource = "mybatis-config.xml";
              InputStream inputStream = Resources.getResourceAsStream(resource);
              sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
              logger.info("SqlSessionFactory初始化成功");
            } catch (IOException e) {
              logger.error("初始化SqlSessionFactory失败", e);
              throw new RuntimeException("初始化SqlSessionFactory失败", e);
            }
          }
          
          public static void main(String[] args) {
            logger.debug("开始执行主程序");

            // 使用 try-with-resources 确保 SqlSession 正确关闭
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              // 使用 Mapper 接口而不是字符串，提供类型安全
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              List<User> users = userMapper.selectAllUsers();
              
              if (users != null && !users.isEmpty()) {
                logger.info("查询到 {} 条用户记录", users.size());
                for (User user : users) {
                  logger.debug("用户信息：{}", user);
                }
              } else {
                logger.warn("未查询到用户记录");
              }
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }

            logger.debug("主程序执行结束");
          }
        }
        ```

    *   创建测试程序：

        ```java
        public class TestMyBatis {
          private static SqlSessionFactory sqlSessionFactory;

          private static final Logger logger = LoggerFactory.getLogger(TestMyBatis.class);

          @BeforeAll
          private static void init() {
            try {
              String resource = "mybatis-config.xml";
              InputStream inputStream = Resources.getResourceAsStream(resource);
              sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
              logger.info("SqlSessionFactory初始化成功");
            } catch (IOException e) {
              logger.error("初始化SqlSessionFactory失败", e);
              throw new RuntimeException("初始化SqlSessionFactory失败", e);
            }
          }

          @Test
          public void testSelectAllUsers() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              List<User> users = userMapper.selectAllUsers();
              
              if (users != null && !users.isEmpty()) {
                logger.info("查询到 {} 条用户记录", users.size());
                for (User user : users) {
                  logger.debug("用户信息：{}", user);
                }
              } else {
                logger.warn("未查询到用户记录");
              }
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testSelectUserById() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              User user = userMapper.selectUserById(1);

              if (user != null) {
                logger.debug("用户信息：{}", user);
              } else {
                logger.warn("未查询到用户记录");
              }
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testSelectUserByIdWithAnnotation() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              User user = userMapper.selectUserByIdWithAnnotation(1);

              if (user != null) {
                logger.debug("用户信息：{}", user);
              } else {
                logger.warn("未查询到用户记录");
              }
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testSelectUserByCondition1() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              String username = "张三";
              String companyName = "%阿里%";
              String companyAddress = "%杭州%";

              List<User> users = userMapper.selectUserByCondition1(username, companyName, companyAddress);

              if (users != null && !users.isEmpty()) {
                logger.info("查询到 {} 条用户记录", users.size());
                for (User user : users) {
                  logger.debug("用户信息：{}", user);
                }
              } else {
                logger.warn("未查询到用户记录");
              }
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testSelectUserByCondition2() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              User user = new User();
              user.setUsername("张三");
              user.setCompanyName("%阿里%");
              user.setCompanyAddress("%杭州%");

              List<User> users = userMapper.selectUserByCondition2(user);
              if (users != null && !users.isEmpty()) {
                logger.info("查询到 {} 条用户记录", users.size());
                for (User u : users) {
                  logger.debug("用户信息：{}", u);
                }
              } else {
                logger.warn("未查询到用户记录");
              }
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testSelectUserByCondition3() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) { 
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              Map<String, Object> params = new HashMap<>();
              params.put("username", "张三");
              params.put("companyName", "%阿里%");
              params.put("companyAddress", "%杭州%");

              List<User> users = userMapper.selectUserByCondition3(params);
              if (users != null && !users.isEmpty()) {
                logger.info("查询到 {} 条用户记录", users.size());
                for (User user : users) {
                  logger.debug("用户信息：{}", user);
                }
              } else {
                logger.warn("未查询到用户记录");
              }
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testSelectUserByCondition4() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              User user = new User();
              user.setCompanyName("%阿里%");
              user.setCompanyAddress("%杭州%");

              List<User> users = userMapper.selectUserByCondition4(user);
              if (users != null && !users.isEmpty()) {
                logger.info("查询到 {} 条用户记录", users.size());
                for (User u : users) {
                  logger.debug("用户信息：{}", u);
                }
              } else {
                logger.warn("未查询到用户记录");
              }
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testSelectUserByCondition5() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              User user = new User();
              user.setCompanyName("%阿里%");

              List<User> users = userMapper.selectUserByCondition5(user);
              if (users != null && !users.isEmpty()) {
                logger.info("查询到 {} 条用户记录", users.size());
                for (User u : users) {
                  logger.debug("用户信息：{}", u);
                }
              } else {
                logger.warn("未查询到用户记录");
              }
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testAddUser() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              User user = new User();
              user.setUsername("赵六");
              user.setPassword("963852");
              user.setGender("女");
              user.setCompanyName("腾讯");
              user.setCompanyAddress("深圳市南山区海天二路33号腾讯滨海大厦");

              userMapper.addUser(user);
              sqlSession.commit();
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testAddUserAndReturnId() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              User user = new User();
              user.setUsername("孙七");
              user.setPassword("741852");
              user.setGender("男");
              user.setCompanyName("百度");
              user.setCompanyAddress("北京市海淀区上地十街10号百度大厦");

              int count = userMapper.addUserAndReturnId(user);
              if (count > 0) {
                sqlSession.commit();
                logger.info("添加用户成功，用户编号：{}", user.getId());
              } else {
                logger.warn("添加用户记录失败");
              }
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testUpdateUser1() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              User user = new User();
              user.setId(5);
              user.setUsername("钱八");
              user.setPassword("123456");
              user.setGender("女");
              user.setCompanyName("百度");
              user.setCompanyAddress("北京市海淀区上地十街10号百度大厦");

              userMapper.updateUser1(user);
              sqlSession.commit();
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testUpdateUser2() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              User user = new User();
              user.setId(5);
              user.setPassword("753951");
              user.setGender("男");

              userMapper.updateUser2(user);
              sqlSession.commit();
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testDeleteUserById() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

              userMapper.deleteUserById(5);
              sqlSession.commit();
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }

          @Test
          public void testDeleteUserByIds() {
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
              UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
              List<Integer> ids = new ArrayList<>();
              ids.add(4);

              userMapper.deleteUserByIds(ids);
              sqlSession.commit();
            } catch (Exception e) {
              logger.error("数据库操作失败", e);
              e.printStackTrace();
            }
          }
        }
        ```

## HTTP

HTTP：超文本传输协议，规定了浏览器和服务器之间数据传输的规则。

*   HTTP协议特点：
    *   基于TCP协议：面向连接，安全。
    *   基于请求-响应模型：一次请求对应一次响应。
    *   HTTP协议是无状态的协议，对于事务处理没有记忆能力，每次请求和响应都是独立的：速度快，但是多次请求间不能共享数据（在Java中会使用会话技术，例如Cookie、Session，来解决不能共享数据的问题）。
*   HTTP中请求数据格式：
    *   请求行：请求数据的第一行。例如`GET /index.html HTTP/1.1`，其中`GET`表示请求方式，`/index.html`表示请求的资源路径，`HTTP/1.1`表示HTTP协议版本。
    *   请求头：请求数据第二行开始，每行一个请求头，格式为`key: value`的形式。
        *   Host：请求的域名。
        *   User-Agent：浏览器版本
        *   Accept：表示浏览器能接收的资源类型。
        *   Accept-Encoding：表示浏览器能接收的压缩编码类型。
        *   Accept-Language：表示浏览器偏好的语言，服务器可以据此返回不同语言的网页。
        *   Cookie：表示浏览器的会话信息。
    *   请求体：POST请求的最后一部分，存放请求参数。只有POST请求才有，且请求头和请求体之间用一个空行隔开。
    *   GET请求和POST请求的区别：
        *   GET请求用于获取指定的资源，GET请求应该只用于获取数据，而不应产生任何“副作用”，例如修改、删除数据；POST请求用于向指定的资源提交数据，例如提交表单、上传文件等，请求服务器处理并可能创建新的资源或更新现有资源。
        *   GET请求的请求参数通过请求行传递，也就是URL的查询字符串（Query String）传递，没有请求体，例如`/index.html?username=superman&password=123456`；POST请求的请求参数可以通过请求行传递，也可以通过请求体传递，通常是通过请求体传递，例如`username=superman&password=123456`。
        *   GET请求的请求参数所能传递的数据大小受限于URL的长度限制（这个长度限制是根据浏览器和服务器配置的）；POST请求的请求参数所能传递的数据大小通常是没有限制的，但也有可能受限于服务器的配置。
        *   GET请求的请求参数会暴露在浏览器的URL栏中；POST请求的请求参数在请求体中，相对比较隐蔽。但是如果使用的是HTTP协议，两种都是没有加密的，也不是特别安全，必须使用HTTPS协议才是安全的。
    *   示例：
        *   GET请求：

            ```txt
            GET /index.html HTTP/1.1
            Host: www.example.com
            User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36
            Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
            Accept-Encoding: gzip, deflate, br
            Accept-Language: zh-CN,zh;q=0.8
            Cookie: name=value
            ```

        *   POST请求：

            ```txt
            POST /login HTTP/1.1
            Host: www.example.com
            Connection: keep-alive
            Cache-Control: max-age=0
            User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36
            
            username=superman&password=123456
            ```

*   HTTP中响应数据格式：
    *   响应行：响应数据的第一行。例如`HTTP/1.1 200 OK`，其中`HTTP/1.1`表示HTTP协议版本，`200`表示响应状态码，`OK`表示响应状态码的描述。
    *   响应头：响应数据第二行开始，每行一个响应头，格式为`key: value`的形式。
        *   Content-Type：表示响应内容的数据类型。
        *   Content-Length：表示响应内容的长度（字节数）。
        *   Content-Encoding：表示响应内容被压缩的编码类型。
        *   Cache-Control：指示客户端应如何缓存，例如`max-age=300`，表示缓存该资源最多300秒。
        *   Set-Cookie：表示服务器设置会话信息，例如`name=value; Max-Age=3600; Path=/`，表示设置会话信息，名称为name，值为value，最大有效时间为1小时，路径为/。
    *   响应体：响应数据的最后一部分，存放响应数据。响应头和响应体之间用一个空行隔开。
    *   响应状态码：
        *   `1xx`：信息性状态码，表示请求已被接收，需要继续处理。这类响应是临时性的，只包含状态行和某些可选的响应头信息，通常不会在浏览器中直接看到，因为客户端和服务器之间的握手过程对用户是透明的。
        *   `2xx`：成功状态码，表示请求已被服务器成功接收、理解并处理。
        *   `3xx`：重定向状态码，表示需要客户端采取进一步的操作才能完成请求，通常用于临时重定向或永久重定向。
        *   `4xx`：客户端错误状态码，表示客户端看起来可能发生了错误，妨碍了服务器的处理，错误原因在客户端，例如语法错误、权限不足或请求无效等。
        *   `5xx`：服务器错误状态码，表示服务器在处理请求时发生了错误，错误原因在服务器，例如服务器内部错误、服务不可用、请求超时等。
    *   示例：

        ```txt
        HTTP/1.1 200 OK
        Server: nginx
        Content-Type: text/html; charset=utf-8
        Transfer-Encoding: chunked

        <html>
        <head>
          <title>Hello World</title>
        </head>
        <body>
        </body>
        </html>
        ```

## Apache Tomcat

JavaEE（Java Enterprise Edition，Java企业版）：是Java企业级开发的技术规范总和。

*   在JavaEE 8及之前的版本中，其核心规范大致上可以划分为13项，随着版本迭代和演进，有些规范被合并、有些规范被剔除、也有一些新的规范被加入，但“13项”确实代表了其最经典的组成。
    *   13项规范分别是：JDBC、JNDI、EJB、RMI、JSP、Servlet、XML、JMS、Java IDL、JTS、JTA、JavaMail、JAF、JCA。
    *   之后加入的规范有：JPA、JSF、CDI、Bean Validation、JAX-RS、JAX-WS、JSON-P等。
*   现在JavaEE由Oracle移交给Eclipse基金会，重新更名为JakarteEE，所有规范中的javax包名都改为了jakarta包名。
*   注意：JavaEE只是定义了标准接口，而具体实现由各种不同的服务器厂商实现，比如Tomcat、Jetty、WebLogic、WebSphere等。

Web服务器：是一个应用程序，对HTTP协议的操作进行封装，使得程序员不必直接对协议进行操作，让Web开发更加便捷。

Tomcat：

*   Tomcat是Apache软件基金会一个核心项目，是一个开源免费的轻量级Web服务器，实现了JavaEE规定的Servlet/JSP规范。
*   Tomcat也被称为Web容器、Servlet容器。Servlet需要依赖于Tomcat才能运行。
*   Web项目结构：
    *   开发中的Maven Web项目结构：

        ```txt
        maven-web-project
        |-- src
        |   |-- main
        |   |   |-- java
        |   |   |-- resources
        |   |   |-- webapp
        |   |       |-- html
        |   |       |-- css
        |   |       |-- js
        |   |       |-- images
        |   |       |-- WEB-INF         # Web 项目核心目录
        |   |           |-- web.xml     # Web 项目配置文件
        |   |-- test
        |       |-- java
        |       |-- resources
        |-- pom.xml
        ```

    *   开发完成后可部署的Maven Web项目结构：

        ```txt
        maven-web-project
        |-- html
        |-- css
        |-- js
        |-- images
        |-- WEB-INF
            |-- classes  # 存放编译后的 Java 字节码文件和 resources 内的资源文件
            |-- lib      # 存放第三方依赖 jar 包
            |-- web.xml
        ```

## Servlet

Servlet：是Java提供的一门动态Web资源开发技术。

*   Servlet是JavaEE规范之一，它其实是一个接口，将来由程序员定义Servlet实现类，实现接口中的方法，并由Web服务器（例如Tomcat）来运行。
*   Servlet执行流程：浏览器发送请求到服务器（例如Tomcat），服务器按照URL路径找到对应的Servlet，并调用Servlet的service方法，将请求转发给Servlet处理，Servlet处理完毕后将响应数据返回给浏览器。
*   Servlet的生命周期：Servlet运行在Setvlet容器（例如Tomcat）中，其生命周期由容器管理，分为4个阶段。
    1.  加载和实例化：默认情况下，当Servlet第一次被访问时，由容器创建Servlet对象。
    2.  初始化：在Servlet实例化之后，容器将调用Servlet的init方法初始化这个对象，完成一些如加载配置文件、创建连接等初始化的工作，该init方法只会执行一次。
    3.  请求处理：每次请求Servlet时，Servlet容器都会调用Servlet的service方法对请求进行处理。
    4.  服务终止：当需要释放内存或者关闭容器时，容器就会调用Servlet实例的destroy方法完成资源的释放。在destroy方法调用之后，容器会释放这个Servlet实例，该实例随后会被Java的垃圾回收器所回收。
*   urlPattern匹配规则：

    ```java
    // 匹配数量：一个
    @WebServlet(urlPattern = "/hello")

    // 匹配数量：多个
    @WebServlet(urlPatterns = {"/hello", "/world"})

    // 匹配规则：精准匹配
    @WebServlet(urlPattern = "/user/add")

    // 匹配规则：目录匹配
    @WebServlet(urlPattern = "/user/*")

    // 匹配规则：扩展名匹配
    @WebServlet(urlPattern = "*.do")

    // 匹配规则：任意匹配
    // / 和 /* 的区别：
    // 当我们的项目中的 Servlet 配置了 "/" 时，会覆盖掉 Tomcat 中的 DefaultServlet，当其他的 url-pattern 都匹配不上时都会走这个 Servlet
    // 当我们的项目中的 Servlet 配置了 "/*" 时，意味着匹配任意访问路径
    // 一般不建议使用 "/" 和 "/*"，会导致静态资源无法访问、程序逻辑错误等等
    @WebServlet(urlPattern = "/")
    @WebServlet(urlPattern = "/*")
    ```

*   快速入门：
    *   编写pom.xml：

        ```xml
        <!-- 打包方式为 war -->
        <packaging>war</packaging>

        <dependencies>
          <!-- 导入 Servlet API 依赖 -->
          <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <!-- provided 表示该依赖包只在编译和测试时有效，在运行时无效，因为 Tomcat 已经内置了该依赖包 -->
            <scope>provided</scope>
          </dependency>
        </dependencies>

        <build>
          <plugins>
            <!-- 使用 Tomcat 插件 -->
            <plugin>
              <groupId>org.apache.tomcat.maven</groupId>
              <artifactId>tomcat7-maven-plugin</artifactId>
              <version>2.2</version>
              <!-- 
                设置端口为 80，访问路径为 /
                默认端口为 8080，如果项目名为 project-name，那么默认访问路径为 /project-name
              -->
              <configuration>
                <port>80</port>
                <path>/</path>
              </configuration>
            </plugin>
          </plugins> 
        </build>
        ```

    *   编写Servlet实现类：
        1.  通过实现Servlet接口来编写实现类：

            ```java
            /**
             * urlPatterns：指定 Servlet 的访问路径
            * onloadOnStartup：指定 Servlet 在启动时加载的顺序
            *   如果值为负整数，表示第一次被访问时创建 Servlet 对象
            *   如果值为 0 或正整数，表示 Servlet 对象在 Tomcat 启动时就会被创建，数字越小，优先级越高
            * 
            * @WebServlet(urlPatterns = "/hello", loadOnStartup = 1)
            */
            @WebServlet(urlPatterns = "/hello")
            public class HelloServlet implements Servlet {
              /**
               * 初始化方法
              * 调用时机：默认情况下，loadOnStartup 值为 -1，所以 Servlet 对象在第一次被访问时创建；如果设置了 loadOnStartup 值为 0 或正整数，那么 Servlet 对象在 Tomcat 启动时就会被创建
              * 调用次数：1 次
              */
              @Override
              public void init(ServletConfig config) throws ServletException {
                System.out.println("init...");
              }

              /**
               * 提供服务
              * 调用时机：每一次 Servlet 被访问时，就会调用 service 方法
              * 调用次数：N 次
              */
              @Override
              public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
                System.out.println("service...");
              }

              /**
               * 销毁方法
              * 调用时机：当内存释放或 Tomcat 服务器停止时，Servlet 对象会被销毁
              * 调用次数：1 次
              */
              @Override
              public void destroy() {
                System.out.println("destroy...");
              }

              @Override
              public ServletConfig getServletConfig() {
                return null;
              }

              @Override
              public String getServletInfo() {
                return null;
              }
            }
            ```

        2.  通过继承HttpServlet类来编写实现类：

            ```java
            @WebServlet(urlPatterns = "/hello")
            public class HelloServlet extends HttpServlet {
              @Override
              protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                System.out.println("doGet...");
              }

              @Override
              protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                System.out.println("doPost...");
              }
            }
            ```
