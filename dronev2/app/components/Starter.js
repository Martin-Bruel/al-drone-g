const PiloteStarter = require('../interfaces/PiloteStarter');
const DroneModifier = require('../interfaces/DroneModifier');
const PositionModifier = require('../interfaces/PositionModifier');

function start(flightPlan, fleet, leaderId){
    PiloteStarter.startJourney(flightPlan);
    DroneModifier.registerFleet(fleet, leaderId);
    PositionModifier.setCurrentPosition(flightPlan.getPositions()[0])
}

module.exports = {
    start
}