const PositionModifier  = require('../interfaces/PositionModifier');
const { Fleet } = require('../model/Fleet');

exports.receiveFollowerPosition = async function(req, res){
    const fleet = new Fleet(req.body.fleet);
    console.log("Receiving position data from drone "+req.body.droneId+": " + JSON.stringify(fleet));
    PositionModifier.updatePositionFleet(fleet);
    res.status(200).json();
}