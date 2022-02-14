const axios = require('axios');
const {ConnectionException} = require('../exceptions/ConnectionException')
const { getConfiguration } = require('../configuration/config');
const BlackListHosts = require('../utils/BlackListHosts');

exports.post = async function (host,port,path,body,response,error){
    if(!reachable(host,port)){
        error()
    }
    else{
        let url = 'http://'+host + ':'+port + path;
        await axios.post(url, body).then(response,error);
    }
    
}

function reachable(host,port){
    if(BlackListHosts.blackList.includes(host+':'+port)) return false;
    return getConfiguration().info.connected;
}