# 字符串、向量和数组

## 命名空间的using声明

作用域操作符（::）：编译器应从操作符左侧名字所示的作用域中寻找右侧那个名字。

using声明（using declaration）：

*   在使用了using声明之后，就可以直接访问命名空间中的名字，而无须专门的前缀（形如namespace::）。
*   每个名字都需要独立的using声明，每个using声明只能引入命名空间中的一个成员。
*   头文件不应包含using声明，防止产生始料未及的名字冲突。

```c++
// using namespace::name;
using std::cin;
using std::cout;
using std::endl;
```

## 标准库类型string

C++语言有几种不同的初始化方式：

*   拷贝初始化（copy initialization, means construction rather than assignment）：如果使用等号初始化一个变量，实际上执行的是拷贝初始化，编译器把等号右侧的初始值拷贝到新创建的对象中去。
*   直接初始化（direct initialization）：如果不使用等号，则执行的是直接初始化。
*   可以使用explicit关键字，禁止拷贝初始化，如下面第二个示例。

```c++
std::string s2(s1);   // direct initialization
std::string s3 = s1;  // copy initialization

int iarr[3] = {1, 2, 3};  // array initialization

// initialization of POD types
struct A {
  int x;
  struct B {
    int i;
    int j;
  } b;
} a = {1, {2, 3}};
```

```c++
class TestClass {
 public:
  // the copy constructor is declared explicit now
  explicit TestClass(const TestClass& t): a_(t.a_) {
    std::cout << "Copy constructor" << std::endl;
  }
  TestClass(int aa): a_(aa) {
    std::cout << "Constructor" << std::endl;
  }
  int a_;
};

int main() {
  TestClass t0(1);
  TestClass t1(t0);   // fine; explicit constructor works fine with direct initialization
  TestClass t2 = t0;  // error; explicit constructor won't be considered for copy initialization
}
```

标准库类型string表示可变长的字符序列。使用string类型必须首先包含string头文件。作为标准库的一部分，string定义在命名空间std中。

初始化string对象的方式，如下面这个表格：

 |       表达式        |                         说明                          |
 | :-----------------: | :---------------------------------------------------: |
 |      string s1      |               默认初始化，s1是一个空串                |
 |    string s2(s1)    |                     s2是s1的副本                      |
 |   string s2 = s1    |                     s2是s1的副本                      |
 | string s3("value")  | s3是字面值"value"的副本，除了字面值最后的那个空字符外 |
 | string s3 = "value" | s3是字面值"value"的副本，除了字面值最后的那个空字符外 |
 |  string s4(n, 'c')  |          把s4初始化为由连续n个字符c组成的串           |

string对象上的操作，如下面这个表格：

 |     表达式     |                           说明                           |
 | :------------: | :------------------------------------------------------: |
 |    os << s     |               将s写到输出流os当中，返回os                |
 |    is >> s     |     从is中读取字符串赋给s，字符串以空白分隔，返回is      |
 | getline(is, s) |               从is中读取一行赋给s，返回is                |
 |   s.empty()    |               s为空返回true，否则返回false               |
 |    s.size()    |     返回s中字符的个数，返回值类型是string::size_type     |
 |      s[n]      |           返回s中第n个字符的引用，位置n从0计起           |
 |    s1 + s2     |                  返回s1和s2连接后的结果                  |
 |    s1 = s2     |               用s2的副本代替s1中原来的字符               |
 |    s1 == s2    |   如果s1和s2中所含的字符完全一样，则它们相等，返回true   |
 |    s1 != s2    | 如果s1和s2中所含的字符并不完全一样，则它们不等，返回true |
 |  <, <=, >, >=  |   利用字符在字典中的顺序进行比较，且对字母的大小写敏感   |

size_type：

*   string类型及其它大多数标准库类型都定义了几种配套的类型。这些配套类型体现了标准库类型与机器无关的特性，类型size_type即是其中的一种。
*   我们不太清楚string::size_type类型的细节，但有一点是肯定的：它是一个无符号类型的值而且能足够存放下任何string对象的大小。
*   所有用于存放string类的size函数返回值的变量，都应该是string::size_type类型的。

cctype头文件定义了一组标准库函数，用于判断字符的类型或改变字符的值：

*   判断字符的类型：isalnum、isalpha、iscntrl、isdigit、isgraph、islower、isprint、ispunct、isspace、isupper和isxdigit。

*   改变字符的值：tolower和toupper。

遍历string对象中的每个元素：

