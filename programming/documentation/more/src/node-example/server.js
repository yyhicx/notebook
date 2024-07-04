let http = require('http');
let { URL } = require('url');

function start(route) {
  function onRequest(request, response) {
    let baseURL = 'http://' + request.headers.host + '/';
    let myURL = new URL(request.url, baseURL);
    let pathname = myURL.pathname;

    console.log('Request for ' + pathname + ' received.');

    route(pathname);

    pathname = pathname.slice(1); // 删除开头的 '/'

    if (pathname[0] !== undefined) {
      pathname = pathname.replace(pathname[0], pathname[0].toUpperCase()); // 首字母大写
    }

    response.writeHead(200, { 'Content-Type': 'text/plain' });
    response.write('Hello ' + pathname);
    response.end();
  }

  http.createServer(onRequest).listen(8888);
  console.log('Server has started.');
}

exports.start = start;
