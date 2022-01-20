const { ConnectionInterface } = require("./ConnectionInterface");
const { Position } = require("./Position");

class Drone{

    constructor(id, connectionInterface, position, timestamp){
        this.connectionInterface = new ConnectionInterface(connectionInterface.host, connectionInterface.port);
        this.id = id;
        this.position = new Position(position.lat, position.lon);
        this.timestamp = timestamp;
    }
}

module.exports = {
    Drone
}