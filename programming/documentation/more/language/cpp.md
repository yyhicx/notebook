# CPP

## CPP简介

C++是由Bjarne Stroustrup于1979年在新泽西州贝尔实验室开始设计开发的。C++进一步扩充和完善了C语言，最初命名为带类的C，后来在1983年更名为C++。C++是C的一个超集，事实上，任何合法的C程序都是合法的C++程序。

C++是一种静态类型的、编译式的、通用的、大小写敏感的、不规则的编程语言，支持过程化编程、面向对象编程和泛型编程。

C++完全支持面向对象的程序设计，包括面向对象开发的四大特性：

*   封装（Encapsulation）：封装是将数据和方法组合在一起，对外部隐藏实现细节，只公开对外提供的接口。这样可以提高安全性、可靠性和灵活性。
*   继承（Inheritance）：继承是从已有类中派生出新类，新类具有已有类的属性和方法，并且可以扩展或修改这些属性和方法。这样可以提高代码的复用性和可扩展性。
*   多态（Polymorphism）：多态是指同一种操作作用于不同的对象，可以有不同的解释和实现。它可以通过接口或继承实现，可以提高代码的灵活性和可读性。
*   抽象（Abstraction）：抽象是从具体的实例中提取共同的特征，形成抽象类或接口，以便于代码的复用和扩展。抽象类和接口可以让程序员专注于高层次的设计和业务逻辑，而不必关注底层的实现细节。

## CPP基础

C++标识符：

*   C++标识符是用来标识变量、函数、类、模块，或任何其他用户自定义项目的名称。一个标识符以字母（A-Z或a-z）或下划线（_）开始，后跟零个或多个字母、下划线和数字（0-9）。
*   C++标识符内不允许出现标点字符，比如@、&和%。C++是区分大小写的编程语言。

C++关键字：

![C++ Keywords](../resources/cpp_keywords.png)

C++注释：单行注释（`//`）和多行注释（`/* */`）。

C++数据类型：

*   内置数据类型：
    *   整型：int、short、long、long long，以及无符号版本（如unsigned int等）。默认情况下，整型都是带符号版本，如int默认就是signed int。
    *   浮点型：float、double、long double。
    *   字符型：char、wchar_t。
    *   布尔型：bool。
    *   指针型：用于存储内存地址，如int*、double*。
    *   引用型：引用是已存在变量的别名，如int&，double&。
    *   其他类型：void（无类型）、void*（通用指针类型）、site_t（数组索引）、ptrdiff_t（指针运算）。
*   用户自定义数据类型：
    *   结构体（struct）：结构体允许你将多个不同类型的变量组合成一个单一的类型。
    *   类（class）：类是C++中面向对象编程的基础。类可以包含数据成员和成员函数。
    *   联合体（union）：联合体允许你在相同的内存位置存储不同的数据类型。但联合体只能同时存储一种类型的值。
    *   枚举（enum）：枚举类型是一种用户定义的整型类型，它可以用来定义一组相关常量。
    *   模板：虽然模板本身不是数据类型，但它允许你创建泛型类或函数，这些类或函数可以处理多种数据类型。
    *   类型别名：使用typedef或using关键字，可以为已存在的类型创建别名。

