# Google C++ 风格指南

1.  [扉页](#扉页)
2.  [头文件](#头文件)
3.  [作用域](#作用域)
4.  [类](#类)
5.  [函数](#函数)
6.  [来自Google的奇巧](#来自google的奇巧)
7.  [其他C++特性](#其他c特性)
8.  [命名约定](#命名约定)
9.  [注释](#注释)
10.  [格式](#格式)
11.  [规则特例](#规则特例)
12.  [结束语](#结束语)

## 扉页

本指南的目的是通过详细阐述C++注意事项来驾驭其复杂性。这些规则在保证代码易于管理的同时，也能高效使用C++的语言特性。

使代码易于管理的方法之一是加强代码一致性，让任何程序员都可以快速读懂你的代码这点非常重要，保持统一编程风格并遵守约定意味着可以很容易根据“模式匹配”规则来推断各种标识符的含义，创建通用、必需的习惯用语和模式可以使代码更容易理解。在一些情况下可能有充分的理由改变某些编程风格，但我们还是应该遵循一致性原则，尽量不这么做。

本指南的另一观点是C++特性的臃肿。C++是一门包含大量高级特性的庞大语言。某些情况下，我们会限制甚至禁用某些特性，这样做是为了保持代码清爽，避免这些特性可能导致的各种问题。指南中举例了这类特性，并解释为什么这些特性被限制使用。

## 头文件

正确使用头文件可令代码在可读性、文件大小和性能上大为改观。

头文件应该`self-contained`（自给自足），具体来说，一个头文件应该具有`#define保护`并包含它需要的所有其他头文件。

`#define保护`：

*   所有头文件都应该使用#define来防止头文件被多重包含，命名格式为：`<PROJECT>_<PATH>_<FILE>_H_`。

前置声明：

*   尽可能地避免使用前置声明（forward declaration），而是使用#include包含需要的头文件。
*   函数：总是使用#include。
*   类模板：优先使用#include。

内联函数：

*   一个较为合理的经验准则：不要内联超过10行的函数。谨慎对待构造函数和析构函数，构造函数和析构函数往往比其表面看起来更长，因为有隐含的成员、基类构造函数和基类析构函数被调用。
*   另一个实用的经验准则：内联那些包含循环或switch语句的函数常常是得不偿失（除非在大多数情况下，这些循环或switch函数从不被执行）。
*   有些函数即使声明为内联的也不一定会被编译器内联。

`#include`的路径及顺序：

*   使用标准的头文件包含顺序可增强可读性，避免隐藏依赖：

    ```txt
    1. 相关头文件
    2. C库
    3. C++库
    4. 其他库的.h文件
    5. 本项目内.h文件
    6. 平台特定（system-specific）代码需要条件编译（conditional includes）
    ```

*   项目内头文件应按照项目源代码目录树结构排列，避免使用UNIX特殊的快捷目录.（当前目录）和..（上级目录）。
*   这种优先的顺序排序保证当相关头文件（foo.h）遗漏包含某些必要的库时，相关源文件（foo_test.cc）的构造会立刻中止。
*   按字母顺序分别对每种类型的头文件进行二次排序是不错的主意。注意较老的代码可不符合这条规则，要在方便的时候改正它们。
*   有时，平台特定代码需要条件编译，这些代码可以放到其他includes之后。

```c++
// 举例来说，google-awesome-project/src/foo/internal/fooserver.cc
#include "foo/public/fooserver.h"
#include <sys/types.h>
#include <unistd.h>
#include <hash_map>
#include <vector>
#include "base/basictypes.h"
#include "base/commandlineflags.h"
#include "base/port.h"             // For LANG_CXX11
#include "foo/public/bar.h"
// 需要条件编译的平台特定代码
#ifdef LANG_CXX11
#include <initializer_list>
#endif
```

## 作用域

命名空间：

*   鼓励在.cc文件内使用匿名命名空间或static声明。使用具名的命名空间时，其名称可基于项目名或相对路径。禁止使用using指示（using-directive）。禁止使用内联命名空间（inline namespace）。
*   根据下文将要提到的策略合理使用命名空间：
    *   遵守命名空间命名中的规则。
    *   像之前的几个例子中一样，在命名空间的最后注释出命名空间的名字。
    *   用命名空间把文件包含，gflags的声明/定义，以及类的前置声明以外的整个源文件封装起来，以区别于其他命名空间。
    *   不要在命名空间std内声明任何东西，包括标准库的类前置声明。在std命名空间声明实体是未定义行为，会导致如不可移植。声明标准库下的实体，需要包含对应的头文件。
    *   不应该使用using指示引入整个命名空间的标识符号。
    *   不要在头文件中使用命名空间别名，除非显式标记内部命名空间使用。因为任何在头文件中引入命名空间都会成为公开API的一部分。
    *   禁止用内联命名空间。

```c++
// .h 文件
namespace mynamespace {
// 所有声明都置于命名空间中
// 注意不要使用缩进
class MyClass {
 public:
  ...
  void Foo();
};
}  // namespace mynamespace
```

```c++
// .cc 文件
namespace mynamespace {
// 函数定义都置于命名空间中
void MyClass::Foo() {
  ...
}
}  // namespace mynamespace
```

```c++
#include "a.h"
DEFINE_FLAG(bool, someflag, false, "dummy flag");
namespace a {
...code for a...                // 左对齐
}  // namespace a
```

```c++
// 禁止 —— 污染命名空间
using namespace foo;
```

```c++
// 在 .cc 中使用别名缩短常用的命名空间
namespace baz = ::foo::bar::baz;
```

```c++
namespace librarian {
namespace impl {  // 仅限内部使用
namespace sidetable = ::pipeline_diagnostics::sidetable;
}  // namespace impl
inline void MyInlineFunction() {
  // 限制在一个函数中的命名空间别名
  namespace baz = ::foo::bar::baz;
  ...
}
}  // namespace librarian
```

匿名命名空间和静态变量：

*   在.cc文件中定义一个不需要被外部引用的变量时，可以将它们放在匿名命名空间或声明为static。但是不要在.h文件中这么做。
*   推荐、鼓励在.cc中对于不需要在其他地方引用的标识符使用内部链接性声明，但是不要在.h中使用。
*   匿名命名空间的声明和具名的格式相同，在最后注释上namespace。

```c++
namespace {
...
}  // namespace
```

非成员函数、静态成员函数和全局函数：

*   使用静态成员函数或命名空间内的非成员函数，尽量不要用裸的全局函数。将一系列函数直接置于命名空间中，不要用类的静态方法模拟出命名空间的效果，类的静态方法应当和类的实体或静态数据紧密相关。
*   有时，把函数的定义同类的实例脱钩是有益的，甚至是必要的。这样的函数可以被定义成静态成员，或是非成员函数。非成员函数不应依赖于外部变量，应尽量置于某个命名空间内。相比单纯为了封装若干不共享任何静态数据的静态成员函数而创建类，不如使用命名空间。

```c++
// Right
namespace myproject {
namespace foo_bar {
void Function1();
void Function2();
}  // namespace foo_bar
}  // namespace myproject
```

```c++
// Wrong
namespace myproject {
class FooBar {
 public:
  static void Function1();
  static void Function2();
};
}  // namespace myproject
```

局部变量：

*   将函数变量尽可能置于最小作用域内，并在变量声明时进行初始化。
*   属于if, while和for语句的变量应当在这些语句中正常地声明，这样子这些变量的作用域就被限制在这些语句中了。
*   有一个例外，如果变量是一个对象，每次进入作用域都要调用其构造函数，每次退出作用域都要调用其析构函数。这会导致效率降低。

```c++
while (const char* p = strchr(str, '/')) str = p + 1;
```

静态变量和全局变量：禁止定义静态储存周期非POD变量，禁止使用含有副作用的函数初始化POD全局变量，因为多编译单元中的静态变量执行时的构造和析构顺序是未明确的，这将导致代码的不可移植。

## 类

构造函数：不要在构造函数中调用虚函数，也不要在无法报错时进行可能失败的初始化。

隐式类型转换：不要定义隐式类型转换. 对于转换运算符和单参数构造函数, 请使用explicit关键字。

可拷贝类型和可移动类型：如果你的类型需要，就让它们支持拷贝/移动，否则，就把隐式产生的拷贝和移动函数禁止。

结构体和类：仅当只有数据成员时使用struct，其他一概使用class。

继承：

*   使用组合常常比使用继承更合理。
*   所有继承必须是public的，如果你想使用私有继承，你应该替换成把基类的实例作为成员对象的方式。
*   必要的话，析构函数声明为virtual。如果你的类有虚函数，则析构函数也应该为虚函数。
*   对于可能被子类访问的成员函数，不要过度使用protected关键字。注意，数据成员都必须是私有的。
*   对于重载的虚函数或虚析构函数，使用override、final或virtual的其中之一进行标记。

多重继承：只有以下情况我们才允许使用多重继承：最多只有一个基类是非抽象类；其他基类都是以Interface为后缀的纯接口类。

接口：

*   接口是指满足特定条件的类，这些类以Interface为后缀（不强制）。
*   当一个类满足一下要求时，称之为纯接口：
    *   只有纯虚函数和静态函数。
    *   没有非静态数据成员。
    *   没有定义任何构造函数。如果有，也不能带有参数，并且必须是protected。
    *   如果它是一个子类，也只能满足上述条件并以Interface为后缀的类继承。

运算符重载：

*   除少数特定环境外，不要重载运算符，也不要创建用户定义字面量。
*   只有在意义明显，不会出现奇怪的行为并且与对应的内建运算符的行为一致时才定义重载运算符。
*   只对用户自己定义的类型重载运算符。更准确地说，将它们和它们所操作的类型定义在同一个头文件、.cc文件或命名空间中。这样做无论类型在哪里都能够使用定义的运算符，并且最大程度上避免了多重定义的风险。
*   不要为了避免重载操作符而走极端。比如说，应当定义==，=，和<<，而不是<code>Equals()</code>，<code>CopyFrom()</code>和<code>PrintTo()</code>。

存取控制：将所有数据成员声明为private，除非是static const类型成员。出于技术上的原因，在使用<kbd>Google Test</kbd>时我们允许测试固件类中的数据成员为protected。

声明顺序：

*   类定义一般以<code>public:</code>开始，后跟<code>protected:</code>，最后是<code>private:</code>，省略空部分。
*   在各个部分中，建议将类似的声明放在一起，并且建议以如下的顺序：类型（包括typedef，using和嵌套的结构体与类），常量，工厂函数，构造函数，赋值运算符，析构函数，其他函数，数据成员。
*   不要将大段的函数定义内联在类定义中，通常，只有那些普通的，或性能关键且短小的函数可以内联在类定义中。

## 函数

输入和输出：

*   我们倾向于按值返回，否则按引用返回。避免返回指针，除非它可以为空。
*   C++函数由返回值天然的输出，有时也通过输出参数（或输入/输出参数）提供。我们倾向于使用返回值而不是输出参数：它们提高了可读性，并且通常提供相同或更好的性能。
*   在排序函数参数时，将所有输入参数放在所有输出参数之前（这并非一个硬性规定，有时不得不有所变通）。
*   非可选输入参数通常是值参或const引用，非可选输出参数或输入/输出参数通常应该是引用（不能为空）。对于可选参数，通常使用std::optional来表示可选的按值输入，使用const指针来表示可选的其他输入。使用非常量指针来表示可选输出和可选输入/输出参数。

编写简短函数：

*   我们倾向于编写简短，凝练的函数。
*   我们承认长函数有时是合理的，因此并不硬性限制函数的长度，如果函数超过40行，可以思索一下能不能在不影响程序结构的前提下对其进行分隔。
*   即使一个长函数现在工作的非常好，一旦有人对其修改，有可能出现新的问题，甚至导致难以发现的bug。使函数尽量简短，以便于他人阅读和修改代码。
*   在处理代码时，你可能会发现复杂的长函数。不要害怕修改现有代码：如果证实这些代码使用/调试起来很困难，或者你只需要使用其中的一小段代码，考虑对其分割为更加简短并易于管理的若干函数。

引用参数：

*   在C语言中，如果函数需要修改变量的值，参数必须为指针，如<code>int foo(int *pval)</code>。
*   在C++中，如果函数需要修改变量的值，可以声明为引用参数，如<code>int foo(int& val)</code>；如果不需要修改变量的值，可以声明为const，如<code>int foo(const int& val)</code>。
*   特殊情况下，输入参数可以是非const引用参数，比如<code>Swap()</code>。有时候，输入参数<code>const T*</code>比<code>const T&</code>更明智。

函数重载：

*   若要使用函数重载，则必须能让读者一看调用点就胸有成竹，而不用花心思猜测调用的重载函数到底是哪一种。这一规则也适用于构造函数。
*   如果打算重载一个函数，可以试试改在函数名里加上参数信息。例如，用<code>AppendString()</code>和<code>AppendInt()</code>等，而不是一口气重载多个<code>Append()</code>。如果重载函数的目的是为了支持不同数量的同一类型参数，则优先考虑使用std::vector以便使用者可以用列表初始化指定参数。

```c++
class MyClass {
 public:
  void Analyze(const string& text);
  void Analyze(const char* text, size_t textlen);
};
```

缺省参数：

*   只允许在非函数中使用缺省参数，且必须保证缺省参数的值始终一致。缺省参数与函数重载遵循同样的规则。一般情况下建议使用函数重载。
*   对于虚函数，不允许使用缺省参数，因为在虚函数中缺省参数不一定能正常工作。如果在每个调用点缺省参数的值都有可能不同，在这种情况下缺省函数也不允许使用。

函数返回类型后置语法：只有在常规写法（返回类型前置）不便于书写或不便于阅读时使用返回类型后置语法。

```c++
// C++ 允许两种不同的函数声明方式
// 以往的写法是将返回值置于函数名之前，如下所示
int Foo(int x);
// C++11 引入一种新的形式，现在可以在函数名之前使用 auto 关键字，在参数列表之后后置返回类型，如下所示
auto Foo(int x) -> int;
```

## 来自Google的奇巧

所有权与智能指针：

*   动态分配出的对象最好有单一且固定的所有主，并通过智能指针传递所有权。
*   所有权是一种登记/管理动态内存和其他资源的技术。动态分配对象的所有主是一个对象或函数，后者负责确保当前者无用时就自动销毁前者。所有权有时可以共享，此时就由最后一个所有主来负责销毁它。甚至也可以不用共享，在代码中直接把所有权传递给其他对象。
*   如果必须使用动态分配，那么更倾向于所有权保持在分配者手上。如果其他地方要使用这个对象，最好传递它的拷贝，或者传递一个不用改变所有权的指针或引用。倾向于使用std::unique_ptr来明确所有权传递。
*   如果没有很好的理由，则不要使用共享所有权。这里的理由是可以为了避免开销高昂的拷贝操作，但是只有当性能提升非常明显，并且操作的对象不可变的时候，才能这么做。如果确实要使用共享所有权，建议使用std::shared_ptr。
*   不要使用std::auto_ptr，使用std::unique_ptr代替它。

Cpplint：使用cpplint.py检查风格错误。

## 其他C++特性

引用参数：

*   所有按引用传递的参数必须加上const。
*   事实上这在Google Code是一个硬性约定：输入参数是值参或const引用，输出参数为指针。输入参数可以是const指针，但绝不能是非const的引用参数，除非用于交换，例如<code>Swap()</code>。

右值引用：只在定义移动构造函数与移动赋值操作时使用右值引用。不要使用std::forward。

函数重载：若要用好函数重载，最好能让读者一看调用点（call site）就胸有成竹，不用花心思猜测调用的重载函数到底是哪一种。该规则适用于构造函数。

缺省参数：

*   我们不允许使用缺省函数参数，少数极端情况下除外。尽可能改用函数重载。
*   由于缺点并不是很严重，有些人依旧偏爱缺省参数胜于函数重载。所以除了一下情况，我们要求必须显式提供所有参数。
    1.  位于.cc文件里的静态函数或匿名空间函数，毕竟都只能在局部文件里调用该函数了。
    2.  可以在构造函数里用缺省参数，毕竟不可能取得它们的地址。
    3.  可以用来模拟变长数组。

变长数组和`alloca()`：

*   我们不允许使用变长数组和alloca()。
*   改用更安全的分配器（allocator），就像std::vector或std::unique_ptr<T[]>。

友元：

*   我们允许合理的使用友元类及友元函数。
*   通常友元应该定义在同一文件内，避免代码读者跑到其他文件查找使用该私有成员的类。经常用到友元的一个地方是将FooBuilder声明为Foo的友元，以便FooBuilder正确构造Foo的内部状态，而无需将该状态暴露出来。某些情况下，将一个单元测试类声明成待测类的友元会很方便。
*   友元扩大了（但没有打破）类的封装边界。某种情况下，相对于将类成员声明为public，使用友元是更好的选择，尤其是如果你只允许另一个类访问该类的私有成员时。当然，大多数类都只应该通过其提供的公有成员进行互操作。

异常：

*   我们不使用C++异常。
*   译者注：对于异常处理，显然不是短短几句话能够说清楚的，以构造函数为例，很多C++书籍上都提到当构造失败时只有异常可以处理，Google禁止使用异常这一点，仅仅是为了自身的方便，说大了，无非是基于软件管理成本上，实际使用中还是自己决定。

运行时类型识别：

*   我们禁止使用<kbd>RTTI</kbd>。
*   RTTI允许程序员在运行时识别C++类对象的类型，它通过使用typeid或者dynamic_cast完成。
*   RTTI有合理的用途但是容易被滥用，因此在使用时请务必注意。在单元测试中可以使用RTTI，但是在其他代码中请尽量避免。尤其是在新代码中，使用RTTI前务必三思。
*   如果你的代码需要根据不同的对象类型执行不同的行为的话，请考虑用以下的两种替代方案之一查询类型：
    *   虚函数可以根据子类类型的不同而执行不同代码，这是把工作交给了对象本身去处理。如果这一工作需要在对象之外完成，可以考虑使用双重分发的方案，例如使用访问者设计模式。这就能够在对象之外进行类型判断。
    *   如果程序能够保证给定的基类实例实际上都是某个派生类的实例，那么就可以自由使用dynamic_cast。

类型转换：

*   不要使用C风格类型转换。如<code>int y = (int)x</code>或<code>int y = int(x)</code>等转换方式。
*   应该使用C++风格：
    *   用static_cast代替C风格的值转换，或某个类指针需要明确的向上转换为父类指针时。
    *   用const_cast去掉const限定符。
    *   用reinterpret_cast指针类型或整型或其他指针之间进行不安全的相互转换。仅在你对所做一切了然于心时使用。
    *   至于dynamic_cast参考<kbd>运行时类型识别</kbd>。

流：

*   不要使用流，除非是日志接口需要。使用printf之类的代替。
*   使用流还有很多利弊，但代码一致性胜过一切，不要在代码中使用流。
*   拓展讨论：每一种方式都是各有利弊，“没有更好，只有更合适”。简单性原则告诫我们必须从中选择其一，最后大多数决定采用<code>printf + read/write</code>。

```c++
/**
 * 有人说 printf 的格式化丑陋不堪，易读性差，但流也好不到哪儿去
 * 可以比较下面两段代码，实现相同的功能，哪个更清晰呢
 */
cerr << "Error connecting to '" << foo->bar()->hostname.first
     << ":" << foo->bar()->hostname.second << ": " << strerror(errno);
fprintf(stderr, "Error connecting to '%s:%u: %s",
        foo->bar()->hostname.first, foo->bar()->hostname.second,
        strerror(errno));
```

前置自增或自减：

*   不考虑返回值的话，前置自增通常要比后置自增效率更高。因为后置自增需要对表达式的值进行一次拷贝。如果是迭代器或其他非数值类型，拷贝的代价是比较大的。
*   对简单数值（非对象），两种都无所谓。对迭代器和模版类型，使用前置自增（自减）。

const用法：

*   我们强烈建议你在任何可能的情况下都要使用const。此时有时改用C++11推出的constexpr更好。
*   在声明的变量或参数前加上关键字const用于指明变量值不可被篡改。为类中的函数加上限定符表明该函数不会修改类成员变量的状态。
*   加上const限定符的变量、数据成员、函数和参数为编译时类型检测增加了一层保障；便于尽早发现错误。因此，我们强烈建议在任何可能的情况下使用const：
    *   如果函数不会修改你传入的引用或指针类型参数，该参数应声明为const。
    *   尽可能将函数声明为const。访问函数应该总是const。其他不会修改任何数据成员、未调用非const函数、不会返回数据成员非const指针或引用的函数也应该声明为const。
    *   如果数据成员在对象构造之后不再发生变化，可将其定义为const。
*   const的位置：有两种形式，分别是`int const* foo`和`const int* foo`。因为在自然语言中形容词（const）在名词（int）之前，所以遵循第二种。但是我们提倡不强制const在前，但要保持代码的一致性。

constexpr用法：

*   在C++11里，用constexpr来定义真正的常量，或实现常量初始化。
*   靠constexpr特性，方才实现了C++在接口上打造真正常量机制的可能。好好用constexpr来定义真常量以及支持常量的函数。
*   千万别痴心妄想地依靠 constexpr来强制代码<kbd>内联</kbd>。

整型：

*   C++内建整型中，仅使用int。如果程序中需要不同大小的变量，可以使用<stdint.h>中长度精确的整型，如int16_t，uint32_t，int64_t。
*   C++没有指定整型的大小，通常人们假设short是16位，int是32位，long是32位，long long是64位。在需要确保整型大小时可以使用<stdint.h>中的类型代替short，unsigned long long等。
*   在C整型中，只使用int。在合适的情况下，推荐使用标准类型如size_t和ptrdiff_t。
*   如果整数不会太大，我们常常会使用int。对于大整数，使用int64_t。
*   小心整型类型转换和整型提升。
*   使用无符号类型表示非负数，可能会导致bug。

```c++
// 下述循环永远不会退出
// 有时 gcc 会发现 bug 并报错，但大部分情况下都不会
for (unsigned int i = foo.Length() - 1; i >= 0; --i) DoSomething();
```

64位下都可移植性：

*   代码应该对64为和32位系统友好。
*   处理打印，比较，结构体对齐时应切记。

```c++
// printf macros for size_t, in the style of inttypes.h
#ifdef _LP64
#define __PRIS_PREFIX "z"
#else
#define __PRIS_PREFIX
#endif
// Use these macros after a % in a printf format string
// to get correct 32/64 bit behavior, like this:
// size_t size = records.size();
// printf("%"PRIuS"\n", size);
#define PRIdS __PRIS_PREFIX "d"
#define PRIxS __PRIS_PREFIX "x"
#define PRIuS __PRIS_PREFIX "u"
#define PRIXS __PRIS_PREFIX "X"
#define PRIoS __PRIS_PREFIX "o"
```

预处理宏：

*   使用宏时要非常谨慎，尽量以内联函数，枚举和常量代替之。
*   值得庆幸的是，C++中，宏不像在C中那么必不可少：
    *   宏展开性能关键的代码，现在可以用内联函数代替。
    *   用宏表示常量可被const变量代替。
    *   用宏“缩写”长变量名可被引用代替。
    *   不要使用宏进行条件编译。
*   下面给出的用法模式可以避免使用宏带来的问题；如果你要宏，尽可能遵守：
    *   不要在.h文件中定义宏。
    *   在马上要使用时才进行#define，使用后要立即#undef。
    *   不要只是对已经存在的宏使用#undef，选择一个不会冲突的名称。
    *   不要视图使用展开后会导致C++构造不稳定的宏，不然也至少要附上文档说明其行为。
    *   不要用##处理函数，类和变量的名字。

0，nullptr和NULL：

*   整数用0。
*   实数用0.0。
*   指针用nullptr（C++11）或NULL（c++03）。
*   字符串用'\0'。

sizeof：尽可能用<code>sizeof(varname)</code>代替<code>sizeof(type)</code>。

auto：用auto绕过繁琐的类型名，只要可读性好就继续用，别用在局部变量之外的地方。

列表初始化：

*   在C++03里，聚合类型（aggregate types）就已经可以被列表初始化了，比如数组和不自带构造函数的结构体。
*   在C++11里，该特性得到进一步的推广，任何对象类型都可以被列表初始化。
*   用户自定义类型也可以定义接收std::initializer_list<T>的构造函数和赋值运算符，以自动列表初始化。当然列表初始化也适用于常规数据类型的构造，哪怕没有接收std::initializer_list<T>的构造函数。

```c++
struct Point { int x; int y; };
Point p = {1, 2};
```

```c++
// vector 接收了一个初始化列表
vector<string> v{"foo", "bar"};
// 不考虑细节上的微妙差距，大致上相同
// 您可以任选其一
vector<string> v = {"foo", "bar"};
// 可以配合 new 一起使用
auto p = new vector<string>{"foo", "bar"};
// map 接收了一些 pair，列表初始化大显神威
map<int, string> m = {{1, "one"}, {2, "2"}};
// 初始化列表也可以用在返回类型上的隐式转换
vector<int> TestFunction() { return {1, 2, 3}; }
// 初始化列表可迭代
for (int i : {-1, -2, -3}) {}
// 在函数调用里用列表初始化
void TestFunction2(vector<int> v) {}
TestFunction2({1, 2, 3});
```

```c++
class MyType {
 public:
  // std::initializer_list 专门接收 init 列表
  // 得以值传递
  MyType(std::initializer_list<int> init_list) {
    for (int i : init_list) append(i);
  }
  MyType& operator=(std::initializer_list<int> init_list) {
    clear();
    for (int i : init_list) append(i);
  }
};
MyType m{2, 3, 5, 7};
```

```c++
double d{1.23};
// MyOtherType 没有 std::initializer_list 构造函数
// 直接上接收常规类型的构造函数
class MyOtherType {
 public:
  explicit MyOtherType(string);
  MyOtherType(int, string);
};
MyOtherType m = {1, "b"};
// 不过如果构造函数是显式的 explicit，您就不能用 `= {}` 了
MyOtherType m{"b"};
```

Lambda表达式：

*   适当使用lambda表达式。别用默认的lambda捕获，所有捕获都要显式写出来。
*   禁止默认捕获，捕获都要显式写出来。
*   匿名函数始终要简短，如果函数体超过了五行，那么还不如起名，或改用函数。
*   如果可读性更好，就显式写出lambda的尾置返回类型。

```c++
[n](int x) {return x+n;}  // 好，读者可以一眼看出 n 是被捕获的值
[=](int x) {return x+n;}  // 差
```

模板编程：

*   不要使用复杂的模板编程。
*   模板编程有时候能够实现更简洁更易用的接口，但是更多的时候却适得其反。因此模版编程最好只用在少量的基础组件，基础数据结构上，因为模板带来的额外的维护成本会被大量的使用给分担掉。
*   如果你使用模板编程，你必须考虑尽可能的把复杂度最小化，并且尽量不要让模板对外爆漏。你最好只在实现里面使用模板，然后给用户暴露的接口里面不要使用模板，这样能提高你的接口的可读性，并且你应该在这些使用模板的代码上尽可能详细的注释。

Boost库：

*   只使用Boost中被认可的库。Boost代码质量普遍较高，可移植性好，填补了C++标准库的很多空白，如型别的特性，更完善的绑定器，更好的智能指针。某些Boost库提倡的编程实践可读性差，比如元编程和其他高级模板技术，以及过度“函数化”的编程风格。

| Library Name                            | File Name                 |
| --------------------------------------- | ------------------------- |
| Call Traits                             | boost/call_traits.hpp     |
| Compressed Pair                         | boost/compressed_pair.hpp |
| The Boost Graph Library                 | boost/graph               |
| Property Map                            | boost/property_map.hpp    |
| Bimap                                   | boost/bimap               |
| Statistical Distributions and Functions | boost/math/distributions  |
| Multi-index                             | boost/mylti_index         |
| Heap                                    | boost/heap                |

C++11：适当用C++11（前身是C++0x）的库和语言扩展，在贵项目用C++11特性前三思可移植性。

## 命名约定

注意：最重要的一致性规则是命名管理。命名的风格能让我们在不需要去查找类型声明的条件下快速地了解某个名字代表的含义：类型，变量，函数，常量，宏，等等。甚至，我们大脑中的模式匹配引擎非常依赖这些命名规则。命名规则具有一定随意性，但相比按个人喜好命名，一致性更重要。

通用命名规则：

*   函数命名，变量命名，文件命名要有描述性；少用缩写。
*   尽可能使用描述性的命名，别心疼空间，毕竟相比之下让代码易于新读者理解更重要。不要用只有项目开发者能理解的缩写，也不要通过砍掉几个字母来缩写单词。
*   注意，一些特定的广为人知的缩写是允许的，例如用i表示迭代变量和用T表示模板参数。

```c++
int price_count_reader;   // 无缩写
int num_errors;           // "num" 是一个常见的写法
int num_dns_connections;  // 人人都知道 "dns" 是什么
```

```c++
int n;                // 毫无意义
int nerr;             // 含糊不清的缩写
int n_comp_conns;     // 含糊不清的缩写
int wgc_connections;  // 只有贵团队知道是什么意思
int pc_reader;        // "pc" 有太多可能的解释了
int cstmr_id;         // 删减了若干字母
```

文件命名：

*   文件名要全部小写，可以包含下划线<kbd>_</kbd>或连字符<kbd>-</kbd>，依照项目的规定，如果没有规定，那么下划线更好
*   C++文件名要以.cc结尾，头文件以.h结尾。专门插入文件的文件则以.inc结尾。
*   不要使用已经存在于/usr/include下的文件名。

```txt
my_useful_class.cc
my-useful-class.cc
myusefulclass.cc
myusefulclass_test.cc
```

类型命名

*   类型名称的每个单词都字母均大写，不包括下划线：MyExcitingClass，MyExcitingEnum。
*   所有类型命名 -- 类，结构体，类型定义（typedef），枚举，类型模板参数 -- 均使用相同约定，即以大写字母开始，每个单词首字母均大写，不包含下划线。

```c++
// 类和结构体
class UrlTable { ...
class UrlTableTester { ...
struct UrlTableProperties { ...
// 类型定义
typedef hash_map<UrlTableProperties *, string> PropertiesMap;
// using 别名
using PropertiesMap = hash_map<URlTableProperties*, string>;
// 枚举
enum UrlTableErrors { ...
```

变量命名：变量（包含函数参数）和数据成员名一律小写，单词之间用下划线连接。类的成员变量以下划线结尾，但结构体的就不用。

```c++
string table_name;  // 好 - 用下划线
string tablename;   // 好 - 全小写
string tableName;   // 差 - 混合大小写
class TableInfo {
  ...
 private:
  string table_name_;             // 好
  string tablename_;              // 好
  static Pool<TableInfo>* pool_;  // 好
};
struct UrlTablProperties {
  string name;
  int num_entries;
  static Pool<UrlTableProperties>* pool;
};
```

常量命名：

*   声明为constexpr或const的变量，或在程序运行期间始终保持不变，命名时以"k"开头，大小写混合，例如<code>const int kDaysInAWeek = 7;</code>。
*   所有具有静态存储类型的变量（例如静态变量或全局变量）都应当以此方式命名。

函数命名：

*   常规函数使用大小写混合，取值和设值函数则要求变量名匹配：<code>MyExcitingFunction()</code>，<code>MyExcitingMethod()</code>，<code>my_exciting_member_variable()</code>，<code>set_my_exciting_member_variable()</code>。
*   一般来说，函数名的每个单词首字母大写，没有下划线，对于首字母缩写的单词，更倾向于将它们视作一个单词进行首字母大写（例如，写作<code>StartRpc()</code>而非<code>StartRPC()</code>）。
*   取值和设值函数的命名与变量一致。一般来说它们的名称与实际的成员变量对应，但并不强制要求。例如<code>int count()</code>与<code>void set_count(int count)</code>。

```c++
AddTableEntry()
DeleteUrl()
OpenFileOrDie()
```

命名空间命名：命名空间以小写字母命名。最高级命名空间的名字取决于项目名称。要注意避免嵌套命名空间的名字之间和常见的顶级命名空间的名字之间发生冲突。

枚举命名：

*   枚举的命名应当和常量或宏一致：kEnumName或ENUM_NAME。
*   单独的枚举值应该优先采用常量的命名方式。但宏方式的命名也可以接受。枚举名是类型，所以要用大小写混合的方式。
*   2009年1月之前，我们一直建议采用宏的方式命名枚举值。由于枚举值和宏之间的命名冲突，直接导致了很多问题。由此，这里改为优先选择常量风格的命名方式。新代码应该尽可能优先使用常量风格，但是老代码没必要切换到常量风格，除非宏风格确实会产生编译期问题。

```c++
enum UrlTableErrors {
  kOK = 0,
  kErrorOutOfMemory,
  kErrorMalformedInput,
};
enum AlternateUrlTableErrors {
  OK = 0,
  OUT_OF_MEMORY = 1,
  MALFORMED_INPUT = 2,
}
```

宏命名：尽量不使用宏，如果你一定要用，像这样命名：MY_MACRO_THAT_SCARES_SMALL_CHILDREN。

```c++
#define ROUND(x) ...
#define PI_ROUNDED 3.0
```

命名规则的特例：

*   如果你命名的实体与已有C/C++实体相似，可参考现有命名策略：
    *   `bigopen`：函数名，参照open()。
    *   `uint`：使用typedef定义的类型别名。
    *   `bigpos`：struct或者class，参照pos。
    *   `sparse_hash_map`：STL-like实体，参照STL命名规则。
    *   `LONGLONG_MAX`：常量，如同INT_MAX。

## 注释

注释虽然写起来很痛苦，但对保证代码可读性至关重要。下面对规则描述了如何注释以及在哪里注释。当然也要记住：注释固然重要，但最好的代码应当本身就是文档。有意义的类型名和变量名，要远胜过要用注释解释的含糊不清的名字。

注释风格：使用`//`或`/* */`，统一就好

文件注释：

*   在每个文件开头加上版权公告。
*   文件注释描述了该文件的内容。如果一个文件只声明，或实现，或测试了一个对象，并且这个对象已经在它的声明处进行了详细的注释，那么就没有必要再加上文件注释。除此以外的其他文件都需要文件注释。
*   每个文件都应该包含许可证引用。为项目选择合适的许可证版本。
*   如果你对原始作者的文件做了重大修改，请考虑删除原作者信息。
*   不要在.h和.cc之间复制注释，这样的注释偏离了注释的实际意义。

类注释：

*   每个类的定义都要附带一份注释，描述类的功能和用法，除非它的功能相当明显。
*   类注释应当为读者理解如何使用与何时使用类提供足够的信息，同时应当提醒读者在正确使用此类时应当考虑的因素。
*   如果类的声明和定义分开了（例如分别放在了.h和.cc文件中），此时，描述类用法的注释应当和接口定义在一起，描述类的操作和实现的注释应当和实现放在一起。

```c++
// Iterates over the contents of a GargantuanTable.
// Example:
//   GargantuanTableIterator* iter = table->NewIterator();
//   for (iter->Seek("foo"); !iter->done(); iter->Next()) {
//     process(iter->key(), iter->value());
//   }
//   delete iter;
class GargantuanTableIterator {
  ...
};
```

函数声明：

*   函数声明处的注释描述函数功能；定义处的注释描述函数实现。
*   基本上每个函数声明处前都应当加上注释，描述函数的功能和用途。只有在函数的功能简单而明显才能省略这些注释。
*   函数声明处注释的内容：
    *   函数的输入输出。
    *   对类成员函数而言：函数调用期间对象是否需要保持引用参数，是否会释放这些参数。
    *   函数是否分配了必须由调用者释放的空间。
    *   参数是否可以为空指针。
    *   是否存在函数使用上的性能隐患。
    *   如果函数是可重入的，其同步前提是什么？

```c++
// Returns an iterator for this table. It is the client's
// responsibility to delete the iterator when it is done with it,
// and it must not use the iterator once the GargantuanTable object
// on which the iterator was created has been deleted.
//
// The iterator is initially positioned at the beginning of the table.
//
// This method is equivalent to:
//   Iterator* iter = table->NewIterator();
//   iter->Seek("");
//   return iter;
// If you are going to immediately seek to another place in the
// returned iterator, it will be faster to use NewIterator()
// and avoid the extra seek.
Iterator* GetIterator() const;
```

变量注释：通常变量名本身足以很好说明变量用途。某些情况下，也需要额外的注释说明。

```c++
 private:
  // Used to bounds-check table accesses. -1 means
  // that we don't yet know how many entries the table has.
  int num_total_entries_;
// The total number of tests cases that we run through in this regression test.
const int kNumTestCases = 6;
```

实现注释：对于代码中巧妙的，晦涩的，有趣的，重要的地方加上注释。

```c++
// Divide result by two, taking into account that x
// contains the carry from the add.
for (int i = 0; i < result->size(); i++) {
  x = (x << 8) + (*result)[i];
  (*result)[i] = x >> 1;
  x &= 1;
}
```

```c++
// If we have enough memory, mmap the data portion too.
mmap_budget = max<int64>(0, mmap_budget - index_->length());
if (mmap_budget >= data_size_ && !MmapData(mmap_chunk_bytes, mlock))
  return;  // Error already logged.
```

```c++
DoSomething();                  // Comment here so the comments line up.
DoSomethingElseThatIsLonger();  // Two spaces between the code and the comment.
{  // One space before comment when opening a new scope is allowed,
   // thus the comment lines up with the following comments and code.
  DoSomethingElse();  // Two spaces before line comments normally.
}
std::vector<string> list{
    // Comments in braced lists describe the next element...
    "First item",
    // .. and should be aligned appropriately.
    "Second item"};
DoSomething();  /* For trailing block comments, one space is fine. */
```

TODO注释：对那些临时的，短期的解决方案，或已经够好但仍不完美的代码使用TODO注释。

```c++
// TODO(kl@gmail.com): Use a "*" here for concatenation operator.
// TODO(Zeke) change this to use relations.
// TODO(bug 12345): remove the "Last visitors" feature
```

## 格式

行长度：

*   每一行代码字符数不能超过80。
*   包含长路径的#include语句可以超出80列。
*   头文件保护可以无视该原则。

非ASCII字符：

*   尽量不使用非ASCII字符，使用时必须使用UTF-8编码。
*   即使是英文，也不应将用户界面的文本硬编码到源代码中，因此非ASCII字符应当很少被用到。特殊情况下可以适当包含此类字符，例如，代码分析外部数据文件时，可以适当硬编码数据文件中作为分隔符的非ASCII字符串；更常见的是（不需要本地化的）单元测试代码可能包含非ASCII字符串，此类情况下，应使用UTF-8编码，因为很多工具都可以理解和处理UTF-8编码。
*   别用C++11的char16_t和char32_t，它们和UTF-8文本没有关系，wchar_t同理，除非你写的代码要调用Windows API，后者广泛使用了wchar_t。

空格还是制表符：只使用空格，每次缩进2个空格。

函数声明与定义：

*   返回类型和函数名在同一行，参数也尽量放在同一行，如果放不下就对形参分行，分行方式与函数调用一致。
*   注意一下几点：
    *   使用好的参数名。
    *   只有在参数未被使用或者其用途非常明显时，才能省略参数名。
    *   如果返回类型和函数名在一行放不下，分行。
    *   如果返回类型与函数声明或定义分行了，不要缩进。
    *   左圆括号总是和函数名在同一行。
    *   函数名和左圆括号间永远没有空格。
    *   圆括号与参数间没有空格。
    *   左大括号总在最后一个参数同一行的末尾处，不另起新行。
    *   右大括号总是单独位于函数最后一行，或者与左大括号同一行。
    *   右圆括号和左大括号间总是有一个空格。
    *   所有形参应尽可能对齐。
    *   缺省缩进为2个空格。
    *   换行后的参数保持4个空格的缩进。

```c++
// 函数看上去像这样
ReturnType ClassName::FunctionName(Type par_name1, Type par_name2) {
  DoSomething();
  ...
}
// 如果同一行文本太多，放不下所有参数
ReturnType ClassName::ReallyLongFunctionName(Type par_name1, Type par_name2,
                                             Type par_name3) {
  DoSomething();
  ...
}
// 甚至连第一个参数都放不下
ReturnType LongClassName::ReallyReallyReallyLongFunctionName(
    Type par_name1,  // 4 space indent
    Type par_name2,
    Type par_name3) {
  DoSomething();  // 2 space indent
  ...
}
// 未被使用的参数，或者根据上下文很容易看出其用途的参数，可以省略参数名
class Foo {
 public:
  Foo(Foo&&);
  Foo(const Foo&);
  Foo& operator=(Foo&&);
  Foo& operator=(const Foo&);
};
// 未被使用的参数如果其用途不明显的话，在函数定义处将参数名注释起来
class Shape {
 public:
  virtual void Rotate(double radians) = 0;
};
class Circle : public Shape {
 public:
  void Rotate(double radians) override;
};
void Circle::Rotate(double /*radians*/) {}
// 属性，和展开为属性的宏，写在函数声明或定义的最前面，即返回类型之前
MUST_USE_RESULT bool IsOK();
```

Lambda表达式：Lambda表达式对形参和函数体的格式化和其他函数一致；捕获列表同理，表项用逗号隔开。

```c++
int x = 0;
auto add_to_x = [&x](int n) { x += n; };
```

```c++
std::set<int> blacklist = {7, 8, 9};
std::vector<int> digits = {3, 9, 1, 8, 4, 7, 1};
digits.erase(std::remove_if(digits.begin(), digits.end(), [&blacklist](int i) {
               return blacklist.find(i) != blacklist.end();
             }),
             digits.end());
```

函数调用：

*   要么一行写完函数调用，要么在圆括号里对参数分行，要么参数另起一行且缩进四格。如果没有其它顾虑的话，尽可能精简行数，比如把多个参数适当地放在同一行里。
*   把多个参数放在同一行以减少函数调用所需的行数，除非影响到可读性。有人认为把每个参数都独立成行，不仅更好读，而且方便编辑参数。不过，比起所谓的参数编辑，我们更看重可读性。

```c++
// 函数调用遵循如下形式
bool retval = DoSomething(argument1, argument2, argument3);
// 如果同一行放不下，可断为多行，后面每一行都和第一个实参对齐，左圆括号和右圆括号前不要留空格
bool retval = DoSomething(averyveryveryverylongargument1,
                          argument2, argument3);
// 参数也可以放在次行，缩进四格
if (...) {
  ...
  ...
  if (...) {
    DoSomething(
        argument1, argument2,  // 4 space indent
        argument3, argument4);
  }
// 如果一些参数本身就是略复杂的表达式，且降低了可读性，那么可以直接创建临时变量描述该表达式，并传递给函数
int my_heuristic = scores[x] * y + bases[x];
bool retval = DoSomething(my_heuristic, x, y, z);
// 或者放着不管，补充上注释
bool retval = DoSomething(scores[x] * y + bases[x],  // Score heuristic.
                          x, y, z);
// 如果一系列参数本身就有一定的结构，可以酌情地按其结构来决定参数格式
my_widget.Transform(x1, x2, x3,
                    y1, y2, y3,
                    z1, z2, z3);
```

列表初始化格式：

*   您平时怎么格式化函数调用，就怎么格式化列表初始化。
*   如果列表初始化伴随着名字，比如类型或变量名，格式化时将将名字视作函数调用名，{}视作函数调用的括号，如果没有名字，就视作名字长度为零。

```c++
// 一行列表初始化示范
return {foo, bar};
functioncall({foo, bar});
pair<int, int> p{foo, bar};
// 当不得不断行时
SomeFunction(
    {"assume a zero-length name before {"},  // 假设在 { 前有长度为零的名字
    some_other_function_parameter);
SomeType variable{
    some, other, values,
    {"assume a zero-length name before {"},  // 假设在 { 前有长度为零的名字
    SomeOtherType{
        "Very long string requiring the surrounding breaks.",  // 非常长的字符串，前后都需要断行
        some, other values},
    SomeOtherType{"Slightly shorter string",  // 稍短的字符串
                  some, other, values}};
SomeType variable{
    "This is too long to fit all in one line"};  // 字符串过长，因此无法放在同一行
MyType m = {  // 注意了，您可以在 { 前断行
    superlongvariablename1,
    superlongvariablename2,
    {short, interior, list},
    {interiorwrappinglist,
     interiorwrappinglist2}};
```

条件语句：

*   倾向于不在圆括号内使用空格，关键字if和else另起一行。
*   对基本条件语句有两种可以接受的格式，一种在圆括号和条件之间有空格，另一种没有。最常见的是没有空格的格式。哪一种都可以，最重要的是保持代码一致。如果你是在修改一个文件，参考当前已有格式。如果是写新的代码，参考目录下或项目中其他文件。还在犹豫的话，就不要加空格了。

```c++
if (condition) {  // 圆括号里没有空格
  ...  // 2 空格缩进
} else if (...) {  // else 与 if 的右括号同一行
  ...
} else {
  ...
}
// 注意所有情况下 if 和左圆括号间都有个空格，右圆括号和左大括号之间也要有个空格
if(condition)     // 差 - IF 后面没空格
if (condition){   // 差 - { 前面没空格
if(condition){    // 变本加厉地差
if (condition) {  // 好 - IF 和 { 都与空格紧邻
// 如果能增强可读性，简短的条件语句允许写在同一行
if (x == kFoo) return new Foo();
// 如果语句有 else 分支则不允许
if (x) DoThis();  // 不允许
else DoThat();
// 通常，单行语句不需要使用大括号，如果你喜欢用也没问题
if (condition)
  DoSomething();  // 2 空格缩进
if (condition) {
  DoSomething();  // 2 空格缩进
}
// 如果语句中某个 if-else 分支使用了大括号的话，其它分支也必须使用
// 不可以这样子 - IF 有大括号 ELSE 却没有
if (condition) {
  foo;
} else
  bar;
// 只要其中一个分支用了大括号，两个分支都要用上大括号
if (condition) {
  foo;
} else {
  bar;
}
```

循环和开关选择语句：switch语句中的case块可以使用大括号也可以不用，取决于你的个人喜好。在单语句循环里，括号可用可不用。空循环体应使用{}或continue，而不是一个简单的分号。

```c++
switch (var) {
  case 0:  // 2 空格缩进
    ...    // 4 空格缩进
    break;
  case 1:
    ...
    break;
  default:
    assert(false);
}
for (int i = 0; i < kSomeNumber; ++i)
  printf("I love you\n");
for (int i = 0; i < kSomeNumber; ++i) {
  printf("I take it back\n");
}
while (condition) {
  // 反复循环直到条件失效
}
for (int i = 0; i < kSomeNumber; ++i) {}  // 可 - 空循环体
while (condition) continue;  // 可 - continue 表明没有逻辑
while (condition);  // 差 - 看起来仅仅只是 while/loop 的部分之一
```

指针和引用表达式：句点和箭头前后不要有空格。指针/地址操作符前后只能有一个空格（个人习惯：C语言中指针操作符和变量名之间没空格，C++语言中指针操作符和引用操作符和类型之间没空格）。

```c++
x = *p;
p = &x;
x = r.y;
x = r->y;
// 在声明指针变量或参数时，星号与类型或变量名紧挨都可以
// 好，空格前置
char *c;
const string &str;
// 好，空格后置，C++语言中推荐
char* c;
const string& str;
int x, *y;  // 不允许 - 在多重声明中不能使用 & 或 *
char * c;  // 差 - * 两边都有空格
const string & str;  // 差 - & 两边都有空格
```

布尔表达式：

*   如果一个布尔表达式超过标准行宽，断行方式要统一一下，一般操作符总是位于行尾。
*   直接用符号形式的操作符，比如&&和~，不要用词语形式的and和compl。

```c++
if (this_one_thing > this_other_thing &&
    a_third_thing == a_fourth_thing &&
    yet_another && last_one) {
  ...
}
```

函数返回值：不要在return表达式里加上非必须的圆括号。

```c++
return result;  // 返回值很简单，没有圆括号
// 可以用圆括号把复杂表达式圈起来，改善可读性
return (some_long_condition &&
        another_condition);
```

变量及数组初始化：用`=`，`()`和`{}`均可。

```c++
int x = 3;
int x(3);
int x{3};
string name("Some Name");
string name = "Some Name";
string name{"Some Name"};
```

预处理指令：预处理指令不要缩进，从行首开始。

```c++
// 好 - 指令从行首开始
  if (lopsided_score) {
#if DISASTER_PENDING  // 正确 - 从行首开始
    DropEverything();
# if NOTIFY           // 非必要 - # 后跟空格
    NotifyClient();
# endif
#endif
    BackToNormal();
  }
// 差 - 指令缩进
  if (lopsided_score) {
    #if DISASTER_PENDING  // 差 - "#if" 应该放在行开头
    DropEverything();
    #endif                // 差 - "#endif" 不要缩进
    BackToNormal();
  }
```

类格式：

*   访问控制块的声明依次顺序是<code>public:</code>，<code>protected:</code>，<code>private:</code>，每个都缩进1个空格。
*   注意事项：
    *   所有基类名应在80列限制下尽量与子类名放在同一行。
    *   关键词<code>public:</code>，<code>protected:</code>，<code>private:</code>，要缩进1个空格。
    *   除第一个关键词（一般是public）外，其他关键词前要空一行，如果类比较小的话也可以不空。
    *   这些关键词后不要保留空行。
    *   public放在最前面，然后是protected，最后是private。

```c++
class MyClass : public OtherClass {
 public:      // 注意有一个空格的缩进
  MyClass();  // 标准的两空格缩进
  explicit MyClass(int var);
  ~MyClass() {}
  void SomeFunction();
  void SomeFunctionThatDoesNothing() {
  }
  void set_some_var(int var) { some_var_ = var; }
  int some_var() const { return some_var_; }
 private:
  bool SomeInternalFunction();
  int some_var_;
  int some_other_var_;
};
```

构造函数初始化列表：构造函数初始化列表放在同一行或按四格缩进并排多行。

```c++
// 如果所有变量能放在同一行
MyClass::MyClass(int var) : some_var_(var) {
  DoSomething();
}
// 如果不能放在同一行
// 必须置于冒号后，并缩进 4 个空格
MyClass::MyClass(int var)
    : some_var_(var), some_other_var_(var + 1) {
  DoSomething();
}
// 如果初始化列表需要置于多行，将每一个成员放在单独的一行
// 并逐行对齐
MyClass::MyClass(int var)
    : some_var_(var),             // 4 space indent
      some_other_var_(var + 1) {  // lined up
  DoSomething();
}
// 右大括号 } 可以和左大括号 { 放在同一行
// 如果这样做合适的话
MyClass::MyClass(int var)
    : some_var_(var) {}
```

命名空间格式化：命名空间内容不缩进。

```c++
namespace {
void Foo() {  // 正确，命名空间内没有额外的缩进
  ...
}
}  // namespace
// 声明嵌套命名空间时，每个命名空间都独立成行
namespace foo {
namespace bar {
```

水平留白：水平留白的使用根据在代码中的位置决定，永远不要在行尾添加没意义的留白。

```c++
void Foo(bool b) {  // 左大括号前总是有空格
  ...
int i = 0;  // 分号前不加空格
// 列表初始化中大括号内的空格是可选的
// 如果加了空格，那么两边都要加上
int x[] = { 0 };
int x[] = {0};
// 继承与初始化列表中的冒号前后恒有空格
class Foo : public Bar {
 public:
  // 对于单行函数的实现，在大括号内加上空格
  // 然后是函数实现
  Foo(int b) : Bar(), baz_(b) {}  // 大括号里面是空的话，不加空格
  void Reset() { baz_ = 0; }  // 用空格把大括号与实现分开
  ...
```

```c++
if (b) {          // if 条件语句和循环语句关键字后均有空格
} else {          // else 前后有空格
}
while (test) {}   // 圆括号内部不紧邻空格
switch (i) {
for (int i = 0; i < 5; ++i) {
switch ( i ) {    // 循环和条件语句的圆括号里可以与空格紧邻
if ( test ) {     // 但这很少见，总之要一致
for ( int i = 0; i < 5; ++i ) {
for ( ; i < 5 ; ++i) {  // 循环里内 ; 后恒有空格，; 前可以加个空格
switch (i) {
  case 1:         // switch case 的冒号前无空格
    ...
  case 2: break;  // 如果冒号有代码，加个空格
```

```c++
// 赋值运算符前后总是有空格
x = 0;
// 其它二元操作符也前后恒有空格，不过对于表达式的子式可以不加空格
// 圆括号内部没有紧邻空格
v = w * x + y / z;
v = w*x + y/z;
v = w * (x + z);
// 在参数和一元操作符之间不加空格
x = -5;
++x;
if (x && !y)
  ...
```

```c++
// 尖括号（< and >）不与空格紧邻，< 前没有空格，> 和 ( 之间也没有
vector<string> x;
y = static_cast<char*>(x);
// 在类型与指针操作符之间留空格也可以，但要保持一致
vector<char *> x;
```

垂直留白：

*   垂直留白越少越好。
*   这不仅仅是规则而是原则问题了：不在万不得已，不要使用空行。尤其是：两个函数定义之间的空行不要超过2行，函数体首尾不要留空行，函数体中也不要随意添加空行。
*   基本原则是：同一屏可以显示的代码越多，越容易理解程序的控制流。当然，过于密集的代码块和过于疏松的代码块同样难看，这取决于你的判断，但通常是垂直留白越少越好。
*   下面的规则可以让加入的空行更有效：
    *   函数体内开头或结尾的空行可读性微乎其微。
    *   在多重if-else块里加空行或许有点可读性。

## 规则特例

现有不合规范的代码：

*   对于现有不符合既定编程风格的代码可以网开一面。
*   当你修改使用其他风格的代码时，为了与代码原有风格保持一致可以不使用本指南约定。如果不放心，可以与代码原作者或现在的负责人员商讨。记住，一致性也包括原有的一致性。

Windows代码：

*   如果你习惯使用Windows编码风格，这儿有必要重申一下某些你可能会忘记的指南：
    *   不要使用匈牙利命名法（比如把整型变量命名成iNum）。使用Google命名约定，包括对源文件使用.cc扩展名。
    *   Windows定义了很多原生类型的同义词，如DWORD，HANDLE等等。在调用Windows API时这是完全可以接受甚至鼓励的。即使如此，还是尽量使用原有的C++类型，例如使用const TCHAR*而不是LPCTSTR。
*   然而，在Windows上仍然有一些我们偶尔需要违反的规则：
    *   通常我们禁止使用多重继承，但在使用COM和ATL/WTL类时可以使用多重继承。为了实现COM或ATL/WTL类/接口，你可能不得不使用多重实现继承。
    *   资源头文件通常命名为resource.h且只包含宏，这一文件不需要遵守本风格指南。

## 结束语

运用常识和判断力，并且保持一致。

编辑代码时，花点时间看看项目中的其它代码，并熟悉其风格。如果其它代码中if语句使用空格，那么你也要使用，如果其中的注释用星号（*）围成一个盒子状，那么你同样要这么做。

风格指南的重点在于提供一个通用的编程规范，这样大家可以把精力集中在实现内容而不是表现形式上。我们展示的是一个总体的的风格规范，但局部风格也很重要，如果你在一个文件中新加的代码和原有代码风格相去甚远，这就破坏了文件本身的整体美观，也会打乱读者在阅读代码时的节奏，所以要尽量避免。

好了，关于编码风格写的够多了；代码本身才更有趣，尽情享受吧！
