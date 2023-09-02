# Debian 10 Buster

1.  [Create Folders](#create-folders)
2.  [Install Software](#install-software)
3.  [Configuration Of Apache Server](#configuration-of-apache-server)
4.  [Configuration Of Nginx Server](#configuration-of-nginx-server)

## Create Folders

Folders:

*   /workspace
*   /frontend
*   /backend
*   /download

## Install Software

Debian Apt Update:

*   copy src/sources.list to /etc/apt/sources.list
*   apt update
*   apt upgrade

GCC/G++: apt install build-essential

Vim:

*   apt install vim
*   copy src/.vimrc to ~/.vimrc

Tree: apt install tree

Python3:

*   debian10 has python3.7.3 installed
*   apt install python3-dev
*   pip

NodeJS And NPM

Apache:

*   apt install apache2
*   apt install apache2-dev
*   mod_wsgi: apt install libapache2-mod-wsgi-py3

Nginx: apt install nginx

MySQL

Git: apt install git

V2ray:

*   配置除config.json外所有的文件：

    ```bash
    # 下载，解压，打开文件夹
    wget https://github.com/v2fly/v2ray-core/releases/download/v4.31.0/v2ray-linux-64.zip

    unzip v2ray-linux-64.zip -d v2ray-linux-64

    cd v2ray-linux-64

    # 添加权限
    chmod 755 v2ray
    chmod 755 v2ctl
    chmod 755 systemd/system/v2ray.service
    chmod 755 systemd/system/v2ray@.service

    # 复制文件
    cp v2ray /usr/local/bin/
    cp v2ctl /usr/local/bin/
    cp systemd/system/v2ray.service /etc/systemd/system/
    cp systemd/system/v2ray@.service /etc/systemd/system/

    mkdir /usr/local/share/v2ray/
    cp geoip.dat /usr/local/share/v2ray/
    cp geosite.dat /usr/local/share/v2ray/
    ```

*   配置config.json文件：
    *   从Windows下的v2rayN客户端下找到需要的服务器，`右键`->`导出所选服务器为客户端配置`，保存为config.json。
    *   将编写好的文件写入：

        ```bash
        mkdir /usr/local/etc/v2ray/
        vim /usr/local/etc/v2ray/config.json
        ```

*   启动：

```bash
# 启动 v2ray
sudo systemctl start v2ray

# 检查 v2ray 状态
sudo systemctl status v2ray

# 设置 v2ray 开机自启动
sudo systemctl enable v2ray
```

Vmess:

```bash
# 登录服务器
ssh root@ip_address

# 安装 x-ui，并且设置用户名、密码和端口（端口号建议 5000）
bash <(curl -Ls https://raw.githubusercontent.com/vaxilu/x-ui/master/install.sh)

# 防火墙允许 5000 端口被访问
ufw allow 5000

# 使用 warp，为 IPv4 only 添加 WARP 双栈网络接口
wget -N https://raw.githubusercontent.com/fscarmen/warp/main/menu.sh && bash menu.sh
```

## Configuration Of Apache Server

`/etc/apache2/apache2.conf`

```txt
ServerRoot "/etc/apache2"
ServerName localhost:8000

<Directory />
    Options FollowSymLinks
    AllowOverride None
    Require all denied
</Directory>

<Directory /usr/share>
    AllowOverride None
    Require all granted
</Directory>

<Directory /var/www/>
    Options Indexes FollowSymLinks
    AllowOverride None
    Require all granted
</Directory>

<Directory /var/www/html>
    Options Indexes FollowSymLinks
    AllowOverride None
    Require all granted
</Directory>
```

`/etc/apache2/mods-available/mod_wsgi.load`

```txt
LoadModule wsgi_module /usr/lib/apache2/modules/mod_wsgi.so
```

`/etc/apache2/ports.conf`

```txt
Listen 8000
```

`/etc/apache2/sites-available/000-default.conf`

```txt
<VirtualHost *:8000>
    ServerAdmin webmaster@localhost
    DocumentRoot /site/backend
    # ServerName localhost:8000
    ServerAlias *.chnxish.com

    Alias /static /site/backend/static
    <Directory /site/backend/static>
        Require all granted
    </Directory>

    Alias /media /site/backend/media
    <Directory /site/backend/media>
        Require all granted
    </Directory>

    <Directory /site/backend/backend>
        <Files wsgi.py>
            Require all granted
        </Files>
    </Directory>

    <Directory /site/backend>
        Options -Indexes +FollowSymLinks
        AllowOverride All
        Require all granted
    </Directory>

    # WSGIDaemonProcess backend python-home=/root/virtualenvs/website python-path=/site/backend
    # WSGIProcessGroup backend 
    WSGIScriptAlias / /site/backend/backend/wsgi.py

    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>
```

## Configuration Of Nginx Server

`/etc/nginx/sites-enabled/default`

```txt
server {
  listen 80 default_server;
  listen [::]:80 default_server;

  root /var/www/html;
}
```
