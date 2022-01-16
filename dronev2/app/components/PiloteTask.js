const { flyTo } = require('../interfaces/EngineActuator');
const { getCurrentPosition } = require('../interfaces/PositionProvider')

async function startJourney(flightPlan) {

    return new Promise(async (accept,reject) => {
        
        let currentStep = 1;

        while(currentStep < flightPlan.getPositions().length){

            let targetPos = flightPlan.getPositions()[currentStep];
            flyTo(targetPos);

            await new Promise((res,rej) => {
                let id = setInterval(() => {
                    if(getCurrentPosition().equals(targetPos)){
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