# npm

## Linux

Install: npm will be installed along with Node.js

Mirrors: copy src/.npmrc to ~/.npmrc

Init project: npm init

Install package:

*   default: npm install package_name
*   development dependencies: npm install package_name --save-dev
*   production dependencies: npm install package_name --save
*   global: npm install package_name --global
*   all: npm install

Uninstall package:

*   default: npm uninstall package_name
*   development dependencies: npm uninstall package_name --save-dev
*   production dependencies: npm uninstall package_name --save
*   global: npm uninstall package_name --global

Script:

*   add `configuration` to project_name/package.json

    ```json
    // configuration
    "scripts": {
      "start": "react-scripts start"
    }
    ```

*   then you can start the program by running `npm run start`
