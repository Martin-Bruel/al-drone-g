const DroneFinder = require('../interfaces/DroneFinder');
const axios = require('axios');

async function sendPositionDrone(idDrone, currentPosition, currentTime){
    const droneLeader= DroneFinder.findLeader();
    let url='http://'+droneLeader.connectionInterface.host +':'+ droneLeader.connectionInterface.port+'/drone-api/position/followers';  
    console.log("Position of drone send the drone leader at "+ currentTime+": "+currentPosition)
    await axios.post(url, 
        {
            droneId: idDrone,
            position: currentPosition,
            timestamp: currentTime
        }
    )
    .then(function (response){
        return response.data;
    })
    .catch(function (error) {
        console.log(error);
    })
}

module.exports = {
    sendPositionDrone
}