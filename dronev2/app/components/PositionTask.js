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

        
        let fleet = DroneFinder.findAll();

        
        TruckService.sendFleet(fleet)
        .then(() => MapService.sendPositionDrone(currentPosition, 0))
        .catch(() => {
            contactDrones(fleet)
        });
        
    }, 30)
}

async function contactDrones(fleet){
    console.log("============-DANS LE CONTACT DRONE LEADER-============");
    let currentPosition = PositionProvider.getCurrentPosition();
    let isSend = false
    fleet = fleet.sort((d1,d2) => d1.id - d2.id)
    for(let drone of fleet){
        if(drone.id == getConfiguration().info.id) break;
        try{
            await DroneService.sendFleet(drone, fleet);
            MapService.sendPositionDrone(currentPosition, drone.id)
            console.log("============-ENVOIE SA POSITION AU DRONE "+drone.id+"-============");
            DroneFinder.setLeader(drone.id);
            isSend = true;
            break;
        }
        catch{
            continue;
        }
        
    }
    if(!isSend) MapService.sendPositionDrone(currentPosition, 999)
}

module.exports = {
    startSendingPositions
}