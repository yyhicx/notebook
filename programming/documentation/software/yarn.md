# yarn

## Linux

Install: npm install yarn --global

Mirrors:

```bash
# query
yarn config get registry
# replace
yarn config set registry https://registry.npmmirror.com
# restore
yarn config set registry https://registry.yarnpkg.com
# delete
yarn config delete registry
```

Init project: yarn init

Install package:

*   default: yarn add package_name
*   development dependencies: yarn add package_name --save-dev
*   production dependencies: yarn add package_name --save
*   global: yarn global add package_name
*   all: yarn install

Uninstall package:

*   default: yarn remove package_name
*   development dependencies: yarn remove package_name --save-dev
*   production dependencies: yarn remove package_name --save
*   global: npm global remove package_name

Script:

*   add `configuration` to project_name/package.json

    ```json
    // configuration
    "scripts": {
      "start": "node app.js"
    }
    ```

*   then you can start the program by running `yarn run start`
