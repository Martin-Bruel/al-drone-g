const axios = require('axios');
const { getConfiguration } = require('../configuration/config');
const states = ['STARTING_DELIVERY','PENDING_DELIVERY','FINISHED_DELIVERY','PACKAGE_DELIVERED']

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

async function sendDeliveryState(statusCode, deliveryId){
    let truck = getConfiguration().context.external.truck;
    let idDrone = getConfiguration().info.id;
    let url='http://'+truck.host + ':'+ truck.port +'/delivery';
    await axios.post(url, 
    {
        droneId:idDrone,
        deliveryState:statusCode,
        deliveryId:deliveryId
    }
    ).then((response) => {
        if(deliveryId) console.log("Notify truck : " + states[statusCode - 1] + ' <' +deliveryId + '>');
        else console.log("Notify truck : " + states[statusCode - 1]);
    }, (error) => {
        console.log(error);
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
        console.log("Position of drone send to truck : "+currentPosition)
        return response.data;
    })
    .catch(function (error) {
        console.log(error);
    })
}


async function sendFleet(fleetInfo){
    let truck = getConfiguration().context.external.truck;
    let url='http://'+truck.host + ':'+ truck.port +'/truck-api/position';

    await axios.post(url, fleetInfo)
    .then(function (response){
        return response.data;
    })
    .catch(function (error) {
        console.log(error);
    })
}

module.exports = {
    sendDeliveryState, 
    connectToTruck,
    sendPositionDrone,
    sendFleet
}