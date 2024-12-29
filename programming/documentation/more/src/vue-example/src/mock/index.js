import Mock from 'mockjs';
import config from '@/request/config';

const moduleFiles = import.meta.glob('./modules/*.js', { eager: true });
const modules = {};
for (const path in moduleFiles) {
  let name = path.replace(/\.\/\modules\/|\.js/g, '');
  name = name
    .substring(0, name.length - 3)
    .replace(/-(\w)/g, (L) => L.toUpperCase())
    .replace(/-/g, '');
  modules[name] = moduleFiles[path];
}
const { baseURL } = config;

const openMock = true;
mockAll(modules, openMock);

function mockAll(modules, isOpen = true) {
  for (const key in modules) {
    mock(modules[key], isOpen);
  }
}

function mock(module, isOpen = true) {
  if (isOpen) {
    for (let key in module) {
      ((response) => {
        if (response.isOpen !== false) {
          let url = baseURL;
          if (!url.endsWith('/')) {
            url += '/';
          }
          url = url + response.url;
          Mock.mock(new RegExp(url), response.method, (options) => {
            options.data = options.body ? JSON.parse(options.body) : null;
            const responseData = Mock.mock(
              typeof response.response === 'function'
                ? response.response(options)
                : response.response
            );
            console.log('%cmock拦截，请求：', 'color:blue', options);
            console.log('%cmock拦截，响应：', 'color:green', responseData);
            return responseData;
          });
        }
      })(module[key]() || {});
    }
  }
}
