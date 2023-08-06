# Google Python 风格指南

1.  [背景](#背景)
2.  [Python语言规范](#python语言规范)
3.  [Python风格规范](#python风格规范)
4.  [临别赠言](#临别赠言)

## 背景

Python是Google主要的脚本语言。这本风格指南主要包含的是针对Python的编程准则。

为帮助读者能够将代码准确格式化，我们提供了针对[Vim的配置文件](https://github.com/google/styleguide/blob/gh-pages/google_python_style.vim)。对于Emacs用户，保持默认设置即可。许多团队使用[yapf](https://github.com/google/yapf)作为自动格式化工具以避免格式不一致。

## Python语言规范

Lint：

*   使用/programming/documentation/software/src/.pylintrc这个文件对代码运行pylint。
*   pylint是一个在Python源代码中查找bug的工具。对于C和C++这样的不那么动态的语言，这些bug通常由编译器来捕获。由于Python的动态特性，有些警告可能不对，不过虚假的警告应该很少。

导入：

*   仅对包和模块使用导入，而不单独导入函数或者类（typing模块例外）。
*   导入是模块间共享代码的可重用机制。

```python
import x              # 导入包或模块
from x import y       # 其中 x 是包前缀，y 是不带前缀的模块名
from x import y as z  # 如果两个要导入的模块都叫做 y 或者 y 太长了
import y as z         # 仅当缩写 z 是通用缩写时才可使用，例如 np 代表 numpy
```

包：使用模块的全路径名来导入每个模块。

```python
import absl.flags
from doctor.who import jodie
FLAGS = absl.flags.FLAGS
```

异常：

*   允许使用异常，但必须小心。
*   异常是一种打破正常控制流以处理错误或其他异常情况的方法。
*   异常必须遵守特定条件：
    *   优先合理的使用内置异常类。比如ValueError指示了一个程序错误，例如在方法需要正数的情况下传递了一个负数而引起的错误。不要使用assert语句来验证公共API的参数值。assert是用来保证内部正确性的，而不是用来强制纠正参数使用，若需要使用异常来指示某些意外情况，不要用assert，用raise语句。
    *   模块或包应该定义自己的特定域的异常基类，这个基类应该从内建的Exception类继承，模块的异常基类后缀应该叫做Error。
    *   永远不要使用<code>except:</code>语句来捕获所有异常，也不要捕获Exception或者StandardError，除非你打算重新触发该异常，或者你已经在当前线程的最外层（记住还是要打印一条错误信息）。在异常方面，Python非常宽容，<code>except:</code>真的会捕获包括Python语法错误在内的任何错误，使用<code>except:</code>很容易隐藏真正的bug。
    *   尽量减少try/except块中的代码量，try块的体积越大，期望之外的异常就越容易被出发。这种情况下，try/except块将隐藏真正的错误。
    *   使用finally子句来执行那些无论try块中有没有异常都应该被执行的代码，这对于清理资源常常很有用，例如关闭文件。

```python
"""Yes"""
def connect_to_next_port(self, minimum: int) -> int:
  """Connects to the next available port.
  Args:
    minimum: A port value greater or equal to 1024.
  Returns:
    The new minimum port.
  Raises:
    ConnectionError: If no available port is found.
  """
  if minimum < 1024:
    # Note that this raising of ValueError is not mentioned in the doc
    # string's "Raises:" section because it is not appropriate to
    # guarantee this specific behavioral reaction to API misuse.
    raise ValueError(f'Min. port must be at least 1024, not {minimum}.')
  port = self._find_next_open_port(minimum)
  if port is None:
    raise ConnectionError(
        f'Could not connect to service on port {minimum} or higher.')
  assert port >= minimum, (
      f'Unexpected port {port} when minimum was {minimum}.')
  return port
"""No"""
def connect_to_next_port(self, minimum: int) -> int:
  """Connects to the next available port.
  Args:
    minimum: A port value greater or equal to 1024.
  Returns:
    The new minimum port.
  """
  assert minimum >= 1024, 'Minimum port must be at least 1024.'
  port = self._find_next_open_port(minimum)
  assert port is not None
  return port
```

全局变量：

*   避免使用全局变量。
*   鼓励使用模块级的常量，例如<code>MAX_HOLY_HANDGRENADE_COUNT = 3</code>。注意常量命名必须全部大写，用<kbd>_</kbd>分隔。
*   若必须使用全局变量，应在模块内声明全局变量，并在名称前加<kbd>_</kbd>使之成为模块内部变量。外部访问必须通过模块级的公共函数。

嵌套/局部/内部类或函数：

*   使用内部类或者嵌套函数可以用来覆盖某些局部变量。
*   类可以定义在方法（Method）、函数（Function）或者类（class）中，函数可以定义在方法或函数中。
    *   封闭区间中定义的变量对嵌套函数是只读的。即内嵌函数可以读外部函数中定义的变量，但是无法改写。
    *   函数是一组执行任务的指令，方法是一组与对象相关联的指令。例如<code>printHello()</code>和<code>object.print()</code>。
*   使用内部类或者嵌套函数可以忽略一些警告，但是应该避免使用内部类或者嵌套函数，除非是想覆盖某些值。若想对模块的用户隐藏某个函数，不要采用嵌套来隐藏，应该在需要被隐藏的方法的模块级名称加<kbd>_</kbd>前缀，以便测试仍然可以访问它。

推导式和生成式：

*   可以在简单情况下使用。
*   列表，字典和集合的推导和生成式提供了一种简洁高效的方式来创建容器和迭代器，而不必借助<code>map()</code>、<code>filter()</code>或者lambda。（元组是没有推导式的，()内加类似推导式的句式返回的是个生成器）。
*   简单的列表推导可以比其它的列表创建方法更加清晰简单，生成器表达式可以十分高效，因为它们避免了创建整个列表。
*   适用于简单情况。每个部分应该单独置于一行：映射表达式，for语句，过滤器表达式。禁止多重for语句或过滤器表达式，复杂情况下还是使用循环。

```python
"""Yes"""
result = [mapping_expr for value in iterable if filter_expr]
result = [{'key': value} for value in iterable
          if a_long_filter_expression(value)]
result = [complicated_transform(x)
          for x in iterable if predicate(x)]
descriptive_name = [
    transform({'key': key, 'value': value}, color='black')
    for key, value in generate_iterable(some_input)
    if complicated_condition_is_met(key, value)
]
result = []
for x in range(10):
    for y in range(5):
        if x * y > 10:
            result.append((x, y))
return {x: complicated_transform(x)
        for x in long_generator_function(parameter)
        if x is not None}
squares_generator = (x**2 for x in range(10))
unique_names = {user.name for user in users if user is not None}
eat(jelly_bean for jelly_bean in jelly_beans
    if jelly_bean.color == 'black')
"""No"""
result = [complicated_transform(
              x, some_argument=x+1)
          for x in iterable if predicate(x)]
result = [(x, y) for x in range(10) for y in range(5) if x * y > 10]
return ((x, y, z)
        for x in range(5)
        for y in range(5)
        if x != y
        for z in range(5)
        if y != z)
```

默认迭代器和操作符：

*   容器类型，像字典和列表，定义了默认的迭代器和关系测试操作符（in和not in）。
*   如果类型支持，就使用默认迭代器和操作符，例如列表、字典和文件等。内建类型也定义了迭代器方法，优先考虑这些方法，而不是那些返回列表的方法。当然，这样遍历容器时，你将不能修改容器，除非必要，否则不要使用诸如<code>dict.iter*()</code>这类Python2的特定迭代方法。

```python
"""Yes"""
for key in adict: ...
if key not in adict: ...
if obj in alist: ...
for line in afile: ...
for k, v in dict.iteritems(): ...
"""No"""
for key in adict.keys(): ...
if not adict.has_key(key): ...
for line in afile.readlines(): ...
```

生成器：

*   按需使用生成器。
*   所谓生成器函数，就是每当它执行一次生成（yield）语句，它就返回一个迭代器，这个迭代器生成一个值，生成值后，生成器函数的运行状态将被挂起，直到下一次生成。
*   鼓励使用，注意在生成器函数的文档字符串中使用“Yields:”而不是“Returns:”。

Lambda函数：

*   与语句相反，lambda在一个表达式中定义匿名函数。常用于为<code>map()</code>和<code>filter()</code>之类的高阶函数定义回调函数或者操作符。
*   适用于单行函数。如果代码超过60~80个字符，最好还是定义成常规（嵌套）函数。
*   对于常见的操作符，例如乘法操作符，使用operator模块中的函数以代替lambda函数。例如，推荐使用<code>operator.mul</code>，而不是<code>lambda x,y: x * y</code>。

条件表达式：

*   条件表达式（又名三元运算符）是对于if语句的一种更为简短的句法规则，例如<code>x = 1 if cond else 2</code>。
*   适用于单行函数。写法上推荐<kbd>true-expression, if-expression, else-expression</kbd>每个独占一行。在其他情况下，推荐使用完整的if语句。

```python
"""Yes"""
one_line = 'yes' if predicate(value) else 'no'
slightly_split = ('yes' if predicate(value)
                  else 'no, nein, nyet')
the_longest_ternary_style_that_can_be_done = (
    'yes, true, affirmative, confirmed, correct'
    if predicate(value)
    else 'no, false, negative, nay')
"""No"""
bad_line_breaking = ('yes' if predicate(value) else
                      'no')
portion_too_long = ('yes'
                    if some_long_module.some_long_predicate_function(
                        really_long_variable_name)
                    else 'no, false, negative, nay')
```

默认参数值：

*   你可以在函数参数列表的最后指定变量的值，例如，<code>def foo(a, b=0):</code>。如果调用foo时只带一个参数，则b被设为0，如果带两个参数，则b的值等于第二个参数。
*   鼓励使用，但是不要在函数或方法定义中使用可变对象作为默认值。

```python
"""Yes"""
def foo(a, b=None):
  if b is None:
    b = []
def foo(a, b: Optional[Sequence] = None):
  if b is None:
    b = []
def foo(a, b: Sequence = ()):  # Empty tuple OK since tuples are immutable.
  ...
"""No"""
from absl import flags
_FOO = flags.DEFINE_string(...)
def foo(a, b=[]):
  ...
def foo(a, b=time.time()):    # The time the module was loaded???
  ...
def foo(a, b=_FOO.value):     # sys.argv has not yet been parsed...
  ...
def foo(a, b: Mapping = {}):  # Could still get passed to unchecked code.
  ...
```

特性：

*   译者注：将“property”译为“特性”，而“attribute”译为“属性”。
*   attribute包括默认的getter/setter/deleter，触发内置的get/set/delete方法；property是特殊的attribute，可以自定义getter/setter/deleter等属性，自己控制get/set/delete时触发的方法，也可以不定义来禁止该操作。
*   你通常习惯于使用访问和设置方法来访问和设置数据，它们简单而轻量。不过我们建议你在新的代码中使用属性，只读属性应该用<kbd>@property</kbd>装饰器来创建。

```python
class Circle(object):
  def __init__(self, radius):
    self.radius = radius
  @property
  def diameter(self):
    return self.radius * 2
  @diameter.setter
  def diameter(self, new_diameter):
    self.radius = new_diameter / 2
```

True/False的求值：

*   Python在布尔上下文中会将某些值求值为false。按简单的直觉来讲，就是所有的“空”值都被认为是false，因此0、None、[]、{}和""都被认为是false。
*   尽可能使用隐式的false，例如使用<code>if foo:</code>而不是<code>if foo != []:</code>。不过还是有一些注意事项需要你铭记在心：
    *   始终使用<code>if foo is None:</code>（或者<code>is not None</code>）来检查None值。
    *   永远不要用==将一个布尔量与false相比较。使用<code>if not x:</code>代替。如果你需要区分false和None，你应该用像<code>if not x and x is not None:</code>这样的语句。
    *   对于序列（字符串，列表，元组），要注意空序列是false。因此<code>if not seq:</code>或者<code>if seq:</code>比<code>if len(seq):</code>或者<code>if not len(seq):</code>要更好。
    *   处理整数时，使用隐式false可能会得不偿失（即不小心将None当作0来处理）。你可以将一个已知式整型（且不是<code>len()</code>的返回结果）的值与0比较。
    *   注意'0'会被当做true。

```python
"""Yes"""
if not users:
  print('no users')
if foo == 0:
  self.handle_zero()
if i % 10 == 0:
  self.handle_multiple_of_ten()
def f(x=None):
  if x is None:
    x = []
"""No"""
if len(users) == 0:
  print 'no users'
if foo is not None and not foo:
  self.handle_zero()
if not i % 10:
  self.handle_multiple_of_ten()
def f(x=None):
  x = x or []
```

过时的语言特性：尽可能使用字符串方法取代<kbd>string</kbd>模块。使用函数调用语法取代<code>apply()</code>。使用列表推导，for循环取代<code>filter()</code>，<code>map()</code>以及<code>reduce()</code>。

```python
"""Yes"""
words = foo.split(':')
[x[1] for x in my_list if x[2] == 5]
map(math.sqrt, data)  # Ok. No inlined lambda expression.
fn(*args, **kwargs)
"""No"""
words = string.split(foo, ':')
map(lambda x: x[1], filter(lambda x: x[2] == 5, my_list))
apply(fn, args, kwargs)
```

词法作用域（Lexical Scoping）：

*   推荐使用。
*   嵌套的Python函数可以引用外层函数中定义的变量，但是不能够对它们赋值。变量绑定的解析是使用词法作用域，也就是基于静态的程序文本。对块中某个名称的任何赋值都会导致Python将对该名称的所有引用视为局部变量，即使在赋值之前使用。如果碰到global声明，该名称就会被视作全局变量。

```python
def get_adder(summand1: float) -> Callable[[float], float]:
  """Returns a function that adds numbers to a given number."""
  def adder(summand2: float) -> float:
    return summand1 + summand2
  return adder
sum = get_adder(summand1)(summand2)
```

函数与方法装饰器：

*   如果好处很显然，就明智而谨慎的使用装饰器，避免使用<kbd>staticmethod</kbd>以及谨慎使用<kbd>classmethod</kbd>。
*   用于函数与方法的装饰器（也就是@标记）。最常见的装饰器就是<code>@classmethod</code>和<code>@staticmethod</code>，用于将实例方法转换成类方法或静态方法。不过，装饰器语法也允许用户自定义装饰器。
*   装饰器应该遵守和函数一样的导入和命名规则。装饰器的Python文档应该清晰的说明该函数是一个装饰器。请为装饰器编写单元测试。

线程：

*   不要依赖内建类型的原子性。
*   虽然Python的内建数据类型（如字典）似乎具有原子操作，但在某些极端情况下它们不是原子的（例如，如果__hash__或__eq__被实现为Python的方法）并且不应依赖它们的原子性。您也不应该依赖原子变量赋值（因为这又取决于字典）。
*   使用Queue模块的Queue数据类型作为线程间数据通信的首选方式。否则，使用threading模块及其锁定原语（locking primitives）。优先使用条件变量和threading.Condition而不是使用较低级别的锁。

```python
"""创建 Thread 对象的方法
1.直接创建 Thread，将一个 Callable 对象从类的构造器传递进去，用来处理任务
"""
import threading
import time
def subthread():
  for i in range(5):
    print(f'{threading.current_thread().name} test {i}')
    time.sleep(0.5)
# daemon 设置为 True，只要主线程结束，子线程也结束
# daemon 设置为 False，如果主线程结束，子线程继续执行
thread = threading.Thread(target=subthread, name='TestThread', daemon=True)
thread.start()
# 阻塞主线程一点五秒，只运行子线程
# thread.join(1.5)
for i in range(5):
  print(f'{threading.current_thread().name} main {i}')
  print(f'{thread.name} is alive {thread.is_alive()}')
  time.sleep(0.5)
"""创建 Thread 对象的方法
2.编写一个自定义类继承 Thread，然后复写 run() 方法，在 run() 方法中编写任务处理代码
"""
import threading
import time
class TestThread(threading.Thread):
  def __init__(self, name=None, daemon=False):
    threading.Thread.__init__(self, name=name, daemon=daemon)
  def run(self):
    for i in range(5):
      print(f'{threading.current_thread().name} test {i}')
      time.sleep(0.5)
thread = TestThread(name='TestThread', daemon=True)
thread.start()
for i in range(5):
  print(f'{threading.current_thread().name} main {i}')
  print(f'{thread.name} is alive {thread.is_alive()}')
  time.sleep(0.5)
```

威力过大的特性：

*   Python是一种异常灵活的语言，它为你提供了很多花哨的特性，诸如元类（metaclasses），字节码访问，任意编译（on-the-fly compilation），动态继承，对象父类重定义（object reparenting），导入黑客（import hacks），反射，系统内修改（modification of system internals）等等。在你的代码中避免使用这些特性。
*   当然，利用了这些特性编写的一些标准库是值得使用的，例如<kbd>abc.ABCMeta</kbd>，<kbd>collection.namedtuple</kbd>，<kbd>dataclasses</kbd>，<kbd>enum</kbd>等等。

现代Python：Python3和`from __future__ import`：

*   尽量使用Python3，即使使用非Python3写的代码，也应该尽量兼容。
*   以上导入的详情参见[absolute imports](https://peps.python.org/pep-0328)，[division behavior](https://peps.python.org/pep-0238)，[print function](https://peps.python.org/pep-3105)，除非代码是只在Python3下运行，否则不要删除以上导入。最好在所有文件里都保留这样的导入，这样若有人用到了这些方法时，编辑时不会忘记导入。还有其他的一些来自<code>from __future__</code>的语句，请在你认为合适的地方使用它们。本文没有推荐<kbd>unicode_literals</kbd>，因为我们认为它不是很棒的改进，它在Python2.7中大量引入隐式的默认编码转换，大多数情况下还是推荐显式的使用<kbd>b</kbd>和<kbd>u</kbd>以及Unicode字符串来显式的指示编码转换。
*   当项目需要同时支持Python2和Python3时，请根据需要使用[six](https://pypi.org/project/six)，[future](https://pypi.org/project/future)，[past](https://pypi.org/project/past)，这些库可以使代码更加清晰和简单。

```python
"""
鼓励使用 from __future__ import 语句
所有的新代码都应该包含以下内容，并尽可能的与之兼容
"""
from __future__ import absolute_import
from __future__ import division
from __future__ import print_function
```

代码类型注解：

*   你可以根据[PEP-484](https://peps.python.org/pep-0484)来对Python3代码进行注解，并使用诸如[pytype](https://github.com/google/pytype)之类的类型检查工具来检查代码，类型注解既可以写在源码，也可以写在[pyi](https://peps.python.org/pep-0484/#stub-files)中，推荐尽量写在源码里，对于第三方扩展包，可以写在pyi文件里。
*   强烈推荐您在更新代码时使用Python类型分析。在添加或修改公共API时使用类型注解，在最终构建整个项目前使用<kbd>pytype</kbd>来进行检查。由于静态分析对于Python来说还不够成熟，因此可能会出现一些副作用（例如错误推断的类型）可能会阻碍项目的部署。在这种情况下，建议作者添加一个TODO注释或者链接，来描述当前构建文件或是代码本身中使用类型注解导致的问题。

```python
# 函数参数和返回值的类型注解
def func(a: int) -> List[int]:
# 变量的类型注解
a: SomeType = some_func()
# 在必须支持老版本 python 运行的代码中使用如下注解
a = some_func() # type: SomeType
```

## Python风格规范

分号：不要在行尾加分号，也不要用分号将两条命令放在同一行。

行长度：

*   每行不超过80个字符。
*   例外：
    *   长的导入模块语句。
    *   注释里的URL，路径以及其他的一些长标记。
    *   不便于换行、不包含空格的模块级字符串常量，比如URL或者路径。
        *   Pylint禁用注释（`# pylint: disable=invalid-name`）。
    *   在其他情况下，若一行超过80个字符，但yapf却无法将该行字数降至80个字符以下时，则允许改行超过80个字符长度。

```python
"""Yes"""
# Python 会将圆括号、中括号和花括号中的行隐式的连接起来，你可以利用这个特点
foo_bar(self, width, height, color='black', design=None, x='foo',
        emphasis=None, highlight=0)
if (width == 0 and height == 0 and
    color == 'red' and emphasis == 'strong'):
# 如果一个文本字符串在一行放不下，可以使用圆括号来实现隐式行连接
x = ('This will build a very long long '
     'long long long long long long string')
# 在注释中，如果必要，将长的 URL 放在一行上
# See details at
# http://www.example.com/us/developer/documentation/api/content/v2.0/csv_file_name_extension_full_specification.html
# 当 with 表达式需要使用三个及其以上的上下文管理器时，可以使用反斜杠换行
# 若只需要两个，请使用嵌套的 with
with very_long_first_expression_function() as spam, \
     very_long_second_expression_function() as beans, \
     third_thing() as eggs:
  place_order(eggs, beans, spam, beans)
with very_long_first_expression_function() as spam:
  with very_long_second_expression_function() as beans:
    place_order(beans, spam)
"""No"""
# See details at
# http://www.example.com/us/developer/documentation/api/content/\
# v2.0/csv_file_name_extension_full_specification.html
with very_long_first_expression_function() as spam, \
     very_long_second_expression_function() as beans:
  place_order(beans, spam)
```

括号：

*   宁缺毋滥的使用括号。
*   除非是用于实现行连接，否则不要在返回语句或条件语句中使用括号。不过在元组两边使用括号是可以的。

```python
"""Yes"""
if foo:
  bar()
while x:
  x = bar()
if x and y:
  bar()
if not x:
  bar()
# For a 1 item tuple the ()s are more visually obvious than the comma.
onesie = (foo,)
return foo
return spam, beans
return (spam, beans)
for (x, y) in dict.items(): ...
"""No"""
if (x):
  bar()
if not(x):
  bar()
return (foo)
```

缩进：

*   用4个空格来缩进代码（个人习惯：2个空格）。
*   绝对不要用tab，也不要tab和空格混用。

```python
"""Yes"""
# Aligned with opening delimiter.
foo = long_function_name(var_one, var_two,
                         var_three, var_four)
meal = (spam,
        beans)
# Aligned with opening delimiter in a dictionary.
foo = {
    'long_dictionary_key': value1 +
                           value2,
    ...
}
# 4-space hanging indent; nothing on first line.
foo = long_function_name(
    var_one, var_two, var_three,
    var_four)
meal = (
    spam,
    beans)
# 4-space hanging indent; nothing on first line,
# closing parenthesis on a new line.
foo = long_function_name(
    var_one, var_two, var_three,
    var_four
)
meal = (
    spam,
    beans,
)
# 4-space hanging indent in a dictionary.
foo = {
    'long_dictionary_key':
        long_dictionary_value,
    ...
}
"""No"""
# Stuff on first line forbidden.
foo = long_function_name(var_one, var_two,
    var_three, var_four)
meal = (spam,
    beans)
# 2-space hanging indent forbidden.
foo = long_function_name(
  var_one, var_two, var_three,
  var_four)
# No hanging indent in a dictionary.
foo = {
    'long_dictionary_key':
    long_dictionary_value,
    ...
}
```

序列元素尾部逗号：仅当<kbd>]</kbd>，<kbd>)</kbd>，<kbd>}</kbd>和末位元素不在同一行时，推荐使用序列元素尾部逗号。当末位元素尾部有逗号时，元素后的逗号可以指示YAPF将序列格式化为每行一项。

```python
"""Yes"""
golomb3 = [0, 1, 3]
golomb4 = [
    0,
    1,
    4,
    6,
]
"""No"""
golomb4 = [
    0,
    1,
    4,
    6
]
```

空行：顶级定义之间空两行，比如函数或者类定义。方法定义，类定义与第一个方法之间，都应该空一行。函数或方法中，某些地方要是你觉得合适，就空一行。

空格：按照标准的排版规范来使用标点两边的空格。

```python
"""Yes"""
# 括号内不要有空格
spam(ham[1], {eggs: 2}, [])
# 不要在逗号、分号或冒号前面加空格，但应该在它们后面加（除了在行尾）
if x == 4:
  print(x, y)
x, y = y, x
# 参数列表、索引或切片的左括号前不应加空格
spam(1)
dict['key'] = list[index]
# 在二元操作符两边都加上一个空格
x = 1
# 当 = 用于指示关键字参数或默认参数值时，不要在其两侧使用空格
# 但若存在类型注解的时候，需要在 = 周围使用空格
def complex(real, imag=0.0): return magic(r=real, i=imag)
def complex(real, imag: float = 0.0): return Magic(r=real, i=imag)
# 不要用空格来垂直对齐多行间的标记，因为这会成为维护的负担（适用于 :，#，= 等）
foo = 1000  # comment
long_name = 2  # comment that should not be aligned
dictionary = {
    "foo": 1,
    "long_name": 2,
}
"""No"""
spam( ham[ 1 ], { eggs: 2 }, [ ] )
if x == 4 :
  print(x , y)
x , y = y , x
spam (1)
dict ['key'] = list [index]
x<1
def complex(real, imag = 0.0): return magic(r = real, i = imag)
def complex(real, imag: float=0.0): return Magic(r = real, i = imag)
foo       = 1000  # comment
long_name = 2     # comment that should not be aligned
dictionary = {
    'foo'      : 1,
    'long_name': 2,
}
```

Shebang：大部分.py文件不必以<kbd>#!</kbd>作为文件的开始。根据[PEP-394](https://www.python.org/dev/peps/pep-0394)，程序的main文件应该以<code>#!/usr/bin/python2</code>或<code>#!/usr/bin/python3</code>开始。

注释：

*   确保对模块、函数、方法文档字符串（DocStrings）和内联注释（inline comments）使用正确的样式。
*   Python有一种独一无二的的注释方式：使用文档字符串。文档字符串是包、模块、类或函数里的第一个语句。这些字符串可以通过对象的<kbd>__doc__</kbd>成员被自动提取，并且被pydoc所用。我们对文档字符串的惯例是使用三重双引号（"""）。
*   一个文档字符串应该这样组织：首先是一行以句号、问号或惊叹号结尾的概述（或者该文档字符串单纯只有一行）。接着是一个空行。接着是文档字符串剩下的部分，它应该与文档字符串的第一行的第一个引号对齐。
*   模块的文档字符串：每个文件应该包含一个许可样板。根据项目使用的许可（例如Apache 2.0，BSD，LGPL，GPL），选择合适的样板。其开头应是对模块内容和用法的描述。
*   函数和方法的文档字符串：
    *   下文所指的函数，包括函数、方法和生成器。
    *   一个函数必须要有文档字符串，除非它满足以下条件：外部不可见；非常短小；简单明了。
    *   文档字符串应该包含函数做什么，以及输入和输出的详细描述。通常，不应该描述“怎么做”，除非是一些复杂的算法。文档字符串应该提供足够的信息，当别人编写代码调用该函数时，他不需要看一行代码，只要看文档字符串就可以了。对于复杂的代码，在代码旁边加注释会比使用文档字符串更有意义。覆盖基类的子类方法应有一个类似<kbd>See base class</kbd>的简单注释来指引读者到基类方法的文档注释。若重载的子类方法和基类方法有很大不同，那么注释中应该指明这些信息。
    *   关于函数的几个方面应该在特定的小节中进行描述记录，这几个方面如下文所述。每节应该以一个标题行开始，标题行以冒号结尾，除标题行外，节的其他内容应被缩进2个空格。
        *   Args：列出每个参数的名字，并在名字后使用一个冒号和一个空格，分隔对该参数的描述。如果描述太长超过了单行80字符，使用2或者4个空格的悬挂缩进（与文件其他部分保持一致）。描述应该包括所需的类型和含义。如果一个函数接受<kbd>\*foo</kbd>（可变长度参数列表）或者<kbd>\*\*bar</kbd>（任意关键字参数），应该详细列出<kbd>*foo</kbd>和<kbd>**bar</kbd>。
        *   Returns或者Yields：描述返回值的类型和语义。如果函数返回None，这一部分可以省略。
        *   Raises：列出与接口有关的所有异常。
*   类的文档字符串：类应该在其定义下有一个用于描述该类的文档字符串。如果你的类有公共属性（Attributes），那么文档中应该有一个属性（Attributes）段。并且应该遵守和函数参数相同的格式。
*   块注释和行注释：
    *   最需要写注释的是代码中那些技巧性的部分。如果你在下次<kbd>代码审查</kbd>的时候必须解释一下，那么你应该现在就给它写注释。对于复杂的操作，应该在其操作开始前写上若干行注释。对于不是一目了然的代码，应在其行尾添加注释。
    *   为了提高可读性，注释应该至少离开代码2个空格。
    *   另一方面，绝不要描述代码。假设阅读代码的人比你更懂Python，他只是不知道你的代码要做什么。
*   标点符号、拼写和语法：
    *   注意标点符号、拼写和语法。
    *   注释应有适当的大写和标点，句子应该尽量完整。对于诸如在行尾上的较短注释，可以不那么正式，但是也应该尽量保持风格一致。

```python
"""模块"""
"""A one line summary of the module or program, terminated by a period.
Leave one blank line.  The rest of this docstring should contain an
overall description of the module or program.  Optionally, it may also
contain a brief description of exported classes and functions and/or usage
examples.
Typical usage example:
foo = ClassFoo()
bar = foo.FunctionBar()
"""
"""函数和方法"""
def fetch_smalltable_rows(table_handle: smalltable.Table,
                          keys: Sequence[Union[bytes, str]],
                          require_all_keys: bool = False,
) -> Mapping[bytes, tuple[str, ...]]:
  """Fetches rows from a Smalltable.
  Retrieves rows pertaining to the given keys from the Table instance
  represented by table_handle.  String keys will be UTF-8 encoded.
  Args:
    table_handle:
      An open smalltable.Table instance.
    keys:
      A sequence of strings representing the key of each table row to
      fetch.  String keys will be UTF-8 encoded.
    require_all_keys:
      If True only rows with values set for all keys will be returned.
  Returns:
    A dict mapping keys to the corresponding table row data
    fetched. Each row is represented as a tuple of strings. For
    example:
    {b'Serak': ('Rigel VII', 'Preparer'),
      b'Zim': ('Irk', 'Invader'),
      b'Lrrr': ('Omicron Persei 8', 'Emperor')}
    Returned keys are always bytes.  If a key from the keys argument is
    missing from the dictionary, then that row was not found in the
    table (and require_all_keys must have been False).
  Raises:
    IOError: An error occurred accessing the smalltable.
  """
"""类"""
class SampleClass:
  """Summary of class here.
  Longer class information...
  Longer class information...
  Attributes:
    likes_spam: A boolean indicating if we like SPAM or not.
    eggs: An integer count of the eggs we have laid.
  """
  def __init__(self, likes_spam: bool = False):
    """Inits SampleClass with blah."""
    self.likes_spam = likes_spam
    self.eggs = 0
  def public_method(self):
    """Performs operation blah."""
"""块注释和行注释"""
# We use a weighted dictionary search to find out where i is in
# the array.  We extrapolate position based on the largest num
# in the array and the array size and then do binary search to
# get the exact number.
if i & (i-1) == 0:  # True if i is 0 or a power of 2.
# BAD COMMENT: Now go through the b array and make sure whenever i occurs
# the next element is i+1
```

字符串：

*   使用f-string、%运算符或format方法来格式化字符串，即使参数都是字符串。不过也不能一概而论，你需要在+和%之间好好判定。
*   避免在循环中用+和+=操作符来累加字符串。由于字符串是不可变的，这样做会创建不必要的临时对象，并且导致二次方而不是线性的运行时间。作为替代方案，你可以将每个子串加入列表，然后在循环结束后用.join连接列表（或者将每个子字符串写入io.StringIO缓冲区）。这些技术始终具有线性运行时复杂性。
*   在同一个文件中，保持使用字符串引号的一致性。使用单引号或者双引号之一用以引用字符串，并在同一文件中沿用。在字符串内可以使用另外一种引号，以避免在字符串中使用。

```python
"""Yes"""
x = f'name: {name}; score: {n}'
x = '%s, %s!' % (imperative, expletive)
x = '{}, {}'.format(first, second)
x = 'name: %s; score: %d' % (name, n)
x = 'name: {}; score: {}'.format(name, n)
x = a + b
items = ['<table>']
for last_name, first_name in employee_list:
  items.append('<tr><td>%s, %s</td></tr>' % (last_name, first_name))
items.append('</table>')
employee_table = ''.join(items)
Python('Why are you hiding your eyes?')
Gollum("I'm scared of lint errors.")
Narrator('"Good!" thought a happy Python reviewer.')
"""No"""
x = first + ', ' + second
x = 'name: ' + name + '; score: ' + str(n)
employee_table = '<table>'
for last_name, first_name in employee_list:
  employee_table += '<tr><td>%s, %s</td></tr>' % (last_name, first_name)
employee_table += '</table>'
Python("Why are you hiding your eyes?")
Gollum('The lint. It burns. It burns us.')
Gollum("Always the great lint. Watching. Watching.")
```

文件和sockets：在文件和sockets结束时，显式的关闭它。

```python
"""推荐使用 with 语句以管理文件"""
with open('hello.txt') as hello_file:
  for line in hello_file:
    print(line)
"""对于不支持 with 语句的类似文件的对象，使用 contextlib.closing()"""
import contextlib
with contextlib.closing(urllib.urlopen('http://www.python.org')) as front_page:
  for line in front_page:
    print(line)
```

TODO注释：

*   为临时代码使用TODO注释，它是一种短期解决方案，不算完美，但够好了。
*   TODO注释应该在所有开头处包含TODO字符串，紧跟着是用括号括起来的你的名字、email地址或其它标识符。然后是一个可选的冒号。接着必须有一行注释，解释要做什么。主要目的是为了有一个统一的TODO格式，这样添加注释的人就可以搜索到（并可以按需提供更多细节）。写了TODO注释并不保证写的人会亲自解决问题。当你写了一个TODO，请注上你的名字。
*   如果你的TODO是将来做某事的形式，那么请确保你包含了一个指定的日期（例如，2009年11月解决）或者一个特定的事件（例如，等到所有的客户都可以处理XML请求就移除这些代码）。

```python
# TODO(kl@gmail.com): Use a "*" here for string repetition.
# TODO(Zeke) Change this to use relations.
```

导入：

*   每个导入应该独占一行，typing的导入除外。
*   导入总应该放在文件顶部，位于模块注释和文档字符串之后，模块全局变量和常量之前。导入应该按照从最通用到最不通用的顺序分组：__future__导入，标准库导入，第三方库导入，本地代码子包导入。
*   每个分组中，应该根据每个模块的完整包路径按字典序排序，忽略大小写。

```python
from __future__ import absolute_import
from __future__ import division
from __future__ import print_function
import collections
import queue
import sys
from absl import app
from absl import flags
import bs4
import cryptography
import tensorflow as tf
from book.genres import scifi
from myproject.backend import huxley
from myproject.backend.hgwells import time_machine
from myproject.backend.state_machine import main_loop
from otherproject.ai import body
from otherproject.ai import mind
from otherproject.ai import soul
```

语句：

*   通常每个语句应该独占一行。
*   不过，如果测试结果与测试语句在一行放得下，你也可以将它们放在同一行。如果是if语句，只有在没有else时才能这样做。特别地，绝不要对try/except这样做，因为try和except不能放在同一行。

Getters和Setters：

*   在Python中，对于琐碎又不太重要的访问函数，你应该直接将该内部属性公开，这样可以避免额外的函数调用开销。当添加更多功能时，你可以用属性（property）来保持语法的一致性。
*   另一方面，如果访问更复杂，或者变量的访问开销很显著，那么你应该使用像<code>get_foo()</code>和<code>set_foo()</code>这样的函数调用。

命名：

*   模块名写法：`module_name`；包名写法：`package_name`；类名：`ClassName`；方法名：`method_name`；异常名：`ExceptionName`；函数名：`function_name`；全局常量名：`GLOBAL_CONSTANT_NAME`；全局变量名：`global_var_name`；实例名：`instance_var_name`；函数参数名：`function_parameter_name`；局部变量名：`local_var_name`。
*   函数名、变量名和文件名应该是描述性的，尽量避免缩写，特别要避免使用非项目人员不清楚难以理解的缩写，不要通过删除单词中的字母来进行缩写。
*   所有Python脚本文件都应该以.py作为后缀名且不包含破折号。

Main：

*   即使是一个打算被用作脚本的文件，也应该是可导入的。并且简单的导入不应该导致这个脚本的主功能（main functionality）被执行，主功能应该放在一个<code>main()</code>函数中。
*   若使用absl，请使用<code>app.run</code>。

```python
def main():
  ...
if __name__ == '__main__':
  main()
```

```python
from absl import app
...
def main(argv):
  # process non-flag arguments
  ...
if __name__ == '__main__':
  app.run(main)
```

函数长度：

*   推荐函数功能尽量集中，简单，小巧。
*   不对函数长度做硬性限制。但是若一个函数超过来40行，推荐考虑一下是否可以在不损害程序结构的情况下对其进行分解，因为即使现在长函数运行良好，但几个月后可能会有人修改它并添加一些新的行为，这容易产生难以发现的bug。保持函数的简练，使其更加容易阅读和修改，当遇到一些很长的函数时，若发现调试比较困难或是想在其他地方使用函数的一部分功能，不妨考虑将这个场函数进行拆分。

类型注解：

*   通用规则：对于方法，仅在必要时才对self或cls注解；若对类型没有任何显示，请使用Any；无需注解模块中所有函数，只有公共的API才需要注解。

```python
"""换行"""
def my_method(self,
              first_var: int,
              second_var: Foo,
              third_var: Optional[Bar]) -> int:
def my_method(
    self, other_arg: Optional[MyLongType]
) -> Dict[OtherLongType, MyLongType]:
def my_method(
    self,
    first_var: Tuple[List[MyLongType1],
                     List[MyLongType2]],
    second_var: List[Dict[
        MyLongType3, MyLongType4]]) -> None:
"""预先声明
若需要使用一个当前模块尚未定义的类名，比如想在类声明中使用类名
请使用 from __future__ import annotations
或者使用字符串作为类名
"""
from __future__ import annotations
class MyClass:
  def __init__(self, stack: Sequence[MyClass]) -> None:
"""参数默认值"""
def func(a: int = 0) -> int:
"""NoneType"""
def func(a: Optional[Text], b: Optional[Text] = None) -> Text:
  ...
def multiple_nullable_union(a: Union[None, Text, int]) -> Text
  ...
"""类型别名"""
_ShortName = module_with_long_name.TypeWithLongName
ComplexMap = Mapping[Text, List[Tuple[int, int]]]
"""TypeVars"""
from typing import List, TypeVar
T = TypeVar("T")
...
def next(l: List[T]) -> T:
    return l.pop()
"""字符串类型
对于仅在 python3 下运行的代码，首选 str，使用 Text 也可以
对于需要兼容 python2 的代码，使用 Text
对于需要处理二进制数据的代码，使用 bytes
"""
from typing import Text
def py2_compatible(x: Text) -> Text:
  ...
def py3_only(x: str) -> str:
  ...
def deals_with_binary_data(x: bytes) -> bytes:
  ...
"""Typing 导入"""
from typing import Any, Dict, Optional
"""泛型
在注解时，尽量将泛型类型注解为类型参数
若实在是要用 Any 作为泛型类型，请显式的使用它，但是多数情况下，TypeVar 通常是更好的选择
"""
# These are both interpreted as get_names(employee_ids: List[Any]) -> Dict[Any, Any]
def get_names(employee_ids: List) -> Dict:
  ...
T = TypeVar('T')
def get_names(employee_ids: List[T]) -> Dict[T, Text]:
  """Returns a mapping from employee ID to employee name for given IDs."""
```

## 临别赠言

请务必保持代码的一致性。
