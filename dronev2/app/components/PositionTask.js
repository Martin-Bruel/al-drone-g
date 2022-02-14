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
            contactDrones(fleet)
        });
        
    }, 50)
}

async function contactDrones(fleet){
    console.log("============-DANS LE CONTACT DRONE LEADER-============");
    fleet = fleet.sort((d1,d2) => d1.id - d2.id)
    for(let drone of fleet){
        if(drone.id == getConfiguration().info.id) break;
        try{
            await DroneService.sendFleet(drone, fleet);
            console.log("============-ENVOIE SA POSITION AU DRONE "+drone.id+"-============");
            DroneFinder.setLeader(drone.id);
            break;
        }
        catch{
            continue;
        }
        
    }
}

module.exports = {
    startSendingPositions
}