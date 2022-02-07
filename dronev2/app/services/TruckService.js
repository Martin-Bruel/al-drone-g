const RequestHelper = require('../utils/RequestHelper');
const { getConfiguration } = require('../configuration/config');
const states = ['STARTING_DELIVERY','PENDING_DELIVERY','FINISHED_DELIVERY','PACKAGE_DELIVERED']
const math = require('math');

async function connectToTruck(){
    
    let truck = getConfiguration().context.external.truck;
    let context = getConfiguration().context;
    await RequestHelper.post(
        truck.host,
        truck.port,
        '/connect/drone/',
        {
            name : context.name,
            host: context.host,
            port: context.port,
            capacity: context.capacity
        },
        (response) => {
            let id = response.data
            console.log("Connected to the truck - my id is : " + id)
            getConfiguration().info.id = id
        },
        (error) => {
            setTimeout(function() {
            connectToTruck()
        }, 1000);
    })
}

async function sendDeliveryState(statusCode, deliveryId){
    let truck = getConfiguration().context.external.truck;
    let idDrone = getConfiguration().info.id;
    await RequestHelper.post(
        truck.host,
        truck.port,
        '/delivery',
        {
            droneId:idDrone,
            deliveryState:statusCode,
            deliveryId:deliveryId
        },
        (response) => {
            if(deliveryId) console.log("Notify truck : " + states[statusCode - 1] + ' <' +deliveryId + '>');
            else console.log("Notify truck : " + states[statusCode - 1]);
        },
        (error) => {
            console.log(error);
        }
    ).catch(error => {});
}

async function sendPositionDrone(idDrone, currentPosition, currentTime){
    let truck = getConfiguration().context.external.truck;
    console.log("Position of drone send to truck at " + math.round(currentTime*100)/100 + ": "+currentPosition)
    await RequestHelper.post(
        truck.host,
        truck.port,
        '/truck-api/position',
        [
            {
                droneId: idDrone,
                position: currentPosition,
                timestamp: currentTime
            }
        ],
        (response) => {
            return response.data;
        },
        (error) => {
            console.log(error);
        }
    ).catch(error => {});
}


async function sendFleet(fleetInfo){
    let truck = getConfiguration().context.external.truck;
    let url='http://'+truck.host + ':'+ truck.port +'/truck-api/position';
    let fleetInfoDto= fleetInfo.map( drone => {
        return {droneId: drone.id,
        position: drone.position.format(),
        timestamp: drone.timestamp};
    })
    return new Promise(async(res, rej) => {
        await RequestHelper.post(
            truck.host,
            truck.port,
            '/truck-api/position',
            fleetInfoDto,
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
    sendDeliveryState, 
    connectToTruck,
    sendPositionDrone,
    sendFleet
}