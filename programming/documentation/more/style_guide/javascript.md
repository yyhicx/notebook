# Google JavaScript 风格指南

## 引言

本文档是Google的JavaScript语言源码标准的完整定义，当且仅当JavaScript源文件遵循此规则时，它才被描述为Google风格。

术语说明：

*   在文档中，除非另有说明：
    *   术语“注释”始终指“实现注释”（implementation comments）。我们不使用术语“文档注释”（documentation comments），而是使用通用术语<kbd>JSDoc</kbd>（即<code>/** */</code>）来表示其中的人类可读文本和机器可读文本。
    *   指南中对“必须”、“禁止”、“应该”、“不应该”和“可能”等术语的用法遵循[RFC 2119](https://www.rfc-editor.org/rfc/rfc2119)中的定义。术语“首选”和“避免”分别对应于“应该”和“不应该”。命令性和声明性语句是规定性的，与“必须”相对应。
*   本文档中偶尔会出现其他术语注释。

指南说明：本文档中的示例代码是非规范性的。也就是说，尽管这些示例采用的是Google样式，但它们可能并不能代表表示代码的唯一时尚方式。在示例中作出的可选格式选择不得作为规则执行。

## 源文件

文件名：文件名必须全部小写，并且可以包含下划线或者破折号，但不能包含其他标点符号。具体请遵循项目使用的约定。文件名的扩展名必须是<kbd>.js</kbd>。

文件编码：源文件以UTF-8编码。

特殊字符：

*   空格字符：除了行终止符序列之外，ASCII水平空格字符（0x20）是唯一出现在源文件中任何地方的空格字符。这意味着字符串文字中的所有其他空格字符均被转义；制表符不用于缩进。
*   特殊转义字符：对于具有特殊转义序列（\'，\"，\\，\b，\f，\n，\r，\t，\v）的任何字符，将使用该序列，而不是相应的数字转义符（例如\x0a，\u000a或\u{a}）。从不使用旧式八进制转义符。
*   非ASCII码字符：对于其余的非ASCII字符，使用实际的Unicode字符（例如∞）或者等效的十六进制或者Unicode转义（例如\u221e），这仅取决于哪种方式使代码更易于阅读和理解。

```javascript
/* Best: perfectly clear even without a comment. */
const units = 'μs';
/* Allowed: but unncessary as μ is a printable character. */
const units = '\u03bcs';  // 'μs'
/* Good: use escapes for non-printable characters with a comment for clarity. */
return '\ufeff' + content;  // Prepend a byte order mark.
```

## 源文件结构

所有新的源文件应为一个<kbd>goog.module</kbd>文件（包含goog.module调用）或者<kbd>ECMAScript</kbd>模块（使用import和export语句）。文件包含的内容按以下顺序排序：

1.  许可证或者版权信息（如果存在）。
2.  JSDoc中的@fileoverview描述（如果存在）。
3.  goog.module语句（如果是goog.module文件）。
4.  ES import语句（如果是ES模块）。
5.  goog.require和goog.requireType语句。
6.  文件的实现。

*   注意：除了文件的实现这一部分外，其余部分使用一个或者两个空行进行分割。

## 格式化

术语说明：块状结构（block-like construct）指的是代码主体为类、函数、方法或用大括号分割的代码块。请注意，任何数组字面量（Array Literals）或对象字面量（Object Literals）都可以选择性地被视为块状结构。

请使用clang-format。JavaScript社区投入了大量精力来确保clang-format对JavaScript文件执行正确的操作。clang-format已经集成于几个主流的编辑器中。
