const PositionModifier  = require('../interfaces/PositionModifier');
const TruckService = require ('../services/TruckService');

exports.getFollowerPosition = async function(req, res){
    console.log("Receiving position data from drone "+req.body.droneId+"( position ="+req.body.position+", timestamp="+req.body.timestamp+")")
    PositionModifier.updatePositionDrone(req.body.droneId,req.body.position, req.body.timestamp);
    res.status(200).json();
}

exports.sendFollowerStatusCode = async function(req, res){
    console.log("Send delivery status code from drone "+req.body.droneId+" (status="+req.body.deliveryStatusCode+", deliveryId="+ req.body.deliveryId+ ")")
    TruckService.sendDeliveryState(req.body.droneId,req.body.deliveryStatusCode, req.body.deliveryId);
    res.status(200).json();
}