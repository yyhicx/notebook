# Apache

## Linux

Install:

*   apt install apache2
*   apt install apache2-dev

Enable: systemctl enable apache2

Disable: systemctl disable apache2

Start: systemctl start apache2  or /etc/init.d/apache2 start

Restart: systemctl restart apache2 or /etc/init.d/apache2 restart

Stop: systemctl stop apache2 or /etc/init.d/apache2 stop

Reload: systemctl reload apache2

Status: systemctl status apache2

Error: tail /var/log/apache2/error.log
