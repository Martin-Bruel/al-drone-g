const PositionProvider = require('../interfaces/PositionProvider');
const { getConfiguration } = require('../configuration/config');
const TruckService = require('../services/TruckService')
const DroneService = require('../services/DroneService')
const MapService = require('../services/MapService')
const TimeUtil = require('../utils/TimeUtil')
const DroneFinder = require('../interfaces/DroneFinder')

async function startSendingPositions(lastPosition) {
    let id = setInterval(() => {
        let currentPosition = PositionProvider.getCurrentPosition();
        if(currentPosition.equals(lastPosition)){
            clearInterval(id);
        }

        MapService.sendPositionDrone(currentPosition);
        
        let fleet = DroneFinder.findAll();

        
        TruckService.sendFleet(fleet).then().catch(() => {
            contactDrones(0, fleet)
        });
        
    }, 1000)
}

async function contactDrones(index, fleet){
    console.log("============-DANS LE CONTACTDRONELEADER-============");
    if(fleet[index].id == getConfiguration().info.id) return;
    DroneService.sendFleet(fleet[index], fleet)
    .then(() => {
        DroneFinder.setLeader(fleet[index].id);
    })
    .catch(() => {
        index++;
        contactDrones(fleet[index], fleet)
    });
}


module.exports = {
    startSendingPositions
}