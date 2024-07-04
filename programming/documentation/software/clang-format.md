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
# Use built-in styles, such as llvm, Google, etc
clang-format -style=google -i foo.c bar.c
# Use a custom style file(.clang-format). The program will first look for a .clang-format file in the current directory, then recursively search parent directories
clang-format -style=file -i foo.c bar.c
# Export a style template
clang-format -style=google -dump-config > .clang-format
```
