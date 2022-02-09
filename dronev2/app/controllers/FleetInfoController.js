const PositionModifier  = require('../interfaces/PositionModifier');

exports.getFollowerPosition = async function(req, res){
    console.log("Receiving position data from drone "+req.body.droneId+"( position ="+req.body.position+", timestamp="+req.body.timestamp+")")
    PositionModifier.updatePositionDrone(req.body.droneId,req.body.position, req.body.timestamp);
    res.status(200).json();
}