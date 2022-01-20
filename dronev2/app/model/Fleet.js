const { Drone } = require('./Drone');

class Fleet{

    constructor(drones){
        this.drones = [];
        drones.forEach(d => {
            this.drones.push(new Drone(d.id, d.connectionInterface, d.position));
        });
    }

    getDrones(){
        return this.drones;
    }
}

module.exports = {
    Fleet
}