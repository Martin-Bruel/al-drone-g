const PositionModifier  = require('../interfaces/PositionModifier');

exports.getFollowerPosition = async function(req, res){
    PositionModifier.updatePositionDrone(req.body.position,req.body.drone, req.body.timestamp);
    res.status(200).json();
}