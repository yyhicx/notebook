# cpplint

## Windows

Install: pip3 install cpplint

Run:

```bash
cpplint file
cpplint --recursive document
# 头文件位于同一文件夹中，排除 build 目录，递归当前目录
cpplint --filter=-build/include_subdir --exclude=./build --recursive .
```
