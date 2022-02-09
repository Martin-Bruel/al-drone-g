const DroneService = require('../services/DroneService');
const TruckService = require('../services/TruckService')
const DeliveryFinder = require('../interfaces/DeliveryFinder')
const DroneFinder = require('../interfaces/DroneFinder')

async function start(){
    await new Promise((res,rej) => {
        let id = setInterval(async function () {           
            /*if(){
                clearInterval(id);
                res();
            }*/
            let deliveries = DeliveryFinder.findAll();
            deliveries.forEach(async (delivery)=>{
                try{
                    console.log("deliveries[0]="+delivery.deliveryStatusCode)
                    await TruckService.sendDeliveryState(delivery.deliveryStatusCode,delivery.deliveryId);
                }catch(e){
                    const leader = DroneFinder.findLeader();
                    DroneService.sendDeliveryState(delivery.deliveryStatusCode,delivery.deliveryId,leader);
                }
            })               
            DeliveryFinder.reset();
        }, 1000)
    })
}

module.exports = {
    start
}