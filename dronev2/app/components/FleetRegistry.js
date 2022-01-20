const { Drone } = require('../model/Drone');
const { Fleet } = require('../model/Fleet');
const { Registry } = require('../registries/registry');

const fleetRegistry = new Registry('fleetRegistry');
var leaderIdRegistry = 0;

function findAll(){
    let drones = fleetRegistry.get();
    let fleet = new Fleet(drones);
    return fleet.getDrones();
}

function findLeader(){
    let leader = fleetRegistry.find({id:leaderIdRegistry});
    return new Drone(leader.id, leader.ip, leader.position);
}

function findDroneDisconnected(){
    return [];
}

function registerFleet(fleet, leaderId){
    let f = new Fleet(fleet.drones);
    for(drone of f.drones){
        fleetRegistry.create(drone);
    }
    leaderIdRegistry = leaderId;
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