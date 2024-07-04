# Chocolatey

## Windows

Install:

*   ensure that you are using an administrative shell
*   Paste the copied text into your shell and press Enter:

    ```bash
    Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
    ```

Run: choco install software_name
