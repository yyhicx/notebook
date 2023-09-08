# JavaScript

1.  [JavaScript简介](#javascript简介)
2.  [JavaScript基础](#javascript基础)
3.  [JavaScript高级教程](#javascript高级教程)

## JavaScript简介

JavaScript是脚本语言：

*   JavaScript是一种轻量级的编程语言。
*   JavaScript是可插入HTML页面的编程代码。
*   JavaScript插入HTML页面后，可由所有的现代浏览器执行。

JavaScript用法：

*   脚本可位于body和head部分中，或者同时存在于两个部分中。脚本会在页面加载时执行。

```html
<script>
  function hello() {
    document.getElementById('demo').innerHTML='Hello World';
  }
<script>
<script src='my_script.js'></script>
```

## JavaScript基础

变量：

*   声明却不赋初值的变量，其值为undefined。
*   在ES6之前，JavaScript只有两种作用域：全局变量和函数内的局部变量。var关键字声明的变量不具备块级作用域，let关键字声明的变量具备块级作用域。
*   const定义的变量并非常量，并非不可变，它定义了一个常量引用一个值。使用const定义的对象或者数组，可以修改对象内的值，但是不能重新赋值新对象。

数据类型：

*   基本类型：字符串（String），数字（Number），布尔（Boolean），空（Null），未定义（Undefined），Symbol。
*   引用数据类型：对象（Object），数组（Array），函数（Function）。
*   constructor：是Object类型的原型属性，它能够返回当前对象的构造器（类型函数）。利用该属性，可以检测复合类型数据的类型，如对象，数组和函数等。

    ```javascript
    function isArray(myArray) {
      return myArray.constructor.toString().indexOf('Array') > -1;
    }
    ```

输出：

```javascript
console.log('new content');
window.alert('new content');
document.write('new content');
document.getElementById('demo').innerHTML = 'new content';
```

数组：

```javascript
let cars = Array();
car[0] = 'BMW';

let num = (1, 2, 3);
```

对象：

```javascript
let person = {
  firstName : 'John',
  lastName : 'Doe',
  id : 5555,
  fullName : function () {
    return this.firstName + ' ' + this.lastName;
  },
};
// function () { return this.firstName + ' ' + this.lastName; }
console.log(person.fullName);
// John Doe
console.log(person.fullName());
```

函数：

```javascript
function myFunc(a, b) {
  return a * b;
}
console.log(myFunc(1, 2));
```

事件：

*   事件可以用于处理表单验证，用户输入，用户行为及浏览器动作：页面加载时触发事件；页面关闭时触发事件；用户点击按钮执行动作；验证用户输入内容的合法性等等。
*   可以是哦那个多种方法来执行JavaScript事件代码：HTML事件属性可以直接执行JavaScript代码；HTML事件属性可以调用JavaScript函数；为HTML元素指定自己的事件处理程序等等。
*   [HTML DOM 事件](https://runoob.com/jsref/dom-obj-event.html)

```html
<!-- onchange -->
<body>
  <input type="text" id="fname" onchange="myFunction()">
  <p>当你离开输入框后，函数将被触发，将小写字母转为大写字母。</p>
<script>
  function myFunction() {
    let x = document.getElementById('fname');
    x.value = x.value.toUpperCase();
  }
</script>
</body>

<!-- onclick -->
<body>
  <button onclick="myFunction()">Click Me</button>
  <p id="demo"></p>
<script>
  function myFunction() {
    document.getElementById('demo').innerHTML = 'hello, world';
  }
</script>
</body>

<!-- onmouseover onmouseout -->
<body>
  <img onmouseover="bigImg(this)" onmouseout="normalImg(this)"
   border="0" src="resources/color_wheel.jpg" 
   alt="Color Wheel" width="32" height="32"
  />
<script>
  function bigImg(x){
    x.style.height='64px';
    x.style.width='64px';
  }
  function normalImg(x){
    x.style.height='32px';
    x.style.width='32px';
  }
</script>
</body>

<!-- onkeydown -->
<body>
  <input type="text" id="fname" onkeydown="myFunction()">
<script>
  function myFunction() {
    let x = document.getElementById('fname');
    console.log(x.value);
  }
</script>
</body>

<!-- onload -->
<body onload="myFunction()">
  <h1>Hello World</h1>
<script>
  function myFunction() {
    console.log('Hello');
  }
</script>
</body>

<!-- onsubmit -->
<body>
  <form name="form1" action="html_from_action.py" onsubmit="greeting()">
    <input type="text" name="fname">
    <input type="submit" value="Submit">
  </form>
<script>
  function greeting() {
    alert('Welcome ' + document.forms['form1']['fname'].value + '!');
  }
</script>
</body>
```

字符串：

*   [字符串属性和方法](https://runoob.com/jsref/jsref-obj-string.html)

```javascript
let carname = 'volvo xc60';
let character = carname[6];
let sln = carname.length;
let string_carname = String('volvo xc60');
carname == string_carname;   // true
carname === string_carname;  // false
```

运算符：

*   支持自增和自减运算符。
*   ==（等于），===（绝对等于，值和类型均相等）。

typeof：

```javascript
typeof 'John';                 // 返回 string
typeof 3.14;                   // 返回 number
typeof false;                  // 返回 boolean
typeof [1,2,3,4];              // 返回 object
typeof {name:'John', age:34};  // 返回 object
```

类型转换：

```javascript
/* Number to String */
String(100 + 23);
(123).toString();

/* Boolean to String */
String(false);
true.toString();

/* Date to String */
Date();

/* String to Number */
Number('3.14');
Number('');       // 0
Number('99 88');  // NaN

/* Boolean to Number*/
Number(false);    // 0
```

错误：

```javascript
function myFunction() {
  let messgae, x;
  message = document.getElementById('tips');
  message.innerHTML = '';
  x = document.getElementById('input_demo').value;
  try {
    if (x == '') throw '值是空的';
    if (isNaN(x)) throw '值不是一个数字';
    x = Number(x);
    if (x > 10) throw '太大';
    if (x < 5) throw '大小';
  }
  catch (err) {
    message.innerHTML = '错误：' + err;
  }
  finally {
    document.getElementById('input_demo').value = '';
  }
}
```

表单：

```html
<!-- 表单验证 -->
<body>
  <form name="myForm" action="demo_form.py" onsubmit="return validateForm();" method="post">
    名字：<input type="text" name="fname">
    <input type="submit" value="Submit">
  </form>
<script>
  function validateForm() {
    let x = document.forms['myForm']['fname'].value;
    if (x == null || x == '') {
      alert('需要输入名字');
      return false;
    }
  }
</script>
</body>

<!-- 表单验证输入的数字 -->
<body>
  <h1>JavaScript 验证输入</h1>
  <p>请输入 1 到 10 之间的数字：</p>
  <input id="numb">
  <button type="button" onclick="myFunction()" >提交</button>
  <p id="demo"></p>
<script>
  function myFunction() {
    let x, text;
    x = document.getElementById('numb').value;
    if (isNaN(x) || x < 1 || x > 10) {
      text = '输入错误';
    } else {
      text = '输入正确';
    }
    document.getElementById('demo').innerHTML = text;
  }
</script>
<body>

<!-- 表单自动验证 -->
<form action="demo_form.py" method="post">
  <input type="text" name="fname" required="required">
  <input type="submit" value="提交">
</form>

<!-- E-mail 验证 -->
<body>
  <form name="myForm" action="demo_form.py" onsubmit="return validateForm();" method="post">
    Email: <input type="text" name="email">
    <input type="submit" value="提交">  
  </form>
<script>
  function validateForm() {
    let x = document.forms['myForm']['email'].value;
    let atpos = x.indexOf('@');
    let dotpos = x.lastIndexOf('.');
    if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length) {
      alert('不是一个有效的e-mail地址');
      return false;
    }
  }
</script>
</body>
```

验证API：

```html
<!-- checkValidity -->
<body>
  <p>输入数字并点击验证按钮：</p>
  <input id="id1" type="number" min="100" max="300" required> 
  <button onclick="myFunction()">验证</button>
  <p>如果输入的数字小于 100 或大于 300 ，会提示错误信息。</p>
  <p id="demo"></p>
<script>
  function myFunction() {
    let inpObj = document.getElementById('id1');
    if (inpObj.checkValidity() == false) {
      document.getElementById('demo').innerHTML = inpObj.validationMessage;
    } else {
      document.getElementById('demo').innerHTML = '输入正确';
    }
  }
</script>
</body>

<!-- validity 属性 -->
<body>
  <p>输入数字并点击验证按钮:</p>
  <input id="id1" type="number" min="100" max="200" required>
  <button onclick="myFunction()">验证</button>
  <p>如果输入的数字大于 200 ( input 的 max 属性)，会显示错误信息。</p>
  <p>如果输入的数字小于 100 ( input 的 min 属性), 会显示错误信息。</p>
  <p id="demo"></p>
<script>
  function myFunction() {
    let txt = '';
    let inpObj = document.getElementById('id1');
    if (!isNumeric(inpObj.value)) {
      txt = '你输入的不是数字';
    } else if (inpObj.validity.rangeOverflow) {
      txt = '输入的值太大了';
    } else if (inpObj.validity.rangeUnderflow) {
        txt = '输入的值太小了';
    } else {
      txt = '输入正确';
    }
    document.getElementById('demo').innerHTML = txt;
  }
  // 判断输入是否为数字
  function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
  }
</script>
</body>
```

this：

*   在方法中，this表示该方法所属的对象。
*   在函数中，this表示全局对象。
*   单独使用时，this表示全局对象。
*   在事件中，this表示事件的元素。
*   支持`call`，`apple`，`bind`方法。

```javascript
let person = {
  firstName: 'John',
  lastName : 'Doe',
  id       : 5566,
  fullName : function () {
    return this.firstName + ' ' + this.lastName;
  }
};
```

```html
<button onclick="this.style.display='none';">
  点我后我就消失了
</button>
```

```javascript
let name = 'Alex', age = 17;
let obj1 = {
  name: 'Dylan',
  objAge: this.age,
  myFun: function (fm, t) {
    console.log('Name: ' + this.name + '; Age: ' + this.age +
                '; From ' + fm + ' to ' + t + ';');
  }
};
let obj2 = {
  name: 'Harrison',
  age: 19,
};
obj1.myFun.call(obj2, 'England', 'China');     // Name: Harrison; Age: 19; From England to China;
obj1.myFun.apply(obj2, ['England', 'China']);  // Name: Harrison; Age: 19; From England to China;
obj1.myFun.bind(obj2, 'England', 'China')();   // Name: Harrison; Age: 19; From England to China;
```

JSON（JavaScript Object Notation）：

*   JSON是一种轻量级的数据交换格式，是独立的语言。通常用于服务端向网页传递数据。
*   JSON语法规则：数据为key/value对；数据由逗号分隔；大括号保存对象；中括号保存数组。
*   JSON和JavaScript对象可以相互转换。

```json
{"sites": [
    {"name": "Runoob", "url": "www.runoob.com"}, 
    {"name": "Google", "url": "www.google.com"},
    {"name": "Taobao", "url": "www.taobao.com"}
]}
```

```javascript
let text = '{ "sites": [' +
  '{ "name": "Runoob" , "url": "www.runoob.com" },' +
  '{ "name": "Google" , "url": "www.google.com" },' +
  '{ "name": "Taobao" , "url": "www.taobao.com" } ]}';
let obj = JSON.parse(text);
console.log(obj.sites[1].name + ' ' + obj.sites[1].url);
let new_text = JSON.stringify(obj);
console.log(new_text);
```

void：

```html
<a href="javascript:void(0);">Click Me</a>
```

异步：

```javascript
/* setTimeout */
setTimeout(function () {
  console.log('Hello, World!');
}, 3000);

/* Promise */
new Promise(function (resolve, reject) {
  let a = 0;
  let b = 1;
  if (b == 0) reject('Divide zero');
  else resolve(a / b);
}).then(function (value) {
  console.log('a / b = ' + value);
}).catch(function (err) {
  console.log(err);
}).finally(function () {
  console.log('End');
});

/* Counter */
function print(delay, message) {
  return new Promise(function (resolve, reject) {
    setTimeout(function () {
      console.log(message);
      resolve();
    }, delay);
  });
}

print(1000, 'first').then(function () {
  return print(4000, 'second');
}).then(function () {
  print(3000, 'third');
});

async function asyncFunc() {
  await print(1000, 'first');
  await print(4000, 'second');
  await print(3000, 'third');
};
asyncFunc();
```

## JavaScript高级教程

函数：

```javascript
/* 函数声明 */
function myFunction(a, b) {
  return a * b;
}

/* 函数表达式 */
let x = function (a, b) { return a * b; };
let z = x(4, 3);

/* Function() 构造函数 */
let myFunction = Function('a', 'b', 'return a * b');
let x = myFunction(4, 3);

/* 自调用函数 */
(function () {
  console.log('Hello');
})();

/* 函数是对象 */
function myFunction(a, b) {
  return arguments.length;  // 返回函数调用过程接收到的参数个数
}
console.log(myFunction.toString());  // 将函数声明打印出来

/**
 * 箭头函数 
 * 使用 const 比使用 var 更安全，因为函数表达式始终是一个常量
 * 如果函数部分只是一个语句，则可以省略 return 关键字和大括号，这样做是一个比较好的习惯
 */
const x1 = () => console.log('Hello');
const x2 = a => console.log(a);
const x3 = (a) => console.log(a);
const x4 = (a, b, c) => { console.log(a * b * c); return true; }

/**
 * 显式参数与隐式参数
 * 函数定义显式参数时没有指定数据类型
 * 函数对隐式参数没有进行类型检测
 * 函数对隐式参数的个数没有进行检测
 */
function myFunction(x, y) {
  y = y || 0;
  return x * y;
}
console.log(myFunction(4));  // return 0

function findMax() {
  let i, max = arguments[0];
  if (arguments.length < 2) return max;
  for (i = 1; i < arguments.length; i++) {
    if (arguments[i] > max) {
      max = arguments[i];
    }
  }
  return max;
}
console.log(findMax(1, 123, 500, 115, 44, 88));  // return 500

/* 内嵌函数 */
function add() {
  let counter = 0;
  function plus() { counter += 1; }
  plus();
  return counter;
}
add();  // return 1

/* 闭包 */
let add = (function () {
  let counter = 0;
  return function () {return counter += 1;}
})();
 
add();
add(); 
add();  // return 3
```

HTML DOM：

*   当网页被加载时，浏览器会创建页面的文档对象模型（Document Object Model）。
*   ![HTML DOM](../resources/html_dom.gif)
*   通过可编程的对象模型，JavaScript获得了足够的能力来创建动态的HTML：
    *   JavaScript能够改变页面中的所有HTML元素。
    *   JavaScript能够改变页面中的所有HTML属性。
    *   JavaScript能够改变页面中的所有CSS样式。
    *   JavaScript能够对页面中的所有事件作出反应。

    ```javascript
    /* 查找HTML元素 */
    let x = document.getElementById('intro');
    let y = document.getElementById('main').getElementsByTagName('p');
    let z = document.getElementsByClassName('intro');
    
    /* 改变HTML输出流 */
    document.write(Date());
    document.getElementById('p1').innerHTML = 'Hello, World';
    document.getElementById('image').src = 'landscape.jpg';  // document.getElementById(id).attribute = new_value
    
    /**
     * 改变 HTML 样式
    * document.getElementById(id).style.property = new_value 
    */
    document.getElementById('p2').style.color = 'blue';
    document.getElementById('p2').style.fontSize = 'larger';
    
    /* 对事件做出反应 */
    document.getElementById('myBtn').onclick = function () { displayDate() };
    function displayDate {
      document.getElementById('demo').innerHTML = Date();
    }
    ```

*   EventListener：
    *   你可以向一个元素添加多个事件句柄。
    *   你可以向同个元素添加多个同类型的事件句柄，如两个click事件。
    *   你可以向任何DOm对象添加事件监听，如window对象。
    *   冒泡和捕获：在冒泡中，内部元素的事件会先被触发，然后再触发外部元素；在捕获中，外部元素的事件会先被触发，然后才会触发内部元素的事件。

    ```javascript
    function myFunction() {
      alert('Hello, World');
    }
    
    /* addEventListener */
    document.getElementById('myBtn').addEventListener('click', myFunction);
    
    /* removeEventListener */
    document.getElementById('myBtn').removeEventListener('click', myFunction);
    
    /** 
     * 冒泡 useCapture = false
    * 捕获 useCapture = true
    */
    addEventListener(event, function, useCapture);
    
    /* 向同个元素中添加多个事件句柄 */
    element.addEventListener('click', myFunction);
    element.addEventListener('click', mySecondFunction);
    
    /* 向同个元素添加不同类型的事件 */
    element.addEventListener('mouseover', myFunction);
    element.addEventListener('click', mySecondFunction);
    element.addEventListener('mouseout', myThirdFunction);
    ```

*   向文档中添加和移除元素（节点）：

    ```javascript
    /* 创建新的 HTML 元素 */
    let para = document.createElement('p');
    let node = document.createTextNode('This is a new paragraph.');
    para.appendChild(node);
    
    /* 添加到已存在元素中 */
    let element = document.getElementById('div1');
    element.appendChild(para);
    
    /* 添加到子元素前面 */
    let element = document.getElementById('div1');
    let child = document.getElementById('p1');
    element.insertBefore(para, child);
    
    /* 移除已存在的元素 */
    let parent = document.getElementById('div1');
    let child = document.getElementById('p1');
    element.removeChild(child);
    
    /* 替换已存在的元素 */
    let parent = document.getElementById('div1');
    let child = document.getElementById('p1');
    parent.replaceChild(para, child);
    ```

*   Collection：Collection看起来可能是一个数组，但其实不是。它无法使用数组的方法，如valueOf，push，pop等。

    ```javascript
    /* Collection */
    let myCollection = document.getElementsByTagName('p');
    for (let i = 0; i < myCollection.length; i++) {
      myCollection[i].style.backgroundColor = 'red';
    }
    ```

*   NodeList：与Collection类似。

    ```javascript
    /* NodeList */
    let myNodeList = document.querySelectorAll('p');
    for (let i = 0; i < myNodelist.length; i++) {
        myNodelist[i].style.backgroundColor = 'red';
    }
    ```

高级：

*   prototype（原型对象）：给已有的函数对象增加属性或者方法。

    ```javascript
    /* prototype */
    function Person(first, last, age, eyecolor) {
      this.firstName = first;
      this.lastName = last;
      this.age = age;
      this.eyeColor = eyecolor;
    }
    Person.prototype.name = function () {
      return this.firstName + ' ' + this.lastName;
    }
    // 如果在一个函数前面带上 new 来调用该函数，那么
    // 将创建一个隐藏连接到该函数的 prototype 成员
    // 的新对象，同时 this 将被绑定到那个新对象上。
    let myFather = new Person('John', 'Doe', 50, 'blue');
    ```

*   Number对象：

    ```javascript
    /* Number */
    let x = 0.2 + 0.1;                   // 输出结果为 0.30000000000000004
    while (myNumber != Infinity) {}      // 无穷大
    isNaN(myNumber);                     // 判断是否为非数字
    isInteger(myNumber);                 // 判断是否为整数
    Number.MAX_VALUE;                    // 最大值
    Number.MIN_VALUE;                    // 最小值
    Number.NaN;                          // 非数字
    let y = 123; typeof(y);              // return 'number'
    let z = new Number(123); typeof(z);  // return 'object'
    (y === z);                           // return false
    ```

*   String对象：

    ```javascript
    /* String */
    let carname = 'Volvo XC60';
    let character = x[7];                // character = 'C'
    carname.length;                      // return 10
    carname.indexOf('olvo');             // return 1
    carname.replace('60', '30');         // 替换，但不修改 carname 本身
    carname.toUpperCase();               // 大写，但不修改 carname 本身
    carname.toLowerCase();               // 小写，但不修改 carname 本身
    let arr = carname.split('o');        // arr = ['V', 'lv', ' XC60']
    ```

*   Date对象：

    ```javascript
    /* Date */
    let data = new Date();               // data = Mon Nov 29 2021 22:23:44 GMT+0800 (中国标准时间)
    data.getFullYear();                  // return 2021
    data.getTime();                      // return 1638195824583
    data.getDay();                       // return 1。0 代表周日，1 代表周一，以此类推。
    data.setFullYear(2100, 0 , 14);      // data = Thu Jan 14 2100 00:00:00 GMT+0800 (中国标准时间)
    let d1 = new Date('October 13, 1975 11:13:00');  // d1 = Mon Oct 13 1975 11:13:00 GMT+0800 (中国标准时间)
    ```

*   Array对象：

    ```javascript
    /* Array */
    let mycars = new Array();            // 等价于 let myCars = new Array('Saab', 'Volvo', 'BMW');
    mycars[0] = 'Saab';
    mycars[1] = 'Volvo';
    mycars[2] = 'BMW';
    mycars.length;                       // return 3
    let arr3 = arr1.concat(arr2);        // 合并数组
    arr1.join();                         // 用数组的元素组成字符串，不修改本身
    arr1.push('abc');                    // 末尾添加新元素
    arr1.pop('abc');                     // 删除最后一个元素
    arr1.sort();                         // 排序，修改本身
    ```

*   Boolean对象：

    ```javascript
    /* Boolean */
    let myBoolean = new Boolean();       // myBoolean = false
    // 使用 0, -0, null, '', false, undefined, NaN 初始化值，结果都是 false，否则其值为 true。
    ```

*   Math对象：

    ```javascript
    /* Math */
    Math.round(2.5);                     // return 3
    Math.round(2.4);                     // return 2
    Math.random();                       // 返回 0 到 1 之间的随机数
    Math.max(value1, value2);            // 返回最大值
    Math.min(value1, value2);            // 返回最小值
    Math.E;                              // return 2.718281828459045
    Math.PI;                             // return 3.141592653589793
    Math.SQRT2;                          // return 1.4142135623730951
    ```

*   RegExp对象：

    ```javascript
    /* RegExp: Regular Expression */
    // let patt = new RegExp(pattern, modifiers);
    // let patt = /pattern/modifiers;
    // i - 修饰符用来执行不区分大小写的匹配
    let str = 'Visit RUNoob';
    let patt = /runoob/i;
    str.match(patt);                     // return ['RUNoob']
    // g - 修饰符用于执行全文搜索
    let str = 'Is this all there is?';
    let patt = /is/g;
    str.match(patt);                     // return ['is', 'is']
    // test 函数：搜索字符串指定的值，根据结果返回真假
    let patt = new RegExp('e');
    patt.test('The best things in life are free');  // return true
    // exec 函数：找到返回本身，没找到返回 null
    let patt = new RegExp('eo');
    patt.exec('Hello, World');           // return null
    ```

浏览器对象模型（Browser Object Model）:

*   Window对象:
    *   所有浏览器都支持window对象（它表示浏览器窗口）。
    *   所有JavaScript全局对象、函数以及变量均自动成为window对象的成员。
    *   全局变量是window对象的属性。
    *   全局函数是window对象的方法。
    *   甚至DOM中document也是window对象的属性之一。

    ```javascript
    // 两者等价
    window.document.getElementById('header');
    document.getElementById('header');
    ```

*   window.screen：

    ```javascript
    /* 窗口尺寸 */
    window.innerHeight;                     // 可见尺寸
    window.innerWidth;
    document.documentElement.clientHeight;  // 可见尺寸，不包括滚动条
    document.documentElement.clientWidth;
    document.body.clientHeight;             // 页面尺寸，页面如果支持滚动会比可见尺寸大
    document.body.clientWidth;
    
    // 其他window方法
    window.open();                          // 打开窗口
    window.close();                         // 关闭窗口
    
    // screen
    screen.availHeight;                     // 可用尺寸
    screen.availWidth;
    ```

*   window.location：

    ```javascript
    /* location */
    location.hostname;                      // 域名
    location.pathname;                      // 当前页面的路径和文件名
    location.href;                          // hostname + pathname
    location.port;                          // 返回端口
    location.protocol;                      // 返回 web 协议
    location.assign('https://www.runoob.com');  // 转到 runoob 主页面
    ```

*   window.history：

    ```javascript
    /* history */
    history.back();                         // 与在浏览器中点击退后按钮相同
    history.forward();                      // 与在浏览器中点击向前按钮相同
    ```

*   window.navigator：信息具有误导性，数据可以被浏览器使用者更改。

    ```javascript
    /* navigator */
    navigator.appCodeName;                  // return 'Mozilla'
    navigator.appName;                      // return 'Netscape'
    navigator.appVersion;                   // return '5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36'
    ```

*   弹窗（Popup）：`window.alert`，`window.confirm`，`window.prompt`。

    ```javascript
    /* 弹窗 */
    window.alert('Hello\nHow are you?');    // 警告框
    window.confirm('按下按钮');              // 确认框
    window.prompt('请输入你的名字', 'Harry Potter');  // 提示框
    ```

*   Cookie：以名/值对形式存储。

    ```javascript
    /* cookie */
    document.cookie;                        // 查看网页的 cookie
    document.cookie="username=John Doe; expires=Thu, 18 Dec 2043 12:00:00 GMT";  // 创建 cookie
    let data = document.cookie;             // 读取 cookie
    document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 GMT";  // 删除 cookie
    ```

内容分发网络（Content Delivery Network）：CDN是包含分享代码库的服务器网络。

*   [国内CDN](https://staticfile.org)
*   [国外CDN](https://cdnjs.com)
