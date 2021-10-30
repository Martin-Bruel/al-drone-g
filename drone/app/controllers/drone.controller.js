const RouteService= require('../services/route.service')
let ACCEPT_CONNECTION=true
const TIMEOUT=1000
exports.startDelivery =async function(req,res) {
    if(ACCEPT_CONNECTION){
        await RouteService.startDelivery(req.body.steps);
        res.status(200).json({started:true,message:'Drone started'})
    } else{
        res.status(500).json("Connection refused")
    }
}


exports.getPosition = async function (req, res) {
    if(ACCEPT_CONNECTION){
        var position= await RouteService.getPosition();
        res.status(200).json(position)
    }else{
        setTimeout(function(){
            res.status(500).json("Connection refused")
        }, TIMEOUT);

    }
}

exports.stopDelivery =async function(req,res) {
    ACCEPT_CONNECTION=false
    console.log("Connexion Lost")
    res.status(200).json("Connection stopped")
}

exports.reStartDelivery =async function(req,res) {
    ACCEPT_CONNECTION=true
    console.log("Reconnexion")
    res.status(200).json("Connection accepted")
}
