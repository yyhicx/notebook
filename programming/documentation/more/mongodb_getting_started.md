# MongoDB入门

## MongoDB相关概念

MongoDB是一个开源、高性能、无模式的文档型数据库，当初设计就是用于简化开发和方便扩展，是NoSQL数据库产品的一种，也是最像关系型数据库的非关系型数据库。

它支持的数据结构非常松散，是一种类似于JSON的格式叫做BSON，所以它既可以存储比较复杂的数据类型，又相当的灵活。

传统的关系型数据库（如MySQL），在数据操作的“三高”需求以及应对Web2.0的网站需求面前，显得力不从心，而MongoDB可应对“三高”需求。

“三高”需求：

*   High Performance：对数据库高并发读写的需求。
*   Huge Storage：对海量数据的高效率存储和访问的需求。
*   High Scalability && High Availability：对数据库的高可扩展性和高可用性的需求。

MySQL和MongoDB在不同的应用场景中各有优势。MySQL适合处理需要高数据一致性和复杂查询的应用，而MongoDB则在处理大规模、高并发的非结构化数据时表现更佳。

数据库：

*   一个MongoDB中可以建立多个数据库。
*   如果在操作时没有指定数据库，MongoDB会使用一个名为`test`的默认数据库，该数据库存储在`data`目录下。

集合：

*   集合是MongoDB的文档组，类似于RDBMS中的表格。
*   集合存在于数据库中，集合没有固定的结构，这意味着你在对集合可以插入不同格式和类型的数据，但通常情况下我们插入集合的数据都会有一定的关联性。

文档：

*   文档是一组键值对（即BSON）。
*   MongoDB的文档不需要设置相同的字段，并且相同的字段不需要相同的数据类型，这与关系数据库有很大的区别，也是MongoDB非常突出的特点。

## 常用命令

注意：在创建数据库的时候，只有插入了集合，即数据库中有实际数据后，才会真正的持久化保存在磁盘中。集合也有相同的情况，必须插入数据后才真正被创建。

使用或创建数据库（如果数据库不存在会自动创建）：`use database_name`。

查看所有数据库：`show databases`。

查看当前数据库：`db`。

删除数据库：`db.dropDatabase()`。

创建集合：`db.createCollection('collection_name')`。

查看当前数据库下所有集合：`show collections`。

删除集合：`db.collection_name.drop()`。

单个文档的插入（如果集合不存在会自动创建）：`db.collection_name.insertOne()`。

多个文档的插入（如果集合不存在会自动创建）：`db.collection_name.insertMany()`。

所有文档的查询：`db.collection_name.find()`。

某些文档的查询：`db.collection_name.find({key: value})`。

首个文档的查询：`db.collection_name.findOne({key: value})`。

投影查询：`db.collection_name.find({key: value}, {field_name: 1, ...})`。

统计查询：`db.collection_name.countDocuments()`。

分页查询：`db.collection_name.find().skip(number1).limit(number2)`。

排序查询：`db.collection_name.find().sort({field_name1: 1}, {field_name2: -1}, ...)`。

正则查询：`db.collection_name.find({field_name: /regex/})`。

文档的所有字段进行替换：`db.collection_name.replaceOne({key: value}, {field_name: value, ...})`。

文档的某些字段进行修改：`db.collection_name.updateOne({key: value}, {$set: {field_name: value, ...}})`。

文档的某些字段进行批量修改：`db.collection_name.updateMany({key: value}, {$set: {field_name: value, ...}})`。

单个文档的删除：`db.collection_name.deleteOne({key: value})`。

多个文档的删除：`db.collection_name.deleteMany({key: value})`。

```javascript
// 单个文档的插入
db.users.insert({'userid': '1001', 'nickname': 'John', 'age': 25, 'sex': 'Male'});  // 不推荐
db.users.insertOne({'userid': '1002', 'nickname': 'Lisa', 'age': 32, 'sex': 'Female'});

// 多个文档的插入
db.users.insertMany([
  {'userid': '1003', 'nickname': 'Michael', 'age': 27, 'sex': 'Male'},
  {'userid': '1004', 'nickname': 'Emily', 'age': 22, 'sex': 'Female'},
  {'userid': '1005', 'nickname': 'David', 'age': 30, 'sex': 'Male'}
]);

// 某些文档的查询
db.users.find({'sex': 'Male'});

// 首个文档的查询
db.users.findOne({'sex': 'Female'});

// 投影查询
db.users.find({'sex': 'Male'}, {'nickname': 1, 'age': 1});

// 排序查询：-1 表示降序，1 表示升序
db.users.find().sort({'nickname': -1});

// 正则查询
db.users.find({'sex': /^Fe/});

// 文档的所有字段进行替换
db.users.replaceOne({'userid': '1001'}, {'userid': '1001', 'nickname': 'Bob', 'age': 27, 'sex': 'Male'});

// 文档的某些字段进行修改
db.users.updateOne({'userid': '1001'}, {$set: {'nickname': 'John', 'age': 25}});

// 文档的某些字段进行批量修改
db.users.updateMany({'sex': 'Male'}, {$set: {'age': 18}});

// 单个文档的删除
db.users.deleteOne({'nickname': 'David'});

// 多个文档的删除
db.users.deleteMany({'sex': 'Female'});
```
