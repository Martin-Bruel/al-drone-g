const DroneFinder = require('../interfaces/DroneFinder');
const RequestHelper = require('../utils/RequestHelper');
const axios = require('axios');
const math = require('math')

async function sendPositionDrone(idDrone, currentPosition, currentTime){
    const droneLeader= DroneFinder.findLeader();
    let url='http://'+droneLeader.connectionInterface.host +':'+ droneLeader.connectionInterface.port+'/drone-api/position/followers';  
    console.log("Position of drone send to the drone leader at "+ math.round(currentTime*100)/100 +": "+currentPosition)
    await RequestHelper.post(
        droneLeader.connectionInterface.host,
        droneLeader.connectionInterface.port,
        {
            droneId: idDrone,
            position: currentPosition,
            timestamp: currentTime
        },
        (response) => {
            return response.data;
        },
        (error) => {
            console.log(error);
        }
    )
}

async function sendDeliveryState(statusCode, deliveryId, drone){
    
    let idDrone= getConfiguration().info.id;
    if(droneId !== undefined && droneId !== null){
        idDrone = droneId;
    }
    
    await RequestHelper.post(
        drone.connectionInterface.host,
        drone.connectionInterface.port,
        '/status/followers',
        {
            droneId:drone.id,
            deliveryState:statusCode,
            deliveryId:deliveryId
        },
        (response) => {
        },
        (error) => {
            console.log(error);
        }
    ).catch(error => {});
}

module.exports = {
    sendPositionDrone
}