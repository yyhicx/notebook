# Google Java 风格指南

1.  [介绍](#介绍)
2.  [源文件基础](#源文件基础)
3.  [源文件结构](#源文件结构)
4.  [格式](#格式)
5.  [命名](#命名)
6.  [编程习惯](#编程习惯)
7.  [Javadoc](#javadoc)

## 介绍

本文档完整的定义了Google的Java语言的代码风格，与其他编程指南一样，该文档涵盖的问题不仅仅是格式的审美问题，还包括其他类型的约定和代码标准。而且，本文的主要关注的是我们普遍遵循的硬性规则，并避免提供无法明确执行的建议。

## 源文件基础

文件名：源文件的文件名由其包含的唯一的顶级类的名称（大小写敏感）加上`.java`扩展名构成。

文件编码：源文件应使用`UTF-8`编码。

特殊字符：

*   空白字符：除了换行符之外，源文件中唯一能够出现的空白字符是ASCII的水平空格字符（`0x20`）。
    *   字符串和字符组成的文本中的所有其他空白字符都应该被转义。
    *   Tab字符（`0x09`）不能用于缩进。
*   特殊转义序列：对于任何由特殊转义序列的字符（如`\b`、`\t`、`\n`、`\f`、`\r`、`\"`、`\'`、`\\`），应使用以上序列，而不是相应的八进制（如`\012`）或Unicode（如`\u000a`）转义。
*   非ASCII字符：对于其余的非ASCII字符，可以使用实际的Unicode字符（如`∞`）或等效的Unicode转义序列（如`\u221e`），这只取决于哪种方式使代码更容易阅读和理解，尽管我们强烈不建议在字符串文本和注释之外使用Unicode转义。

## 源文件结构

源文件应由以下部分按顺序组成，并且各部分之间用一个空白行隔开：

*   许可或版权信息（如果有的话）。
*   包的声明。
*   导入语句。
*   有且仅有一个顶级类。

导入语句：

*   不使用任何形式的通配符导入，无论是静态的还是其他形式。
*   导入语句不换行，列限制（`Column limit`）不适用于导入语句。
*   所有的静态导入都在一个单独的块中；所有非静态导入都在一个单独的块中。如果既有静态导入又有非静态导入，则两个块之间有一个空白行。但导入语句之间没有其他空白行。
*   每个块中，导入的名称按ASCII排序顺序出现。
*   不对类使用静态导入。

类的声明：

*   每个顶级类都位于其自己的源文件中。
*   类中内容的顺序：类中成员和初始化器的顺序对于代码的可读性有很大影响。然而，并没有一个唯一正确的顺序，不同的类可能会以不同的方式排序其内容。重要的是，每个类都使用某种逻辑顺序，并且在被问及时，其维护者可以做出相应的解释。
*   类中拥有同一名称的方法应该出现在一个连续的组中，中间不应该有任何其他成员。

## 格式

花括号：

*   对于空块（可选择使用花括号的情况）：在`if`、`else`、`for`、`do`和`while`语句中，即使主体为空或只包含一个语句，也应该写出花括号。其他选择性的花括号情况，比如`lambda`表达式的花括号，依然不是必须写出的。
*   对于非空块和块状结构遵循`K&R`风格：
    *   在打开花括号之前不换行。
    *   在打开花括号之后换行。
    *   在关闭花括号之前换行。
    *   仅在该花括号终止一个语句或终止方法、构造器或命名类的主体时，在关闭花括号之后才需要换行。例如，如果花括号后面跟的是`else`或逗号，则不换行。

```java
return () -> {
  while (condition()) {
    method();
  }
};

return new MyClass() {
  @Override 
  public void method() {
    if (condition()) {
      try {
        something();
      } catch (ProblemException e) {
        recover();
    } else if (otherCondition()) {
      somethingElse();
    } else {
      lastThing();
    }
  }
}
```

代码块缩进：

*   每次打开一个新的代码块或块状结构时，缩进增加两个空格。
*   当块结束时，缩进返回到之前的缩进级别。

Java代码的列限制为100个字符。

换行：将可以在一行内书写的代码分成多行的操作被称为换行。

*   换行的首要准则是：倾向于在`更高级的句法层次`进行换行。
*   换行时，第一行之后的每一行至少从原行缩进4个空格。当有多个连续行时，根据需要，缩进可以在4个空格之外变化。通常，只有当两个连续行从语法上开始于平行的元素时，它们才使用相同的缩进级别。

```java
// 正确做法
public void myMethod(
    String longParameterName1, String longParameterName2,
    int anotherParameter) {
  // 代码块
}

if (longCondition1 &&
    longCondition2 ||
    anotherLongCondition) {
  // 条件体
}

if result = longExpression1 +
            longExpression2 -
            longExpression3;

// 错误做法
public void myMethod(String longParameterName1,
    String longParameterName2, int anotherParameter) {
  // 代码块
}

if (longCondition1 && 
    longCondition2 || anotherLongCondition) {
  // 条件体    
}

int result = longExpression1 + longExpression2 
             - longExpression3;
```

空白字符：

*   垂直空白：
    *   单个空白行始终应该出现在：一个类的连续成员或初始化器之间：字段、构造函数、方法、嵌套类、静态初始化器和实例初始化器。
    *   单个空白行也可以出现在任何使用它可以提高代码可读性的位置，例如在语句之间将代码组织成逻辑子部分。类的第一个成员或初始化器之前，或者最后一个成员或初始化器之后的空白行既不被鼓励也不被反对。
    *   多个连续的空白行是允许的，但从不被要求。
*   水平空白：
    *   除了语言或其他风格规则所要求的地方，以及字符串文本、注释和JavaDoc之外，单个ASCII空白字符也仅出现在以下位置：
        *   将任何保留字，如`if`、`for`、`catch`，与其后面的左括号隔开。
        *   将任何保留字，如`else`、`catch`，与其前面的右括号隔开。
        *   在代码中任何内容和开始注释的双斜杠（`//`）之间。允许多个空格（个人推荐两个）。
        *   在声明的类型与变量名之间。
    *   此规则不应被解读为在行的开始或结束时要求或禁止额外的空格；它只涉及内部空格。
*   水平对齐：指在代码中添加变化数量的额外空格，目的是使某些标记直接出现在前面几行的某些其他标记的下方。
    *   这种做法是允许的，但永远不是必要的。

    ```java
    private int   x;
    private Color color;
    ```

具体结构：

*   枚举类：

    ```java
    private enum Answer {
      YES {
        @Override
        public String toString() {
          return "yes";
        }
      },
      No,
      MAYBE
    }

    private enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
    ```

*   变量声明：
    *   一次只声明一个变量，不能使用`int a, b;`这样的声明。但在`for`循环的头部中，多个变量的声明是可以接受的。
    *   局部变量一般不在其所在的块或块状结构的开始处声明。相反，局部变量在首次使用的地方附加声明，以最小化它们的作用域。
    *   数组初始化器：

        ```java
        new int[] {0, 1, 2, 3, 4};

        new int[] {
          0, 1, 2, 3, 4
        };
        ```

    *   对于数组不要使用C语言风格的声明，即`String[] args`而非`String args[]`。方括号是类型的一部分，而非变量的一部分。
    *   `switch`语句：

        ```java
        switch (input) {
          case 1:
          case 2:
            prepareOneOrTwo();
            // fall through  // 没有 break 语句，可以标识 fall through 到下一个 case
          case 3:
            handleOneTwoOrThree();
            break;
          default:  // 必须要有 default 语句
            handleLargeNumber(input);
        }
        ```

    *   注解：

        ```java
        // 类型注解
        final @Nullable String name;
        public @Nullable Person getPersonByName(String name) {
          // ...
        }

        // 类注解
        @Deprecated
        @CheckReturnValue
        public final class Frozzler { 
          //...
        }

        // 方法注解
        @Deprecated
        @Override
        public String getNameIfPresent() {
          // ...
        }

        // 字段注解
        @Partial @Mock DataLoader loader;
        ```

    *   块状注释：

        ```java
        /*
         * This is          // And so           /* Or you can
         * okay.            // is this.          * even do this. */
         */
        ```

    *   修饰符：当存在类或成员的修饰符时，它们出现的顺序应遵循Java语言规范推荐的顺序。

        ```java
        public protected private abstract default static final transient volatile synchronized native strictfp
        ```

    *   数值字面量：长整型字面量使用大写的`L`作为前缀，而不推荐使用小写的`l`，避免与`1`混淆。

## 命名

标识符只使用ASCII字母、数字以及在下面提到的极少数情况中才会使用的下划线。因此，每个有效的标识符名称都应与正则表达式`\w+`匹配。

包名：只使用小写字母和数字（不使用下划线），连续的单词直接连在一起。例如`com.example.deepspace`，而不是`com.example.deepSpace`或`com.example.deep_space`。

类名：使用大驼峰命名法。

方法名：使用小驼峰命名法。

常量字段名：通常是指由`static final`修饰的字段，采用大蛇形命名法（`UPPER_SNAKE_CASE`）。

```java
// Constants
static final int NUMBER = 5;
static final ImmutableList<String> NAMES = ImmutableList.of("Ed", "Ann");
static final Map<String, Integer> AGES = ImmutableMap.of("Ed", 35, "Ann", 32);
static final Joiner COMMA_JOINER = Joiner.on(',');  // because Joiner is immutable
static final SomeMutableType[] EMPTY_ARRAY = {};

// Not constants
static String nonFinal = "non-final";
final String nonStatic = "non-static";
static final Set<String> mutableCollection = new HashSet<String>();
static final ImmutableSet<SomeMutableType> mutableElements = ImmutableSet.of(mutable);
static final ImmutableMap<String, SomeMutableType> mutableValues =
    ImmutableMap.of("Ed", mutableInstance, "Ann", mutableInstance2);
static final Logger logger = Logger.getLogger(MyClass.getName());
static final String[] nonEmptyArray = {"these", "can", "change"};
```

非常量字段名：使用小驼峰命名法。

参数名：使用小驼峰命名法。

局部变量名：使用小驼峰命名法。

特殊情况：

| Prose form             | Correct              | Incorrect            |
| ---------------------- | -------------------- | -------------------- |
| `XML HTTP request`     | `XmlHttpRequest`     | `XMLHTTPRequest`     |
| `new customer ID`      | `newCustomerId`      | `newCustomerID`      |
| `inner stopwatch`      | `innerStopwatch`     | `innerStopWatch`     |
| `supports IPv6 on iOS` | `supportsIpv6OnIos`  | `supportsIPv6OnIOS`  |
| `YouTube video player` | `YouTubeVideoPlayer` | `YoutubeVideoPlayer` |

## 编程习惯

`@Override`始终被使用。但当父方法被`@Deprecated`标记时，可以省略`@Override`。

在使用静态成员时通常通过类名来引入。

```java
Foo aFoo = ...;
Foo.aStaticMethod();  // 好
aFoo.aStaticMethod();  // 差
somethingThatYieldsAFoo().aStaticMethod();  // 非常差
```

析构方法是不使用的，重写`Object.finalize`方法是极其罕见的。

## Javadoc

一般格式：

```java
/**
 * Multiple lines of Javadoc text are written here,
 * wrapped normally...
 */
public int method(String p1) { ... }

/** An especially short bit of Javadoc. */
```
