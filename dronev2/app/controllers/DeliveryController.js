const DeliveryModifier = require ('../interfaces/DeliveryModifier');

exports.saveFollowerStatusCode = async function(req, res){
    console.log("Saving delivery status code from drone "+req.body.droneId+" (status="+req.body.deliveryStatusCode+", deliveryId="+ req.body.deliveryId+ ")")
    DeliveryModifier.save(req.body);
    res.status(200).json();
}