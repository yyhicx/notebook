# clang-format

## Linux

Install: apt install clang-format

## Macos

Install: brew install clang-format

## Windows

Install:

*   open <https://releases.llvm.org/download.html> and download
*   copy `LLVM\bin` to `environment variables/Path`

Run:

```bash
# 使用内置 style，比如 llvm、Google 等
clang-format -style=google -i foo.c bar.c
# 使用自定义 style 文件 .clang-format，程序会先查找当前目录是否有 .clang-format，然后递归查找父目录
clang-format -style=file -i foo.c bar.c
# 导出一份 style 模板
clang-format -style=google -dump-config > .clang-format
```
