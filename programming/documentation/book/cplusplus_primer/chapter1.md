# 开始

1.  [问题](#问题)

## 问题

访问main函数返回值的方法依赖于系统：

*   UNIX系统下：`echo $?`
*   Windows系统下：`echo %ERRORLEVEL%`

换行问题：

1.  有符号连接，可以直接换行
1.  字符串连接，推荐符号连接，不能使用反斜杠
1.  define宏定义，推荐反斜杠

*   src: simple_output.cc

文件重定向：

```bash
add_items <infile >outfile
```

编译：

```bash
# 指定g++编译器版本为C++11
g++ -std=c++11 -o test test.cc (g++ version is greater than or equal to 4.8)
```
