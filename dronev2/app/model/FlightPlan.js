const { Position } = require("./Position");

class FlightPlan{

    constructor(positions){
        this.positions = [];
        positions.forEach(p => {
           this.positions.push(new Position(p.lat, p.lon)); 
        });
    }

    getPositions(){
        return this.positions;
    }
}

module.exports = {
    FlightPlan
}