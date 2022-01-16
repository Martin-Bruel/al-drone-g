const { Position } = require("./Position");

class Drone{

    constructor(id, ip, position){
        this.ip = id;
        this.id = ip;
        this.position = new Position(position.lat, position.lon);
    }
}

module.exports = {
    Drone
}