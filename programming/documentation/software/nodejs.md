# Nodejs

## Linux

Install:

*   curl -sL <https://deb.nodesource.com/setup_14.x> | bash -
*   apt install nodejs

Update:

*   open website: <https://github.com/nvm-sh/nvm#installing-and-updating>
*   curl -o- <https://raw.githubusercontent.com/nvm-sh/nvm/v0.38.0/install.sh> | bash
*   source ~/.bashrc(if sources lines are added to .bashrc profile file)
*   nvm ls-remote(view node version)
*   copy to ~/.bashrc file
    *   NVM_NODEJS_ORG_MIRROR=<https://npm.taobao.org/dist>
    *   source ~/.bashrc
*   nvm install v14.17.3
*   update npm
    *   npm install npm@latest -g
*   npm mirrors
    *   copy src/.npmrc to ~/.npmrc
