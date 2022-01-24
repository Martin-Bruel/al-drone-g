var sockets = [];

function handleWebSocketConnection(ws){
    console.log('new connection');
    sockets.push(ws);
}

function notifyWebSockets(data){
    sockets.forEach(ws => {
        ws.send(data);
    });
}

module.exports = {
    handleWebSocketConnection,
    notifyWebSockets
}