const { Drone } = require('../model/Drone');
const { Fleet } = require('../model/Fleet');
const { Registry } = require('../registries/registry');

let fleetRegistry = new Registry('fleet');
let leaderIdRegistry = 0;

function findAll(){
    let drones = fleetRegistry.get();
    let fleet = new Fleet(drones);
    return fleet.getDrones();
}

function findLeader(){
    let leader = fleetRegistry.find({id:leaderIdRegistry});
    return new Drone(leader.id,{ host:leader.connectionInterface.host, port:leader.connectionInterface.port} ,leader.position);
}

function findDroneDisconnected(){
    return [];
}

function registerFleet(fleet, leaderDroneId){
    let f = new Fleet(fleet.drones);
    for(drone of f.drones){
        console.log("Registering drone "+ drone.id+"");
        fleetRegistry.create(drone);
    }
    
    console.log("The leader is drone " + leaderDroneId)
    leaderIdRegistry = leaderDroneId;
}

function updatePositionDrone(droneId, position,timestamp){
    let oldDrone = fleetRegistry.find({id:droneId});
    let newDrone = oldDrone;
    newDrone.position = position;
    newDrone.timestamp=timestamp;
    fleetRegistry.update(oldDrone, newDrone);
}

module.exports = {
    findAll,
    findLeader,
    findDroneDisconnected,
    registerFleet,
    updatePositionDrone
}