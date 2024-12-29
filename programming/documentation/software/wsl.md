# WSL

## Windows

使用WSL前的设置：

*   打开控制面板，点击“程序和功能”选项，选择“启用或关闭Windows功能”
*   勾选“适用于Linux的Windows子系统”和“虚拟机平台”功能
*   重启电脑

设置默认版本为WSL2：wsl --set-default-version 2

安装Debian：

*   wsl --list --online
*   wsl --install -d Debian

查看已安装的发行版的列表：wsl -l -v

启动已安装的发行版：wsl -d Debian

修改安装目录：

*   wsl -l -v
*   wsl --export Debian d:\debian.tar
*   wsl --unregister Debian
*   wsl --import Debian d:\Debian d:\debian.tar --version 2
*   debian config --default-user username
*   del d:\debian.tar

关于WSL占用过多内存的问题：

*   restart WSL
    *   wsl -l -v
    *   wsl --shutdown
*   copy src/.wslconfig to C:\Users\username\.wslconfig
*   open debian and run command:
    *   echo 3 > /proc/sys/vm/drop_caches
