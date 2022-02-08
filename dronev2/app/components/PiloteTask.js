const EngineActuator = require('../interfaces/EngineActuator');
const PositionModifier = require('../interfaces/PositionModifier');
const PositionProvider = require('../interfaces/PositionProvider');
const TrackingStarter = require('../interfaces/TrackingStarter');
const DeliveryStatusCode = require('../constants/DeliveryStatusCode');
const DroneFinder = require('../interfaces/DroneFinder');

const TruckService = require('../services/TruckService')
const DroneService = require('../services/DroneService')
const MapService = require('../services/MapService')

async function startJourney(flightPlan) {

    return new Promise(async (accept,reject) => {

        steps = flightPlan.steps;
        nbPositions = steps.length;

        PositionModifier.setCurrentPosition(steps[0].position);
        let currentStep = 1;

        let nextDeliveryId = flightPlan.nextDelivery(currentStep); // La prochaine adresse de livraison est la livraison courante
        await TruckService.sendDeliveryState(DeliveryStatusCode.STARTING_DELIVERY, nextDeliveryId);
        await MapService.sendStatusDrone(DeliveryStatusCode.STARTING_DELIVERY);
        await TrackingStarter.startSendingPositions(steps[nbPositions - 1].position);

        while(currentStep < nbPositions){
            nextDeliveryId = flightPlan.nextDelivery(currentStep);
            let targetDeliveryId =steps[currentStep].deliveryId;
            console.log("nextDeliveryId="+nextDeliveryId);
            console.log("targetDeliveryId="+targetDeliveryId);
            let targetPos = steps[currentStep].position;
            await sendAndRetryDeliveryState(DeliveryStatusCode.PENDING_DELIVERY,nextDeliveryId);       
            await EngineActuator.flyTo(targetPos);

            await new Promise((res,rej) => {
                let id = setInterval(() => {
                    if(PositionProvider.getCurrentPosition().equals(targetPos)){
                        console.log("Step reached :"+targetPos)
                        // Le step est une adresse de livraison
                        if(targetDeliveryId !== undefined 
                            && targetDeliveryId !==null){
                            sendAndRetryDeliveryState(DeliveryStatusCode.PACKAGE_DELIVERED,targetDeliveryId);
                        }
                        currentStep++;
                        clearInterval(id);
                        res();
                    }
                }, 1000)
            })
        }
        accept();

        await TruckService.sendDeliveryState(DeliveryStatusCode.FINISHED_DELIVERY);
        await MapService.sendStatusDrone(DeliveryStatusCode.FINISHED_DELIVERY);
    });


}

// Envoyer le status de livraison au camion et réessayer avec le drone leader actuelle
async function sendAndRetryDeliveryState(deliveryStatusCode,deliveryId){
    try{
        await TruckService.sendDeliveryState(deliveryStatusCode,deliveryId);
    }catch(e){
        const leader = DroneFinder.findLeader();
        DroneService.sendDeliveryState(deliveryStatusCode,deliveryId,leader);
    }
}

module.exports = {
    startJourney
}
