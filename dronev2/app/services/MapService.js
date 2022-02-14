const axios = require('axios');
const { getConfiguration} = require('../configuration/config');
const mapPort = getConfiguration().context.external.map.port;
const mapHost = getConfiguration().context.external.map.host;
const BlackListHosts = require('../utils/BlackListHosts');
var connected = true;

async function sendPositionDrone(currentPosition, id){
    
    let url='http://'+mapHost +':'+ mapPort+'/map-api/update/drone/position/' + getConfiguration().info.id + '/' + id;
    await axios.post(url, currentPosition)
    .then(function (response){
        BlackListHosts.blackList = response.data;
        console.log('BlackListHosts.blackList ='+BlackListHosts.blackList);
        connected = true;
        return response.data;
    })
    .catch(function (error) {
        if(connected) {
            console.log("cannot etablish connection to map...");
            connected = false;
        }
    })
}

async function sendStatusDrone(status){
    
    let url='http://'+mapHost +':'+ mapPort+'/map-api/update/drone/status/' + getConfiguration().info.id;
    await axios.post(url, {status: status})
    .then(function (response){
        connected = true;
        return response.data;
    })
    .catch(function (error) {
        if(connected) {
            console.log("cannot etablish connection to map...");
            connected = false;
        }
    })
}

module.exports = {
    sendPositionDrone,
    sendStatusDrone
}