*   索引遍历，即使用下标运算符：
    *   string对象的下标必须大于等于0而小于s.size()。
    *   使用超出此范围的下标将引发不可预知的结果，以此推断，使用下标访问空string也会引发不可预知的结果。
    *   C++标准并不要求标准库检测下标是否合法。一旦使用了一个超过范围的下标，就会产生不可预知的结果。
*   迭代器遍历。不能在迭代器遍历过程中改变其所遍历序列的大小，会导致迭代器失效。
*   基于范围for语句，C++11新标准。使用范围for语句时，不应该在for语句体内改变其所遍历序列的大小。

```c++
using std::string;

string str("value");

// index loop
for (string::size_type i = 0; i < str.size(); i++) {
  std::cout << str[i] << std::endl;
}

// iterator loop
for (string::iterator it = str.begin(); it != str.end(); it++) {
  std::cout << *it << std::endl;
}

// Range-based for loop
for (const auto& c : str) {
  std::cout << c << std::endl;
}
```

src: read_file/main.cc

## 标准库类型vector

模板（template）：

*   模板本身不是类或函数，相反可以将模板看作为编译器生成类或函数编写的一份说明。编译器根据模板创建类或函数的过程称为实例化（instantiation），当使用模板时，需要指出编译器应把类或函数实例化成何种类型。
*   C++语言既有类模板，也有函数模版，其中vector是一个类模板。

标准库类型vector表示对象的集合，其中所有对象的类型都相同。使用vector类型必须首先包含vector头文件。作为标准库的一部分，vector定义在命名空间std中。因为vector“容纳着”其它对象，所以它也常被称作容器（container）。

```c++
std::vector<int> ivec;
std::vector<SalesItem> sivec;
std::vector<std::vector<std::string>> files;   // in c++11
std::vector<std::vector<std::string> > files;  // in c++03
```

C++11新标准提供了一种新的初始化方法，即列表初始化（list initialize）。原理是通过C++11提供的std::initializer_list来实现，使用std::initializer_list需要包含initializer_list头文件。

```c++
int arr[]{1, 2, 3};
std::set<int> ss = {1, 2, 3};
```

```c++
class FooVector {
 public:
  FooVector(std::initializer_list<int> list) {
    for (auto it = list.begin(); it != list.end(); ++it) {
      content_.push_back(*it);
    }
  }
  /* ... */

 private:
  std::vector<int> content_;
};
```

初始化vector对象的方式：

*   只提供vector对象容纳的元素数量而省略掉初始值（如下面表格中v4），此时标准库会创建一个值初始化的（value-initialized）元素初值，并把它赋给容器中的所有元素。这个初值由vector对象中元素的类型决定：如果vector对象的元素是内置类型，比如int，则元素初始值自动设为0；如果元素是某种类类型，比如string，则元素由类默认初始化。
*   使用C++11标准提供的列表初始化（如下面表格中v5）。
*   特殊情况：如果初始化时使用了花括号的形式但是提供的值又不能用来列表初始化，编译器会尝试用花括号中的值调用构造函数初始化vector对象，如下面的示例。

 |           表达式            |                              说明                               |
 | :-------------------------: | :-------------------------------------------------------------: |
 |       `vector<T> v1`        |     v1是一个空vector，它潜在的元素是T类型的，执行默认初始化     |
 |     `vector<T> v2(v1)`      |                   v2中包含有v1所有元素的副本                    |
 |     `vector<T> v2 = v1`     |                   v2中包含有v1所有元素的副本                    |
 |   `vector<T> v3(n, val)`    |           v3包含了n个重复的元素，每个元素的值都是val            |
 |      `vector<T> v4(n)`      |              v4包含了n个重复地执行了值初始化的对象              |
 |  `vector<T> v5{a,b,c...}`   | v5包含了初始值个数的元素，每个元素被赋予相应的初始值，C++新标准 |
 | `vector<T> v5 = {a,b,c...}` |                 等价于v5{a,b,c...}，C++11新标准                 |

```c++
std::vector<std::string> v7{10};        // v7 有 10 个默认初始化的元素
std::vector<std::string> v8{10, "hi"};  // v8 有 10 个值为 "h1" 的元素
```

