const http = require('http');
const express = require('express');
const bodyParser = require('body-parser');

const app = express();

app.use(bodyParser.urlencoded({ extended: false }));

app.post('/sms', (req, res) => {
  const body = req.body.Body;
  const from = req.body.From;
  const to = req.body.To;

  console.log(`Message received from ${from} to ${to}: ${body}`);

  res.send('Message received!');
});

http.createServer(app).listen(1337, () => {
  console.log('Express server listening on port 1337');
});



// https://timberwolf-mastiff-9776.twil.io/demo-reply