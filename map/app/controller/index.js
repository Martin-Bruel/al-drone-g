const config = require('../configuration');
const { Position } = require('../model/Position');
const WebService = require('../webservice');

var truckPosition = new Position(43.617226, 7.075738);
var truckHost = process.env.APP_ENV=='prod'?'truck:8085':'localhost:8085';
var drones = [];

function calculInvisibleDrones(fromId, toId, position){

    let invisibleDrones = [];
    let link = toId;
    let drone = drones.find(d => d.id == fromId);

    drone.position = position;
    if(truckPosition.distance(position) > config.getConfiguration().info.radius) {
        invisibleDrones.push(truckHost);
    }

    for(let d of drones){
        if(d.position.distance(position) > config.getConfiguration().info.radius) {
            invisibleDrones.push(d.connectionInterface.host+':'+d.connectionInterface.port);
        }
    }

    drone.link = link;
    WebService.notifyWebSockets(JSON.stringify(drone));
    return invisibleDrones;
}

function addDrone(drone){
    drones.push(drone);
    console.log(`Drone ${drone.id} added`);
    WebService.notifyWebSockets(JSON.stringify(drone));
    return drone;
}

function updateStatus(droneId, status){
    let drone = drones.find(d => d.id == droneId);
    drone.status = status;
    //console.log(`Drone ${droneId} change status ${status}`);
    WebService.notifyWebSockets(JSON.stringify(drone));
    return drone;
}

function getInfo(){
    return {drones: drones, truckPosition: truckPosition};
}

module.exports = {
    calculInvisibleDrones,
    addDrone,
    updateStatus,
    getInfo
}