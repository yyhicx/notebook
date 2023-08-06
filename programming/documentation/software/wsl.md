# WSL

## Windows

修改安装目录：

*   wsl -l -v
*   wsl --export Ubuntu d:\ubuntu.tar
*   wsl --unregister Ubuntu
*   wsl --import Ubuntu d:\Ubuntu d:\ubuntu.tar --version 2
*   ubuntu config --default-user username
*   del d:\ubuntu.tar

WSL占用过多内存：

*   restart WSL
    *   wsl -l -v
    *   wsl --shutdown
*   copy src/.wslconfig to C:\Users\username\.wslconfig
*   echo 3 > /proc/sys/vm/drop_caches
