# Next

## Next基础

要从头开始使用React构建一个完整的Web应用程序，需要考虑许多重要的细节：

*   必须使用打包程序（例如webpack）打包代码，并使用Babel等编译器进行代码转换。
*   你需要针对生产环境进行优化，例如代码拆分。
*   你可能需要对一些页面进行预先渲染以提高页面性能和SEO。你可能还希望使用服务器端渲染或客户端渲染。
*   你可能必须编写一些服务器端代码才能将React应用程序连接到数据存储。

Next.js是一个React开发框架，为上述问题提供了解决方案，它内置了许多功能：

*   直观的、基于页面的路由系统（并支持动态路由）。
*   预渲染，支持在页面级的静态生成（SSG）和服务器端渲染（SSR）。
*   自动代码拆分，提升页面加载速度。
*   具有经过优化的预取功能的客户端路由。
*   内置CSS和Sass的支持，并支持任何CSS-in-JS库。
*   开发环境支持快速刷新。
*   利用Serverless Functions及API路由构建API功能。
*   完全可扩展。

Install：

```bash
yarn create next-app
yarn create next-app --typescript
```

基本特性：

*   页面：
    *   在Next.js中，一个页面（page）就是一个从.js、jsx、.ts或.tsx文件导出（export）的React组件，这些文件存放在pages目录下。每个page（页面）都使用其文件名作为路由（route）。
    *   预渲染：
        *   默认情况下，Next.js将预渲染每个page。这意味着Next.js会预先为每个页面生成HTML文件，而不是由客户端JavaScript来完成。预渲染可以带来更好的性能和SEO效果。
        *   每个生成的HTML文件都与该页面所需的最少JavaScript代码相关联。当浏览器加载一个page时，其JavaScript代码将运行并使页面完全具有交互性（此过程称为水合（hydration））。
    *   两种形式的预渲染：
        *   静态生成（Static Generation）：推荐的用法，HTML会在构建时生成，并在每次页面请求（request）时重用。
            *   生成不带数据的静态页面（Next.js为每个页面生成一个HTML文件即可）：

                ```javascript
                function About() {
                  return <div>About</div>;
                }

                export default About;
                ```

            *   需要获取数据的静态生成：
                *   页面content（内容）取决于外部数据：使用`getStaticProps`。
                *   页面paths（路径）取决于外部数据：使用`getStaticPaths`（通常还要同时使用`getStaticProps`）。
        *   服务器渲染：在每次页面请求时重新生成HTML。

## API参考

## 部署

将项目源文件上传至云服务器并执行如下命令：

```bash
yarn install
yarn build
```

编辑`/etc/nginx/sites-available/default`文件：

```txt
server {
    listen 80 default_server;
    listen [::]:80 default_server;

    root /var/www/html;

    server_name domain_name;

    location / {
        proxy_pass http://localhost:3000;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;

        # First attempt to serve request as file, then
        # as directory, then fall back to displaying a 404.
        # try_files $uri $uri/ =404;
    }
}
```

重启nginx服务器：

```bash
sudo nginx -t # check syntax errors
sudo systemctl restart nginx
```

安装并使用pm2：

```bash
yarn global add pm2

pm2 start --name frontend_name yarn --watch -- start
```
