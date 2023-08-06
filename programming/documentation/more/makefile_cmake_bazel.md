# Makefile CMake Bazel

## Makefile

[跟我一起写Makefile](https://seisman.github.io/how-to-write-makefile/overview.html)

概述：

*   一个工程中的源文件不计其数，其按类型、功能、模块分别放在若干个目录中，makefile定义了一系列的规则来指定哪些文件需要先编译，哪些文件需要后编译，哪些文件需要重新编译，甚至于进行更复杂的功能操作，因为makefile就像一个Shell脚本一样，也可以执行操作系统的命令。
*   makefile带来的好处就是——“自动化编译”，一旦写好，只需要一个make命令，整个工程完全自动编译，极大的提高了软件开发的效率。

关于程序的编译和链接：

*   将源文件翻译成中间目标文件的过程称为编译（Compile），然后将大量的目标文件合成可执行文件的过程称为链接（Link）。
*   目标文件在Windows下就是.obj文件，在UNIX下就是.o文件。
*   由于源文件太多，编译生成的中间目标文件太多，为此给中间目标文件进行打包。在Window下这种包叫做库文件（Library File），也就是.lib文件，在UNIX下是Archive File，也就是.a文件。
*   静态库和动态库：
    *   静态库（.lib，.a）是可以直接链接到链接器生成的最终可执行文件的库，它包含在其中，无需将库放入将部署可执行文件的系统中。
    *   共享库（.dll，.so）是链接但未嵌入最终可执行文件中的库，因此将在启动可执行文件时加载，并且需要存在于部署可执行文件的系统中。

makefile介绍：

*   make命令执行时，需要一个makefile文件，以告诉make命令需要怎么样的去编译和链接程序。
*   make是如何工作的：
    1.  make会在当前目录下找名字叫“Makefile”或“makefile”的文件。
    2.  如果找到，它会找文件中的第一个目标文件（target），在下面的示例中，他会找到“edit”这个文件，并把这个文件作为最终的目标文件。
    3.  如果edit文件不存在，或是edit所依赖的后面的.o文件的文件修改时间要比edit这个文件新，那么，他就会执行后面所定义的命令来生成edit这个文件。
    4.  如果edit所依赖的.o文件也不存在，那么make会在当前文件中找目标为.o文件的依赖性，如果找到则再根据那一个规则生成.o文件。
    5.  如果C文件和H文件是存在的，那么make会生成.o文件，然后再用.o文件生成make的终极任务，也就是执行文件edit了。
*   Makefile里主要包含了五个东西：显式规则、隐晦规则、变量定义、文件指示和注释：
    *   显式规则：说明了如何生成一个或多个目标文件。这是由Makefile的书写者明显指出要生成的文件、文件的依赖文件和生成的命令。
    *   隐晦规则：由于我们的make有自动推导的功能，所以隐晦的规则可以让我们比较简略地书写Makefile，这是由make所支持的。
    *   变量的定义：在Makefile中我们要定义一系列的变量，变量一般都是字符串，这个有点像你C语言中的宏，当Makefile被执行时，其中的变量都会被扩展到相应的引用位置上。
    *   文件指示：其包括了三个部分，一个是在一个Makefile中引用另一个Makefile，就像C语言中的include一样；另一个是指根据某些情况指定Makefile中的有效部分，就像C语言中的预编译#if一样；还有就是定义一个多行的命令。
    *   注释：Makefile中只有行注释，和UNIX的Shell脚本一样，其注释是用#字符，这个就像C/C++中的//一样。如果你要在你的Makefile中使用#字符，可以用反斜杠进行转义，如：`\#`。
*   使用include命令后，make会在当前目录下首先寻找，如果当前目录下没有找到，那么make还会在下面的几个目录下找：
    *   如果make执行时，有-I或--include-dir参数，那么make就会在这个参数所指定的目录下去寻找。
    *   如果目录`<prefix>/include`（一般是：/usr/local/bin或/usr/include）存在的话，make也会去找。
*   make的工作方式：
    1.  读入所有的Makefile。
    2.  读入被include的其它Makefile。
    3.  初始化文件中的变量。
    4.  推导隐晦规则，并分析所有规则。
    5.  为所有的目标文件创建依赖关系链。
    6.  根据依赖关系，决定哪些目标要重新生成。
    7.  执行生成命令。

```makefile
# makefile 的规则
# target：可以是一个 object file（目标文件），也可以是一个执行文件，还可以是一个标签（label）
# prerequisites：生成该 target 所依赖的文件
# command：该 target 要执行的命令（任意的 shell 命令）
target ... : prerequisites ...
    command
    ...
    ...
```

```makefile
# 示例
edit : main.o kbd.o command.o display.o \
        insert.o search.o files.o utils.o
    cc -o edit main.o kbd.o command.o display.o \
        insert.o search.o files.o utils.o
main.o : main.c defs.h
    cc -c main.c
kbd.o : kbd.c defs.h command.h
    cc -c kbd.c
command.o : command.c defs.h command.h
    cc -c command.c
display.o : display.c defs.h buffer.h
    cc -c display.c
insert.o : insert.c defs.h buffer.h
    cc -c insert.c
search.o : search.c defs.h buffer.h
    cc -c search.c
files.o : files.c defs.h buffer.h command.h
    cc -c files.c
utils.o : utils.c defs.h
    cc -c utils.c
# 无依赖项，clean 是一个编译无关的命令
clean :
    rm edit main.o kbd.o command.o display.o \
        insert.o search.o files.o utils.o
```

```makefile
# 改良版的示例
# 定义变量
objects = main.o kbd.o command.o display.o \
    insert.o search.o files.o utils.o
# 使用变量
edit : $(objects)
    cc -o edit $(objects)
main.o : main.c defs.h
    cc -c main.c
kbd.o : kbd.c defs.h command.h
    cc -c kbd.c
command.o : command.c defs.h command.h
    cc -c command.c
display.o : display.c defs.h buffer.h
    cc -c display.c
insert.o : insert.c defs.h buffer.h
    cc -c insert.c
search.o : search.c defs.h buffer.h
    cc -c search.c
files.o : files.c defs.h buffer.h command.h
    cc -c files.c
utils.o : utils.c defs.h
    cc -c utils.c
clean :
    rm edit $(objects)
```

```makefile
# 自动推导的示例
objects = main.o kbd.o command.o display.o \
    insert.o search.o files.o utils.o
edit : $(objects)
    cc -o edit $(objects)
main.o : defs.h
kbd.o : defs.h command.h
command.o : defs.h command.h
display.o : defs.h buffer.h
insert.o : defs.h buffer.h
search.o : defs.h buffer.h
files.o : defs.h buffer.h command.h
utils.o : defs.h
# 更稳健的做法
.PHONY : clean
clean :
    rm edit $(objects)
```

```makefile
# 引用其他的 Makefile
include <filename>
```

书写规则：

*   规则包含两个部分，一个是依赖关系，一个是生成目标的方法。
    通配符：*，?和~。
*   文件搜索：在一些大的工程中，有大量的源文件，我们通常的做法是把这许多的源文件分类，并存放在不同的目录中。所以，当make需要去找寻文件的依赖关系时，你可以在文件前加上路径，但最好的方法是把一个路径告诉make，让make在自动去找。
*   “伪目标”并不是一个文件，只是一个标签，由于“伪目标”不是文件，所以make无法生成它的依赖关系和决定它是否要执行。
*   Makefile的规则中的目标可以不止一个，其支持多目标。

```makefile
# 规则的语法
targets : prerequisites
    command
    ...
# 或是这样
# command 是命令行，如果其不与 "target:prerequisites" 在一行，那么必须以 Tab 键开头
targets : prerequisites ; command
    command
    ...
```

```makefile
# 使用 Makefile 文件中的特殊变量 VPATH
# 指定两个目录 "src" 和 "../headers" 用于文件搜索
VPATH = src:../headers
# 使用 Makefile 内的关键字 VPATH
# 为符合模式 <pattern> 的文件指定搜索目录 <directories>
vpath <pattern> <directories>
# 清除符合模式 <pattern> 的文件的搜索目录
vpath <pattern>
# 清除所有已被设置好了的文件搜索目录
vpath
# '%' 的意思是匹配零或若干字符
vpath %.c foo
vpath %   blish
vpath %.c bar
```

```makefile
# PHONY 用于指明 clean 是一个伪目标
.PHONY : clean
clean :
    rm *.o temp
# 伪目标可以作为“默认目标”，则一定会执行
# 不会生成 all 文件，但是 prog1、prog2 和 prog3 会生成
all : prog1 prog2 prog3
.PHONY : all
prog1 : prog1.o utils.o
    cc -o prog1 prog1.o utils.o
prog2 : prog2.o
    cc -o prog2 prog2.o
prog3 : prog3.o sort.o utils.o
    cc -o prog3 prog3.o sort.o utils.o
# 伪目标也可以有依赖关系
.PHONY : cleanall cleanobj cleandiff
cleanall : cleanobj cleandiff
    rm program
cleanobj :
    rm *.o
cleandiff :
    rm *.diff
```

```makefile
# 多目标
target1 target2 ... : prerequisites
    command
    ...
```

## CMake

[掌握CMake](https://cmake.org/cmake/help/book/mastering-cmake)

在Windows环境下指定MinGW为编译器，编译C项目：

1.  编写CMakeLists.txt和创建空的build文件夹
2.  打开build文件并运行“cmake -G "MinGW Makefiles" ..”命令
3.  生成Makefile文件后，执行mingw32-make命令

## Bazel

[Bazel学习笔记](https://blog.gmem.cc/bazel-study-note)

简介：

*   Bazel是Google开源的、类似于Make、Maven或Gradle的构建和测试工具。它使用可读性强的、高层次的构建语言，支持多种编程语言，以及为多种平台进行交叉编译。
*   Bazel的优势：
    *   高层次的构建语言：更加简单，Bazel抽象出库、二进制、脚本、数据集等概念，不需要编写调用编译器或链接器的脚本。
    *   快而可靠：能够缓存所有已经完成的工作步骤，并且跟踪文件内容、构建命令的变动情况，避免重复构建。此外Bazel还支持高度并行构建、增量构建。
    *   多平台支持：可以在Linux/macOS/Windows上运行，可以构建在桌面/服务器/移动设备上运行的应用程序。
    *   可扩容性：处理10万以上源码文件时仍然能保持速度。
    *   可扩展性：支持Android、C/C++、Java、Objective-C、Protocol Buffer、Python...还支持扩展以支持其它语言

概念和术语：

*   Workspace：工作空间是一个目录，它包含：构建目标所需要的源码文件，以及相应的BUILD文件；指向构建结果的符号链接；WORKSPACE文件，可以为空，可以包含对外部依赖的引用。
*   Package：
    *   包是工作空间中主要的代码组织单元，其中包含一系列相关的文件（主要是代码）以及描述这些文件之间关系的BUILD文件。
    *   包是工作空间的子目录，它的根目录必须包含文件BUILD.bazel或BUILD。除了那些具有BUILD文件的子目录（子包）以外，其它子目录属于包的一部分。
*   Target
