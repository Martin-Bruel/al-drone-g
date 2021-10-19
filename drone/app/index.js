const express = require('express')
const axios = require('axios');
const app = express();
const { getConfiguration} = require('./config')
app.use(express.json());
const api = require('./api')
app.use('/drone-api', api);
app.listen(getConfiguration().context.port, function() {
   console.log('(env:'+getConfiguration().env+') Server ['+getConfiguration().service+'] started on port: ' + getConfiguration().context.port);
});

let truckService=getConfiguration().context.external.truck;
const truckUrl=truckService.baseUrl+':'+truckService.port;
setTimeout(function() {
   axios.post('http://'+truckUrl+'/connect/drone/name/' + getConfiguration().context.name)
}, 10000);
