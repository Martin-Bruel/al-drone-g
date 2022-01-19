const PositionModifier  = require('../interfaces/PositionModifier');

exports.getFollowerPosition = async function(req, res){
    PositionModifier.modifyPositionDrone(req.body.position,req.body.drone);
    res.status(200).json();
}