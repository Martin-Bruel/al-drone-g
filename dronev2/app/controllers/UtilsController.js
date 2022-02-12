const { getConfiguration } = require('../configuration/config');
const DroneFinder= require('../interfaces/DroneFinder')

exports.disconnection = async function(req, res){
    if(req.body.onlyFollowers){
        const leader =DroneFinder.findLeader();
        if(getConfiguration().info.id !== leader.id){
            getConfiguration().info.connected = false;
            console.log("I'm deconnecting ..")
        }
    }else{
        getConfiguration().info.connected = false;
        console.log("I'm deconnecting ..")
    }    
    res.status(200).json();
}

exports.connection = async function(req, res){
    getConfiguration().info.connected = true;
    console.log("I'm reconnecting ..")
    res.status(200).json();
}