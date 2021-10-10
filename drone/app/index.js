const express = require('express')
const app = express();
const { getConfiguration} = require('./config')
const api = require('./api')
const mongoose = require('mongoose');
app.use(express.json());
app.use('/',  (request, response, next) => {
  const requestStart = Date.now();

  response.on("finish", () => {
    const { rawHeaders, httpVersion, method, socket, url } = request;
    const { remoteAddress, remoteFamily } = socket;
    let processingTime= Date.now() - requestStart
    console.log(
      '['+getConfiguration().service+'] '+method+" "+url+" - "+processingTime+" ms"
    );
  });
  next()
});
app.use('/time', api);
app.listen(getConfiguration().context.port, function() {
   console.log('(env:'+getConfiguration().env+') Server ['+getConfiguration().service+'] started on port: ' + getConfiguration().context.port);
});