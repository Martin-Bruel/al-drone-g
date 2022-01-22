const DroneFinder = require('../interfaces/DroneFinder');
const axios = require('axios');
const math = require('math')

async function sendPositionDrone(idDrone, currentPosition, currentTime){
    const droneLeader= DroneFinder.findLeader();
    let url='http://'+droneLeader.connectionInterface.host +':'+ droneLeader.connectionInterface.port+'/drone-api/position/followers';  
    console.log("Position of drone send to the drone leader at "+ math.round(currentTime*100)/100 +": "+currentPosition)
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