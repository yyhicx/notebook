# Markdown

1.  [标题](#标题)
2.  [段落格式](#段落格式)
3.  [列表](#列表)
4.  [区块](#区块)
5.  [代码](#代码)
6.  [链接](#链接)
7.  [图片](#图片)
8.  [表格](#表格)
9.  [高级技巧](#高级技巧)

## 标题

一级标题：

```markdown
一级标题
=======

# 一级标题
```

二级标题：

```markdown
二级标题
-------

## 二级标题
```

三级标题：

```markdown
### 三级标题
```

四级标题：

```markdown
#### 四级标题
```

五级标题：

```markdown
##### 五级标题
```

六级标题：

```markdown
###### 六级标题
```

## 段落格式

段落：

*   段落结尾加两个空格并回车，起一个新段落。
*   段落结尾加两个回车，起一个新段落。

字体：

```markdown
*斜体文本*
_斜体文本_
**粗体文本**
__粗体文本__
___粗斜体文本___
```

分隔线：在一行中使用三个以上的星号、减号或下划线，建立分隔线。

```markdown
* * *
---
_ _ _
```

删除线：

```markdown
~~删除线文本~~
```

下划线：

```markdown
<u>下划线文本</u>
```

脚注（VSCode）：

```markdown
Markdown是一种轻量级标记语言[1]

[1]: 不支持中文，不能用空格
```

## 列表

无序列表：用星号、加号和减号作为列表标记。

有序列表：使用数字和英文句号作为列表标记。

```markdown
1.第一项

第一个嵌套

第二个嵌套

第三个嵌套

2.第二项

  - 第一个嵌套

  - 第二个嵌套
  
  - 第三个嵌套
```

## 区块

区块：

```markdown
> 第一个  
> 第二个  
> 第三个  
```

嵌套区块：

```markdown
> 第一层  
> > 第二层  
> > > 第三层  
```

列表区块：

```markdown
> + 第一项  
> - 第二项  
> * 第三项  
```

区块列表：

```markdown
+ 第一项

    > 第一层  
    > 第二层  

+ 第二项
```

## 代码

代码片段：

```markdown
`print('Hello, World')`
```

代码区块：

*   四个空格或一个制表符。
*   六个反单引号。

```markdown
    def hello():
        print('Hello, World')
```

```markdown
like this
```

## 链接

文本链接：

```markdown
[python](https://www.python.org)
```

地址链接：

```markdown
<https://www.python.org>
```

高级链接：

```markdown
[google][1]

[1]: https://www.google.com
```

## 图片

图片显示：

```markdown
![RUNOOB 图标](https://static.runoob.com/images/runoob-logo.png "RUNOOB")
```

图片尺寸：

```markdown
<img src="https://static.runoob.com/images/runoob-logo.png" width="50%">
```

## 表格

表格显示：

```markdown
| align left | align right | align center | not align |
| :--------- | ----------: | :----------: | --------- |
| cell       |        cell |     cell     | cell      |
```

## 高级技巧

HTML元素：

```markdown
<kbd>键盘文本</kbd>  
<b>粗体文本</b>  
<i>斜体文本</i>  
<em>强调文本</em>  
<sup>上标文本</sup>  
<sub>下标文本</sub>  
<br>  
```

转义字符：

```markdown
\
```

公式：用四个美元符包裹TeX或LaTex格式的数学公式。

```markdown
$$
e^{i \pi} + 1 = 0
$$
```
