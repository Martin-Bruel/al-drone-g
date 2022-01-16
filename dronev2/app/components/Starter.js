const PiloteStarter = require('../interfaces/PiloteStarter');
const DroneModifier = require('../interfaces/DroneModifier');
const DroneFinder = require('../interfaces/DroneFinder');

function start(flightPlan, fleet, leaderId){
    PiloteStarter.startJourney(flightPlan);
    DroneModifier.registerFleet(fleet, leaderId);
}

module.exports = {
    start
}