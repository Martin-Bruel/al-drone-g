const axios = require('axios');
const { getConfiguration } = require('../configuration/config');

async function connectToTruck(){
    
    let truck = getConfiguration().context.external.truck;
    let context = getConfiguration().context;
    let url='http://'+truck.host + ':'+ truck.port +'/connect/drone/';
    
    await axios.post(url, {
        name : context.name,
        host: context.host,
        port: context.port,
        capacity: context.capacity
    }).then((response) => {
        let id = response.data
        console.log("Connected to the truck - my id is : " + id)
        getConfiguration().info.id = id
    }, () => {
            setTimeout(function() {
            connectToTruck()
        }, 1000);
    });
}

async function sendPositionDrone(idDrone, currentPosition, currentTime){
    let truck = getConfiguration().context.external.truck;
    let url='http://'+truck.host + ':'+ truck.port +'/truck-api/position';

    await axios.post(url, 
        [
            {
                droneId: idDrone,
                position: currentPosition.format(),
                timestamp: currentTime
            }
        ]
    )
    .then(function (response){
        console.log("Position of drone send to truck")
        return response.data;
    })
    .catch(function (error) {
        console.log(error);
    })
}

module.exports = {
    connectToTruck,
    sendPositionDrone
}