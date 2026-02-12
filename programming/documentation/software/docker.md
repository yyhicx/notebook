# Docker

## Linux

Install: open `https://docs.docker.com` and follow the instructions

Start: systemctl start docker

Stop: systemctl stop docker

Enable: systemctl enable docker

Disable: systemctl disable docker

Check which version of the image can be downloaded: docker search image_name

View local images: docker images

Remove image: docker rmi image_id

View containers: docker ps -a

Remove container: docker rm container_id

Start container: docker start container_id

Stop container: docker stop container_id

Open container bash: docker exec -it container_id /bin/bash

Print logs: docker logs container_id (--tail 100)

Mirrors:

*   vim /etc/docker/daemon.json

```json
{
  "registry-mirrors": [
    "https://docker.1ms.run",
    "https://docker.xuanyuan.me",
  ]
}
```

*   systemctl daemon-reload && systemctl restart docker

Install mysql:

```bash
docker pull mysql:5.7
docker pull mysql:8.0.30
```

Create mysql data directory: mkdir -p /dockerdata/mysql/log /dockerdata/mysql/data /dockerdata/mysql/conf

Stop and remove mysql: docker stop mysql && docker rm mysql

Run mysql:

```bash
docker run -p 3306:3306 --name mysql \
-v /dockerdata/mysql/log:/var/log/mysql \
-v /dockerdata/mysql/data:/var/lib/mysql \
-v /dockerdata/mysql/conf:/etc/mysql/conf.d \
--restart=always --privileged=true \
-e MYSQL_ROOT_PASSWORD=123456 \
-d mysql:5.7

docker run -p 3306:3306 --name mysql \
-v /dockerdata/mysql/log:/var/log/mysql \
-v /dockerdata/mysql/data:/var/lib/mysql \
-v /dockerdata/mysql/conf:/etc/mysql/conf.d \
--restart=always --privileged=true \
-e MYSQL_ROOT_PASSWORD=123456 \
-d mysql:8.0.30
```

Set mysql self-starting: docker update mysql --restart=always

Set mysql privileged: docker update mysql --privileged=true

Config mysql:

*   open `/dockerdata/mysql/conf/my.cnf` and add the following content:

    ```txt
    [client]
    default-character-set=utf8(utf8mb4)
    
    [mysql]
    default-character-set=utf8(utf8mb4)
    
    [mysqld]
    init_connect='SET NAMES utf8'
    character-set-server=utf8(utf8mb4)
    collation-server=utf8_unicode_ci(utf8mb4_0900_ai_ci)
    skip-character-set-client-handshake
    skip-name-resolve
    ```

*   configure ssl certificate:
    *   check ssl status: use Navicat connect to mysql and run `show variables like '%ssl%'` to check
    *   open mysql: docker exec -it mysql_container_id /bin/bash
    *   generate ssl certificate: run `mysql_ssl_rsa_setup --datadir=/var/lib/mysql`
    *   exit mysql: run `exit`
    *   open `/dockerdata/mysql/conf/my.cnf` and add the following content:

        ```txt
        [mysqld]
        ssl-ca=/var/lib/mysql/ca.pem
        ssl-cert=/var/lib/mysql/server-cert.pem
        ssl-key=/var/lib/mysql/server-key.pem
        ```

    *   restart mysql: docker restart mysql_container_id
    *   check ssl status: use Navicat connect to mysql and run `show session status like 'Ssl_cipher'` to check
*   docker restart mysql

Install redis:

```bash
docker pull redis:6.2
docker pull redis:7.0.10
```

Create redis data directory: mkdir -p /dockerdata/redis/data /dockerdata/redis/conf

Stop and remove redis: docker stop redis && docker rm redis

Run redis(before run redis, please make sure configuration redis.conf is correct):

```bash
docker run -p 6379:6379 --name redis \
-v /dockerdata/redis/data:/data \
-v /dockerdata/redis/conf:/etc/redis/ \
--restart=always \
-d redis:6.2 redis-server /etc/redis/redis.conf

docker run -p 6379:6379 --name redis \
-v /dockerdata/redis/data:/data \
-v /dockerdata/redis/conf:/etc/redis/ \
--restart=always \
-d redis:7.0.10 redis-server /etc/redis/redis.conf
```

Set redis self-starting: docker update redis --restart=always

Config redis:

*   open `/dockerdata/redis/conf/redis.conf` and add the following content:

    ```txt
    # 设置端口
    port 6379
    # 开启持久化
    appendonly yes
    bind 0.0.0.0
    requirepass 123456

    # 生成环境推荐
    # 只允许本地连接，或指定具体 IP
    # bind 127.0.0.1
    # 设置访问密码
    # requirepass 123456
    # 启用保护模式
    # protected-mode yes
    ```

*   docker restart redis

