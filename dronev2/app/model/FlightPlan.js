const { Position } = require("./Position");
const { Step } = require('./Step');

class FlightPlan{

    constructor(steps, start){
        this.steps = [];
        this.start = new Position(start.latitude, start.longitude);
        steps.forEach(p => {
           this.steps.push(new Step(new Position(p.latitude, p.longitude), p.deliveryId)); 
        });
    }

    getPositions(){
        let positions = [];
        positions.push(this.start)
        for(let step of this.steps){
            positions.push(step.position);
        }
        positions.push(this.start);
        return positions;
    }
}

module.exports = {
    FlightPlan
}