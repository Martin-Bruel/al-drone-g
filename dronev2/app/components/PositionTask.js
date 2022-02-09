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
            contactDroneLeader(fleet)
        });
        
    }, 1000)
}

function contactDroneLeader(fleet){
    for(var drone of fleet){
        if(drone.id < getConfiguration().info.id){
            DroneService.sendFleet(drone, fleet)
                .then(() => {
                    DroneFinder.setLeader(drone.id);
                    break;
                })
                .catch(() => {
                    continue;
                });
        }
    }
}


module.exports = {
    startSendingPositions
}