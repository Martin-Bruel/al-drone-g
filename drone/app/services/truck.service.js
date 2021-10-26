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

exports.connectToTruck=async function(){
  let url='http://'+truckService.host + ':'+ truckService.port +'/connect/drone/';
  let result = await axios.post(url, {
    name : context.name,
    host: context.host,
    port: context.port,
    capacity: context.capacity
  }).then((response) => {
    let id = response.data
    console.log("Connected to the truck - my id is : " + id)
    getConfiguration().info.id = id
  }, (error) => {
    console.log(error);
  });
  
}