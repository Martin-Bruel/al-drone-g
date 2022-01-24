const axios = require('axios');
const { getConfiguration} = require('../configuration/config');
const mapPort = getConfiguration().context.external.map.port;
const mapHost = getConfiguration().context.external.map.host;
const droneId = getConfiguration().info.id
const BlackListHosts = require('../utils/BlackListHosts');

async function sendPositionDrone(currentPosition){
    
    let url='http://'+mapHost +':'+ mapPort+'/map-api/update/drone/position/' + droneId;
    await axios.post(url, currentPosition)
    .then(function (response){
        BlackListHosts.blackList = response.data;
        return response.data;
    })
    .catch(function (error) {
        console.log(error)
        console.log("cannot etablish connection to map...");
    })
}

async function sendStatusDrone(status){
    
    let url='http://'+mapHost +':'+ mapPort+'/map-api/update/drone/status/' + droneId;
    await axios.post(url, {status: status})
    .then(function (response){
        return response.data;
    })
    .catch(function (error) {
        console.log(error)
        console.log("cannot etablish connection to map...");
    })
}

module.exports = {
    sendPositionDrone,
    sendStatusDrone
}