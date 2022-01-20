const DroneStarter = require('../interfaces/DroneStarter');
const { FlightPlan } = require('../model/FlightPlan');
const { Fleet } = require('../model/Fleet');

exports.deliveryStart = async function(req, res){
    
    let flightPlan = new FlightPlan(req.body.flightPlan.steps, req.body.flightPlan.start);
    let fleet = new Fleet(req.body.drones);
    let leaderId = req.body.leaderId;

    DroneStarter.start(flightPlan, fleet, leaderId);
    res.status(200).json();
}