const config = require('../configuration');
const { Position } = require('../model/Position');
const WebService = require('../webservice');

var truckPosition = new Position(43.617226, 7.075738);
var drones = [];

function calculInvisibleDrones(droneId, position){

    let invisibleDrones = [];
    let drone = drones.find(d => d.id == droneId);

    drone.position = position;
    console.log(`Drone ${droneId} dected at position ${position}`);
    WebService.notifyWebSockets(JSON.stringify(drone));
    

    for(let d of drones){
        if(d.position.distance(position) > config.radius) invisibleDrones.push(d.id);
    }
    if(truckPosition.distance(position) > config.radius) invisibleDrones.push(0);

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
    console.log(`Drone ${droneId} change status ${status}`);
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