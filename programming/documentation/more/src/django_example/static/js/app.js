// 对于任何第三方依赖项，如 jQuery，将它们放入 lib 文件夹中。
requirejs.config({
  baseUrl: 'lib',
  paths: {
    app: '../app',
  },
});

// 开始加载主应用程序文件。
requirejs(['/static/js/app/main.js']);
