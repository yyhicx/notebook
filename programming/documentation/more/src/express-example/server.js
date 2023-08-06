let express = require('express');
let fs = require('fs');
let util = require('util');
let cors = require('cors');  // 跨域
let ejs = require('ejs');    // 视图
let bodyParser = require('body-parser');  // 用于处理 JSON、Raw、Text 和 URL 编码的数据
let multer = require('multer');  // 用于处理 enctype="multipart/form-data"（设置表单的 MIME 编码）的表单数据
let cookieParser = require('cookie-parser');  // 这就是一个解析 Cookie 的工具

let app = express();

app.use(cors());
app.use("/public", express.static('public'));  // 使用静态文件
app.use(bodyParser.urlencoded({ extended: false }));  // 创建 application/x-www-form-urlencoded 编码解析
app.use(multer({ dest: '/tmp/'}).array('image'));
app.use(cookieParser());

app.get('/', function (req, res) {
  console.log('主页 Get 请求');
  console.log('Cookies: ' + util.inspect(req.cookies));
  fs.readFile('views/index.ejs', 'utf-8', function (e, data) {
		res.write(ejs.render(data,  { title: 'Hello Get', content: '' }));
		res.end();
  });
});

app.post('/', function (req, res) {
  console.log('主页 POST 请求');
  fs.readFile('views/index.ejs', 'utf-8', function (e, data) {
		res.write(ejs.render(data,  { title: 'Hello Post', content: '' }));
		res.end();
  });
});

app.get('/del-user', function (req, res) {
  console.log('/del-user 响应 DELETE 请求');
  fs.readFile('views/index.ejs', 'utf-8', function (e, data) {
		res.write(ejs.render(data,  { title: '删除页面', content: '' }));
		res.end();
  });
});

app.get('/list-user', function (req, res) {
  console.log('/list-user GET 请求');
  fs.readFile('views/index.ejs', 'utf-8', function (e, data) {
		res.write(ejs.render(data,  { title: '用户列表页面', content: '' }));
		res.end();
  });
});

// 对页面 abcd，abxcd，ab123cd 等响应 GET 请求
app.get('/ab*cd', function (req, res) {
  console.log('/ab*cd GET 请求');
  fs.readFile('views/index.ejs', 'utf-8', function (e, data) {
		res.write(ejs.render(data,  { title: '正则匹配', content: '' }));
		res.end();
  });
});

// get example
app.get('/get-example', function (req, res) {
  console.log('/get-example GET 请求');
  fs.readFile('views/get-example.ejs', 'utf-8', function (e, data) {
    res.write(ejs.render(data));
    res.end();
  });
});

app.get('/process-get', function (req, res) {
  console.log(req.url);
  // 输出 JSON 格式
  let response = {
    "first_name": req.query.fname,
    "last_name": req.query.lname
  };
  console.log(response);
  res.end(JSON.stringify(response));
})

// post example
app.get('/post-example', function (req, res) {
  console.log('/post-example POST 请求');
  fs.readFile('views/post-example.ejs', 'utf-8', function (e, data) {
    res.write(ejs.render(data));
    res.end();
  });
});

app.post('/process-post', function (req, res) {
  // 输出 JSON 格式
  let response = {
    "first_name": req.body.fname,
    "last_name": req.body.lname
  };
  console.log(response);
  res.end(JSON.stringify(response));
});

// file upload
app.get('/upload-example', function (req, res) {
  console.log('/upload-example GET 请求');
  fs.readFile('views/upload-example.ejs', 'utf-8', function (e, data) {
    res.write(ejs.render(data));
    res.end();
  });
});

app.post('/file-upload', function (req, res) {
  console.log(req.files[0]);  // 上传的文件信息

  let des_file = __dirname + "/tmp/" + req.files[0].originalname;
  fs.readFile(req.files[0].path, function (err, data) {
    fs.writeFile(des_file, data, function (err) {
      if (err) {
        console.log(err);
      } else {
        response = {
          message: 'File uploaded successfully',
          filename: req.files[0].originalname
        };
      }
      console.log(response);
      res.end(JSON.stringify(response));
    });
  });
});

let server = app.listen(8081, function () {
  console.log('程序已经运行');
});
