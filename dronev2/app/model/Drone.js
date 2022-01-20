const { ConnectionInterface } = require("./ConnectionInterface");
const { Position } = require("./Position");

class Drone{

    constructor(id, connectionInterface, position){
        this.connectionInterface = new ConnectionInterface(connectionInterface.host, connectionInterface.port);
        this.id = id;
        this.position = new Position(position.lat, position.lon);
    }
}

module.exports = {
    Drone
}