const PiloteStarter = require('../interfaces/PiloteStarter');

function start(flightPlan, fleet, leaderId){
    PiloteStarter.startJourney(flightPlan);
}

module.exports = {
    start
}