vector对象上的操作，如下面这个表格：

 |     表达式      |                                                                                           说明                                                                                           |
 | :-------------: | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
 |    v.empty()    |                                                                         如果v不含有任何元素，返回真；否则返回假                                                                          |
 |    v.size()     |                                                                  返回v中元素的个数，返回值类型为`vector<T>::size_type`                                                                   |
 | v.push_back(t)  |                                                                               向v的尾部添加一个值为t的元素                                                                               |
 |      v[n]       |                                                                               返回v中第n个位置上元素的引用                                                                               |
 |     v1 = v2     |                                                                              用v2中的元素拷贝替换v1中的元素                                                                              |
 | v1 = {a,b,c...} |                                                                             用列表中元素的拷贝替换v1中的元素                                                                             |
 |    v1 == v2     |                                                    v1和v2相等则返回true，v1和v2相等当且仅当它们的元素数量相同且对应位置的元素值都相同                                                    |
 |    v1 != v2     |                                                                                  v1和v2不相等则返回true                                                                                  |
 |  <, <=, >, >=   | 如果两个vector对象的容量不同，但是在相同位置上的元素值都一样，则元素较少的vector对象小于元素较多的vector对象；若元素的值有区别，则vector对象的大小关系由第一对相异的元素值的大小关系决定 |

src: calculate_grades.cc

## 迭代器介绍

使用迭代器：

*   有迭代器的类型同时拥有返回迭代器的成员。
*   有迭代器的类型都拥有名为begin和end的成员，其中begin成员负责返回指向第一个元素的迭代器，end成员则负责返回指向尾元素的下一个位置的迭代器。
*   end成员返回的迭代器常被称为尾后迭代器（off-the-end iterator）或者简称为尾迭代器（end iterator）。
*   特殊情况下如果容器为空，则begin和end返回的是同一个迭代器，都是尾后迭代器。

```c++
std::vector<int> ivec = {1, 2, 3, 4, 5};

for (std::vector<int>::iterator it = ivec.begin(); it != ivec.end(); it++) {
  std::cout << *it << std::endl;
}
```

标准容器迭代器支持的运算符，如下面这个表格：

|     表达式     |                           说明                           |
| :------------: | :------------------------------------------------------: |
|     *iter      |               返回迭代器iter所指元素的引用               |
|   iter->mem    | 解引用iter并获取该元素的名为mem的成员，等价于(*iter).mem |
|     ++iter     |               令iter指示容器中的下一个元素               |
|     --iter     |               令iter指示容器中的上一个元素               |
| iter1 == iter2 |                  判断两个迭代器是否相等                  |
| iter1 != iter2 |                 判断两个迭代器是否不相等                 |

迭代器类型：

*   拥有迭代器的标准库类型使用iterator和const_iterator来表示迭代器的类型。const_iterator能读取但不能修改它所指的元素值；iterator的对象可读可写。
*   如果支持迭代器的对象是一个常量，只能使用const_iterator；如果对象不是常量，那么既能使用iterator也能使用const_iterator。
*   C++11新标准引入了两个新函数，分别是cbegin和cend。类似于begin和end，上述两个新函数也分别返回指示容器第一个元素或最后元素下一个位置的元素。有所不同的是，不论该支持迭代器的对象本身是否是常量，返回值都是const_iterator类型。

vector和string迭代器支持的运算符，如下面的表格：

|    表达式     |                                                                                                                                    说明                                                                                                                                    |
| :-----------: | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|   iter + n    |                                                                                            迭代器加上一个整数值仍得一个迭代器，迭代器指示的新位置与原来相比向前移动了若干个元素                                                                                            |
|   iter - n    |                                                                                            迭代器减去一个整数值仍得一个迭代器，迭代器指示的新位置与原来相比向后移动了若干个元素                                                                                            |
|  iter1 += n   |                                                                                                            迭代器加法的复合赋值语句，将iter1加n的结果赋给iter1                                                                                                             |
|  iter1 -= n   |                                                                                                            迭代器减法的复合赋值语句，将iter1减n的结果赋给iter1                                                                                                             |
| iter1 - iter2 | 两个迭代器相减的结果是它们之间的距离，也就是说，将运算符右侧的迭代器向前移动差值个元素后将得到左侧的迭代器。参与运算的两个迭代器必须指向的是同一个容器中的元素或者尾元素的下一个位置。如果两个迭代器是string的迭代器，则iter1 - iter2的返回值类型为string::difference_type |
| <, <=, >, >=  |                                                    迭代器的关系运算符，如果某迭代器指向的容器位置在另一个迭代器所指位置之前，则说前者小于后者。参与运算的两个迭代器必须指向的是同一个容器中的元素或者尾元素的下一个位置                                                    |

src: binary_search.cc

## 数组

定义和初始化内置数组：

*   数组是一种复合类型，其声明形如a[d]：
    *   a是数组的名字。
    *   d是数组的维度，表示数组中元素个数，必须大于0。
    *   数组中元素的个数也属于数组类型的一部分，编译的时候维度应该是已知的，即维度必须是一个常量表达式（constant expression）。
*   初始化数组：
    *   默认情况下，数组的元素被默认初始化。
    *   显式初始化数组元素
