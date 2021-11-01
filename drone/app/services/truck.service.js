const axios = require('axios')
const { getConfiguration} = require('../config')
var ACCEPT_CONNECTION=getConfiguration().info.speed
let context = getConfiguration().context;
let truckService=context.external.truck;

sendDeliveryState=async function(droneId,statusCode, deliveryId){

  if(ACCEPT_CONNECTION){
    let url = 'http://'+truckService.host+":"+truckService.port+'/delivery'
    let result= await axios.post(url, {
        droneId:droneId,
        deliveryState:statusCode,
        deliveryId:deliveryId
      })
      .then((response) => {
        console.log("Update state");
      }, (error) => {
        console.log(error);
    });
  }
  else {
    setTimeout(function() {
      sendDeliveryState(droneId, statusCode,deliveryId)
   }, 1000);
  }
}

connectToTruck=async function(){
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
    setTimeout(function() {
      connectToTruck()
   }, 1000);
  });
  
}

module.exports = {
  sendDeliveryState,
  connectToTruck
}