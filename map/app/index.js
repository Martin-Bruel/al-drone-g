const config = require('./configuration')
const express = require('express')
const api = require('./api')

const app = express();

app.use(express.json());
app.use('/map-api', api);
app.listen(config.port, () => console.log('Server [map] started on port: ' + config.port));


const { WebSocketServer } = require('ws');
const WebService = require('./webservice');

const wss = new WebSocketServer({ port: config.websocketPort });

wss.on('connection', WebService.handleWebSocketConnection);