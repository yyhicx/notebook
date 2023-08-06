# Google Markdown 风格指南

Markdown的出色之处主要在于能够使用纯文本书写并且得到一流的格式化输出结果。

我们想要平衡以下三个目标：

1.  *源代码具有良好的可读性和可移植性。*
2.  *Markdown文件随时间推移、在团队之间的可维护性。*
3.  *语法简单且容易记忆。*

内容：

1.  [文档布局](#文档布局)
1.  [字符行限制](#字符行限制)
1.  [尾随空格](#尾随空格)
1.  [标题](#标题)
    1.  [ATX风格的标题](#atx风格的标题)
    1.  [标题中的间隔](#标题中的间隔)
1.  [列表](#列表)
    1.  [对长列表使用“懒人编号法”](#对长列表使用懒人编号法)
    1.  [嵌套列表间距](#嵌套列表间距)
1.  [代码](#代码)
    1.  [行内代码](#行内代码)
    1.  [代码块](#代码块)
    1.  [语言声明](#语言声明)
    1.  [缩进代码块有时会更清晰易读](#缩进代码块有时会更清晰易读)
    1.  [避免换行](#避免换行)
    1.  [列表内嵌代码块](#列表内嵌代码块)
1.  [链接](#链接)
    1.  [使用具有提示性的链接标题](#使用具有提示性的链接标题)
1.  [图像](#图像)
1.  [优先使用列表](#优先使用列表)
1.  [优先使用Markdown语法](#优先使用markdown语法)

## 文档布局

一般情况下，大多数文档会采用下面几种布局：

```markdown
# Document Title

Short introduction.

[TOC]

## Topic

Content.

## See also

* https://link-to-more-info
```

1.  `# Document Title`：第一个标题应当是一个一级标题，并且应该尽可能和文件名称保持一致。第一个一级标题会被用作页面`<title>`。

1.  `author`：可选项。如果你想要说明对文档的所有权或者它是你的得意之作，就把你自己放到标题下吧，虽然版本修订记录通常就足够说明这一点了。

1.  `Short introduction`：1~3句能够概括整个主题的话。想象你自己是一个完全的新手，刚刚接触你写的《Extending Foo》，需要了解那些你自己认为理所应当的最基本的前置知识，比如“什么是Foo”，“为什么我要学习Foo”。

1.  `[TOC]`：用来生成目录。

1.  `## Topic`：你剩下的标题应该从二级标题开始使用。

## 字符行限制

尽可能遵守项目的字符行限制（个人习惯：行长度没有限制）。

```markdown
Lorem ipsum dolor sit amet, nec eius volumus patrioque cu, nec et commodo
hendrerit, id nobis saperet fuisset ius.

*   Malorum moderatius vim eu. In vix dico persecuti. Te nam saperet percipitur
    interesset. See the [foo docs](https://gerrit.googlesource.com/gitiles/+/master/Documentation/markdown.md).
```

通常在长链接前新起一行有利于可读性，并且能够最小化链接的溢出。

```markdown
Lorem ipsum dolor sit amet. See the
[foo docs](https://gerrit.googlesource.com/gitiles/+/master/Documentation/markdown.md)
for details.
```

## 尾随空格

不要使用尾随空格，用尾随的反斜杠代替。

## 标题

### ATX风格的标题

```markdown
## Heading 2
```

含有`=`或`-`下划线的标题维护起来会很讨厌，并且和其他标题语法不兼容。用户不知道`---`的意思是H1还是H2。

### 标题中的间隔

请在`#`后加空格，并和上下文保持间隔：

```markdown
...text before.

# Heading 1

Text after...
```

缺少间隔会让源码阅读起来有点困难：

```markdown
...text before.

# Heading 1
Text after...DO NOT DO THIS.
```

## 列表

### 对长列表使用“懒人编号法”

Markdown非常智能，可以让生成的HTML文件正确排列你的有序列表。对于比较长的、可能会修改的列表（尤其是很长的嵌套列表），请使用“懒人编号法”：

```markdown
1.  Foo.
1.  Bar.
    1.  Foofoo.
    1.  Barbar.
1.  Baz.
```

而对于比较短的、很少修改的有序列表，按顺序标号更好，因为这样源码读起来更容易：

```markdown
1.  Foo.
2.  Bar.
3.  Baz.
```

### 嵌套列表间距

嵌套列表时，对数字开头的列表和星号开头的列表都使用四个空格的缩进：

```markdown
1.  2 spaces after a numbered list.
    4 space indent for wrapped text.
2.  2 spaces again.

*   3 spaces after a bullet.
    4 space indent for wrapped text.
    1.  2 spaces after a numbered list.
        8 space indent for the wrapped text of a nested list.
    2.  Looks nice, don't it?
*   3 spaces after a bullet.
```

下面这种写法也能奏效，但看起来很乱：

```markdown
* One space,
with no indent for wrapped text.
     1. Irregular nesting... DO NOT DO THIS.
```

即使没有嵌套，使用四个空格的缩进也会让包含文本的布局显得更加连续：

```markdown
*   Foo,
    wrapped.

1.  2 spaces
    and 4 space indenting.
2.  2 spaces again.
```

当然，如果列表规模很小、没有嵌套、只有单行，那么单个空格也足够了：

```markdown
* Foo
* Bar
* Baz.

1. Foo.
2. Bar.
```

## 代码

### 行内代码

&#96;反引号&#96;定义了单行代码，并且会逐字逐句呈现所有包含的文本，用它来进行简短的代码和字段的引用：

```markdown
You'll want to run `really_cool_script.sh arg`.

Pay attention to the `foo_bar_whammy` field in that table.
```

只在抽象意义上指明一个文件类型时，使用单行代码而不是一个具体的文件：

```markdown
Be sure to update your `README.md`!
```

### 代码块

代码超过一行时，请使用代码块：

<pre>
```python
def Foo(self, bar):
  self.bar = bar
```
</pre>

#### 语言声明

分开进行语言声明是最好的，这样无论语法高亮器还是另外的文本编辑器都不需要瞎猜。

#### 缩进代码块有时会更清晰易读

四个空格的缩进会被翻译成代码块，这样使源码变得更加清晰易读，缺点是无法区分语言（个人习惯：使用代码块）。我们建议在写较短的片段时这样做：

```markdown
You'll need to run:

    bazel run :thing -- --foo

And then:

    bazel run :another_thing -- --bar

And again:

    bazel run :yet_again -- --baz
```

#### 避免换行

由于大多数命令行代码片段要被直接复制粘贴进终端，最好的方法是避免任何换行，在行尾加一个反斜杠即可：

<pre>
```bash
bazel run :target -- --flag --foo=longlonglonglonglongvalue \
--bar=anotherlonglonglonglonglonglonglonglonglonglongvalue
```
</pre>

#### 列表内嵌代码块

如果你要在列表中内嵌代码块，使用缩进确保它不会破坏列表：

```markdown
*   Bullet.

    ```c++
    int foo;
    ```

*   Next bullet.
```

你也可以用4个空格来创建内嵌代码块，只需要从列表缩进处额外加4个空格：

```markdown
*   Bullet.

        int foo;

*   Next bullet.
```

## 链接

尽可能缩短你的链接。

### 使用具有提示性的链接标题

Markdown链接语法允许你像HTML一样设置链接，但你要正确地使用它。

当读者快速浏览像“link”或“here”这样的链接标题时，他们完全得不到任何信息，这只是一种对空间的浪费。

```markdown
See the syntax guide for more info: [link](syntax_guide.md).
Or, check out the style guide [here](style_guide.md).
DO NOT DO THIS.
```

正确的做法应该是：先按文章的意思写好句子，再回过头找出最合适的短语，把它标记成链接。

```markdown
See the [syntax guide](syntax_guide.md) for more info.
Or, check out the [style guide](style_guide.md).
```

## 图像

尽可能少用图像，多使用简单的截图。这一建议的意思是纯文本能够让用户更快了解到信息的内容，而减少分心和来自作者的拖延。
尽管如此，有时候图片对于表明你的意思还是很有帮助的。

## 优先使用列表

任何Markdown中的表格都应该尽可能小，复杂的、大型的表格在源码中读起来很困难，接下来想修改会更痛苦。

```markdown
| Fruit | Attribute | Notes |
| ----- | --------- | ----- ||
| Apple  | [Juicy](https://example.com/SomeReallyReallyReallyReallyReallyReallyReallyReallyLongQuery), Firm, Sweet               | Apples keep doctors away.                             |
| Banana | [Convenient](https://example.com/SomeDifferentReallyReallyReallyReallyReallyReallyReallyReallyLongQuery), Soft, Sweet | Contrary to popular belief, most apes prefer mangoes. |

DO NOT DO THIS
```

列表和子标题通常足够你用来呈现相同的信息，不那么紧凑，却要容易编辑得多：

```markdown
## Fruits

### Apple

* [Juicy](https://SomeReallyReallyReallyReallyReallyReallyReallyReallyReallyReallyReallyReallyReallyReallyReallyReallyLongURL)
* Firm
* Sweet

Apples keep doctors away.

### Banana

* [Convenient](https://example.com/SomeDifferentReallyReallyReallyReallyReallyReallyReallyReallyLongQuery)
* Soft
* Sweet

Contrary to popular belief, most apes prefer mangoes.
```

尽管如此，有时候小型表格还是很有用的：

```markdown
| Transport        | Favored by     | Advantages                    |
| ---------------- | -------------- | ----------------------------- |
| Swallow          | Coconuts       | Otherwise unladen             |
| Bicycle          | Miss Gulch     | Weatherproof                  |
| X-34 landspeeder | Whiny farmboys | Cheap since the X-38 came out |
```

## 优先使用Markdown语法

尽可能使用标准Markdown语法，避免嵌入使用HTML。如果你无法实现你想要的效果，再好好考虑一下你是否真的需要它。因为除了大型表格，Markdown几乎已经可以满足任何需求。

任何HTML或Javascript的嵌入都会降低可读性和可移植性，这反过来会限制文档和其它工具整合的有效性
