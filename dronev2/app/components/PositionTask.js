const PositionProvider = require('../interfaces/PositionProvider');
const { getConfiguration } = require('../configuration/config');
const TruckService = require('../services/TruckService')
const DroneService = require('../services/DroneService')
const MapService = require('../services/MapService')
const TimeUtil = require('../utils/TimeUtil')
const DroneFinder = require('../interfaces/DroneFinder')

async function startSendingPositions(lastPosition) {
    let leader =DroneFinder.findLeader();
    if(leader.id == getConfiguration().info.id){
        let id = setInterval(() => {
            let currentPosition = PositionProvider.getCurrentPosition();
            if(currentPosition.equals(lastPosition)){
                clearInterval(id);
            }
            //console.log("Positions of drones in fleet sent to truck")
            let fleet = DroneFinder.findAll();
            // console.log(fleet)
            TruckService.sendFleet(fleet);  
            MapService.sendPositionDrone(currentPosition);
        }, 50)
        return;
    }
    let id = setInterval(() => {
        let currentPosition = PositionProvider.getCurrentPosition();
        if(currentPosition.equals(lastPosition)){
            clearInterval(id);
        }

        let currentTime = TimeUtil.getCurrentTime();
        let idDrone = getConfiguration().info.id;
        let error= true;
        try{
            if(getConfiguration().info.connected){
                TruckService.sendPositionDrone(idDrone, currentPosition, currentTime);
                error= false;
            }
        }catch(e){
            // TODO: Handle exception
            // console.log(e)
        }

        if(error){
            console.log("Cannot connect to truck")
            console.log("Sending to the leader drone")
            DroneService.sendPositionDrone(idDrone, currentPosition, currentTime);
        }

    }, 5000)
}


module.exports = {
    startSendingPositions
}