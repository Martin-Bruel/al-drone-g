const RouteService= require('../services/route.service')

exports.startDelivery =async function(req,res) {
    await RouteService.startDelivery(req.body.steps);
    res.status(200).json({started:true,message:'Drone started'})
}

exports.getPosition = async function (req, res) {
    var position= await RouteService.getPosition();
    res.status(200).json(position)
}