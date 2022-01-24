const EngineActuator = require('../interfaces/EngineActuator');
const PositionModifier = require('../interfaces/PositionModifier');
const PositionProvider = require('../interfaces/PositionProvider');
const TrackingStarter = require('../interfaces/TrackingStarter');
const DeliveryStatusCode = require('../constants/DeliveryStatusCode');
const DroneModifier = require('../interfaces/DroneModifier');

const TruckService = require('../services/TruckService')
const MapService = require('../services/MapService')

async function startJourney(flightPlan) {

    return new Promise(async (accept,reject) => {

        positions = flightPlan.getPositions();
        nbPositions = positions.length;

        PositionModifier.setCurrentPosition(positions[0]);
        let currentStep = 1;

        // TODO : Remplacer l'id de package envoyer par défaud par le(s) vrais ids
        await TruckService.sendDeliveryState(DeliveryStatusCode.STARTING_DELIVERY, 1);
        await MapService.sendStatusDrone(DeliveryStatusCode.STARTING_DELIVERY);
        await TrackingStarter.startSendingPositions(positions[nbPositions - 1]);

        while(currentStep < nbPositions){

            let targetPos = positions[currentStep];
            await EngineActuator.flyTo(targetPos);

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

        DroneModifier.unregisterFleet();

        await TruckService.sendDeliveryState(DeliveryStatusCode.FINISHED_DELIVERY);
        await MapService.sendStatusDrone(DeliveryStatusCode.FINISHED_DELIVERY);
    });


}

module.exports = {
    startJourney
}
