# 微头条

## 微头条业务简介

用户功能：

*   注册功能。
*   登录功能。
*   jwt实现。

头条新闻：

*   新闻的分页浏览。
*   通过标题关键字搜索新闻。
*   查看新闻详情。
*   新闻的修改和删除。

## 技术栈介绍

前端技术栈：

*   ES6作为基础JS语法。
*   NodeJS用于运行环境。
*   NPM用于项目依赖管理功能。
*   Vite用于项目构建工具。
*   Vue3用于项目数据的渲染框架。
*   ElementPlus提供组件。
*   Axios用于前后端数据的交互。
*   VueRouter用于页面的跳转。
*   Pinia用于存储用户的数据。
*   LocalStorage作为用户校验token的存储手段。

后端技术栈：

*   Java作为开发语言，版本为JDK17。
*   Tomcat作为服务容器，版本为10+。
*   SpringBoot3作为项目基础架构。
*   SpringMVC用于控制层实现前后端数据交互。
*   Jackson用于转换JSON。
*   Druid用于提供数据源的连接池。
*   MyBatisPlus用于实现数据的CRUD。
*   MySql8用于项目存储数据。
*   MD5用于用户密码的加密。
*   JWT用于token的生成和校验。

## 接口分析

用户模块：

*   登录：

    ```txt
    需求说明：用户在客户端输入用户名和密码并向后端提交，后端根据用户名和密码判断登录是否成功
    请求 URI：/user/login
    请求方式：POST
    请求 Header：
    {
      "Content-Type": "application/json"
    }
    请求 JSON：
    {
      "username": "zhangsan",
      "password": "123456"
    }
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": {
        "token": "token_value"
      }
    }
    {
      "code": 501,
      "message": "username error",
      "data": null
    }
    {
      "code": 503,
      "message": "password error",
      "data": null
    }
    ```

*   获取用户数据：

    ```txt
    需求说明：客户端发送请求，请求头中携带Token，后端根据Token获取登录用户的详细信息并返回给客户端进行存储
    请求 URI：/user/getUserInfo
    请求方式：GET
    请求 Header：
    {
      "Authorization": "Bearer token_value"
    }
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": {
        "loginUser": {
          "uid": 1,
          "username": "zhangsan",
          "nickName": "张三"
        }
      }
    }
    {
      "code": 504,
      "message": "not login",
      "data": null
    }
    ```

*   注册：

    ```txt
    需求说明：客户端将新用户信息发送给服务端，服务端将新用户存入数据库，存入之前做用户名是否被占用校验，校验通过后返回响应成功
    请求 URI：/user/register
    请求方式：POST
    请求 Header：
    {
      "Content-Type": "application/json"
    }
    请求 JSON：
    {
      "username": "zhangsan",
      "password": "123456",
      "nickName": "张三"
    }
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": null
    }
    {
      "code": 505,
      "message": "username used",
      "data": null
    }
    ```

*   重复名查询：

    ```txt
    需求说明：用户在注册时或修改用户名时，后端根据用户名查询用户是否存在，并返回是否可用给客户端
    请求 URI：/user/checkUsername
    请求方式：POST
    请求 Header：
    {
      "Content-Type": "application/x-www-form-urlencoded"
    }
    请求 Param：
    username=zhangsan
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": null
    }
    {
      "code": 505,
      "message": "username used",
      "data": null
    }
    ```

首页模块：

*   查询首页分类：

    ```txt
    需求说明：进入网站首页，查询所有分类
    请求 URI：/portal/findAllTypes
    请求方式：GET
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": {
        "types": [
          {
            "tid": 1,
            "tname": "新闻"
          },
          {
            "tid": 2,
            "tname": "体育"
          },
          {
            "tid": 3,
            "tname": "娱乐"
          },
          {
            "tid": 4,
            "tname": "科技"
          },
          {
            "tid": 5,
            "tname": "其他"
          }
        ]
      }
    }
    ```

