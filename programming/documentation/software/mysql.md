# MySQL

## Linux

Install:

*   wget <https://downloads.mysql.com/archives/get/p/23/file/mysql-server_8.0.21-1debian10_amd64.deb-bundle.tar> (for debian10)
*   tar -xvf mysql-server_8.0.21-1debian10_amd64.deb-budle.tar
*   mkdir mysql-8.0.21
*   mv *.deb mysql-8.0.21
*   cd mysql-8.0.21
*   dpkg -i package_name

```txt
for mysql-server_8.0.21-1debian10_amd64.deb
    for mysql-community-server_8.0.21-1debian10_amd64.deb
    for mysql-client_8.0.21-1debian10_amd64.deb
        for mysql-community-client_8.0.21-1debian10_amd64.deb
        for mysql-common_8.0.21-1debian10_amd64.deb
            for mysql-community-client-core_8.0.21-1debian10_amd64.deb
    for mysql-community-server-core_8.0.21-1debian10_amd64.deb
        for libmecab2(apt install)
        for libnuma1(apt install)
        apt --fix-broken install(in fact, only need)
    for psmisc(apt install)
```

Usage Method:

*   src/mysql_command.txt
