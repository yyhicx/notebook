# Debian 10 Buster

1.  [Create Folders](#create-folders)
2.  [Install Software](#install-software)

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
wget -N https://gitlab.com/fscarmen/warp/-/raw/main/menu.sh && bash menu.sh
```

SSH:

*   如果系统运行在虚拟机中，通过`ip addr`查看IP地址然后`ping`IP地址。如果可以ping通，则说明虚拟机网络配置正确；如果不能ping通，则调整虚拟机网络模式为`桥接模式`（Vmware）并`复制物理网络连接状态`，然后重启虚拟机。
*   apt install openssh-server
*   systemctl start sshd
*   编辑`/etc/ssh/sshd_config`：

    ```bash
    # 启用端口
    Port 22
    # 允许密码登录
    PasswordAuthentication yes
    # 允许 root 登录
    PermitRootLogin yes
    ```

*   重启 sshd 服务：`systemctl restart sshd`。
