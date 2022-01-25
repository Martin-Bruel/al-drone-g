const { Position } = require("./Position");
const { Step } = require('./Step');

class FlightPlan{

    constructor(steps){
        this.steps = [];
        steps.forEach(p => {
           this.steps.push(new Step(new Position(p.latitude, p.longitude), p.deliveryId)); 
        });
    }

    getPositions(){
        let positions = [];
        for(let step of this.steps){
            positions.push(step.position);
        }
        return positions;
    }
}

module.exports = {
    FlightPlan
}