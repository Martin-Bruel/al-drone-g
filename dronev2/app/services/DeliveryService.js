const DroneService = require('../services/DroneService');
const TruckService = require('../services/TruckService')
const DeliveryFinder = require('../interfaces/DeliveryFinder')
const DroneFinder = require('../interfaces/DroneFinder')
const DroneModifier = require('../interfaces/DroneModifier')
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
                    DroneModifier.reset();
                }catch(e){
                    if(getConfiguration().info.id !== leader.id){
                        const leader = DroneFinder.findLeader();
                        DroneService.sendDeliveryState(delivery.deliveryStatusCode,delivery.deliveryId,leader);
                        DroneModifier.reset();
                    }                    
                }
            })               
            
        }, 1000)
    })
}

module.exports = {
    start
}