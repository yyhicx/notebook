# Spring Todo

接口分析：

1.  待办计划分页查询：

    ```txt
    需求说明：查询全部数据页数据
    请求 URI：/todo/{pageSize}/{currentPage}
    请求方式：GET
    响应 JSON：
      {
        "code": 200,
        "msg": "Data query successful",
        "data": {
          // 本页数据
          todos: [
            {id: 1, description: "Learn Java", completed: true},
            {id: 2, description: "Learn Spring", completed: true},
            {id: 3, description: "Learn HTML", completed: true},
            {id: 4, description: "Learn CSS", completed: true},
            {id: 5, description: "Learn JavaScript", completed: true}
          ],
          // 分页参数
          pageSize: 5,
          currentPage: 1,
          total: totalNumber,
        }
      }
    ```

2.  待办计划保存：

    ```txt
    需求说明：增加待办计划
    请求 URI：/todo
    请求方式：POST
    请求 JSON：
      {
        description: "Do Something",
        completed: false
      }
    响应 JSON：
      {
        "code": 200,
        "msg": "Data saved successfully",
        "data": null
      }
    ```

3.  待办计划修改：

    ```txt
    需求说明：根据 ID 修改数据
    请求 URI：/todo
    请求方式：PUT
    请求 JSON：
      {
        id: idNumber,
        description: "Do Something",
        completed: false
      }
    响应 JSON：
      {
        "code": 200,
        "msg": "Data modification successful",
        "data": null
      }
    ```

4.  待办计划删除：

    ```txt
    需求说明：根据 ID 删除待办计划
    请求 URI：/todo/{id}
    请求方式：DELETE
    响应 JSON：
      {
        "code": 200,
        "msg": "Data deletion successful",
        "data": null
      }
    ```
