# JetBrains

## Windows

Shortcut keys:

*   Format code: ctrl + alt + l
*   Move line: alt + shift + up/down
*   Duplicate line: ctrl + d
*   Copy current line: ctrl + c
*   Paste content: ctrl + v
*   Delete current line: ctrl + y
*   Undo content: ctrl + z
*   Find text: ctrl + f
*   Replace text: ctrl + r
*   Go to Declaration: ctrl + b

Extensions:

*   CMD Support
*   Fitten Code
*   JBLJavaToWeb
*   Lombok Builder Helper
*   MybatisX

在IntelliJ IDEA中修改Maven的数据源：

1.  打开`IntelliJ IDEA\plugins\maven\lib\maven3\conf\settings.xml`文件。
2.  修改`<mirrors>`标签下内容：

    ```xml
    <mirrors>
      <!-- mirror
        | Specifies a repository mirror site to use instead of a given repository. The repository that
        | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used
        | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.
        |
      <mirror>
        <id>mirrorId</id>
        <mirrorOf>repositoryId</mirrorOf>
        <name>Human Readable Name for this Mirror.</name>
        <url>http://my.repository.com/repo/path</url>
      </mirror>
        -->
      <!--
      <mirror>
        <id>maven-default-http-blocker</id>
        <mirrorOf>external:http:*</mirrorOf>
        <name>Pseudo repository to mirror external repositories initially using HTTP.</name>
        <url>http://0.0.0.0/</url>
        <blocked>true</blocked>
      </mirror>
      -->
      <mirror>
        <id>ali-maven</id>
        <mirrorOf>central</mirrorOf>
        <name>aliyun maven</name>
        <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
      </mirror>
    </mirrors>
    ```
