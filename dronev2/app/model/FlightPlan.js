const { Position } = require("./Position");
const { Step } = require('./Step');

class FlightPlan{

    constructor(steps){
        this.steps = [];
        steps.forEach(step => {
            let pos = step.position
            this.steps.push(new Step(new Position(pos.latitude, pos.longitude), step.deliveryId));
        });
    }

    getPositions(){
        let positions = [];
        for(let step of this.steps){
            positions.push(step.position);
        }
        return positions;
    }

    nextDelivery(currentStep){
        for(let i = currentStep; i< this.steps.length; i++){
            if (steps[i].deliveryId !== undefined 
                && steps[i].deliveryId !== null){
                return steps[i].deliveryId;
            }
        }
        return null;
    }
}

module.exports = {
    FlightPlan
}