Run redis-cli: docker exec -it redis redis-cli

Install nginx: docker pull nginx:1.24.0

Create nginx data directory: mkdir -p /dockerdata/nginx/conf /dockerdata/nginx/log /dockerdata/nginx/html /dockerdata/nginx/ssl

Stop and remove nginx: docker stop nginx && docker rm nginx

Run temp nginx:

```bash
docker run --name nginx_temp -d nginx:1.24.0

docker cp nginx_temp:/etc/nginx/. /dockerdata/nginx/conf/
docker cp nginx_temp:/usr/share/nginx/html/. /dockerdata/nginx/html/

docker stop nginx_temp && docker rm nginx_temp
```

Run nginx:

```bash
docker run -p 80:80 -p 443:443 --name nginx \
-v /dockerdata/nginx/html:/usr/share/nginx/html \
-v /dockerdata/nginx/log:/var/log/nginx \
-v /dockerdata/nginx/conf:/etc/nginx \
-v /dockerdata/nginx/ssl:/etc/nginx/ssl \
--restart=always \
-d nginx:1.24.0
```

Config nginx:

*   open `/dockerdata/nginx/conf/conf.d/default.conf` and add the following content:

    ```conf
    # 默认站点配置文件
    server {
      listen 80;
      listen [::]:80;
      server_name example.com;

      # 访问日志
      access_log /var/log/nginx/access.log main;
      error_log /var/log/nginx/error.log warn;

      # 根目录设置
      location / {
        root /usr/share/nginx/html;
        index index.html index.htm;
      }

      # 重定向服务器错误页面到静态页面 /50x.html
      error_page 500 502 503 504 /50x.html;
      location = /50x.html {
        root /usr/share/nginx/html;
      }

      # 健康检查
      location = /health {
        return 200 "OK\n";
        add_header Content-Type text/plain;
      }

      # 禁止访问隐藏文件
      location ~ /\. {
        deny all;
      }
    }
    ```

*   open `/dockerdata/nginx/conf/conf.d/ssl.conf` and add the following content:

    ```conf
    # 创建 HTTPS 配置文件
    server {
      listen 443 ssl http2;
      server_name example.com;

      # SSL 证书路径
      ssl_certificate /etc/nginx/ssl/example.com.crt;
      ssl_certificate_key /etc/nginx/ssl/example.com.key;

      # SSL 配置
      ssl_protocols TLSv1.2 TLSv1.3;
      ssl_ciphers ECDHE-RSA-AES256-GCM-SHA512:DHE-RSA-AES256-GCM-SHA512;
      ssl_prefer_server_ciphers on;
      ssl_session_cache shared:SSL:10m;
      ssl_session_timeout 10m;

      # 根目录设置
      location / {
        root /usr/share/nginx/html;
        index index.html index.htm;
      }
    }
    ```

*   open `/dockerdata/nginx/conf/nginx.conf` and add the following content:

    ```conf
    # 优化 nginx 配置
    user nginx;
    worker_processes auto;
    error_log /var/log/nginx/error.log warn;
    pid /var/run/nginx.pid;

    events {
      worker_connections 1024;
      use epoll;
      multi_accept on;
    }

    http {
      include /etc/nginx/mime.types;
      default_type application/octet-stream;

      # 日志格式
      log_format main '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '$http_user_agent "$http_x_forwarded_for"';

      access_log /var/log/nginx/access.log main;

      # 基础设置
      sendfile on;
      tcp_nopush on;
      tcp_nodelay on;
      keepalive_timeout 65;
      types_hash_max_size 2048;

      # Gzip 压缩
      gzip on;
      gzip_vary on;
      gzip_min_length 1024;
      gzip_types text/plain text/css application/json application/javascript application/x-javascript text/xml application/xml application/xml+rss text/javascript application/vnd.ms-fontobject application/x-font-ttf font/opentype image/svg+xml image/x-icon;

      # 包含其他配置文件
      include /etc/nginx/conf.d/*.conf;
    }
    ```

*   docker restart nginx

Install nacos: docker pull nacos/nacos-server:v2.2.2

Run nacos:

```bash
docker run -p 8848:8848 -p 9848:9848 --name nacos \
--restart=always \
-e MODE=standalone -d nacos/nacos-server:v2.2.2
```

Install minio: docker pull minio/minio:RELEASE.2023-06-29T05-12-28Z

Create nginx data directory: mkdir /dockerdata/minio/data

Run minio:

```bash
docker run -p 9000:9000 -p 9001:9001 --name minio \
-v /dockerdata/minio/data:/data \
-e "MINIO_ROOT_USER=minioadmin" \
-e "MINIO_ROOT_PASSWORD=12345678" \
--restart=always \
-d minio/minio:RELEASE.2023-06-29T05-12-28Z server /data --console-address ":9001"
```

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
