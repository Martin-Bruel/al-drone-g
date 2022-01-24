const PiloteStarter = require('../../app/interfaces/PiloteStarter');
const { FlightPlan } = require('../../app/model/FlightPlan');
const { Position } = require('../../app/model/Position');

async function piloteTaskTest(){

    console.log('> PiloteTask Test')

    let truckPos = new Position(0,0);
    let deliveryPos = new Position(8,4);

    let flightPlan = new FlightPlan([truckPos, deliveryPos, truckPos]);

    await PiloteStarter.startJourney(flightPlan);
}

module.exports = {
    piloteTaskTest
}