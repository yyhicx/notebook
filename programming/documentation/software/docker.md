# Docker

## Linux

Install: open `https://docs.docker.com` and follow the instructions

Start: systemctl start docker

Stop: systemctl stop docker

Enable: systemctl enable docker

Disable: systemctl disable docker

View images: docker images

Remove image: docker rmi image_id

View containers: docker ps -a

Remove container: docker rm container_id

Start container: docker start container_id

Stop container: docker stop container_id

Open container bash: docker exec -it container_id /bin/bash

Mirrors:

*   vim /etc/docker/daemon.json

```json
{
  "registry-mirrors": [
    "https://docker.1ms.run",
    "https://docker.1panel.live"
  ]
}
```

*   systemctl daemon-reload && systemctl restart docker

Install mysql: docker pull mysql:5.7

Run mysql:

```bash
docker run -p 3306:3306 --name mysql \
-v /dockerdata/mysql/log:/var/log/mysql \
-v /dockerdata/mysql/data:/var/lib/mysql \
-v /dockerdata/mysql/conf:/etc/mysql/conf.d \
-e MYSQL_ROOT_PASSWORD=123456 \
-d mysql:5.7
```

Set mysql self-starting: docker update mysql --restart=always

Config mysql:

*   open `/dockerdata/mysql/conf/my.cnf` and add the following content:

    ```txt
    [client]
    default-character-set=utf8
    
    [mysql]
    default-character-set=utf8
    
    [mysqld]
    init_connect='SET NAMES utf8'
    character-set-server=utf8
    collation-server=utf8_unicode_ci
    skip-character-set-client-handshake
    skip-name-resolve
    ```

*   docker restart mysql

Install redis: docker pull redis(The new version supports persistence by default)

Run redis:

```bash
docker run -p 6379:6379 --name redis \
-v /dockerdata/redis/data:/data \
-v /dockerdata/redis/conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf
```

Set redis self-starting: docker update redis --restart=always

Run redis-cli: docker exec -it redis redis-cli

Config redis password:

*   open redis-cli and run `CONFIG set requirepass "123456"`, then restart redis container
*   After that, you need to use `AUTH 123456` to authenticate before using redis

## Windows

安装Docker：

1.  先创建`D:\Software\Docker`目录。
2.  使用管理员身份打开cmd窗口：

    ```bash
    mklink /j "C:\Program Files\Docker" "D:\Software\Docker"
    ```

3.  使用`Docker Desktop Installer`安装程序。

添加镜像加速：

1.  登录阿里云复制镜像加速地址：

    ```bash
    https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors
    ```

2.  打开Docker的Settings，并在Docker Engine中配置如下内容：

    ```javascript
    {
      // Other configuration information
      "registry-mirrors": [
        "https://xxxxxxxx.mirror.aliyuncs.com"  // mirrors
      ]
    }
    ```
