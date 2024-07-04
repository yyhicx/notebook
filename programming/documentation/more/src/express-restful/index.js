let express = require('express');
let fs = require('fs');
let ejs = require('ejs');
let cors = require('cors');
let bodyParser = require('body-parser');

let app = express();

app.use(cors());
app.use(bodyParser.urlencoded({ extended: false }));

app.get('/list-users', function (req, res) {
  fs.readFile(
    __dirname + '/data/' + 'users.json',
    'utf-8',
    function (err, data) {
      let myData = JSON.parse(data);
      fs.readFile('views/list-users.ejs', 'utf-8', function (err, template) {
        res.write(ejs.render(template, { data: myData }));
        res.end();
      });
    }
  );
});

app.get('/add-user', function (req, res) {
  fs.readFile('views/add-user.ejs', 'utf-8', function (err, data) {
    res.write(ejs.render(data));
    res.end();
  });
});

app.get('/delete-user', function (req, res) {
  fs.readFile(__dirname + '/data/' + 'users.json', function (err, data) {
    let myData = JSON.parse(data);
    fs.readFile('views/delete-user.ejs', 'utf-8', function (err, template) {
      res.write(ejs.render(template, { data: myData }));
      res.end();
    });
  });
});

app.post('/process-post', function (req, res) {
  fs.readFile(__dirname + '/data/' + 'users.json', function (err, data) {
    let oldData = JSON.parse(data);
    let response = {
      name: req.body.name,
      password: req.body.password,
      profession: req.body.profession,
      id: oldData.length + 1,
    };
    oldData.push(response);
    fs.writeFile(
      __dirname + '/data/' + 'users.json',
      JSON.stringify(oldData),
      function (err) {
        fs.readFile('views/base.ejs', 'utf-8', function (err, template) {
          res.write(ejs.render(template, { content: '数据添加完成' }));
          res.end();
        });
      }
    );
  });
});

app.post('/process-delete', function (req, res) {
  let i = req.body.id;
  fs.readFile(__dirname + '/data/' + 'users.json', function (err, data) {
    let oldData = JSON.parse(data);
    oldData.splice(i - 1, 1);
    for (let j = 0; j < oldData.length; j++) {
      oldData[j].id = j + 1;
    }
    fs.writeFile(
      __dirname + '/data/' + 'users.json',
      JSON.stringify(oldData),
      function (err) {
        fs.readFile('views/base.ejs', 'utf-8', function (err, template) {
          res.write(ejs.render(template, { content: '数据删除完成' }));
          res.end();
        });
      }
    );
  });
});

app.get('/:id', function (req, res) {
  fs.readFile(
    __dirname + '/data/' + 'users.json',
    'utf8',
    function (err, data) {
      let i = req.params.id;
      let myData = JSON.parse(data);
      if (i > 0 && i <= myData.length) {
        fs.readFile('views/list-users.ejs', 'utf-8', function (err, template) {
          res.write(ejs.render(template, { data: myData.slice(i - 1, i) }));
          res.end();
        });
      } else {
        fs.readFile('views/base.ejs', 'utf-8', function (err, template) {
          res.write(ejs.render(template, { content: '没有符合的用户' }));
          res.end();
        });
      }
    }
  );
});

let server = app.listen(8081, function () {
  console.log('程序已经运行');
});
