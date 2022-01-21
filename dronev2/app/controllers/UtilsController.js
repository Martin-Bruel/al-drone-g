const { getConfiguration } = require('../configuration/config');

exports.disconnection = async function(req, res){
    getConfiguration().info.connected = false;
    console.log("I'm deconnecting ..")
    res.status(200).json();
}

exports.connection = async function(req, res){
    getConfiguration().info.connected = true;
    console.log("I'm reconnecting ..")
    res.status(200).json();
}