const PositionProvider = require('../interfaces/PositionProvider');
const { getConfiguration } = require('../configuration/config');
const TruckService = require('../services/TruckService')
const DroneService = require('../services/DroneService')
const TimeUtil = require('../utils/TimeUtil')
const DroneFinder = require('../interfaces/DroneFinder')

async function startSendingPositions(lastPosition) {
    
    
        
        let id = setInterval(() => {
            let leader =DroneFinder.findLeader();
            let currentPosition = PositionProvider.getCurrentPosition();
            if(currentPosition.equals(lastPosition)){
                clearInterval(id);
            }
            if(leader.id == getConfiguration().info.id){
                console.log("Positions of drones in fleet sent to truck")
                let fleet = DroneFinder.findAll();
                // console.log(fleet)
                TruckService.sendFleet(fleet);  
            }else{
                let currentTime = TimeUtil.getCurrentTime();
                let idDrone = getConfiguration().info.id;
                try{
                    TruckService.sendPositionDrone(idDrone, currentPosition, currentTime);
                }catch(e){
                    console.log(e)
                    console.log("Cannot connect to truck")
                    console.log("Sending to the leader drone")
                    DroneService.sendPositionDrone(idDrone, currentPosition, currentTime);
                }
            }
        }, 5000)
}


module.exports = {
    startSendingPositions
}