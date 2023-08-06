# 数据结构和算法

1.  [将序列分解为单独的变量](#将序列分解为单独的变量)
2.  [从任意长度的可迭代对象中分解元素](#从任意长度的可迭代对象中分解元素)
3.  [保存最后N个元素](#保存最后n个元素)
4.  [找到最大或最小的N个元素](#找到最大或最小的n个元素)
5.  [实现优先级队列](#实现优先级队列)
6.  [在字典中将键映射到多个值上](#在字典中将键映射到多个值上)
7.  [让字典保持有序](#让字典保持有序)
8.  [与字典有关的计算问题](#与字典有关的计算问题)
9.  [在两个字典中寻找相同点](#在两个字典中寻找相同点)
10.  [从序列中移除重复项且保持元素间顺序不变](#从序列中移除重复项且保持元素间顺序不变)
11.  [对切片命名](#对切片命名)
12.  [序列中出现次数最多的元素](#序列中出现次数最多的元素)
13.  [通过某个关键字排序一个字典列表](#通过某个关键字排序一个字典列表)
14.  [排序不支持原生比较的对象](#排序不支持原生比较的对象)
15.  [通过某个字段将记录分组](#通过某个字段将记录分组)
16.  [过滤序列元素](#过滤序列元素)
17.  [从字典中提取子集](#从字典中提取子集)
18.  [映射名称到序列元素](#映射名称到序列元素)
19.  [转换并同时计算数据](#转换并同时计算数据)
20.  [合并多个字典和映射](#合并多个字典和映射)

## 将序列分解为单独的变量

```python
data = ['ACME', 50, 91.1, (2012, 12, 21)]
name, shares, price, date = data
```

## 从任意长度的可迭代对象中分解元素

```python
user_record = ('Dave', 'dave@example.com', '773-555-1212', '847-555-1212')
name, email, *phone_numbers = user_record
```

## 保存最后N个元素

```python
from collections import deque

def search(lines, pattern, history = 5):
  previous_lines = deque(maxlen = history)
  for line in lines:
    if pattern in line:
      yield line, previous_lines
    previous_lines.append(line)
        
def print_somefile(path):
  with open(path) as f:
    for line, prevlines in search(f, 'python', 5):
      for pline in prevlines:
        print(pline, end = '')
      print(line, end = '')
      print('-' * 20)
        
print_somefile('./resources/none_somefile.txt')
print_somefile('./resources/less_somefile.txt')
print_somefile('./resources/more_somefile.txt')
```

## 找到最大或最小的N个元素

*   N = 1时，使用min、max方法。
*   N比集合数量小很多时，使用heapq.nlargest、heapq.nsmallest方法。
*   N和集合大小差不多时，使用sorted方法。

```python
import heapq

nums = [1, 8, 2, 23, 7, -4, 18, 23, 42, 37, 2]

print(heapq.nlargest(3, nums))

print(heapq.nsmallest(3, nums))
```

```python
portfolio = [
    {'name': 'IBM', 'shares': 100, 'price': 91.1},
    {'name': 'AAPL', 'shares': 50, 'price': 543.22},
    {'name': 'FB', 'shares': 200, 'price': 21.09},
    {'name': 'HPQ', 'shares': 35, 'price': 31.75},
    {'name': 'YHOO', 'shares': 45, 'price': 16.35},
    {'name': 'ACME', 'shares': 75, 'price': 115.65}
]

cheap = heapq.nsmallest(3, portfolio, key = lambda s: s['price'])
print(cheap)

expensive = heapq.nlargest(3, portfolio, key = lambda s: s['price'])
print(expensive)
```

## 实现优先级队列

```python
import heapq

class PriorityQueue:
  def __init__(self):
    self._queue = []
    self._index = 0
        
  def push(self, item, priority):
    heapq.heappush(self._queue, (-priority, self._index, item))
    self._index += 1
        
  def pop(self):
    return heapq.heappop(self._queue)[-1]

class Item:
  def __init__(self, name):
    self.name = name
        
  def __repr__(self):
    return 'Item({!r})'.format(self.name)

q = PriorityQueue()
q.push(Item('foo'), 1)
q.push(Item('bar'), 5)
q.push(Item('spam'), 4)
q.push(Item('grok'), 1)
```

## 在字典中将键映射到多个值上

*   collections.defaultdict
*   dict.setdefault

```python
from collections import defaultdict

# first
d = {
    'a': [1, 2, 3],
    'b': [4, 5]
}

# second
d = defaultdict(list)
d['a'].append(1)
d['a'].append(2)
d['b'].append(4)

# third
d = {}  # A regular dictionary
d.setdefault('a', []).append(1)
d.setdefault('a', []).append(2)
d.setdefault('b', []).append(4)
```

## 让字典保持有序

collections.OrderedDict：

*   OrderedDict内部维护了一个双向链表，它会根据元素加入的顺序来排列键的位置。
*   请注意OrderedDict的大小是普通字典的2倍多，这是由于它格外创建的链表导致的。

```python
from collections import OrderedDict
import json

d = OrderedDict()
d['foo'] = 1
d['bar'] = 2
d['spam'] = 3
d['grok'] = 4

for key, value in d.items():
  print('{0} {1}'.format(key, value))

# use OrderedDict
dj = json.dumps(d)

json.loads(dj)
```

## 与字典有关的计算问题

```python
prices = {
    'ACME': 45.23,
    'AAPL': 612.78,
    'IBM': 205.55,
    'HPQ': 37.20,
    'FB': 10.75,
}

min_price = min(zip(prices.values(), prices.keys()))

max_price = max(zip(prices.values(), prices.keys()))

# zip() 创建了一个迭代器
# min，max，sorted 都可以将迭代器对象实例化
# 当在这样的元组上执行比较操作时，值会先进行比较，然后才是键
prices_sorted = sorted(zip(prices.values(), prices.keys()))
```

## 在两个字典中寻找相同点

*   操作符：|、&和-。
*   字典的keys()和items()方法返回keys-view对象和items-view对象，支持集合操作。
*   字典的values()方法并不支持集合操作。

```python
a = {
    'x': 1,
    'y': 2,
    'z': 3,
}

b = {
    'w': 10,
    'x': 11,
    'y': 2,
}

print(a.keys() & b.keys())

print(a.keys() - b.keys())

print(a.keys() | b.keys())

print({key: a[key] for key in a.keys() - {'z', 'w'}})
```

## 从序列中移除重复项且保持元素间顺序不变

```python
# 序列中的值是可哈希的（hashable）
def dedupe_canhashable(items):
  seen = set()
  for item in items:
    if item not in seen:
      yield item
      seen.add(item)

a = [1, 5, 2, 1, 9, 1, 5, 10]

print(list(dedupe_canhashable(a)))

def dedupe_cannothashable(items, key = None):
  seen = set()
  for item in items:
    val = item if key is None else key(item)
    if val not in seen:
      yield item
      seen.add(val)

a = [{'x': 1, 'y': 2}, {'x': 1, 'y': 3}, {'x': 1, 'y': 2}, {'x': 2, 'y': 4}]

print(list(dedupe_cannothashable(a, lambda d: (d['x'], d['y']))))
```

## 对切片命名

slice：

*   作为一条基本准则，代码中如果有很多硬编码的索引值，将导致可读性和可维护性都不佳。
*   一般来说，内置的slice()函数会创建一个切片对象，可以用在任何允许进行切片操作的地方。
*   属性：start、stop和step。
*   函数：indices(size)。

```python
items = list(range(7))

a = slice(2, 4)

print(items[2:4])
print(a)
```

```python
a = slice(5, 50, 2)

s = 'HelloWorld'

print(a.indices(len(s)))
```

```python
for i in range(*a.indices(len(s))):
  print(s[i], end = ' ')
```

## 序列中出现次数最多的元素

collections.Counter：

*   Counter对象可以接受任意的由可哈希（hashable）元素构成的序列对象。
*   在底层实现上，一个Counter对象就是一个字典，将元素映射到它出现的次数上。
*   函数：most_common和update。
*   运算符：+和-。

```python
from collections import Counter

words = [
    'look', 'into', 'my', 'eyes', 'look', 'into', 'my', 'eyes',
    'the', 'eyes', 'the', 'eyes', 'the', 'eyes', 'not', 'around', 'the',
    'eyes', "don't", 'look', 'around', 'the', 'eyes', 'look', 'into',
    'my', 'eyes', "you're", 'under'
]

word_counts = Counter(words)

top_three = word_counts.most_common(3)  # 出现频率最高的 3 个单词

print(top_three)
```

```python
morewords = ['why', 'are', 'you', 'not', 'looking', 'in', 'my', 'eyes']

for word in morewords:
  word_counts[word] += 1
    
print(word_counts['eyes'])
print(word_counts['looking'])
```

## 通过某个关键字排序一个字典列表

operator.itemgetter

```python
from operator import itemgetter

rows = [
    {'fname': 'Brian', 'lname': 'Jones', 'uid': 1003},
    {'fname': 'David', 'lname': 'Beazley', 'uid': 1002},
    {'fname': 'John', 'lname': 'Cleese', 'uid': 1001},
    {'fname': 'Big', 'lname': 'Jones', 'uid': 1004}
]

# use itemgetter
rows_by_lfname = sorted(rows, key = itemgetter('lname', 'fname'))
print(rows_by_lfname)

# no use itemgetter
rows_by_lfname = sorted(rows, key = lambda r: (r['lname'], r['fname']))
print(rows_by_lfname)
```

## 排序不支持原生比较的对象

*   内置的sorted()函数有一个关键字参数key，可以传入一个callable对象对它，这个callable对象对每个传入的对象返回一个值，这个值会被sorted用来排序这些对象。
*   operator.attrgetter

```python
class User:
  def __init__(self, user_id):
    self.user_id = user_id
        
  def __repr__(self):
    return 'User({0})'.format(self.user_id)

users = [User(23), User(3), User(99)]
print(users)
print(sorted(users, key = lambda u: u.user_id))

from operator import attrgetter
sorted(users, key = attrgetter('user_id'))
```

## 通过某个字段将记录分组

*   itertools.groupby

```python
from operator import itemgetter
from itertools import groupby

rows = [
    {'address': '5412 N CLARK',      'date': '07/01/2013'},
    {'address': '5148 N CLARK',      'date': '07/04/2012'},
    {'address': '5800 E 58TH',       'date': '07/01/2012'},
    {'address': '2122 N CLARK',      'date': '07/01/2012'},
    {'address': '5645 N RAVENSWOOD', 'date': '07/02/2013'},
    {'address': '1060 W ADDISON',    'date': '07/02/2012'},
    {'address': '4801 N BROADWAY',   'date': '07/01/2013'},
    {'address': '1039 W GRANVILIE',  'date': '07/01/2012'}
]

def for_rows(row):
  date = row['date']
  date_list = date.split('/')
  date_list.append(date_list.pop(0))
  date_list.append(date_list.pop(0))
  return '/'.join(date_list)

rows.sort(key = for_rows)

for date, item in groupby(rows, key = itemgetter('date')):
  print(date)
  for i in item:
    print(' ', i)
```

## 过滤序列元素

*   列表推导式：
    *   输入非常大的时候会产生一个非常大的结果集，占用大量内存。
*   filter
*   itertools.compress：
    *   过滤工具compress函数以一个iterable对象和一个相对应的Boolean选择器序列作为输入参数。

```python
mylist = [1, 4, -5, 10, -7, 2, 3, -1]

print([n for n in mylist if n > 0])
print([n for n in mylist if n < 0])
print([n if n > 0 else 0 for n in mylist])
```

```python
values = ['1', '2', '-3', '-', '4', 'N/A', '5']

def is_int(val):
  try:
    x = int(val)
    return True
  except ValueError:
    return False
    
ivals = list(filter(is_int, values))

print(ivals)
```

```python
from itertools import compress

address = [
    '5412 N CLARK',
    '5148 N CLARK',
    '5800 E 58TH',
    '2122 N CLARK',
    '5645 N RAVENSWOOD',
    '1060 W ADDISON',
    '4801 N BROADWAY',
    '1039 W GRANVILIE',
]

counts = [0, 3, 10, 4, 1, 7, 6, 1]

more5 = [n > 5 for n in counts]

print(more5)

print(list(compress(address, more5)))
```

## 从字典中提取子集

字典推导式

```python
prices = {
    'ACME': 45.23,
    'AAPL': 612.78,
    'IBM': 205.55,
    'HPQ': 37.20,
    'FB': 10.75,
}

tech_names = {'AAPL', 'IBM', 'HPQ', 'MSFT'}

print({key: value for key, value in prices.items() if value > 200})
print({key: value for key, value in prices.items() if key in tech_names})
print(dict((key, value) for key, value in prices.items() if value > 200))
```

## 映射名称到序列元素

collections.namedtuple：

*   实际上是一个返回Python中标准元组类型子类的工厂方法。
*   namedtuple的实例看起来像一个普通的类实例，但是它跟元组类型是可交换的，支持所有的普通元组操作。
*   命名元组的一个主要用途是将你的代码从下标操作中解脱出来。
*   命名元组的另一个用途就是作为字典的替代，因为字典操作需要更多的内存空间。但是命名空间是不可更改的。

```python
from collections import namedtuple

Subscriber = namedtuple('Subscriber', ['addr', 'joined'])
sub = Subscriber('jonesy@example.com', '2012-10-19')

print(sub)
print(sub.addr)
print(sub.joined)
print(len(sub))
```

```python
from collections import namedtuple

Stock = namedtuple('Stock', ['name', 'shares', 'price'])

s = Stock('ACME', 100, 123.45)
print(s)

# 使用 _replace() 方法会创建一个全新的命名元组并将对应的字段用新的值取代
s = s._replace(shares = 75)

print(s)
```

```python
from collections import namedtuple

# use _replace 填充字段
Stock = namedtuple('Stock', ['name', 'shares', 'price', 'date', 'time'])

stock_prototype = Stock('', 0, 0.0, None, None)

def dict_to_stock(s):
  return stock_prototype._replace(**s)

a = {'name': 'ACME', 'shares': 100, 'price': 123.45}

print(dict_to_stock(a))
```

## 转换并同时计算数据

生成器表达式：生成器表达式作为一个单独参数传递给函数时可以不用多加一个括号。

```python
nums = [1, 2, 3, 4, 5]

s = sum(x * x for x in nums)
print(s)

s = ('ACME', 50, 123.45)
print(','.join(str(x) for x in s))
```

## 合并多个字典和映射

collections.ChainMap：

*   ChainMap接受多个字典并将它们在逻辑上变成一个字典，只是视图。这些字典并不是真的合并在一起，ChainMap类只是内部创建了一个容纳这些字典的列表。
*   大部分字典操作都是可以正常使用的。
*   如果出现重复键，那么第一次出现的映射值会被返回。
*   对于字典的更新和删除操作总是影响的是列表中的第一个字典。

```python
from collections import ChainMap

a = {'x': 1, 'z': 3}
b = {'y': 2, 'z': 4}

c = ChainMap(a, b)
print(c['z'])
print(len(c))
print(list(c.keys()))

c['z'] = 10
print(c)
```
