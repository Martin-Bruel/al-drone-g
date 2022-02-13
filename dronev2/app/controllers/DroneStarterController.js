const DroneStarter = require('../interfaces/DroneStarter');
const { FlightPlan } = require('../model/FlightPlan');
const { Fleet } = require('../model/Fleet');

exports.deliveryStart = async function(req, res){
    let flightPlan = new FlightPlan(req.body.flightPlan.steps);
    console.log(req.body.flightPlan.steps)
    let fleet = new Fleet(req.body.fleet.drones);
    let leaderDroneId = req.body.fleet.leaderDroneId;

    DroneStarter.start(flightPlan, fleet, leaderDroneId);
    res.status(200).json();
}