const DroneFinder = require('../interfaces/DroneFinder');
const axios = require('axios');
const { getConfiguration } = require('../configuration/config');


async function sendPositionDrone(idDrone, currentPosition, currentTime){
    const droneLeader= DroneFinder.findLeader();
    let url='http://'+droneLeader.ip+'/drone-api/position/followers';

    await axios.post(url, 
        {
            droneId: idDrone,
            position: currentPosition,
            timestamp: currentTime
        }
    )
    .then(function (response){
        console.log("Position of drone sent to the drone leader")
        return response.data;
    })
    .catch(function (error) {
        console.log(error);
    })
}

module.exports = {
    sendPositionDrone
}