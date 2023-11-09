# Docker入门

## 配置

Windows下Docker安装：

1.  先创建`D:\Software\Docker`目录。
2.  使用管理员身份打开cmd窗口：

    ```bash
    mklink /j "C:\Program Files\Docker" "D:\Software\Docker"
    ```

3.  使用`Docker Desktop Installer`安装程序。

Windows下Docker镜像加速：

1.  登录阿里云复制镜像加速地址：

    ```bash
    https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors
    ```

2.  打开Docker的Settings，并在Docker Engine中配置如下内容：

    ```js
    {
      // Other configuration information
      "registry-mirrors": [
        "https://xxxxxxxx.mirror.aliyuncs.com" // mirrors
      ]
    }
    ```