```c++
/* Print size of types */
#include <iostream>
#include <limits>
using namespace std;

struct Point {
  int x;
  int y;
};

class Rectangle {
 public:
  int width;
  int height;

  Rectangle(int w, int h) : width(w), height(h) {}

  int Area() { return width * height; }
};

union Data {
  int i;
  double d;
};

enum Color { RED, GREEN, BLUE };

int main() {
  cout << "********************************** size of type **********************************" << endl;
  cout << "bool: \t\tbytes:" << sizeof(bool);
  cout << "\t\t max value:" << static_cast<int>((numeric_limits<bool>::max)());
  cout << "\t\t\t min value:" << static_cast<int>((numeric_limits<bool>::min)()) << endl;
  cout << "char: \t\tbytes:" << sizeof(char);
  cout << "\t\t max value:" << static_cast<int>((numeric_limits<char>::max)());
  cout << "\t\t\t min value:" << static_cast<int>((numeric_limits<char>::min)()) << endl;
  cout << "signed char: \tbytes:" << sizeof(signed char);
  cout << "\t\t max value:" << static_cast<int>((numeric_limits<signed char>::max)());
  cout << "\t\t\t min value:" << static_cast<int>((numeric_limits<signed char>::min)()) << endl;
  cout << "unsigned char: \tbytes:" << sizeof(unsigned char);
  cout << "\t\t max value:" << static_cast<int>((numeric_limits<unsigned char>::max)());
  cout << "\t\t\t min value:" << static_cast<int>((numeric_limits<unsigned char>::min)()) << endl;
  cout << "wchar_t: \tbytes:" << sizeof(wchar_t);
  cout << "\t\t max value:" << static_cast<int>((numeric_limits<wchar_t>::max)());
  cout << "\t\t min value:" << static_cast<int>((numeric_limits<wchar_t>::min)()) << endl;
  cout << "short: \t\tbytes:" << sizeof(short);
  cout << "\t\t max value:" << (numeric_limits<short>::max)();
  cout << "\t\t min value:" << (numeric_limits<short>::min)() << endl;
  cout << "int: \t\tbytes:" << sizeof(int);
  cout << "\t\t max value:" << (numeric_limits<int>::max)();
  cout << "\t\t min value:" << (numeric_limits<int>::min)() << endl;
  cout << "unsigned: \tbytes:" << sizeof(unsigned);
  cout << "\t\t max value:" << (numeric_limits<unsigned>::max)();
  cout << "\t\t min value:" << (numeric_limits<unsigned>::min)() << endl;
  cout << "long: \t\tbytes:" << sizeof(long);
  cout << "\t\t max value:" << (numeric_limits<long>::max)();
  cout << "\t\t min value:" << (numeric_limits<long>::min)() << endl;
  cout << "long long: \tbytes:" << sizeof(long long);
  cout << "\t\t max value:" << (numeric_limits<long long>::max)();
  cout << "\t min value:" << (numeric_limits<long long>::min)() << endl;
  cout << "float: \t\tbytes:" << sizeof(float);
  cout << "\t\t max value:" << (numeric_limits<float>::max)();
  cout << "\t\t min value:" << (numeric_limits<float>::min)() << endl;
  cout << "double: \tbytes:" << sizeof(double);
  cout << "\t\t max value:" << (numeric_limits<double>::max)();
  cout << "\t\t min value:" << (numeric_limits<double>::min)() << endl;
  cout << "long double: \tbytes:" << sizeof(long double);
  cout << "\t max value:" << (numeric_limits<long double>::max)();
  cout << "\t min value:" << (numeric_limits<long double>::min)() << endl;
  cout << "int*: \t\tbytes:" << sizeof(int *) << endl;
  cout << "double*: \tbytes:" << sizeof(double *) << endl;
  cout << "int&: \t\tbytes:" << sizeof(int &) << endl;
  cout << "double&: \tbytes:" << sizeof(double &) << endl;
  cout << "void*: \t\tbytes:" << sizeof(void *) << endl;
  cout << "size_t: \tbytes:" << sizeof(size_t);
  cout << "\t\t max value:" << (numeric_limits<size_t>::max)();
  cout << "\t\t min value:" << (numeric_limits<size_t>::min)() << endl;
  cout << "ptrdiff_t: \tbytes:" << sizeof(ptrdiff_t);
  cout << "\t\t max value:" << (numeric_limits<ptrdiff_t>::max)();
  cout << "\t\t min value:" << (numeric_limits<ptrdiff_t>::min)() << endl;
  cout << "string: \tbytes:" << sizeof(string) << endl;
  cout << "struct Point: \tbytes:" << sizeof(struct Point) << endl;
  cout << "Rectangle: \tbytes:" << sizeof(Rectangle) << endl;
  cout << "union Data: \tbytes:" << sizeof(union Data) << endl;
  cout << "enum Color: \tbytes:" << sizeof(enum Color) << endl;
  cout << "********************************** size of type **********************************" << endl;
  return 0;
}
```

类型转换：将一个数据类型的值转换为另一个数据类型的值。

*   隐式类型转换（Implicit Type Conversion）：由编译器自动完成，无需显式指示，但个人不推荐。
    *   算术运算：当两个不同类型的数据进行算术运算时，较低精度的数据类型会自动转换为较高精度的数据类型。

        ```c++
        cout << int_var + doouble_var << endl;
        ```

    *   赋值运算：当将一个值赋给一个不同类型的变量时，如果这个转换是安全的，编译器会自动进行类型转换。

        ```c++
        int int_var = double_var;
        ```

    *   函数调用：当函数的参数类型与实际传递的参数类型不同时，编译器会自动进行类型转换。

        ```c++
        void PrintInteger(int i) { /* doSomething */ }
        PrintInteger(double_var);
        ```

*   显式类型转换（Explicit Type Conversion）：
    *   C风格的强制类型转换：使用`(type)expression`语法进行转换。
    *   函数风格的强制类型转换：使用`type(expression)`语法进行转换。
    *   C++风格的强制类型转换：
        *   静态转换（static_cast）：用于执行常规的类型转换，如基本数据类型之间的转换、指针类型的转换、类层次结构中的上行转换和一些用户自定义的转换，但不进行运行时类型检查。

            ```c++
            int int_var = static_cast<int>(double_var);  // 基本数据类型之间的转换

            Derived derived;
            Base* base_ptr = static_cast<Base*>(&derived); // 上行转换
            ```

        *   动态转换（dynamic_cast）：主要用于多态类型的转换，尤其是在运行时进行类型检查和安全向下转换（从基类指针或引用转换成派生类指针或引用）。因为进行类型检查，如果不能进行转换则返回空指针或引发异常。

            ```c++
            Base* base_ptr = new Derived();
            Derived* derived_ptr = dynamic_cast<Derived*>(base_ptr); // 下行转换
            ```

        *   常量转换（const_cast）：用于添加或移除变量的const属性，它通常用于函数重载或者处理遗留代码中的const不一致问题。

            ```c++
            const int const_var = 42;
            int* non_const_ptr = const_cast<int*>(&const_var);
            ```

        *   重新解释转换（reinterpret_cast）：用于指针类型之间的位模式转换，或者将指针转换为足够大的整数类型，通常用于底层操作。使用reinterpret_cast时，必须非常小心，因为它可能导致未定义行为。

            ```c++
            int int_var = 42;
            int* int_ptr = &int_var;
            uintptr_t uiptr = reinterpret_cast<uintptr_t>(int_ptr); // 将指针转换为整数，以进行地址计算
            ```
