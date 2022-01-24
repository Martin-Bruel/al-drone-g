const axios = require('axios');
const {ConnectionException} = require('../exceptions/ConnectionException')
const { getConfiguration } = require('../configuration/config');

exports.post = async function (host,port,path,body,response,error){
    if(!reachable(host,port)){
        throw new ConnectionException("This device is unreachable.")
    }
    let url = 'http://'+host + ':'+port + path;
    await axios.post(url, body).then(response,error);
}

function reachable(host,port){
    //TO DO: Issue #80
    return getConfiguration().info.connected;
}