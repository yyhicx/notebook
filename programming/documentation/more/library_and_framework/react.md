# React

1.  [React文档](#react文档)
2.  [常用库](#常用库)

## React文档

React是一个用于构建用户界面的JavaScript库。

React中使用样式：

```javascript
// 行内样式
<div sstyle={{width: '40px', display: 'inline-block'}}></div>

// className
// 在 React 中设置 class 时使用 className，然后引入对应的 CSS 文件
<div className="red"><div>
```

工具链：

*   使用流行的React工具链，有助于完成如下的任务：
    *   扩展文件和组件的规模。
    *   使用来自npm的第三方库。
    *   尽早发现常用错误。
    *   在开发中实时编辑CSS和JS。
    *   优化生产输出。
*   工具链选择：
    *   不需要工具链，可以考虑把React作为普通`<script>`标记添加到HTML页面中。
    *   使用`Create React App`，用于创建一个新的单页应用。
    *   使用`Next.js`，用于Node.js构建服务端渲染的网站。
*   一组JavaScript构建工具链通常由这些组成：
    *   package管理器，比如npm。它能让你充分利用庞大的第三方package的生态系统，并且轻松地安装或更新它们。
    *   打包器，比如webpack。它能让你编写模块化代码，并将它们组合在一起成为小的package，以优化加载时间。
    *   编译器，比如Babel。它能让你编写的新版本JavaScript代码，在旧版浏览器中依然能够工作。
*   React Devtools：React官方提供的浏览器扩展插件。

核心概念：

*   JSX简介：
    *   是一个JavaScript的语法扩展，可以很好地描述UI应该呈现出它应有交互的本质形式。
    *   Babel会把JSX转译成一个名为`React.createElement()`函数调用。

    ```javascript
    /* jsx */
    const user = {
      firstName: 'Harper',
      lastName: 'Perez',
    };
    function formatName(user) {
      return user.firstName + ' ' + user.lastName;
    }
    const element = <h1>Hello, {formatName(user)}!</h1>;
    ReactDOM.render(
      element,
      document.getElementById('root')
    );
    ```

*   元素渲染：
    *   元素是构成React应用的最小砖块。
    *   与浏览器的DOM元素不同，React元素是创建开销极小的普通对象。React DOM会负责更新DOM来与React元素保持一致。
    *   React DOM会将元素和它的子元素与它们之前的状态进行比较，并只会进行必要的更新来使DOM达到预期的状态。
*   组件和Props：
    *   组件允许你将UI拆分成独立可复用的代码片段，并对每个片段进行独立构思。
    *   组件，从概念上类似于JavaScript函数。它接受任意的入参（即"props"），并返回用于描述页面展示内容的React元素。
    *   当React元素为用户自定义组件时，它会将JSX所接收的属性（attributes）以及子组件（children）转换为单个对象传递给组件，这个对象被称之为"props"。
    *   React会将以小写字母开头的组件视为原生DOM标签，所以自定义组件名称必须以大写字母开头。

    ```javascript
    /* 函数组件与 class 组件 */
    class Welcome extends React.Component {
      render() {
        return <h1>Hello, {this.props.name}</h1>;
      }
    }
    function Welcome(props) {
      return <h1>Hello, {props.name}</h1>;
    }
    function App() {
      return (
        <div>
          <Welcome name="sara" />
          <Welcome name="Cahal" />
          <Welcome name="Edite" />
        </div>
      );
    }
    ReactDOM.render(
      <App />,
      document.getElementById('root')
    );
    ```

*   State和生命周期：
    *   当组件第一次被渲染到DOM中的时候，这个过程在React中被称为挂载（mount）。当DOM中组件被删除的时候，这个过程在React中被称为卸载（unmount）。
    *   当组件挂载或卸载时就会去执行一些方法，这些方法叫做生命周期方法。如`componentDidMount()`，`componentWillUnmount()`函数等等。
    *   如果你把一个以组件构成的树想象成一个props的数据瀑布的话，那么每一个组件的state就像是在任意一点上给瀑布增加额外的水源，但是它只能向下流动。

    ```javascript
    /* State */
    class Clock extends React.Component {
      // 构造函数是唯一给 this.state 赋值的地方
      // 应该使用 setState() 函数修改 this.state 的值
      constructor(props) {
        super(props);
        this.state = {date: new Date()};
      }
      componentDidMount() {
        this.timerID = setInterval(
          () => this.tick(),
          1000
        );
      }
      componentWillUnmount() {
        clearInterval(this.timerID);
      }
      tick() {
        this.setState({
          date: new Date()
        });
      }
      render() {
        return (
          <div>
            <h1>Hello, World!</h1>
            <h2>It is {this.state.date.toLocaleTimeString()}.</h2>
          </div>
        );
      }
    }
    ReactDOM.render(
      <Clock />,
      document.getElementById('root')
    );
    ```

*   事件处理：
    *   React事件的命名采用小驼峰式，而不是纯小写。
    *   使用JSX语法时需要传入一个函数作为事件处理函数，而不是一个字符串。

    ```javascript
    /* 事件处理 */
    // 在传统的 HTML 中
    <a href="#" onclick="console.log('This link was clicked.'); return false;">
      Click me
    </a>
    // 在 React 中，函数组件
    function ActionLink() {
      function handleClick(e) {
        e.preventDefault();
        console.log('The link was clicked.');
      }
      return (
        <a href="#" onClick={handleClick}>
          Click me
        </a>
      );
    }
    // 在 React 中，class 组件
    class Toggle extends React.Component {
      constructor(props) {
        super(props);
        this.state = {isToggleOn: true};
        // 为了在回调中使用 `this`，这个绑定是必不可少的
        this.handleClick = this.handleClick.bind(this);
      }
      handleClick() {
        this.setState(state => ({
          isToggleOn: !state.isToggleOn
        }));
      }
      render() {
        return (
          <button onClick={this.handleClick}>
            {this.state.isToggleOn ? 'ON' : 'OFF'}
          </button>
        );
      }
    }
    ```

*   条件渲染：React中的条件渲染和JavaScript中的一样，使用JavaScript运算符if或者条件运算符去创建元素来表现当前的状态。

    ```javascript
    /* 条件渲染 */
    function UserGreeting(props) {
      return <h1>Welcome back!</h1>;
    }
    function GuestGreeting(props) {
      return <h1>Please sign up.</h1>;
    }
    function Greeting(props) {
      const isLoggedIn = props.isLoggedIn;
      if (isLoggedIn) {
        return <UserGreeting />;
      } else {
        return <GuestGreeting />;
      }
    }
    ReactDOM.render(
      // Try changing to isLoggedIn={true}:
      <Greeting isLoggedIn={false} />,
      document.getElementById('root')
    );
    // 元素变量
    function LoginButton(props) {
      return (
        <button onClick={props.onClick}>
          Login
        </button>
      );
    }
    function LogoutButton(props) {
      return (
        <button onClick={props.onClick}>
          Logout
        </button>
      );
    }
    class LoginControl extends React.Component {
      constructor(props) {
        super(props);
        this.state = {isLoggedIn: false};
        this.handleLoginClick = this.handleLoginClick.bind(this);
        this.handleLogoutClick = this.handleLogoutClick.bind(this);
      }
      handleLoginClick() {
        this.setState({isLoggedIn: true});
      }
      handleLogoutClick() {
        this.setState({isLoggedIn: false});
      }
      render() {
        const isLoggedIn = this.state.isLoggedIn;
        let button;
        if (isLoggedIn) {
          button = <LogoutButton onClick={this.handleLogoutClick} />;
        } else {
          button = <LoginButton onClick={this.handleLoginClick} />;
        }
        return (
          <div>
            <Greeting isLoggedIn={isLoggedIn} />
            {button}
          </div>
        );
      }
    }
    ReactDOM.render(
      <LoginControl />,
      document.getElementById('root')
    );
    // 与运算符 &&
    return (
      <div>
        <h1>Hello!</h1>
        {unreadMessages.length > 0 &&
          <h2>
            You have {unreadMessages.length} unread messages.
          </h2>
        }
      </div>
    );
    // 三目运算符
    return (
      <div>
        The user is <b>{isLoggedIn ? 'currently' : 'not'}</b> logged in.
      </div>
    );
    // 阻止渲染
    function WarningBanner(props) {
      if (!props.warn) {
        return null;
      }
      return (
        <div className="warning">
          Warning!
        </div>
      );
    }
    ```

*   列表和Key：
    *   Key帮助React识别哪些元素改变了，比如被添加或删除。因此应当给数组中的每一个元素赋予一个确定的标识。
    *   一个好的经验法则是：在`map()`方法中的元素需要设置key属性。
    *   数组元素中使用的key在其兄弟节点之间应该是独一无二的。然而，它们不需要是全局唯一的。
    *   key会传递信息给React，但不会传递给你的组件。如果你的组件中需要使用key属性的值，请用其他属性名（如id）显式传递这个值。

    ```javascript
    /* 列表和 Key */
    function NumberList(props) {
      const numbers = props.numbers;
      const listItems = numbers.map((number) => 
        <li key={number.toString()}>{number}</li>
      );
      return (
        <ul>{listItems}</ul>
      );
    }
    const numbers = [1, 2, 3, 4, 5];
    ReactDOM.render(
      <NumberList numbers={numbers} />,
      document.getElementById('root')
    );
    // 在 JSX 中嵌入 map()
    function NumberList(props) {
      const numbers = props.numbers;
      return (
        <ul>
          {numbers.map((number) =>
            <ListItem key={number.toString()}
                      value={number} />
          )}
        </ul>
      );
    }
    ```

*   表单：在HTML中，表单元素（如`<input>`，`textarea`，`<select>`）之类的表单元素通常自己维护state，并根据用户输入进行更新。而在React中，可变状态（mutable state）通常保存在组件的state属性中，并且只能通过使用`setState()`来更新。我们可以把两者结合起来，使React的state成为“唯一数据源”。渲染表单的React组件还控制着用户输入过程中表单发生的操作。被React以这种方式控制取值的表单输入元素就叫做“受控组件”。

    ```javascript
    /* 表单 */
    // input tag
    class NameForm extends React.Component {
      constructor(props) {
        super(props);
        this.state = {value: ''};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }
      handleChange(event) {
        this.setState({value: event.target.value});
      }
      handleSubmit(event) {
        alert('提交的名字：' + this.state.value);
        event.preventDefault();
      }
      render() {
        return (
          <form onSubmit={this.handleSubmit}>
            <label>
              名字:
              <input type="text" value={this.state.value} onChange={this.handleChange} />
            </label>
            <input type="submit" value="提交" />
          </form>
        );
      }
    }
    // textarea tag
    class EssayForm extends React.Component {
      constructor(props) {
        super(props);
        this.state = {
          value: '请撰写一篇关于你喜欢的 DOM 元素的文章.'
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }
      handleChange(event) {
        this.setState({value: event.target.value});
      }
      handleSubmit(event) {
        alert('提交的文章: ' + this.state.value);
        event.preventDefault();
      }
      render() {
        return (
          <form onSubmit={this.handleSubmit}>
            <label>
              文章:
              <textarea value={this.state.value} onChange={this.handleChange} />
            </label>
            <input type="submit" value="提交" />
          </form>
        );
      }
    }
    // select tag
    class FlavorForm extends React.Component {
      constructor(props) {
        super(props);
        this.state = {value: 'coconut'};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }
      handleChange(event) {
        this.setState({value: event.target.value});
      }
      handleSubmit(event) {
        alert('你喜欢的风味是: ' + this.state.value);
        event.preventDefault();
      }
      render() {
        return (
          <form onSubmit={this.handleSubmit}>
            <label>
              选择你喜欢的风味:
              <select value={this.state.value} onChange={this.handleChange}>
                <option value="grapefruit">葡萄柚</option>
                <option value="lime">酸橙</option>
                <option value="coconut">椰子</option>
                <option value="mango">芒果</option>
              </select>
            </label>
            <input type="submit" value="提交" />
          </form>
        );
      }
    }
    // 处理多个输入
    class Reservation extends React.Component {
      constructor(props) {
        super(props);
        this.state = {
          isGoing: true,
          numberOfGuests: 2
        };
        this.handleInputChange = this.handleInputChange.bind(this);
      }
      handleInputChange(event) {
        const target = event.target;
        const value = target.name === 'isGoing' ? target.checked : target.value;
        const name = target.name;
        this.setState({
          [name]: value
        });
      }
      render() {
        return (
          <form>
            <label>
              参与：
              <input
                name="isGoing"
                type="checkbox"
                checked={this.state.isGoing}
                onChange={this.handleInputChange} />
            </label>
            <br />
            <label>
              来宾人数:
              <input
                name="numberOfGuests"
                type="number"
                value={this.state.numberOfGuests}
                onChange={this.handleInputChange} />
            </label>
          </form>
        );
      }
    }
    ```

*   状态提升：在React中，将多个组件中需要共享的state向上移动到它们的最近共同父组件中，便可实现共享state。这就是所谓的“状态提升”。

    ```javascript
    /* 状态提升 */
    function BoilingVerdict(props) {
      if (props.celsius >= 100) {
        return <p>The water would boil.</p>
      } else {
        return <p>The water would not boil.</p>
      }
    }
    function toCelsius(fahrenheit) {
      return (fahrenheit - 32) * 5 / 9;
    }
    function toFahrenheit(celsius) {
      return (celsius * 9 / 5) + 32;
    }
    function tryConvert(temperature, convert) {
      const input = parseFloat(temperature);
      if (Number.isNaN(input)) {
        return '';
      }
      const output = convert(input);
      // 保留三位小数并四舍五入
      const rounded = Math.round(output * 1000) / 1000;
      return rounded.toString();
    }
    const scaleNames = {
      c: 'Celsius',
      f: 'Fahrenheit',
    }
    class TemperatureInput extends React.Component {
      constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
      }
      handleChange(e) {
        this.props.onTemperatureChange(e.target.value);
      }
      render() {
        const temperature = this.props.temperature;
        const scale = this.props.scale;
        return (
          <fieldset>
            <legend>Enter temperature in {scaleNames[scale]}:</legend>
            <input value={temperature} onChange={this.handleChange} />
          </fieldset>
        );
      }
    }
    class Calculator extends React.Component {
      constructor(props) {
        super(props);
        this.handleCelsiusChange = this.handleCelsiusChange.bind(this);
        this.handleFahrenheitChange = this.handleFahrenheitChange.bind(this);
        this.state = {temperature: '', scale: 'c'};
      }
      handleCelsiusChange(temperature) {
        this.setState({scale: 'c', temperature});
      }
      handleFahrenheitChange(temperature) {
        this.setState({scale: 'f', temperature});
      }
      render() {
        const scale = this.state.scale;
        const temperature = this.state.temperature;
        const celsius = scale === 'f' ? tryConvert(temperature, toCelsius) : temperature;
        const fahrenheit = scale === 'c' ? tryConvert(temperature, toFahrenheit) : temperature;
        return (
          <div>
            <TemperatureInput
              scale="c"
              temperature={celsius}
              onTemperatureChange={this.handleCelsiusChange} />
            <TemperatureInput
              scale="f"
              temperature={fahrenheit}
              onTemperatureChange={this.handleFahrenheitChange} />
            <BoilingVerdict
              celsius={parseFloat(celsius)} />
          </div>
        );
      }
    }
    ```

*   组合和继承：
    *   React有十分强大的组合模式，推荐使用组合而非继承来实现组件间的代码重用。
    *   Props和组合为你提供了清晰而安全地定制组件外观和行为的灵活方式。注意：组件可以接受任意props，包含基本数据类型，React元素以及函数。
    *   如果你想要在组件间复用非UI的功能，建议将其提取为一个单独的JavaScript模块，如函数、对象或者类。组件可以直接引入（import）而无需通过 extend 继承它们。

    ```javascript
    /* 组合 */
    // 包含关系
    function FancyBorder(props) {
      return (
        <div className={'FancyBorder FancyBorder-' + props.color}>
          {props.children}
        </div>
      );
    }
    function WelcomeDialog() {
      return (
        <FancyBorder color="blue">
          <h1 className="Dialog-title">
            Welcome
          </h1>
          <p className="Dialog-message">
            Thank you for visiting our spacecraft!
          </p>
        </FancyBorder>
      );
    }
    // 包含关系：利用自定义 prop 传递
    function SplitPane(props) {
      return (
        <div className="SplitPane">
          <div className="SplitPane-left">
            {props.left}
          </div>
          <div className="SplitPane-right">
            {props.right}
          </div>
        </div>
      );
    }
    function App() {
      return (
        <SplitPane
          left={
            <Contacts />
          }
          right={
            <Chat />
          } />
      );
    }
    // 特例关系
    function Dialog(props) {
      return (
        <FancyBorder color="blue">
          <h1 className="Dialog-title">
            {props.title}
          </h1>
          <p className="Dialog-message">
            {props.message}
          </p>
          {props.children}
        </FancyBorder>
      );
    }
    class SignUpDialog extends React.Component {
      constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSignUp = this.handleSignUp.bind(this);
        this.state = {login: ''};
      }
      render() {
        return (
          <Dialog title="Mars Exploration Program"
                  message="How should we refer to you?">
            <input value={this.state.login}
                  onChange={this.handleChange} />
            <button onClick={this.handleSignUp}>
              Sign Me Up!
            </button>
          </Dialog>
        );
      }
      handleChange(e) {
        this.setState({login: e.target.value});
      }
      handleSignUp() {
        alert(`Welcome aboard, ${this.state.login}!`);
      }
    }
    ```

*   React哲学：
    *   React是用JavaScript构建快速响应的大型Web应用程序的首选方式。它在Facebook和Instagram上表现优秀。
    *   你可以自上而下或者自下而上构建应用：自上而下意味着首先编写层级较高的组件，自下而上意味着从最基本的组件开始编写。当你的应用比较简单时，使用自下而上的方式更方便；对于较为大型的项目来说，自下而上地构建，并同时为低层组件编写测试是更加简单的方式。
    *   src: open react-example project and run it, then visit localhost:3000/filterable-product-table

高级指引：

*   无障碍：
    *   网络无障碍辅助功能（Accessibility，也被称为a11y，因为以A开头，以Y结尾，中间一共11个字母）是一种可以帮助所有人获得服务的设计和创造。无障碍辅助功能是使得辅助技术正确解读网页的必要条件。
    *   WAI-ARIA：网络无障碍倡议-无障碍互联网应用。
    *   JSX支持所有`aria-*`HTML属性。虽然大多数React的DOM变量和属性命名都是用驼峰命名（camelCased），但`aria-*`应该像其在HTML中一样使用带连字符的命名方法。

    ```javascript
    /* 无障碍 */
    <input
      type="text"
      aria-label={labelText}
      aria-required="true"
      onChange={onChangeHandler}
      value={inputValue}
      name="name"
    />
    ```

*   代码分割：
    *   打包：大多数React应用都会使用Webpack，Rollup或Browserify这类的构建工具来打包文件。打包是一个将文件引入并合并到一个单独文件的过程，最终形成一个“bundle”。接着在页面上引入该bundle，整个应用即可一次性加载。
    *   打包是个非常棒的技术，但随着你的应用增长，你的代码包也将随之增长。尤其是在整合了体积巨大的第三方库的情况下。你需要关注你的代码包中所包含的代码，以避免因体积过大而导致加载时间过长。
    *   为了避免搞出大体积的代码包，在前期就思考该问题并对代码包进行分割是个不错的选择。代码分割是由诸如Webpack这类打包器支持的一项技术，能够创建多个包并在运行时动态加载。
    *   对你的应用进行代码分割能够帮助你“懒加载”当前用户所需要的内容，能够显著地提高你的应用性能。尽管并没有减少应用整体的代码体积，但你可以避免加载用户永远不需要的代码，并在初始加载的时候减少所需的加载的代码量。

    ```javascript
    /* 代码分割 */
    // 源代码：app.js
    import { add } from './math.js';
    console.log(add(16, 26));  // 42
    // 源代码：math.js
    export function add(a, b) {
      return a + b;
    }
    // 打包后
    function add(a, b) {
      return a + b;
    }
    console.log(add(16, 26));
    // 代码分割：import()
    import('./math').then(math => {
      console.log(math.add(16, 26);
    });
    // 代码分割：React.lazy
    // React.lazy 和 Suspense 技术还不支持服务端渲染。如果需要服务端渲染，推荐 Loadable Components 这个库。
    import React, { Suspense } from 'react';
    const OtherComponent = React.lazy(() => import('./OtherComponent'));
    const AnotherComponent = React.lazy(() => import('./AnotherComponent'));
    function MyComponent() {
      return (
        <div>
          {/* 如果模块加载失败（如网络问题），可以通过 Error boundaries 技术来处理 */}
          <MyErrorBoundary>
            <Suspense fallback={<div>Loading...</div>}>
              <section>
                <OtherComponet />
                <AnotherComponent />
              </section>
            </Suspense>
          </MyErrorBoundary> 
        </div>
      );
    }
    // 代码分割：路由
    import React, { Suspense, lazy } from 'react';
    import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
    const Home = lazy(() => import('./routes/Home'));
    const About = lazy(() => import('./routes/About'));
    const App = () => (
      <Router>
        <Suspense fallback={<div>Loading...</div>}>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/about" component={About} />
            <Route />
          </Switch>
        </Suspense>
      </Router>
    );
    // 命名导出（Named Exports）
    // ManyComponets.js
    export const MyComponent = /* ... */;
    export const MyUnusedComponent = /* ... */;
    // MyComponent.js
    export { MyComponent as default } from './ManyComponents.js';
    // MyApp.js
    const MyComponent = lazy(() => import('./MyComponents.js'));
    ```

*   Context：
    *   Context提供了一种在组件之间共享此类值的方式，而不必显式地通过组件树的逐层传递props。
    *   Context设计目的是为了共享那些对于一个组件树而言是“全局”的数据，例如当前认证的用户、主题和首选语言。
    *   src: open react-example project and run it, then visit localhost:3000/context-example

    ```javascript
    /* Context */
    const ThemeContext = React.createContext('light');
    class App extends React.Component {
      render() {
        return (
          <ThemeContext.Provider value="dark">
            <Toolbar />
          </ThemeContext.Provider>
        );
      }
    }
    function Toolbar() {
      return (
        <div>
          <ThemeButton />
        </div>
      );
    }
    class ThemeButton extends React.Component {
      static contextType = ThemeContext;
      render() {
        return <Button theme={this.context} />;
      }
    }
    ```

*   错误边界（Error Boundaries）：
    *   部分UI的JavaScript错误不应该导致整个应用奔溃，为了解决这个问题，React16引入错误边界。
    *   错误边界是一种React组件，这种组件可以捕获并打印发生在其子组件树任何位置的JavaScript错误，并且，它会渲染出备用UI，而不是渲染那些崩溃的子组件树。
    *   错误边界在渲染期间，生命周期方法和整个组件树的构造函数中捕获错误。
    *   注意错误边界仅可以捕获其子组件的错误，它无法捕获其自身的错误。如果一个错误边界无法渲染错误信息，则错误会冒泡至最近的上层错误边界，这也类似JavaScript中catch {} 的工作机制。

    ```javascript
    /* 错误边界 */
    class ErrorBoundary extends React.Component {
      constructor(props) {
        super(props);
        this.state = { hasError: false };
      }
      static getDerivedStateFromError(error) {
        // 更新 state 使下一次渲染能够显示降级后的 UI
        return { hasError: true };
      }
      componentDidiCatch(error, errorInfo) {
        // 你同样可以将错误日志上报给服务器
        logErrorToMyService(error, errorInfo);
      }
      render() {
        if (this.state.hasError) {
          return <h1>Something went wrong.</h1>;
        }
        return this.props.children;
      }
    }
    <ErrorBoundary>
      <MyWidget />
    </ErrorBoundary>
    ```

*   Fragments：React中的一个常见模式是一个组件返回多个元素。Fragments允许你将子列表分组，而无需向DOM添加额外节点。

    ```javascript
    /* Fragments */
    // 可以用 <></> 代替 <React.Fragment></React.Fragment>
    <React.Fragment>
      <ChildA />
      <ChildB />
      <ChildC />
    </React.Fragment>
    ```

*   高阶组件：
    *   高阶组件（HOC）是React中用于复用组件逻辑的一种高级技巧。HOC自身不是React API的一部分，它是一种基于React的组合特性而形成的设计模式。
    *   具体而言，高阶组件是参数为组件，返回值为新组件的函数。
    *   组件是将props转换为UI，而高阶组件是将组件转换为另一个组件。

    ```javascript
    /* 高阶组件 */
    class CommentList extends React.Component {
      render() {
        return (
          <div>
            {this.props.data.map((comment) => (
              <Comment comment={comment} key={comment.id} />
            ))}
          </div>
        );
      }
    }
    class BlogPost extends React.Component {
      render() {
        return <TextBlock text={this.props.data} />;
      }
    }
    function withSubscription(WrappedComponent, selectData) {
      return class extends React.Component {
        constructor(props) {
          super(props);
          this.handleChange = this.handleChange.bind(this);
          this.state = {
            data: selectData(DataSource, props)
          };
        }
        componentDidMount() {
          DataSource.addChangeListener(this.handleChange);
        }
        componentWillUnmount() {
          DataSource.removeChangeListener(this.handleChange);
        }
        handleChange() {
          this.setState({
            data: selectData(DataSource, this.props)
          });
        }
        render() {
          return <WrappedComponent data={this.state.data} {...this.props} />;
        }
      };
    }
    const CommentListWithSubscription = withSubscription(
      CommentList,
      (DataSource) => DataSource.getComments()
    );
    const BlogPostWithSubscription = withSubscription(
      BlogPost,
      (DataSource, props) => DataSource.getBlogPost(props.id)
    );
    // 约定：将不相关的 props 传递给被包裹的组件
    render() {
      // 过滤掉非此 HOC 额外的 props，且不要进行透传
      const { extraProp, ...passThroughProps } = this.props;
      // 将 props 注入到被包装的组件中
      // 通常为 state 的值或者实例方法
      const injectedProp = someStateOrInstanceMethod;
      return (
        <WrappedComponent 
          injectedProp={injectedProp}
          {...passThroughProps}
        />
      );
    }
    ```

*   与第三方库协同：React不会理会React自身之外的DOM操作。它根据内部虚拟DOM来决定是否需要更新，而且如果同一个DOM节点被另一个库操作了，React会觉得困惑并且没有办法恢复。
*   深入JSX：实际上，JSX仅仅只是`React.createElement()`函数的语法糖。

    ```javascript
    /* 深入 JSX */
    <MyButton color="blue" shadowSize={2}>
      Click Me
    </MyButton>
    // 等价于
    React.createElement(
      MyButton,
      {color: 'blue', shadowSize: 2},
      'Click Me'
    )
    // 在 JSX 类型中使用点语法
    import React from 'react';
    const MyComponents = {
      DatePicker: function DatePicker(props) {
        return <div>Imagine a {props.color} datepicker here.</div>;
      }
    }
    function BlueDatePicker() {
      return <MyComponents.DatePicker color="blue" />;
    }
    // Props 默认值为 True，完全不推荐这样使用
    <MyTextBox autocomplete />  // autocomplete = True
    // 属性展开，使用 ... 运算符
    function App() {
      const props = { firstName: 'Ben', lastName: 'Hector' };
      return <Greeting {...props} />;
    }
    const Button = props => {
      const { kind, ...other } = props;
      const className = kind === 'primary' ? 'PrimaryButton' : 'SecondaryButton';
      return <button className={className} {...other} />;
    }
    // JavaScript 表达式作为子元素
    function Item(props) {
      return <li>{props.message}</li>;
    }
    function TodoList() {
      const todos = ['finish doc', 'submit pr', 'nag dan to review'];
      return (
        <ul>
          {todos.map((message) => <Item key={message} message={message} />)}
        </ul>
      );
    }
    // 函数作为 props.children
    function Repeat(props) {
      let items = [];
      for (let i = 0; i < props.numTimes; i++) {
        items.push(props.children(i));
      }
      return <div>{items}</div>;
    }
    function ListOfTenThings() {
      return (
        <Repeat numTimes={10}>
          {(index) => <div key={index}>This is item {index} in the list</div>}
        </Repeat>
      );
    }
    // 布尔值判断
    <div>
      {showHeader && <Header/>}
      <Content />
    </div>
    <div>
      {props.message.length > 0 &&
        <MessageList messages={props.message} />
      }
    </div>
    ```

*   性能优化：UI更新需要昂贵的DOM操作，而React内部使用几种巧妙的技术以便最小化DOM操作次数。对于大部分应用而言，使用React时无需专门优化就已拥有高性能的用户界面。尽管如此，你仍然有办法来加速你的React应用。
*   Portals：
    *   Protal提供了一种将子节点渲染到存在于父组件以外的DOM节点的优秀的方案。
    *   src: open react-example project and run it, then visit localhost:3000/portal-example
*   Profiler：
    *   Profiler测量渲染一个React应用多久渲染一次以及渲染一次的“代价”。它的目的是识别出应用中渲染较慢的部分，或是可以使用类似memoization优化的部分，并从相关优化中获益
    *   Profiling增加了额外的开支，所以它在`生产构建`中会被禁用，需要其他特殊的生产构建环境。
    *   src: open react-example project and run it, then visit localhost:3000/profiler-example
*   不使用ES6：如果你还未使用过ES6，你可以使用create-react-class模块来定义class组件。

    ```javascript
    /* 不使用 ES6 */
    // 使用 ES6
    class Greeting exntends React.Component {
      render() {
        return <h1>Hello, {this.props.name}</h1>;
      }
    }
    // 不使用 ES6
    let createReactClass = require('create-react-class');
    let Greeting = createReactClass({
      render: function () {
        return <h1>Hello, {this.props.name}</h1>;
      }
    });
    ```

*   不使用JSX：React并不强制要求使用JSX。当你不想在构建环境中配置有关JSX编译时，不在React中使用JSX会更加方便。

    ```javascript
    /* 不使用 JSX */
    class Hello extends React.Component {
      render() {
        return React.createElement('div', null, `Hello ${this.props.toWhat}`);
      }
    }
    ReactDOM.render(
      React.createElement(Hello, {toWhat: 'World'}, null);
      document.getElementById('root');
    );
    ```

*   协调：
    *   React提供的声明式API让开发者可以在对React的底层实现并不了解的情况下编写应用。在开发者编写应用时，可以保持相对简单的心智，但开发者无法了解其内部的实现原理。本文描述了在实现React的“diffing”算法过程中所做出的设计决策，以保证组件更新可预测，且在繁杂业务场景下依然保持应用的高性能。
    *   设计动机：
        *   在某一时间节点调用React的`render()`方法，会创建一棵由React元素组成的树。在下一次state或props更新时，相同的`render()`方法会返回一棵不同的树。React需要基于这两棵树之间的差别来判断如何高效的更新UI，以保证当前UI与最新的树保持同步。此算法有一些通用的解决方案，即生成将一棵树转换为另一棵树的最小操作次数。然而，即使使用最优的算法，该算法的复杂度仍为O(n<sup>3</sup>)，其中n是树中元素的数量。
        *   上述方法开销太过高昂，于是React在一下两个假设的基础之上提出了一套O(n)的启发式算法：两个不同类型的元素会产生出不同的树。开发者可以通过设置key属性，来告知渲染哪些子元素在不同的渲染下可以保持不变。
    *   Diffing算法：
        *   对比不同类型的元素：当根节点为不同类型的元素时，React会拆卸原有的树并且建立起新的树。当卸载一棵树时，对应的DOM节点也会被销毁。组件实例将执行`componentWillUnmount()`方法。当建立一棵新的树时，对应的DOM节点会被创建以及插入到DOM中。组件实例将执行`UNSAFE_componentWillMount()`方法，紧接着`componentDidMount()`方法。所有与之前的树相关联的state也会被销毁。
        *   对比同一类型的DOM Element：当对比两个相同类型的React元素时，React会保留DOM节点，仅比对及更新有改变的属性。当更新style属性时，React仅更新有所更变的属性。在处理完当前节点之后，React继续对子节点进行递归。
        *   对比同一类型的Component Element：当一个组件更新时，组件实例会保持不变，因此可以在不同的渲染时保持`state`一致。React将更新该组件实例的`props`以保证与最新的元素保持一致，并且调用该实例的`UNSAFE_componentWillReceiveProps()`、`UNSAFE_componentWillUpdate()`以及`componentDidUpdate()`方法。
        *   对子节点进行递归：默认情况下，当递归DOM节点的子元素时，React会同时遍历前后两个子元素的列表；当产生差异时，生成一个mutation。所以在子元素列表末尾新增元素时，更新开销比较小。
        *   Keys：为了解决上述问题，React引入了`key`属性。当子元素拥有`key`时,React使用`key`来匹配原有树上的子元素以及最新树上的子元素。
        *   权衡：请谨记协调算法是一个实现细节。React可以在每个action之后对整个应用进行重新渲染，得到对最终结果也会是一样的。在此情境下，重新渲染表示在所有组件内调用`render`方法，这不代表React会卸载或装载它们。React只会基于以上提到的规则来决定如何进行差异的合并。
*   Refs and the DOM：
    *   Refs提供了一种方式，允许我们访问DOM节点或在render方法中创建的React元素。
    *   何时使用Refs：管理焦点，文本选择或媒体播放。触发强制动画。集成第三方DOM库。
    *   勿过度使用Refs。

    ```javascript
    /* Refs and the DOM */
    class MyComponent extends React.Component {
      constructor(props) {
        super(props);
        this.myRef = React.createRef();
        this.focusTextInput = this.forcusTextInput.bind(this);
      }
      focusTextInput() {
        this.textInput.current.focus();
      }
      render() {
        return (
          <div>
            <input 
              type="text"
              ref={this.textInput} />
            <input 
              type="button"
              value="Focus the text input"
              onClick={this.focusTextInput}
            />
          </div>
        );
      }
    }
    // 模拟 CustomTextInput 挂在之后立即被点击
    class AutoFocusTextInput extends React.Component {
      constructor(props) {
        super(props);
        this.textInput = React.createRef();
      }
      componentDidMount() {
        this.textInput.current.focusTextInput();
      }
      render() {
        return (
          <CustomTextInput ref={this.textInput} />
        );
      }
    }
    ```

*   Render Props：
    *   术语“render prop”是指一种在React组件之间使用一个值为函数的prop共享代码的简单技术。具有render prop的组件接受一个返回React元素的函数，并在组件内部通过调用此函数来实现自己的渲染逻辑。
    *   src: open react-example project and run it, then visit localhost:3000/render-props-example
*   静态类型检查：像Flow和TypeScript等这些静态类型检查器，可以在运行前识别某些类型的问题。他们还可以通过增加自动补全等功能来改善开发者的工作流程。出于这个原因，我们建议在大型代码库中使用Flow或TypeScript来代替PropTypes。
*   严格模式：`StrictMode`是一个用来突出显示应用程序中潜在问题的工具。与`Fragment`一样，`StrictMode`不会渲染任何可见的UI。它为其后代元素触发额外的检查和警告。

    ```javascript
    /* 严格模式 */
    function ExampleApplication() {
      return (
        <div>
          <Header />
          <React.StrictMode>
            <div>
              <ComponentOne />
              <ComponentTwo />
            </div>
          </React.StrictMode>
          <Footer />
        </div>
      );
    }
    ```

*   使用PropTypes进行类型检查。

    ```javascript
    /* PropTypes */
    import PropTypes from 'prop-types';
    class Greeting extends React.Component {
      const children = this.props.children;
      render() {
        return (
          <h1>
            Hello, {this.props.name}
            <div>
              {children}
            </div>
          </h1>
        );
      }
    }
    Greeting.propTypes = {
      name: PropTypes.string,
      children: PropTypes.element.isRequired,
    };
    ```

*   非受控组件：在大多数情况下，我们推荐使用`受控组件`来处理表单数据。在一个受控组件中，表单数据是由React组件来管理的。另一种替代方案是使用`非受控组件`，这时表单数据将交由DOM节点来处理。

    ```javascript
    /* 非受控组件 */
    class NameForm extends React.Component {
      constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.input = React.createRef();
      }
      handleSubmit(event) {
        alert('A name was submitted: ' + this.input.current.value);
        event.preventDefault();
      }
      render() {
        return (
          <form onSubmit={this.handleSubmit}>
            <label>
              Name:
              <input type="text" ref={this.input} />
            </label>
            <input type="submit" value="Submit" />
          </form>
        );
      }
    }
    ```

*   Web Components：React和Web Components为了解决不同的问题而生。Web Components为可复用组件提供了强大的封装，而React则提供了声明式的解决方案，使DOM与数据保持同步。两者旨在互补。作为开发人员，可以自由选择在Web Components中使用React，或者在React中使用Web Components，或者两者共存。

    ```javascript
    /* Web Components */
    class XSearch extends HTMLElement {
      connectedCallback() {
        const mountPoint = document.createElement('span');
        this.attachShadow({ mode: 'open' }).appendChild(mountPoint);
        const name = this.getAttribute('name');
        const url = 'https://www.google.com/search?q=' + encodeURIComponent(name);
        ReactDOM.render(<a href={url}>{name}</a>, mountPoint);
      }
    }
    customElements.define('x-search', XSearch);
    class HelloMessage extends React.Component {
      render() {
        return <div>Hello <x-search>{this.props.name}</x-search>!</div>;
      }
    }
    ```

HOOK：

*   Hook简介：
    *   Hook是React 16.8的新增特性，它可以让你在不编写class情况下使用state以及其他的React特性。
    *   Hook是一个特殊的函数，它可以让你“钩入”React的特性。
    *   如果你在编写函数组件并意识到需要向其添加一些state时，就应该使用Hook。
*   使用State Hook：
    *   State只在组件首次渲染的时候被创建。在下一次重新渲染时，`useState`返回给我们当前的state。
    *   当我们使用`useState`定义state变量时候，它返回一个有两个值的数组。第一个值是当前的state，第二个值是更新state的函数。

    ```javascript
    /* State Hook */
    import React, { useReact } from 'react';
    function Example() {
      const [count, setCount] = useState(0);
      return (
        <div>
          <p>You clicked {count} times</p>
          <button onClick={() => setCount(count + 1)}>
            Click Me
          </button>
        </div>
      );
    }
    ```

*   使用Effect Hook：
    *   Effect Hook可以让你在函数组件中执行副作用操作。
    *   如果你熟悉React Class的生命周期，你可以把useEffect Hook看作componentDidMount，componentDidUpdate和componentWillUnmount这三个函数的组合。
    *   在React组件中有两种常见副作用操作：需要清除的和不需要清除的。
    *   无需清除的Effect：在React更新DOM之后运行一些额外的代码。比如发送网络请求，手动变更DOM，记录日志，这些都是常见的无需清除的操作。
    *   需要清除的Effect：例如订阅外部数据源。

    ```javascript
    /* Effect Hook */
    import React, { useState, useEffect } from 'react';
    function Example() {
      const [count, setCount] = useState(0);
      // Similar to componentDidMount and componentDidUpdate:
      useEffect(() => {
        // Update the document title using the browser API
        document.title = `You clicked ${count} times`;
      });
      return (
        <div>
          <p>You clicked {count} times</p>
          <button onClick={() => setCount(count + 1)}>
            Click Me
          </button>
        </div>
      );
    }
    // 清除函数
    import React, { useState, useEffect } from 'react';
    function FriendStatus(props) {
      const [isOnline, setIsOnline] = useState(null);
      useEffect(() => {
        function handleStatusChange(status) {
          setIsOnline(status.isOnline);
        }
        ChatAPI.subscribeToFriendStatus(props.friend.id, hangleStatusChange);
        // Specify how to clean up after this effect:
        return function cleanup() {
          ChatAPI.unsubscribeFromFriendStatus(props.friend.id, handleStatusChange);
        };
      });
      if (isOnline === null) {
        return 'Loading...';
      }
      return isOnline ? 'Online' : 'Offline';
    }
    ```

*   Hook规则：
    *   Hook本质就是JavaScript函数，但是在使用它时需要遵循两条规则。也可以使用linter插件强制执行这些规则。
    *   两条规则：只在最顶层使用Hook，不要在循环、条件或嵌套函数中调用Hook。只在React函数中调用Hook，不要在普通的JavaScript函数中调用Hook。
*   自定义Hook：
    *   通过自定义Hook，可以将组件逻辑提取到可重用的函数中。
    *   自定义Hook是一个函数，其名称以“use”开头，函数内部可以调用其他的Hook。

    ```javascript
    /* 自定义Hook */
    import React, { useState, useEffect } from 'react';
    function useFriendStatus(friendID) {
      const [isOnline, setIsOnline] = useState(null);
      useEffect(() => {
        function handleStatusChange(status) {
          setIsOnline(status.isOnline);
        }
        ChatAPI.subscribeToFriendStatus(friendID, handleStatusChange);
        return () => {
          ChatAPI.unsubscribeFromFriendStatus(friendID, handleStatusChange);
        };
      });
    }
    function FriendStatus(props) {
      const isOnline = useFriendStatus(props.friend.id);
      if (isOnline === null) {
        return 'Loading...';
      }
      return isOnline ? 'Online' : 'Offline';
    }
    function FriendListItem(props) {
      const isOnline = useFriendStatus(props.friend.id);
      return (
        <li style={{ color: isOnline ? 'green' : 'black' }}>
          {props.friend.name}
        </li>
      );
    }
    // 在多个 Hook 之间传递信息
    const friendList = [
      { id: 1, name: 'Phoebe' },
      { id: 2, name: 'Rachel' },
      { id: 3, name: 'Ross' },
    ];
    function ChatRecipientPicker() {
      const [recipientID, setRecipientID] = useState(1);
      const isRecipientOnline = useFriendStatus(recipientID);
      return (
        <>
          <Circle color={ isRecipientOnline ? 'green' : 'red' } />
          <select
            value={recipientID}
            onChange={e => setRecipientID(Number(e.target.value))}
          >
            {friendList.map(friend => (
              <option key={friend.id} value={friend.id}>
                {friend.name}
              </option>
            ))}
          </select>
        </>
      );
    }
    ```

*   Hook API索引：
    *   `const [state, setState] = useState(initialState);`
        *   返回一个state，以及更新state的函数。
        *   在初始渲染期间，返回的状态（state）与传入的第一个参数（initialState）相同。
        *   setState函数用于更新state。它接受一个新的state值并将组件的一次重新渲染加入队列。
        *   在后续的重新渲染中，useState返回的第一个值将始终是更新后最新的state。
    *   `useEffect(didUpdate);`
        *   该Hook接受一个包含命令式、且可能有副作用代码的函数。
        *   在函数组件主体内（这里指在React渲染阶段）改变DOM、添加订阅、设置定时器、记录日志以及执行其他包含副作用的操作都是不被允许的，因为这可能会产生莫名其妙的bug并破坏UI的一致性。
        *   使用useEffect完成副作用操作。赋值给useEffect的函数会在组件渲染到屏幕之后执行。可以把effect看作从React的纯函数式世界通往命令式世界的逃生通道。
        *   默认情况下，effect将在每轮渲染结束后执行，但你可以选择让它在只有某些值改变的时候才执行。
        *   effect的执行时机：与componentDidMount，componentDidUpdate不同的是，在浏览器完成布局与绘制之后，传给useEffect的函数会延迟调用。虽然useEffect会在浏览器绘制后延迟执行，但会保证在任何新的渲染前执行，这样用户才不会感觉到视觉上的不一致。
        *   effect的条件执行：默认情况下，effect会在每轮组件渲染完成后执行。这样的话，一旦effect的依赖发生变化，它就会被重新创建。然而，在某些场景下这样做可能会矫枉过正。有时候不需要在每次组件更新时都创建新的订阅，而是仅需要prop改变时重新创建。
        *   如果effect的第二个参数为空则函数组件每次被渲染；如果第二个参数为一个空数组，那么仅会在组件第一次被渲染时执行。其他的情况都是在数组内元素完全相同时才不执行。
    *   `const value = useContext(MyContext);`
        *   接收一个context对象（React.createContext的返回值）并返回该context的当前值。当前的context值由上层组件中距离当前组件最近的`<MyContext.Provider>`的value prop决定。
        *   当组件上层最近的`<MyContext.Provider>`更新时，该Hook会触发重渲染，并使用最新传递给MyContext Provider的context value值。
    *   `const [state, dispatch] = useReducer(reducer, initialArg, init);`
        *   useState的替代方案。它接收一个形如(state, action) => newState的reducer，并返回当前的state以及与其配置的dispatch方法。
        *   在某些场景下，useReducer会比useState更适用，例如state逻辑较复杂且包含多个子值，或者下一个state依赖于之前的state等。
    *   `const memoizedCallback = useCallback(() => { doSomething(a, b); }, [a, b] );`
        *   返回一个memoized回调函数。
        *   把内联回调函数及依赖项数组作为参数传入useCallback，它将返回该回调函数的memoized版本，该回调函数仅在某些依赖项改变时才会更新。
    *   `const memoizedValue = useMemo(() => computeExpensiveValue(a, b), [a, b]);`
        *   返回一个memoized值。
        *   把创建函数和依赖项数组作为参数传给useMemo，它仅会在某个依赖项改变时才重新计算memoized值。这种优化有助于避免在每次渲染时都进行高开销的计算。
    *   `const refContainer = useRef(initialValue);`
        *   useRef返回一个可变的ref对象，其.current属性被初始化为传入的参数（initialValue）。返回的ref对象在组件的整个生命周期内保持不变。
    *   `useImperativeHandle(ref, createHandle, [deps]);`
        *   useImperativeHandle可以让你在使用ref时自定义暴露给父组件的实例值。
        *   useImperativeHandle应当与forwardRef一起使用。
    *   `useLayoutEffect`
        *   其函数签名与useEffect相同，但它会在所有的DOM变更之后同步调用effect。
        *   尽可能使用标准的useEffect以避免阻塞视觉更新。
    *   `useDebugValue(value);`
        *   useDebugValue可用于在React开发者工具中显示自定义hook标签。

    ```javascript
    /* Hook API 索引 */
    // useState
    function Counter({initialCount}) {
      const [count, setCount] = useState(initialCount);
      return (
        <>
          Count: {count}
          <button onClick={() => setCount(initialCount)}>Reset</button>
          <button onClick={() => setCount(prevCount => prevCount - 1)}>-</button>
          <button onClick={() => setCount(prevCount => prevCount + 1)}>+</button>
        </>
      );
    }
    // useEffect
    useEffect(() => {
      const subscription = props.source.subscribe();
      return () => {
        subscription.unsubscribe();
      };
    });
    // effect 的条件执行
    useEffect(
      () => {
        const subscription = props.source.subscribe();
        return () => {
          subscription.unsubscribe();
        };
      },
      [props.source],
    );
    // useContext
    const themes = {
      light: {
        foreground: '#000000',
        background: '#eeeeee',
      },
      dark: {
        foreground: '#ffffff',
        background: '#222222',
      }
    };
    const ThemeContext = React.createContext(themes.light);
    function App() {
      return (
        <ThemeContext.Provider value={themes.dark}>
          <Toolbar />
        </ThemeContext.Provider>
      );
    }
    function Toolbar(props) {
      return (
        <div>
          <ThemeButton />
        </div>
      );
    }
    function ThemeButton() {
      const theme = useContext(ThemeContext);
      return (
        <button style={{ background: theme.background, color: theme.foreground }}>
          I am styled by theme context!
        </button>
      );
    }
    // useReducer
    const initialState = {count: 0};
    function reducer(state, action) {
      switch (action.type) {
        case 'increment':
          return {count: state.count + 1};
        case 'decrement':
          return {count: state.count - 1};
        default:
          throw new Error();
      }
    }
    function Counter() {
      const [state, dispatch] = useReducer(reducer, initialState);
      return (
        <>
          Count: {state.count}
          <button onClick={() => dispatch({type: 'decrement'})}>-</button>
          <button onClick={() => dispatch({type: 'increment'})}>+</button>
        </>
      );
    }
    // useReducer 惰性初始化
    function init(initialCount) {
      return {count: initialCount};
    }
    function reducer(state, action) {
      switch (action.type) {
        case 'increment':
          return {count: state.count + 1};
        case 'decrement':
          return {count: state.count - 1};
        case 'reset':
          return init(action.payload);
        default:
          throw new Error();
      }
    }
    function Counter({initialCount}) {
      const [state, dispatch] = useReducer(reducer, initialCount, init);
      return (
        <>
          Count: {state.count}
          <button
            onClick={() => dispatch({type: 'reset', payload: initialCount})}>
            Reset
          </button>
          <button onClick={() => dispatch({type: 'decrement'})}>-</button>
          <button onClick={() => dispatch({type: 'increment'})}>+</button>
        </>
      );
    }
    // useRef
    function TextInputWithFocusButton() {
      const inputEl = useRef(null);
      const onButtonClick = () => {
        // current 指向已挂载到 DOM 上的文本输入元素
        inputEl.current.focus();
      }
      return (
        <>
          <input ref={inputEl} type="text" />
          <button onClick={onButtonClick}>Focus the input</button>
        </>
      );
    }
    // do not use useImperativeHandle
    import React, { useRef, forwardRef } from 'react';
    const MyInput = forwardRef((props, ref) => {
      return <input type="text" ref={ref} />;
    });
    function ForwardDemo() {
      const inputRef = useRef(null);
      return (
        <div>
          <MyInput ref={inputRef} />
          <button onClick={e => inputRef.current.focus()}>Focus</button>
        </div>
      );
    }
    // use useImperativeHandle
    import React, { useRef, forwardRef, useImperativeHandle } from 'react';
    const MyInput = forwardRef((props, ref) => {
      const inputRef = useRef();
      useImperativeHandle(ref, () => ({
        focus: () => {
          inputRef.current.focus()
        }
      }), [inputRef]);
      return <input type="text" ref={inputRef} />;
    })
    function UseImperativeHandle() {
      const inputRef = useRef();
      return (
        <div>
          <MyInput ref={inputRef} />
          <button onClick={e => inputRef.current.focus()}>Focus</button>
        </div>
      );
    }
    ```

## 常用库

Create React App:

*   Install: yarn create react-app my-app

Redux:

*   Install: yarn add redux react-redux @reduxjs/toolkit
*   Source:
    *   src: open react-example project and run it, then visit localhost:3000/counter
    *   src: open react-example project and run it, then visit localhost:3000/todos

```javascript
import { createSlice, configureStore } from '@reduxjs/toolkit';

const counterSlice = createSlice({
  name: 'counter',
  initialState: {
    value: 0,
  },
  reducers: {
    incremented: state => {
      // Redux Toolkit allows us to write "mutating" logic in reducers. It
      // doesn't actually mutate the state because it uses the Immer library,
      // which detects changes to a "draft state" and produces a brand new
      // immutable state based off those changes
      state.value += 1;
    },
    decremented: state => {
      state.value -= 1;
    }
  },
});

export const { incremented, decremented } = counterSlice.actions;

const store = configureStore({
  reducer: counterSlice.reducer;
})

// Can still subscribe to the store
store.subscribe(() => console.log(store.getState()));

// Still pass action objects to `dispatch`, but they're created for us
store.dispatch(incremented());
// {value: 1}
store.dispatch(incremented());
// {value: 2}
store.dispatch(decremented());
// {value: 1}
```

React Router

Axios

Chakra UI:

*   Install: yarn add @chakra-ui/react @emotion/react @emotion/styled framer-motion

MUI

Babel

Webpack

React Spring:

*   Install: yarn add react-spring
*   API:
    *   Common:
        *   Props: `useSpring({ from: { ... }, to: { ... }, delay: 100, onRest: () => ... });`
        *   Configs: `useSpring({ config: { duration: 250 }, ... });`
        *   Imperatives & Refs
    *   Hooks:
        *   useSpring
        *   useSprings
        *   useTransition
        *   useChain
        *   useTrail
*   Source:
    *   src: open react-example project and run it, then visit localhost:3000/react-spring-example
    *   src: open react-example project and run it, then visit localhost:3000/animated-card
    *   src: open react-example project and run it, then visit localhost:3000/animated-tree
    *   src: open react-example project and run it, then visit localhost:3000/draggable-list
    *   src: open react-example project and run it, then visit localhost:3000/masonry

```javascript
/* Imperatives */
const [props, set, stop] = useSpring(() => ({ opacity: 1 }));
// Update spring with new props
set({ opacity: toggle ? 1 : 0 });
/* Refs */
const springRef = useSpringRef();
const { size, ...rest } = useSpring({
  ref: springRef,
  config: config.stiff,
  from: { size: '20%', background: 'hotpink' },
  to: {
    size: '100%',
    background: 'white',
  },
});
```
