const DroneService = require('../services/DroneService');
const TruckService = require('../services/TruckService')
const DeliveryFinder = require('../interfaces/DeliveryFinder')
const DroneFinder = require('../interfaces/DroneFinder')
const DeliveryModifier = require('../interfaces/DeliveryModifier')
const { getConfiguration } = require('../configuration/config');

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
                    await TruckService.sendDeliveryState(delivery.deliveryStatusCode,delivery.deliveryId);
                    DeliveryModifier.reset();
                }catch(e){
                    const leader = DroneFinder.findLeader();
                    if(getConfiguration().info.id !== leader.id){
                        DroneService.sendDeliveryState(delivery.deliveryStatusCode,delivery.deliveryId,leader);
                    }                    
                }
            })               
            
        }, 1000)
    })
}

module.exports = {
    start
}