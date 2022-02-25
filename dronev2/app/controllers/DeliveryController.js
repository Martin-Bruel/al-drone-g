const DeliveryModifier = require ('../interfaces/DeliveryModifier');

exports.saveFollowerStatusCode = async function(req, res){
    console.log("Saving delivery status code from drone "+req.body.droneId+" (status="+req.body.deliveryState+", deliveryId="+ req.body.deliveryId+ ")")
    DeliveryModifier.save(req.body.deliveryState,req.body.deliveryId,req.body.droneId);
    res.status(200).json();
}