const PiloteStarter = require('../interfaces/PiloteStarter');
const DroneModifier = require('../interfaces/DroneModifier');
const PositionModifier = require('../interfaces/PositionModifier');
const DeliveryService = require('../services/DeliveryService')

function start(flightPlan, fleet, leaderId){
    DroneModifier.registerFleet(fleet, leaderId);
    PiloteStarter.startJourney(flightPlan);
    PositionModifier.setCurrentPosition(flightPlan.getPositions()[0])
    DeliveryService.start()
}

module.exports = {
    start
}