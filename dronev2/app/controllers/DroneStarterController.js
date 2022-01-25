const DroneStarter = require('../interfaces/DroneStarter');
const { FlightPlan } = require('../model/FlightPlan');
const { Fleet } = require('../model/Fleet');

exports.deliveryStart = async function(req, res){

    console.log("START DELIVERY")
    console.log(req.body)
    
    let flightPlan = new FlightPlan(req.body.flightPlan.steps);
    let fleet = new Fleet(req.body.drones);
    let leaderDroneId = req.body.leaderDroneId;

    DroneStarter.start(flightPlan, fleet, leaderDroneId);
    res.status(200).json();
}