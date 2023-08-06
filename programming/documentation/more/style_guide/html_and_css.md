# Google HTML/CSS 风格指南

1.  [背景](#背景)
2.  [总则](#总则)
3.  [HTML](#html)
4.  [CSS](#css)
5.  [结束语](#结束语)

## 背景

本文档定义了HTML/CSS的编写格式和风格规则。它旨在提高合作和代码质量，并使其支持基础架构。它适用于HTML/CSS文件。只要代码质量是可以被维护的，就能很好的被工具混淆、压缩和合并。

## 总则

样式规则：

*   协议：
    *   尽可能将HTTPS协议用于<kbd>Embedded Resources</kbd>（嵌入式资源是HTML页面完全发挥功能并与页面本身一起加载所需的资源）。
    *   始终对图像（images）和其他媒体文件，样式文件（style sheets）和脚本文件（scripts）使用HTTPS协议，除非相应的文件不能通过HTTPS获得。

```html
<!-- Recommended -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!-- Not recommended: uses HTTP -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
```

```css
/* Recommended */
@import 'https://fonts.googleapis.com/css?family=Open+Sans';
/* Not recommended: uses HTTP */
@import 'http://fonts.googleapis.com/css?family=Open+Sans';
```

排版规则：

*   缩进：一次缩进2个空格，不要使用制表符（Tab）或混合制表符和空格来缩进。
*   大小写：
    *   仅使用小写。
    *   所有代码必须为小写：这适用于HTML元素名称、属性和属性值（除了<kbd>text/CDATA</kbd>），CSS的选择器、属性和属性值（字符串除外）。
*   行尾空格：删除行尾空格。

```html
<!-- Recommended -->
<ul>
  <li>Fantastic</li>
  <li>Great</li>
</ul>
<!-- Recommended -->
<img src="google.png" alt="Google">
<!-- Not recommended -->
<A HREF="/">Home</A>
```

```css
/* Recommended */
.example {
  color: blue;
}
/* Recommended */
color: #e5e5e5;
/* Not recommended */
color: #E5E5E5;
```

文件Meta规则：

*   编码：
    *   使用UTF-8编码（不带BOM头）。
    *   在HTML模板和文件中指定编码<meta charset="utf-8">。不需要指定样式表的编码，它默认为UTF-8。
*   注释：
    *   根据需要尽可能的去解释你写的代码。用注释来解释代码：它涵盖了什么，它的目的是什么，为什么使用或优先使用这个解决方案？
    *   本规则可选，要求对代码进行完整的注释并不现实，注释也会增重HTML和CSS的代码体积。
*   事项：
    *   用TODO标记待办事项和处于活动状态事项。
    *   在括号中附加联系人（用户名或邮件列表），格式为<kbd>TODO(contact)</kbd>。
    *   在冒号后附加操作项，如<kbd>TODO: action item</kbd>。
    *   译者注：
        *   `NOTE`：代码如何工作的描述（比较复杂需要解释）。
        *   `XXX`：警告可能存在的陷阱，如`NOTE:XXX: 陷阱`。
        *   `HACK`：用于规避问题、错误，代码不健壮或存在异常，如`HACK:FIXME: 临时填坑`。
        *   `FIXME`：虽然实现功能，但应该有更优的代码方案，需要重构。
        *   `BUG`：这里有问题。
        *   `TODO`：没问题，但需要编写额外的代码（当前工作被中断，未来得及完成）。

```html
<!-- TODO: remove optional tags -->
<ul>
  <li>Apples</li>
  <li>Oranges</li>
</ul>
```

## HTML

HTML代码风格规则：

*   Document类型：
    *   使用HTML5，`<!DOCTYPE html>`。
    *   推荐使用HTML，即<kbd>text/html</kbd>，不要使用XHTML，因为<kbd>application/xhtml+xml</kbd>文档标记缺少浏览器和基础架构（infrastructure）支持，并且存储空间优化比HTML少。
    *   编写HTML时，尽管关闭void元素是很好的行为，但是不要这么做。比如写<br>，不写<br />（个人习惯：HTML文件中使用<br>写法，React文件中的JSX内使用<br />写法）。
*   HTML代码有效性：
    *   尽可能使用有效的HTML。
    *   使用[W3C HTML validator](https://validator.w3.org/nu)等工具进行测试。
    *   HTML代码有效性是代码质量衡量的重要标准，并且有助于学习技术规范，以确保正确使用HTML代码。
*   语义：
    *   根据HTML元素的用途合理使用它们。例如，使用heading元素作为标题，p元素用于段落，a元素用于锚点等。
    *   合理使用HTML元素对可访问性、代码重用和编码效率等各方面有重要的影响。
*   多媒体备选方案：
    *   为多媒体提供备选内容。
    *   对于多媒体，如图像，视频，通过canvas读取的动画元素，请确保提供代替访问方案。对于图像提供有意义的代替文本（alt），对于视频和音频提供有效的副本和字幕说明。
*   分离：
    *   将结构、表象和行为分离。
    *   严格保持结构（标记）、表象（样式）和行为（脚本）的分离，并尽量让三者之间的交互保持最低限。
    *   确保文档和模板只包含HTML，且HTML仅用于结构目的，把所有表象都纳入样式表中，把所有行为都放进脚本中。
    *   此外，使联系区域尽可能小，文档和模板中尽可能地减少样式表和脚本的链接（一是减少链接数，二是减少代码作用范围）。
    *   基于可维护性，将结构从表象和行为分离非常重要。更改HTML文档或模板比更新样式表或脚本成本要高。
*   实体引用：
    *   不要使用实体引用。
    *   不需要使用类似<kbd>&mdash;</kbd>、<kbd>&rdquo;</kbd>和<kbd>&#x263a;</kbd>等的实体引用，假定团队之间所用的文件和编辑器是同一编码（UTF-8）。
    *   唯一的例外适用于HTML中具有特殊含义的字符（如<和&）以及控制或“不可见”字符（如不间断空格）。
*   可选标签：
    *   省略可选标签（可选）。
    *   出于优化文件大小和校验，可以考虑省略可选标签，哪些是可选标签可以参考[HTML5 specification](https://html.spec.whatwg.org/multipage/syntax.html#syntax-tag-omission)。
*   type属性：
    *   在样式表和脚本的标签中忽略type属性。
    *   HTML5默认type为<kbd>text/css</kbd>和<kbd>text/javascript</kbd>类型，所以没必要指定。即使是老浏览器也是支持的。

```html
<!-- Recommended -->
<img src="spreadsheet.png" alt="Spreadsheet screenshot.">
<!-- Not recommended -->
<img src="spreadsheet.png">
<!-- Recommended -->
<!DOCTYPE html>
<title>My first CSS-only redesign</title>
<link rel="stylesheet" href="default.css">
<h1>My first CSS-only redesign</h1>
<p>I’ve read about this on a few sites but today I’m actually
  doing it: separating concerns and avoiding anything in the HTML of
  my website that is presentational.</p>
<p>It’s awesome!</p>
<!-- Not recommended -->
<!DOCTYPE html>
<title>HTML sucks</title>
<link rel="stylesheet" href="base.css" media="screen">
<link rel="stylesheet" href="grid.css" media="screen">
<link rel="stylesheet" href="print.css" media="print">
<h1 style="font-size: 1em;">HTML sucks</h1>
<p>I’ve read about this on a few sites but now I’m sure:
  <u>HTML is stupid!!1</u>
<center>I can’t believe there’s no way to control the styling of
  my website without doing everything all over again!</center>
<!-- Recommended -->
The currency symbol for the Euro is “€”.
<!-- Not recommended -->
The currency symbol for the Euro is &ldquo;&eur;&rdquo;.
<!-- Recommended -->
<link rel="stylesheet" href="https://www.google.com/css/maia.css">
<script src="https://www.google.com/js/gweb/analytics/autotrack.js"></script>
<!-- Not recommended -->
<link rel="stylesheet" href="https://www.google.com/css/maia.css" type="text/css">
<script src="https://www.google.com/js/gweb/analytics/autotrack.js" type="text/javascript"></script>
```

HTML代码格式规则：

*   一般规则：
    *   为每一个块（block）、列表（list）或表（table）元素使用一个新行，并缩进每个这样的子元素。
    *   将元素撇开样式（CSS允许元素用display属性赋予不同的性质）。
    *   另外，块元素、列表元素或表格元素的子元素需要缩进。
*   换行：
    *   长行换行（可选）。
    *   虽然没有针对HTML代码列长度的限制和建议，但如果能够明显提高可读性，则可以考虑长行换行。
    *   换行时，新延续的行在原始行缩进基础上再缩进4个空格。
*   引号：属性值包裹使用双引号而不是单引号。

```html
<!-- Recommended -->
<blockquote>
  <p><em>Space</em>, the final frontier.</p>
</blockquote>
<!-- Recommended -->
<ul>
  <li>Moe</li>
  <li>Larry</li>
  <li>Curly</li>
</ul>
<!-- Recommended -->
<table>
  <thead>
    <tr>
      <th scope="col">Income</th>
      <th scope="col">Taxes</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>$ 5.00</td>
      <td>$ 4.50</td>
    </tr>
  </tbody>
</table>
<!-- Recommended -->
<md-progress-circular
    md-mode="indeterminate"
    class="md-accent"
    ng-show="ctrl.loading"
    md-diameter="35">
</md-progress-circular>
<!-- Recommended -->
<md-progress-circular md-mode="indeterminate"
                      class="md-accent"
                      ng-show="ctrl.loading"
                      md-diameter="35">
</md-progress-circular>
<!-- Recommended -->
<a class="maia-button maia-button-secondary">Sign in</a>
<!-- Not recommended -->
<a class='maia-button maia-button-secondary'>Sign in</a>
```

## CSS

CSS代码风格规则：

*   CSS代码有效性：
    *   尽可能使用有效的CSS。
    *   使用[W3C CSS validator](https://jigsaw.w3.org/css-validator)等工具进行测试。
    *   CSS代码有效性是代码质量衡量的重要标准，如果发现CSS代码没有任何效果可以删除它，并确保正确的使用CSS。
*   Class命名：
    *   使用有意义且通用的Class名。
    *   命名不可表象、生僻和模糊，通过Class命名能知道元素有什么用途，或者是其他通用命名方式。
    *   首选具有特定名称并反映元素用途的命名，这些名称最容易理解且不易改变，以减少更新。
    *   通用命名方式是对元素进行简单的补充。
    *   使用功能性或通用命名方式，会减少不必要的文档或模板修改。
*   Class命名风格：
    *   Class命名，尽量简短，如需必要则详细描述。
    *   Class名尽可能包含语义语境。
    *   采用这样的命名方式，代码易读、易懂且高效。
*   Class命名分隔符：用连字符（<kbd>-</kbd>）分隔类名中的单词。
*   前缀：
    *   在大型项目以及嵌入到其他项目或外部站点的代码中，使用前缀（作为命名空间）作为类名。使用简短的唯一标识符，后跟破折号。
    *   使用命名空间有助于防止命名冲突，并使维护更容易，例如在搜索和替换操作中。
*   类型选择器：避免使用类型选择器限定Class名。
*   ID选择器：
    *   避免使用ID选择器。
    *   ID属性在整个页面中应该是唯一的，当一个页面包含由许多不同工程师处理的许多组件时，这很难保证。在所有情况下都应该首选Class选择器。
*   速记属性：
    *   尽可能使用速记属性。
    *   CSS提供了各种速记属性（如font），应尽可能使用，即使在只显式设置一个值的情况下也是如此。
    *   使用速记属性可以提高编码效率和可理解性。
*   0和单位：
    *   除非需要，否则省略0值后的单位。
    *   非必要的情况下0后面不加单位。
*   前导数字0：
    *   0开头的浮点数值省略前导0。
    *   值或长度在-1与1之间的小数，小数前的0可以忽略不写。
*   十六进制表示法：
    *   尽可能使用3个字符的十六进制表示法。
    *   对于许可的颜色值，3个字符的十六进制表示法更短更简洁。
*   important声明：避免使用<code>!important</code>声明。
*   Hacks：避免使用user agent检测以及CSS的hacks，优先尝试使用其他解决方案。

```css
/* Recommended: specific */
.gallery {}
.login {}
.video {}
/* Recommended: generic */
.aux {}
.alt {}
/* Not recommended: meaningless */
.yee-1901 {}
/* Not recommended: presentational */
.button-green {}
.clear {}
/* Recommended */
.nav {}
.author {}
/* Not recommended */
.navigation {}
.atr {}
/* Recommended */
.video-id {}
.ads-sample {}
/* Not recommended: does not separate the words “demo” and “image” */
.demoimage {}
/* Not recommended: uses underscore instead of hyphen */
.error_status {}
/* Recommended */
.example {}
.error {}
/* Not recommended */
ul.example {}
div.error {}
/* Recommended */
.example {}
/* Not recommended */
#example {}
/* Recommended */
.example{
  padding: 0 1em 2em;
  font: 100%/1.6 palatino, georgia, serif;
  border-top: 0;
}
/* Not recommended */
.example {
  font-size: 100%;
  font-family: palatino, georgia, serif;
  line-height: 1.6;
  padding-top: 0;
  padding-right: 1em;
  padding-bottom: 2em;
  padding-left: 1em;
  border-top-style: none;
}
/* Recommended */
.example {
  flex: 0px; /* This flex-basis component requires a unit. */
  flex: 1 1 0px; /* Not ambiguous without the unit, but needed in IE11. */
  margin: 0;
  padding: 0;
}
/* Recommended */
.example {
  font-size: .8em;
}
```

CSS代码格式规则：

*   声明顺序：
    *   按照字母顺序声明样式，这样易于记忆和维护。
    *   用于浏览器兼容性前缀的声明在排序中应忽略。但是，CSS属性中用于多个浏览器的兼容性前缀声明，应该进行排序（如-moz前缀在-webkit之前）。
    *   译者注：
        *   不要按字母顺序声明样式！
        *   因为，如position相关的top/right/bottom/left/z-index中间会有其他属性间隔，不能便捷的、逻辑上的添加和修改。
        *   [个人习惯](https://github.com/constverum/stylelint-config-rational-order)：
            *   Positioning：position/top/right/bottom/left/z-index。
            *   Box Model：display/flex/grid/gap/align-content/align/items/align-self/justify-content/justify-items/justify-self/order/float/clear/box-sizing/width/height/margin/padding/overflow。
            *   Typography：color/font/font-weight/font-size/font-family/line-height/direction/letter-spacing/text-align/text-justify/text-wrap/text-overflow。
            *   Visual：list-style/background/background-color/background-image/background-size/border/border-color/border-style/border-width/border-image/border-radius/outline/box-shadow/transform/visibility/cursor/opacity/filter。
            *   Animation：transition/animation。
            *   Misc：content/user-select/zoom/fill/stroke。
*   代码块内容缩进：缩进所有代码块（{}之间的声明）。
*   声明结束：每个声明都是用分号结尾。
*   属性名结束：在属性名称冒号后面加一个空格。
*   声明间隔：
    *   选择器和声明块之间加一个空格。
    *   在最后一个选择器和声明块开始的左大括号之间添加一个空格。
    *   左大括号与规则中的最后一个选择器处于一行。
*   选择器和声明间隔：
    *   将选择器和声明隔行分开。
    *   每个选择器之间，每个声明之间都隔行分开。
*   规则间隔：从新的一行开始每个规则，规则之间隔一个空白行。
*   CSS引号：
    *   对属性选择器和属性值使用单引号而不是双引号。
    *   不要在URI值（url()）中使用引号。
    *   例外：<kbd>@charset</kbd>规则中使用双引号 - [single quotation marks are not permitted](https://www.w3.org/TR/CSS21/syndata.html#charset)。

```css
/* Recommended */
.example {
  color: black;
  text-align: center;
  text-indent: 2em;
  background: fuchsia;
  border: 1px solid;
  -moz-border-radius: 4px;
  -webkit-border-radius: 4px;
  border-radius: 4px;
}
/* Recommended */
@media screen, projection {
  html {
    color: #444;
    background: #fff;
  }
}
/* Recommended */
.test {
  display: block;
  height: 100px;
}
/* Recommended */
h3 {
  font-weight: bold;
}
/* Recommended */
#video {
  margin-top: 1em;
}
/* Recommended */
h1,
h2,
h3 {
  font-weight: normal;
  line-height: 1.2;
}
/* Not recommended */
a:focus, a:active {
  position: relative; top: 1px;
}
/* Recommended */
html {
  background: #fff;
}
body {
  width: 50%;
  margin: auto;
}
/* Recommended */
@import url(https://www.google.com/css/maia.css);
html {
  font-family: 'open sans', arial, sans-serif;
}
/* Not recommended */
@import url("https://www.google.com/css/maia.css");
html {
  font-family: "open sans", arial, sans-serif;
}
```

CSS Meta规则：

*   注释：
    *   分组注释（可选）。
    *   如果可以，按功能、层级等方式对样式分组后统一写注释。独立成行。

```css
/* Header */
#adw-header {}
/* Footer */
#adw-footer {}
/* Gallery */
.adw-gallery {}
```

## 结束语

追求一致性。

如果要编辑代码，先查看已有代码的风格，与之保持一致。

约束一致性的编码环境需要一份公共的编码风格指南，参与者通过规则内容可以清晰明了要做成什么样，而不是考虑规则本身balabala什么。这里仅介绍了统一风格的规则，以便开发者去了解和借鉴。保持自己的编码风格固然重要，但是你把自己代码添加到文件中，与现有的代码风格完全不同，这样会降低代码的可读性和可理解性，避免这样做。
