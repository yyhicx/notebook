# Dart

1.  [Dart基础](#dart基础)
2.  [Dart速查表](#dart速查表)

## Dart基础

Dart语言是一门纯对象的编程语言。这意味着Dart语言在运行时所处理的值都是对象，甚至包括一些其他语言常见的基本类型的值（如数值、布尔值等）以及函数都是对象，所有对象的父类都是`Object`。

内置类型：

*   数值类型：`int`、`double`、`num`。
*   布尔类型：`bool`。
*   字符串类型：`String`。
*   列表类型：`List`。
*   键值对类型：`Map`。
*   注意：
    *   不支持内置强制类型转换。
    *   不支持整数类型和布尔类型之间的相互转换，例如在`if`语句的条件表达式中使用整数会导致编译错误。
    *   支持单引号、双引号创建字符串，也可以使用3个单引号或双引号创建多行字符串，还可以使用`r`前缀创建原始字符串。

Dart中特有的运算符：

*   避空运算符：`??`、`??=`。
*   级联运算符：`..`。

重载运算符：

```dart
class Point {
  double x, y;

  Point(this.x, this.y);

  operator +(Point other) {
    return Point(x + other.x, y + other.y);
  }

  @override
  String toString() {
    return 'Point($x, $y)';
  }
}
```

函数：函数也是对象，并且函数的对象类型为`Function`，这一点与JavaScript类似。在JavaScript中，函数还可以作为参数传递；在Dart语言中也一样，函数能保存在变量中，也能作为参数和函数的返回值。

Dart语言支持继承、多态、抽象类、接口、mixin等特性。

*   继承：Dart支持单继承，但支持多重继承。
*   多态：Dart支持运行时多态，这意味着你可以在运行时根据对象的实际类型调用不同的方法。
*   抽象类：Dart支持抽象类，抽象类不能实例化，只能作为基类被继承。
*   接口：Dart支持接口，接口是抽象类，但它不能实例化，只能作为其他类实现。
*   mixin：Dart语言独有的混入语法特性，它的实现是为了解决多继承问题。mixin是一个可以把自己的方法提供给其他类，而不用成为其父类的类，它以非继承的方式来复用类中的方法。

```dart
abstract class Eatable {
  void eat();
}

mixin Running {
  void run() {
    print('I am running.');
  }
}

class Animal {
  String name;

  Animal(this.name);

  void sound() {
    print('The $name makes a sound.');
  }

  @override
  String toString() {
    return 'An animal named $name.';
  }
}

class Dog extends Animal with Running implements Eatable {
  Dog(String name) : super(name);

  void bark() {
    print('The $name barks.');
  }

  @override
  String toString() {
    return 'An dog named $name';
  }

  @override
  void eat() {
    print('The $name is eating.');
  }
}

void main() {
  Animal animal = Dog('Fido');
  Dog dog = Dog('Rufus');

  animal.sound();
  // animal.bark();  // Error: 'Animal' doesn't have a method named 'bark'
  print(animal);
  dog.sound();
  dog.bark();
  dog.eat();
  dog.run();
  print(dog);
}
```

泛型：

```dart
class MyGenericClass<T> {
  T value;

  MyGenericClass(this.value);

  T getValue() {
    return value;
  }
}
```

库：

*   导入库：

    ```dart
    import 'dart:math';
    import 'package:http/http.dart' as http;
    ```

*   拆分库：

    ```dart
    // my_library.dart
    library my_library;
    part 'a.dart';
    part 'b.dart';

    // a.dart
    part of my_library;
    void printSomething() {
      print('Something');
    }
    
    // b.dart
    part of my_library;
    void printSomethingElse() {
      print('Something else');
    }
    ```

## Dart速查表

字符串插值：为了将表达式的值放在字符串中，使用`${expression}`。若表达式为单个标识符，可省略`{}`。

```dart
'${3 + 2}'                 // '5'
'${"word".toUpperCase()}'  // 'WORD'
'$myObject'                // myObject.toString()
```

可空的变量：Dart 2.12引入了健全的空安全，这意味着在启动了空安全后，除非变量显式声明为空类型，否则它们将不能为空。

```dart
int a = null;   // INVALID in null-safe Dart
int? a = null;  // Valid in null-safe Dart
```

避空运算符：`??=`和`??`。

```dart
a ??= 3;         // if a = null, a = 3, else unchanged
print(a ?? 12);  // if a = null, print 12, else print a
```

条件属性访问：要保护可能会是空的属性的正常访问，请在点（.）之前加上一个问好（?）。

```dart
myObject?.someProperty;
(myObject != null) ? myObject.someProperty : null;  // Equivalent to the above code
```

集合字面量：Dart内置了对list、map以及set的支持。Dart的类型推断可以自动帮你分配这些变量的类型。

```dart
final aListOfStrings = ['one', 'two', 'three'];  // List<String>
final aSetOfStrings = {'one', 'two', 'three'};   // Set<String>
final aMapOfStringsToInts = {'one': 1, 'two': 2, 'three': 3};  // Map<String, int>
```

箭头语法：`=>`符号是一种定义函数的方法，该函数将在其右侧执行表达式并返回其值。

```dart
bool hasEmpty = aListOfStrings.any((s) {
  return a.isEmpty;
});
bool hasEmpty = aListOfStrings.any((s) => s.isEmpty);  // Equivalent to the above code
```

级连：要对同一对象执行一系列操作，请使用`..`符号。

```dart
var button = querySelector('#confirm');
button?.text = 'Confirm';
button?.classes.add('important');
button?.onClick.listen((e) => window.alert('Confirmed!'));
querySelector('#confirm')  // Equivalent to the above code
  ?..text = 'Confirm'
  ..classes.add('important')
  ..onClick.listen((e) => window.alert('Confirmed!'));
```

Getters and setters：

```dart
class MyClass {
  int _aProperty = 0;
  int get aProperty => _aProperty;
  set aProperty(int value) {
    if (value >= 0) {
      _aProperty = value;
    }
  }
}
class MyClass {
  final List<int> _values = [];
  void addValue(int value) {
    _values.add(value);
  }
  int get count {
    return _values.length;
  }
}
```

Dart有两种传参方式：位置参数和命名参数。一个方法不能同时使用可选位置参数和可选命名参数。

```dart
/* 位置参数 */
int sumUp(int a, int b, int c) {
  return a + b + c;
}
int sumUpToFive(int a, [int? b, int? c, int? d, int? e]) {
  int sum = a;
  if (b != null) sum += b;
  if (c != null) sum += c;
  if (d != null) sum += d;
  if (e != null) sum += e;
  return sum;
}
int total = sumUpToFive(1, 2);
int otherTotal = sumUpToFive(1, 2, 3, 4, 5);

/* 命名参数 */
void printName(String firstName, String lastName, {String? suffix}) {
  print('$firstName $lastName ${suffix ?? ''}');
}
printName('Avinash', 'Gupta');
printName('Poshmeister', 'Moneybuckets', suffix: 'IV');
```

构造方法：

*   在构造方法中使用this：

    ```dart
    class MyColor {
      int red;
      int green;
      int blue;
      MyColor(this.red, this.green, this.blue);
    }
    final color = MyColor(80, 80, 128);
    class MyColor {
      // ...
      MyColor({required this.red, required this.green, required this.blue});
    }
    final color = MyColor(red: 80, green: 80, blue: 80);
    ```

*   Initializer lists：

    ```dart
    class Point {
      double? x;
      double? y;
      Point.fromJson(Map<String, double> json)
        : x = json['x'],
          y = json['y'] {
        print('In Point.fromJson(): ($x, $y)');
      }
    }
    NonNegativePoint(this.x, this.y)  // 初始化列表也是放置断言的便利位置，它仅会在开发期间运行
      : assert(x >= 0),
        assert(y >= 0) {
      print('I just made a NonNegativePoint: ($x, $y)');
    }
    ```

*   命名构造方法：

    ```dart
    class Point {
      double x, y;
      Point(this.x, this.y);
      Point.origin()
        : x = 0,
          y = 0;
    }
    ```

*   工厂构造方法：能够返回其子类甚至`null`对象。

    ```dart
    class Square extends Shape {}
    class Circle extends Shape {}
    class Shape {
      Shape();
      factory Shape.fromTypeName(String typeName) {
        if (typeName == 'square') return Square();
        if (typeName == 'circle') return Circle();
        throw ArgumentError('Unrecognized $typeName');
      }
    }
    class Rectangle {
      double width, height;
      Rectangle({required this.width, required this.height});
      factory Rectangle.withData() {
        return Rectangle(
          width: _createData()['width'],
          height: _createData()['height'],
        );
      }
      static Map _createData() {
        return {'width': 10.0, 'height': 10.0};
      }
    }
    Rectangle rect = Rectangle.withData();
    ```

*   重定向构造方法：

    ```dart
    class Automobile {
      String make;
      String model;
      int mpg;
      Automobile(this.make, this.model, this.mpg);
      Automobile.hybrid(String make, String model) : this(make, model, 60);
      Automobile.fancyHybrid(): this.hybrid('Futurecar', 'Mark 2');
    }
    ```

*   Const构造方法：如果类生成的对象永远不变，则可以让这些对象成为编译时常量。

    ```dart
    class ImmutablePoint {
      static const ImmutablePoint origin = ImmutablePoint(0, 0);
      final int x;
      final int y;
      const ImmutablePoint(this.x, this.y);
    }
    ```

异常：Dart代码可以抛出和捕获异常。与Java相比，Dart的所有异常都是unchecked exception。方法不会声明它们可能抛出的异常，你也不需要捕获任何异常。

```dart
throw Exception('Something bad happened.');
throw 'Waaaaaaah!';
try {
  breedMoreLlamas();
} on OutOfLlamasException {
  // A specific exception
  buyMoreLlamas();
} on Exception catch (e) {
  // Anything else that is an exception
  print('Unknown exception: $e');
} catch (e) {
  // No specified type, handles all
  print('Something really unknown: $e');
} finally {
  // Always clean up, even if an exception is thrown
  cleanLlamaStalls();
}
```

可迭代集合：

*   集合是表示一组对象的对象，这些对象称为元素。可迭代对象是一种集合。
*   List：用于按索引读取元素。
*   Set：用于包含只能出现一次的元素。
*   Map：用于使用键读取元素。

```dart
void main() {
Iterable<int> iterable = [1, 2, 3];
int value = iterable.elementAt(1); 
print(value);  // Print 2
for (final element in iterable) {
  print(element);  // Print 1, 2, 3
}
print(iterable.first);  // Print 1
print(iterable.last);   // Print 3
}
// firstWhere(): Find the first element that satisfies certain conditions.
int element = iterable.firstWhere((element) => element > 1);
// singleWhere(): This method works similarly to firstWhere(), but in this case it expects only one element of the Iterable to satisfy the predicate. If more than one or no element in the Iterable satisfies the predicate condition, then the method throws a StateError exception.
return items.singleWhere((element) => element.startsWith('M') && element.contains('a'));
// where(): Find all the elements that satisfy a certain condition.
var evenNumbers = numbers.where((number) => number.isEven);
for (final number in evenNumbers) {
  print('$number is even');
}
// takeWhile(): Returns an Iterable that contains all the elements before the one that satisfies the predicate.
// skipWhile(): Returns an Iterable that contains all elements after and including the first one that doesn’t satisfy the predicate.
void main() {
  const numbers = [1, 3, -2, 0, 4, 5];
  var numbersUntilZero = numbers.takeWhile((number) => number != 0);
  print('$numbersUntilZero');       // Print (1, 3, -2)
  var numbersStartingAtZero = numbers.skipWhile((number) => number != 0);
  print('$numbersStartingAtZero');  // Print (0, 4, 5)
}
// any(): Returns true if at least one element satisfies the condition.
if (items.any((item) => item.contains('Z'))) {
  print('At least one item contains "Z"');
} else {
  print('No item contains "Z"');
}
// every(): Returns true if all elements satisfy the condition.
return items.every((item) => item.length >= 5);
// map()
Iterable<int> output = numbers.map((number) => number * 10);
```

Dart异步编程（Future和Stream）：

*   关键术语：
    *   同步操作：同步操作阻止其他操作执行，直到它完成。
    *   同步函数：同步函数只执行同步操作。
    *   异步操作：一旦启动，异步操作允许在完成之前执行其他操作。异步操作让您的程序在等待另一个操作完成的同时完成工作。
    *   异步函数：异步函数至少执行一个异步操作，也可以执行同步操作。
*   以下是一些常见的异步操作：通过网络获取数据；写入数据库；从文件中读取数据。
*   Future和async-await：
    *   Future表示异步操作的结果，可以有两种状态：未完成（Uncompleted）或已完成（Completed）。
    *   async和await关键字导致异步代码看起来很像同步代码。

    ```dart
    /* Future */
    Future<void> fetchUserOrder() {
      // Imagine that this function is fetching user info from another service or database.
      return Future.delayed(const Duration(seconds: 2), () => print('Large Latte'));
    }
    void main() {
      fetchUserOrder();
      print('Fetching user order...');
    }
    /* async-await */
    Future<String> createOrderMessage() async {
      var order = await fetchUserOrder();
      return 'Your order is: $order';
    }
    Future<String> fetchUserOrder() =>
        // Imagine that this function is
        // more complex and slow.
        Future.delayed(
          const Duration(seconds: 2),
          () => 'Large Latte',
        );
    Future<void> main() async {
      print('Fetching user order...');
      print(await createOrderMessage());
    }
    /* async-awit */
    // Print Awaiting user order...
    // Print 1
    // Print 2
    // Print 3
    // Print 4
    // Print Your order is: Large Latte
    Future<void> printOrderMessage() async {
      print('Awaiting user order...');
      var order = await fetchUserOrder();
      print('Your order is: $order');
    }
    Future<String> fetchUserOrder() {
      // Imagine that this function is more complex and slow.
      return Future.delayed(const Duration(seconds: 4), () => 'Large Latte');
    }
    void countSeconds(int s) {
      // You can ignore this function - it's here to visualize delay time in this example.
      for (var i = 1; i <= s; i++) {
        Future.delayed(Duration(seconds: i), () => print(i));
      }
    }
    Future<void> main() async {
      countSeconds(4);
      await printOrderMessage();
    }
    ```

*   处理异步错误：

    ```dart
    /* Future：处理异步错误 */
    Future<void> printOrderMessage() async {
      try {
        print('Awaiting user order...');
        var order = await fetchUserOrder();
        print(order);
      } catch (err) {
        print('Caught error: $err');
      }
    }
    Future<String> fetchUserOrder() {
      // Imagine that this function is more complex.
      var str = Future.delayed(
          const Duration(seconds: 4),
          () => throw 'Cannot locate user order');
      return str;
    }
    Future<void> main() async {
      await printOrderMessage();
    }
    /* Stream */
    Future<int> sumStream(Stream<int> stream) async {
      var sum = 0;
      await for (final value in stream) {
        sum += value;
      }
      return sum;
    }
    Stream<int> countStream(int to) async* {
      for (int i = 1; i <= to; i++) {
        yield i;
      }
    }
    Future<void> main() async {
      var stream = countStream(10);
      var sum = await sumStream(stream);
      print(sum);  // Print 55
    }
    /* Stream：处理异步错误 */
    Future<int> sumStream(Stream<int> stream) async {
      var sum = 0;
      try {
        await for (final value in stream) {
          sum += value;
        }
      } catch (e) {
        return -1;
      }
      return sum;
    }
    Stream<int> countStream(int to) async* {
      for (int i = 1; i <= to; i++) {
        if (i == 4) {
          throw Exception('Intentional exception');
        } else {
          yield i;
        }
      }
    }
    Future<void> main() async {
      var stream = countStream(10);
      var sum = await sumStream(stream);
      print(sum);  // -1
    }
    ```

*   Future和Stream类是Dart异步编程的核心：
    *   Future表示一个不会立即完成的计算过程。与普通函数直接返回结果不同的是异步函数返回一个将会包含结果的Future。该Future会在结果准备好时通知调用者。
    *   Stream是一系列异步事件的序列。其类似于一个异步的Iterable，不同的是当你向Iterable获取下一个事件时它会立即给你，但是Stream则不会立即给你而是在它准备好时告诉你。
