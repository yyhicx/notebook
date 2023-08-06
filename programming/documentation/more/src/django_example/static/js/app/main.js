define(function (require) {
  let messages = require('./messages.js');
  let print = require('/static/js/lib/print.js');

  print(messages.getHello());
});
