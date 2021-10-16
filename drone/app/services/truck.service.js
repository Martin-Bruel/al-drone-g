const axios = require('axios')
const { getConfiguration} = require('../config')
let context = getConfiguration().context;
let truckService=context.external.truck;

exports.sendDeliveryState=async function(droneId,statusCode){
    let url = 'http://'+truckService.host+":"+truckService.port+'/delivery'
    let result= await axios.post(url, {
        droneId:droneId,
        deliveryState:statusCode
      })
      .then((response) => {
        console.log("Update state");
      }, (error) => {
        console.log(error);
    });
}