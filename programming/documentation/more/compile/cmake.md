# CMake

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
