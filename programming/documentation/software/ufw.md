# UFW

## Linux

Install: apt install ufw

Restart: ufw reset

Enable: ufw enable

Disable: ufw disable

Status: ufw status verbose

Open Port:

*   ufw allow OpenSSH
*   ufw allow 80/tcp
*   ufw allow 443/tcp
*   uft allow 8000/tcp

Deny:

*   ufw deny from 23.24.25.0/24 to any port 80
*   ufw deny from 23.24.25.0/24 to any port 443
