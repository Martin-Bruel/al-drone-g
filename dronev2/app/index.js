const express = require('express')
const { getConfiguration} = require('./configuration/config')
const TruckService = require('./services/TruckService')
const api = require('./api')

const app = express();
app.use(express.json());
app.use('/drone-api', api);
app.listen(getConfiguration().context.port, function() {
   console.log('(env:'+getConfiguration().env+') Server ['+getConfiguration().service+'] started on port: ' + getConfiguration().context.port);
});

TruckService.connectToTruck()