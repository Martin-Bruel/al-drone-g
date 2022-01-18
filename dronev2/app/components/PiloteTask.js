const EngineActuator = require('../interfaces/EngineActuator');
const PositionModifier = require('../interfaces/PositionModifier');
const PositionProvider = require('../interfaces/PositionProvider');
const TrackingStarter = require('../interfaces/TrackingStarter');

async function startJourney(flightPlan) {

    return new Promise(async (accept,reject) => {

        positions = flightPlan.getPositions();
        nbPositions = positions.length;
        
        PositionModifier.setCurrentPosition(flightPlan.getPositions()[0]);
        let currentStep = 1;

        TrackingStarter.startSendingPositions(positions[nbPositions-1]);

        while(currentStep < flightPlan.getPositions().length){

            let targetPos = flightPlan.getPositions()[currentStep];
            EngineActuator.flyTo(targetPos);

            await new Promise((res,rej) => {
                let id = setInterval(() => {
                    if(PositionProvider.getCurrentPosition().equals(targetPos)){
                        currentStep++;
                        clearInterval(id);
                        res();
                    }
                }, 1000)
            })
        }
        accept();
    });

    
}

module.exports = {
    startJourney
}