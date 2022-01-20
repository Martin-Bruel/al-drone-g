const PositionProvider = require('../interfaces/PositionProvider');
const { getConfiguration } = require('../configuration/config');
const TruckService = require('../services/TruckService')
const DroneService = require('../services/DroneService')
const TimeUtils = require('../utils/TimeUtil')
const DroneFinder = require('../interfaces/DroneFinder')

async function startSendingPositions(lastPosition) {
    let leader =DroneFinder.findLeader();
    let isLeader = (leader.id == getConfiguration().info.id)
    if(isLeader){
        let id = setInterval(() => {
            let currentPosition = PositionProvider.getCurrentPosition();
            if(currentPosition.equals(lastPosition)){
                clearInterval(id);
            }
            console.log("Positions of drones in fleet sent to truck")
            let fleet = DroneFinder.findAll();
            TruckService.sendFleet(fleet);  
        }, 5000)
        return;
    }
    let id = setInterval(() => {
        let currentPosition = PositionProvider.getCurrentPosition();
        if(currentPosition.equals(lastPosition)){
            clearInterval(id);
        }

        let currentTime = TimeUtils.getCurrentTime();
        let idDrone = getConfiguration().info.id;
        let error= true;
        try{
            if(getConfiguration().info.connected){
                TruckService.sendPositionDrone(idDrone, currentPosition, currentTime);
                error= false;
            }
        }catch(e){
            // TO DO: Handle exception
            // console.log(e)
        }

        if(error){
            console.log("Cannot connect to truck")
            DroneService.sendPositionDrone(idDrone, currentPosition, currentTime);
        }

    }, 5000)
}


module.exports = {
    startSendingPositions
}