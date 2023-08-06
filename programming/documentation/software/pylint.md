# pylint

## Windows

Install:

*   pip3 install pylint
*   copy src/.pylintrc to project/.pylintrc

Run:

```bash
pylint file
pylint document
# 指定 rc 文件
pylint --rcfile=.pylintrc document
# 生成报告
pylint -ry document
```
