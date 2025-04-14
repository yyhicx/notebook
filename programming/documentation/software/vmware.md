# VMware

## Windows

安装VM-Tools实现文件共享：

```bash
apt install open-vm-tools-desktop

reboot  # 重启系统
```

使用VSCode的SSH插件，连接虚拟机中Debian系统，实现远程访问文件夹：

*   Debian系统安装并使用ssh服务：

```bash
apt install ssh
apt install openssh-server

systemctl start ssh  
service status ssh  # 查看 ssh 服务状态

systemctl enable ssh  # 设置 ssh 服务开机自启

ifconfig  # 查看 IP 地址
```

*   配置`/etc/ssh/sshd_config`：

```txt
PermitRootLogin yes  # 允许 root 用户直接使用密码连接，这样方便但是不安全，不推荐
```

*   VSCode安装`Remote SSH`插件并连接虚拟机。

使用unlock解锁VMware中MacOS系统，并修改MacOS.vmx文件以运行MacOS（MacOS系统镜像、VMware、unlock、配置都有可能导致虚拟机无法正常运行，请谨慎操作）：

```txt
smc.version = "0"
cpuid.0.eax = "0000:0000:0000:0000:0000:0000:0000:1011"
cpuid.0.ebx = "0111:0101:0110:1110:0110:0101:0100:0111"
cpuid.0.ecx = "0110:1100:0110:0101:0111:0100:0110:1110"
cpuid.0.edx = "0100:1001:0110:0101:0110:1110:0110:1001"
cpuid.1.eax = "0000:0000:0000:0001:0000:0110:0111:0001"
cpuid.1.ebx = "0000:0010:0000:0001:0000:1000:0000:0000"
cpuid.1.ecx = "1000:0010:1001:1000:0010:0010:0000:0011"
cpuid.1.edx = "0000:0111:1000:1011:1111:1011:1111:1111"
smbios.reflectHost = "TRUE"
hw.model = "iMac19,1"
board-id = "Mac-AA95B1DDAB278B95"
```
