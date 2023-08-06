# 自我风格指南

代码风格使用Google Style Guide标准。

推荐代码注释使用英文。

文档段落中推荐使用`backquote`、<code>code</code>、<kbd>kbd</kbd>和<font color="#f07d71">font</font>等标记描述内容。

文档内中英文混写（或中文数字混写）不用空格隔开，代码注释中中英文混写（或中文数字混写）推荐空格隔开。

代码注释中根据情况给行尾添加或不添加句号。

Markdown风格：

*   有序列表使用“懒人编号法”，除了“contents”段落（使用1、2、3等这种编号方法）。
*   列表与代码块或者表格推荐使用空行分隔：

    ```markdown
    *   content

    | Parameter | Description |
    | --------- | ----------- |
    |           |             |
    ```

注释风格

*   c++：

    ```c++
    // annotation

    /* annotation */

    /**
    * annotation
    */
    ```

*   python：

    ```python
    # annotation

    """annotation"""
    ```

*   html：

    ```html
    <!-- annotation -->

    <!-- 
      annotation
    -->
    ```
