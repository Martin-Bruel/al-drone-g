const config = require('./configuration')
const express = require('express')
const api = require('./api')

const app = express();

app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

app.use(express.json());
app.use('/map-api', api);
app.listen(config.port, () => console.log('Server [map] started on port: ' + config.port));


const { WebSocketServer } = require('ws');
const WebService = require('./webservice');

const wss = new WebSocketServer({ port: config.websocketPort });

wss.on('connection', WebService.handleWebSocketConnection);