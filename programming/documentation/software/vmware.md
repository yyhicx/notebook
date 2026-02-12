# VMware

## Windows

Windows环境下安装Linux虚拟机的常规配置：

*   内存4GB；处理器数量1个，4内核；硬盘60GB。
*   网络适配器使用桥接模式并复制物理网络连接状态。在桥接模式的基础上，设置ens33网卡的IP地址为静态IP：

    ```conf
    # /etc/network/interfaces
    source /etc/network/interfaces.d/*

    auto lo
    iface lo inet loopback

    auto ens33
    iface ens33 inet static
    address 192.168.0.201
    netmask 255.255.255.0
    gateway 192.168.0.1
    ```
