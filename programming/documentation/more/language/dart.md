# Dart

Dart速查表：

*   字符串插值：为了将表达式的值放在字符串中，使用`${expression}`。若表达式为单个标识符，可省略`{}`。

    ```dart
    /* 字符串插值 */
    '${3 + 2}'                 // '5'
    '${"word".toUpperCase()}'  // 'WORD'
    '$myObject'                // myObject.toString()
    ```

*   可空的变量：Dart 2.12引入了健全的空安全，这意味着在启动了空安全后，除非变量显式声明为空类型，否则它们将不能为空。

    ```dart
    /* 可空的变量 */
    int a = null;   // INVALID in null-safe Dart
    int? a = null;  // Valid in null-safe Dart
    ```

*   避空运算符：`??=`和`??`。

    ```dart
    /* 避空运算符 */
    a ??= 3;         // if a = null, a = 3, else unchanged
    print(a ?? 12);  // if a = null, print 12, else print a
    ```

*   条件属性访问：要保护可能会是空的属性的正常访问，请在点（.）之前加上一个问好（?）。

    ```dart
    /* 条件属性访问 */
    myObject?.someProperty;
    (myObject != null) ? myObject.someProperty : null;  // Equivalent to the above code
    ```

*   集合字面量：Dart内置了对list、map以及set的支持。Dart的类型推断可以自动帮你分配这些变量的类型。

    ```dart
    /* 集合字面量 */
    final aListOfStrings = ['one', 'two', 'three'];  // List<String>
    final aSetOfStrings = {'one', 'two', 'three'};   // Set<String>
    final aMapOfStringsToInts = {'one': 1, 'two': 2, 'three': 3};  // Map<String, int>
    ```

*   箭头语法：`=>`符号是一种定义函数的方法，该函数将在其右侧执行表达式并返回其值。

    ```dart
    /* 箭头语法 */
    bool hasEmpty = aListOfStrings.any((s) {
      return a.isEmpty;
    });
    bool hasEmpty = aListOfStrings.any((s) => s.isEmpty);  // Equivalent to the above code
    ```

*   级连：要对同一对象执行一系列操作，请使用`..`符号。

    ```dart
    /* 级连 */
    var button = querySelector('#confirm');
    button?.text = 'Confirm';
    button?.classes.add('important');
    button?.onClick.listen((e) => window.alert('Confirmed!'));
    querySelector('#confirm')  // Equivalent to the above code
      ?..text = 'Confirm'
      ..classes.add('important')
      ..onClick.listen((e) => window.alert('Confirmed!'));
    ```

*   Getters and setters：

    ```dart
    /* Getters and setters */
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

*   Dart有两种传参方式：位置参数和命名参数。一个方法不能同时使用可选位置参数和可选命名参数。

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

*   异常：Dart代码可以抛出和捕获异常。与Java相比，Dart的所有异常都是unchecked exception。方法不会声明它们可能抛出的异常，你也不需要捕获任何异常。

    ```dart
    /* 异常 */
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

*   构造方法：
    *   在构造方法中使用this：

        ```dart
        /* 在构造方法中使用 this */
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
        /* Initializer lists */
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
        /* 命名构造方法 */
        class Point {
          double x, y;
          Point(this.x, this.y);
          Point.origin()
            : x = 0,
              y = 0;
        }
        ```

    *   工厂构造方法：

        ```dart
        /* 工厂构造方法：能够返回其子类甚至 null 对象 */
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
        /* 重定向构造方法 */
        class Automobile {
          String make;
          String model;
          int mpg;
          Automobile(this.make, this.model, this.mpg);
          Automobile.hybrid(String make, String model) : this(make, model, 60);
          Automobile.fancyHybrid(): this.hybrid('Futurecar', 'Mark 2');
        }
        ```

    *   Const构造方法：

        ```dart
        /* Const构造方法：如果类生成的对象永远不变，则可以让这些对象成为编译时常量 */
        class ImmutablePoint {
          static const ImmutablePoint origin = ImmutablePoint(0, 0);
          final int x;
          final int y;
          const ImmutablePoint(this.x, this.y);
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
