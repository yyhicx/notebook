# Debian 10 Buster

1.  [Create Folders](#create-folders)
2.  [Install Software](#install-software)
3.  [Configuration Of Apache Server](#configuration-of-apache-server)
4.  [Configuration Of Nginx Server](#configuration-of-nginx-server)

## Create Folders

Folders:

*   /workspace
*   /site
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

Apache:

*   apt install apache2
*   apt install apache2-dev
*   mod_wsgi: apt install libapache2-mod-wsgi-py3

Nginx: apt install nginx

MySQL

Git: apt install git

NodeJS And NPM

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

  root /site/frontend/build
}
```
