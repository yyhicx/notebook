# Yaml

1.  [基本语法](#基本语法)
2.  [数据类型](#数据类型)
3.  [引用](#引用)

## 基本语法

大小写敏感。

使用缩进表示层级关系。

缩进不允许使用tab，只允许空格。

缩进的空格数不重要，只要相同层级的元素左对齐即可。

'#'表示注释。

## 数据类型

字典：

```yaml
key:
  child-key1: value1
  child-key2: value2
```

```yaml
?
  - complexkey1
  - complexkey2
:
  - complexvalue1
  - complexvalue2
```

列表：

```yaml
- A
- B
- C
```

```yaml
companies:
  -
    id: 1
    name: company1
    price: 200w
  -
    id: 2
    name: company2
    price: 500w
```

复合结构：

```yaml
languages:
  - Ruby
  - Perl
  - Python
websites:
  YAML: yaml.org
  Ruby: ruby-lang.org
  Python: python.org
  Perl: use.perl.org
```

```yaml
json:
  {
    languages: ['Ruby', 'Perl', 'Python'],
    websites: {
      YAML: 'yaml.org',
      Ruby: 'ruby-lang.org',
      Python: 'python.org',
      Perl: 'use.perl.org'
    }
  }
```

纯量：

*   字符串：

    ```yaml
    string
      - 'Hello World'
      - newline1
        newline2  # 字符串可以拆成多行，每一行会被转换为一个空格
    ```

*   布尔值：

    ```yaml
    boolean:
      - TRUE
      - FALSE
    ```

*   整数：

    ```yaml
    int:
      - 123
      - 0b1010_0111_0100_1010_1110  # 二进制
    ```

*   浮点数：

    ```yaml
    float:
      - 3.14
      - 6.8523015e+5
    ```

*   Null：

    ```yaml
    null:
      nodeName: 'node'
      parent: ~  # 使用 ~ 表示 null
    ```

*   时间：

    ```yaml
    datetime:
      - 2018-02-17T15:02:31+08:00
    ```

*   日期：

    ```yaml
    date:
      - 2018-02-17
    ```

## 引用

锚点和别名：

*   使用`&`建立锚点，`<<`合并数据，`*`引用锚点。

```yaml
defaults: &defaults
  adapter:  postgres
  host:     localhost
development:
  database: myapp_development
  <<: *defaults
test:
  database: myapp_test
  <<: *defaults
```

```yaml
Equivalent:
  defaults:
    adapter:  postgres
    host:     localhost
  development:
    database: myapp_development
    adapter:  postgres
    host:     localhost
  test:
    database: myapp_test
    adapter:  postgres
    host:     localhost
```

```yaml
- &showell Steve 
- Clark 
- Brian 
- Oren 
- *showell 
```

```yaml
json:
  [ 'Steve', 'Clark', 'Brian', 'Oren', 'Steve' ]
```
