const axios = require('axios');
const { getConfiguration } = require('../configuration/config');

async function connectToTruck(){
    
    let truck = getConfiguration().context.external.truck;
    let context = getConfiguration().context;
    let url='http://'+truck.host + ':'+ truck.port +'/connect/drone/';
    
    await axios.post(url, {
        name : context.name,
        host: context.host,
        port: context.port,
        capacity: context.capacity
    }).then((response) => {
        let id = response.data
        console.log("Connected to the truck - my id is : " + id)
        getConfiguration().info.id = id
    }, () => {
            setTimeout(function() {
            connectToTruck()
        }, 1000);
    });
}

module.exports = {
    connectToTruck
}