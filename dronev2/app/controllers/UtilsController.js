const { getConfiguration } = require('../configuration/config');

exports.disconnection = async function(req, res){
    getConfiguration().info.connected = false;
    res.status(200).json();
}

exports.connection = async function(req, res){
    getConfiguration().info.connected = true;
    res.status(200).json();
}