# LaTeX入门

1.  [TeX和LaTeX](#tex和latex)
2.  [LaTeX支持的数学公式写法](#latex支持的数学公式写法)
3.  [LaTeX支持的数学符号](#latex支持的数学符号)
4.  [XeLaTeX示例](#xelatex示例)

## TeX和LaTeX

TeX：是一种宏语言，同时其也是一种排版引擎。基本的TeX系统只有300多个元命令，十分精悍，但是很难读懂。

TeX引擎：执行TeX语法的程序的统称。TeX引擎的基本功能就是解释TeX语法，把字排成行，把行排成页，涉及到断字、断行、分页等算法。

*   Tex：1978年由Donald Erwin Knuth开发，是最原始的引擎，是后来大部分TeX引擎的基础。其生成dvi文件，然后经由其它程序转换为pdf文件。
*   pdfTeX：TeX语言的又一个实现，将TeX代码直接编译成PDF文件。
*   XeTeX：TeX语言的新实现，支持Unicode编码和直接访问操作系统字体。
*   LuaTeX：TeX语言的又一完整的支持扩展的实现。LuaTeX支持Unicode、系统字体和内嵌语言扩展，能直接输出PDF格式文件，也可以输出dvi文件。

TeX格式：TeX语言本身只有300个命令，晦涩难懂。一个简单的符号可能就需要多个命令来实现，所以可以将这些最基本的命令封装起来做个简写（宏）以实现特殊的目的。这样一堆简写的合集就构成了TeX格式。各种TeX格式可以与不同的引擎相结合。

*   Plain TeX：由Don Knuth提供的最小的宏集合。
*   LaTeX：更易于使用的宏集合，最常见的一种格式。
*   ConTeXt：另一种常见的格式。

TeX宏包：是一种辅助文件，在LaTeX中叫做packages，在ConTeXt叫做modules。在LaTeX格式中，导言区的\usepackage的作用就是引入各种宏包。宏包其实就是一堆基本的LaTeX命令的集合，只是不够全，所以称之为宏包而不是格式。

TeX发行版：一个完整的TeX需要最基本的TeX引擎、格式支持、各种辅助宏包、一些转换程序、GUI、编辑器、文档查看器等等。通过选择不同的组合就构成了不同的发行版。

*   TeX Live：支持Linux、Windows、MacOS。
*   CTeX：基于MiKTeX，并支持中文，但是只支持Windows。
*   TinyTeX：基于TeX Live的轻量级、跨平台、可移植且易于维护的LaTeX发行版。

## LaTeX支持的数学公式写法

行中公式:

```latex
$数学公式$
```

行间公式：单独成行。

```latex
$$数学公式$$
```

## LaTeX支持的数学符号

[LaTeX公式手册](https://www.cnblogs.com/1024th/p/11623258.html)

$$
% 指数
\exp_a b = a^b, \exp b = e^b, 10^m \\
% 对数
\ln c, \lg d = \log e, \log_{10} f \\
% 三角函数
\sin a, \cos b, \tan c, \cot d, \sec e, \csc f \\
\arcsin a, \arccos b, \arctan c \\
\sinh a, \cosh b, \tanh c, \coth d \\
\operatorname{sh}k, \operatorname{ch}l, \operatorname{th}m, \operatorname{coth}n \\
\operatorname{argsh}o, \operatorname{argch}p, \operatorname{argth}q \\
% 绝对值
\left\vert s \right\vert \\
% 最小值和最大值
\min(x,y), \max(x,y) \\
% 极限
\lim_{x \to \infty} \frac{1}{n(n+1)} \\
% 微分及导数
dt, \mathrm{d}t, \partial t, \nabla\psi \\
dy/dx, \mathrm{d}y/\mathrm{d}x, \frac{dy}{dx}, \frac{\mathrm{d}y}{\mathrm{d}x}, \frac{\partial^2}{\partial x_1\partial x_2}y \\
\prime, \backprime, f^\prime, f', f'', f^{(3)}, \dot y, \ddot y \\
% 积分
\int_{-N}^{N} e^x\, {\rm d}x \\
% 模运算
a \bmod b \\
% 根号
\surd, \sqrt{2}, \sqrt[n]{}, \sqrt[3]{\frac{x^3+y^3}{2}} \\
% 运算符
+, -, \pm, \mp, \dotplus \\
\times, \div, \divideontimes, /, \backslash \\
\cdot, * \ast, \star, \circ, \bullet \\
\oplus, \ominus, \otimes, \oslash, \odot \\
$$

## XeLaTeX示例

```latex
\documentclass{ctexart}
\usepackage{amsmath}

\author{作者姓名}
\title{标题}

\begin{document}
\maketitle  %显示标题

\section{第一章}
  内容
\subsection{第一章第一节}
  内容
\subsubsection{第一章第一节第一小节}
  内容
\paragraph{段落一：}
  内容
\subparagraph{段落一的子段落：}
  内容
\paragraph{段落二：}
  内容

\section{第二章}
  相对论公式
  \[e = m{c^2}\]

公式标号一
\begin{align}
  \frac{\partial F}{x} = -\frac{2}{9}x \\
  \frac{\partial F}{y} = -\frac{1}{2}y \\
  \frac{\partial F}{z} = 1
\end{align}

公式标号二
\begin{equation}
\begin{aligned}
  \frac{\partial F}{x} = -\frac{2}{9}x \\
  \frac{\partial F}{y} = -\frac{1}{2}y \\
  \frac{\partial F}{z} = 1
\end{aligned}
\end{equation}

\end{document}
```
