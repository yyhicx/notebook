# JavaScript中常见问题

1.  [模块化](#模块化)

## 模块化

模块化：

*   随着JS代码量激增，所有源代码放在同一个文件里面，不容易维护，并且牵一发而动全身。
*   这时候就需要将代码按照逻辑放在不同的文件里面，按照一定的语法规则，遵循特定的规范将一个庞大的文件拆分若干个相互依赖的文件。这些文件对外暴露数据或接口，在需要的时候导入引用。这就是前端模块化。

模块化分为三个阶段：早期“伪模块化”时代、多种规范标准时代和ES原生时代。

早期“伪模块化”时代：

*   借助函数的作用域来实现伪模块化，不同的功能封装成不同的函数。

```javascript
/**
 * 函数模块化
 *   特点：JavaScript 函数有独立的作用域，在函数中可以放任何代码，只需要在使用的地方
 *   调用它，实现代码分离组织，视觉上看起来也很清晰。
 * 
 *   局限：如果代码量巨大，无法保证模块之间不发生冲突，各个函数在同一个文件中，没有前后
 *   逻辑的依赖关系，混乱的调用，而且存在命名冲突和变量污染问题。
 */
function fn1() {}
function fn2() {}
function fn3() {
  fn1();
  fn2();
}

/**
 * 对象模块化
 *   特点：对象可以有属性，而且它的属性可以是数据，也可以是方法。对象的属性可以通过
 *   对象名字来访问，相当于设定了一个命名空间，于是对象模块化也叫做命名空间模式。
 * 
 *   局限：数据安全性低，内部属性是赤裸暴露的，对象的内部成员可以随意修改。
 */
const module1 = {
  data1: 'data1',
  fn1: function () {
    // ...
  },
};

const module2 = {
  data2: 'data2',
  fn2: function () {
    // ...
  },
};

module2.data2 = 'data1';

// 解决方案：IIFE 立即执行函数 + 闭包 + 对外暴露数据和接口。
// 闭包可以解决数据访问安全，立即执行函数创建一个私有的作用域，
// 这样就能实现私有数据和共享方法。
(function (window) {
  let data = 'data';

  function showData() {
    console.log(`data is ${data}`);
  }

  function updateData() {
    data = 'newData';
    console.log(`data is ${data}`);
  }

  window.module1 = {  // 暴露模块给外界（window）
    showData,
    updateData,
  };
})(window);

// 将 module1 作为参数传入给 module2
let module1 = (function () {
  let age = 'age';
  
  function getAge() {
    console.log(age);
  }
  
  return {
    getAge,
  };
})();

let module2 = (function (module) {
  let name = 'name';
  
  function getName() {
    console.log(name);
  }
  
  return {
    getName,
    ...module
  };
})(module1);
```

多种规范标准时代：

*   CommonJS规范：
    *   CommonJS是2009年由JavaScript社区提出的包含了模块化的一个标准，后来被Node.js所采用并实现，也就是说我们在Node.js中用到的模块导入导出都是依照CommonJS标准来实现的。
    *   CommonJS用同步的方法加载模块，在服务器端的模块文件都存放在本地磁盘中，读取速度快。
    *   CommonJS规范特点：
        *   所有的代码都在独立的模块作用域中，不会污染全局作用域。
        *   模块加载顺序是按照其在代码中的引入顺序加载。
        *   模块可以多次加载，但是只会在第一次加载时运行一次，运行结果被缓存。之后加载是从缓存中直接读取，清空缓存重新运行。
        *   module.exports属性输出是值拷贝。一旦操作完成，模块内发生的任何变化不会影响到已经输出的值。

    ```javascript
    /**
     * CommonJS 规范
    * 导出：
    *   使用 module.exports 或 exports
    *   exports 的引用是指向 module.exports 的，等价于 let exports = module.exports
    *   注意，不能对 exports 进行重新赋值，这就使得 exports 和 module.exports 没有关系了
    * 导入：
    *   使用 require
    */
    function show() {
      // ...
    }

    module.exports = {  // use module.exports
      show,
    };

    exports.show = show;  // use exports

    const bModule = require('./b.js');  // use require
    ```

*   AMD规范：
    *   AMD（Asynchronous Module Definition），即异步模块定义。
    *   AMD用异步的方法加载模块，这对浏览器端非常友好。
    *   AMD可以解决以下两个问题：
        *   实现JavaScript文件的异步加载，避免网页失去响应。
        *   管理模块之间的依赖性，便于代码的编写和维护。
    *   require.js是一个JavaScript脚本加载器，它遵循AMD规范，实现JavaScript脚本的异步加载，不阻塞页面的渲染和其后的脚本的执行，并提供了在加载完成之后的执行相应回调函数的功能。
        *   src: open django_example project and run it, then visit localhost:8000/html-example/use-requirejs
*   CMD规范：
    *   CMD（Common Module Definition），即通用模块定义。
    *   seajs是一个遵循CMD规范的JavaScript模块加载框架，可以实现JavaScript的模块化开发及加载机制。

ES6原生模块：

*   ES6模块化的两个特点：

    *   ES6模块化规范中导出的值是引用，所以不能何时修改模块中变量，在外部都会有体现。

    *   静态化，编译的时候就确定了模块之间的关系，每个模块的输入和输出变量也是确定的（静态化是为了实现tree shaking，以提升运行性能）。

```javascript
/**
 * ES6 规范
 * 导出：
 *   使用 export 或者 export default
 *   注意，一个模块只能默认导出一次
 * 导入：
 *   使用 import
 */
export function show1() {}  // the first case of use export
export function show2() {}

function print1() {}
function print2() {}
export { print1, print2 };  // the second case of use export

function show() {}
export default show;  // use export default

import { show1 as print1, show2 as print2} from 'module1.js';  // use import
import * as module1 from 'module1.js';
import show from 'module1.js';  // the show function is exported through export default
```
