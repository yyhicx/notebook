# Flutter

## Flutter文档

src: open flutter_example project and run it

用户界面：

*   Widgets介绍：
    *   Flutter支持两种设计：Material（Android）和Cupertino（IOS）。
    *   StatelessWidget是一种无状态的Widget。StatefulWidget是一种特殊的Widget，它会生成State对象，用于保存状态。
    *   Keys：
        *   Key是Widgets、Elements和SemanticsNodes的标识符。
        *   如果新添加的Widget的Key与该元素关联的当前Widget的Key相同，则新Widght将仅用于更新现有元素。
        *   全局Key可以用来标识唯一子Widget。全局Key在整个Widget结构中必须是全局唯一的，而不像本地Key只需要在兄弟Widget中唯一。
*   布局构建：
    *   Flutter中的布局：
        *   Flutter布局的核心机制是Widgets。在Flutter中，几乎所有东西都是Widget，包括布局模型（行、列、网格等）、图像、图标和文本等。
        *   Flutter中的Widget分为两类：
            *   Widgets库中标准Widgets，使用<code>import 'package:flutter/widgets.dart'</code>调用。
            *   Material库中的Widgets，使用<code>import 'package:flutter/material.dart'</code>调用。
            *   任何app都可以使用Widgets库，但是Material库中的组件只能在Material App中使用。
            *   Material库包含Widgets库。
            *   下面所介绍的Widgets中，只有Card和ListTile属于Material库，其余都属于Widgets库。
        *   Row和Column：
            *   Row和Column是两种最常用的布局模式，它们每个都有一个子Widget列表，子Widget本身可以是Row、Column或其它复杂Widget。
            *   可以使用maxAxisAlignment和crossAxisAlignment属性控制Row或Column如何对齐其子项。对于一行来说，主轴水平延伸，交叉轴垂直延伸。对于一列来说，主轴垂直延伸，交叉轴水平延伸。
            *   可以使用Expanded这个Widget，调整被Row和Column包含的子Widget的大小，以适合Row和Column。
        *   Container：向Widget添加padding、margins、borders、background color或者其它的“装饰”。
        *   GridView：将Widget展示为一个可滚动的网格。
        *   ListView：将Widget展示为一个可滚动的列表。
        *   Stack：使用alignment属性将一个Widget覆盖在另一个Widget的上面。
        *   Card：将相关信息整理到一个有圆角和阴影的盒子中。
        *   ListTile：将最多三行的文本、可选的导语以及后面的图标组织在一行中。

## 常用库

fluro

dio

http

file_picker

bloc

shared_preferences

sqflite

mdi
