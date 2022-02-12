const DroneFinder = require('../interfaces/DroneFinder');
const RequestHelper = require('../utils/RequestHelper');
const { getConfiguration } = require('../configuration/config');
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

async function sendFleet(droneToContact, fleet){  
    let myDroneId = getConfiguration().info.id
    let body = {
        droneId : myDroneId,
        fleet : fleet
    }
    return new Promise(async(res, rej) => {
        await RequestHelper.post(
            droneToContact.connectionInterface.host,
            droneToContact.connectionInterface.port,
            '/drone-api/position/followers',
            body,
            (response) => {
                res();
            },
            (error) => {
                rej();
            }
    
        );
    })
    
}

module.exports = {
    sendPositionDrone,
    sendFleet
}