*   查询首页头条信息：

    ```txt
    需求说明：客户端向服务端发送查询关键字、新闻类别、页码数和页大小等，根据条件查询页面信息
    请求 URI：/portal/findHeadlines
    请求方式：POST
    请求 Header：
    {
      "Content-Type": "application/json"
    }
    请求 JSON：
    {
      "keywords": "北京",  // 关键字
      "type": 1,  // 头条类别
      "pageNum": 1,
      "pageSize": 10
    }
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": {
        "pageInfo": {
          "pageData": [
            {
              "hid": 2,
              "title": "北京连续三天最高温超40℃，6月“炎值”因何爆表？",
              "type": 1,
              "pageViews": 0,
              "pastHours": past_hours_value,
              "publisher": 1
            }
          ],
          "pageNum": 1,  //页码数
          "pageSize": 10,  // 页大小
          "totalPage": 20,  // 总页数
          "totalSize": 200  // 总记录数
        }
      }
    }
    ```

*   查询头条详情：

    ```txt
    需求说明：客户端向服务器端发送头条id，后端根据头条id查询完整新闻文章信息并返回，浏览量+1
    请求 URI：/portal/findHeadlineDetail
    请求方式：POST
    请求 Header：
    {
      "Content-Type": "application/x-www-form-urlencoded"
    }
    请求 Param：
    hid=1
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": {
        "headlineDetail": {
          "hid": 1,
          "title": "title_value",
          "article": "article_value",
          "type": 1,
          "typeName": "新闻",
          "pageViews": "page_views_value",
          "pastHours": "past_hours_value",
          "publisher": 1,
          "author": "张三"
        }
      }
    }
    ```

头条模块：

*   登录验证和保护：

    ```txt
    需求说明：客户端在进入发布页前、发布新闻前、进入修改页前、修改前、删除新闻前先向服务端发送请求携带Token请求头，后端接收Token请求头后，校验用户登录是否过期并做响应
    前端需要获取Token，来判断页面是否跳转：
      请求 URI：/user/checkLogin
      请求方式：GET
      请求 Header：
      {
        "Authorization": "Bearer token_value"
      }
      响应 JSON：
      {
      "code": 200,
      "message": "success",
      "data": null
      }
      {
        "code": 504,
        "message": "not login",
        "data": null
      }
    拦截器：所有/headline开头的请求都先执行登录保护拦截器
    ```

*   头条发布：

    ```txt
    需求说明：用户在客户端输入发布的新闻信息完毕后，发布前先请求后端的登录校验接口验证登录（拦截器实现），登录通过则提交新闻信息，后端将新闻信息存入数据库
    请求 URI：/headline/publish
    请求方式：POST
    请求 Header：
    {
      "Content-Type": "application/json",
      "Authorization": "Bearer token_value"
    }
    请求 JSON：
    {
      "title": "title_value",
      "article": "article_value",
      "type": 1
    }
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": null
    }
    {
      "code": 504,
      "message": "not login",
      "data": null
    }
    ```

*   修改头条回显：

    ```txt
    需求说明：客户端先调用登录校验接口，校验登录是否过期（拦截器实现），登录校验通过后，则根据新闻id查询新闻的完整信息并响应给前端
    请求 URI：/headline/findHeadlineByHid
    请求方式：POST
    请求 Header：
    {
      "Content-Type": "application/x-www-form-urlencoded",
      "Authorization": "Bearer token_value"
    }
    请求 Param：  
    hid=1
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": {
        "headline": {
          "hid": 1,
          "title": "title_value",
          "article": "article_value",
          "type": 1
        }
      }
    }
    ```

*   头条修改实现：

    ```txt
    需求说明：客户端先调用登录校验接口，校验登录是否过期（拦截器实现），登录校验通过则提交修改后的新闻信息，后端接收并更新进入数据库
    请求 URI：/headline/update
    请求方式：POST
    请求 Header：
    {
      "Content-Type": "application/json",
      "Authorization": "Bearer token_value"
    }
    请求 JSON：
    {
      "hid": 1,
      "title": "title_value",
      "article": "article_value",
      "type": 1
    }
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": null
    }
    ```

*   删除头条：

    ```txt
    需求说明：将要删除的新闻id发送给服务端，服务端校验登录是否过期，未过期则直接删除，过期则响应登录过期信息（拦截器实现）
    请求 URI：/headline/removeByHid
    请求方式：POST
    请求 Header：
    {
      "Content-Type": "application/x-www-form-urlencoded",
      "Authorization": "Bearer token_value"
    }
    请求 Param：
    hid=1
    响应 JSON：
    {
      "code": 200,
      "message": "success",
      "data": null
    }
    ```
