var cron = require('node-cron');
const { Route } = require('../models')
const TruckService= require('../services/truck.service')
const {getConfiguration}=require('../config')
let task=[]

exports.startDelivery= async function(itinary){
    let droneId= getConfiguration().info.id;
    Route.clean()
    Route.create({id:0,direction:1,step:0,itinary:itinary})
    console.log('Drone reiceve flight plan : ' + JSON.stringify(itinary))
    TruckService.sendDeliveryState(droneId,1);
    startTask() 
}

function startTask(){
    let droneId= getConfiguration().info.id;
    console.log("Starting delivery")
    task=cron.schedule('*/5 * * * * *', async () =>  {
        var route =Route.getById(0)
        var step = route.step;
        var itinary=route.itinary;
        var direction=route.direction;
        console.log("I'm in position "+toString_Position(itinary[step]));
        if(step+direction==itinary.length){//Arriving to address
            direction=-direction;
            console.log("Delivering package..");
            console.log("Done.");
            TruckService.sendDeliveryState(droneId,4);//Sending delivery confirmation
            console.log("Comming back from customer");
        }
        if(step==0 && direction <0){//Returned to truck
            await TruckService.sendDeliveryState(droneId,3);
            Route.clean();
            console.log("Arriving to truck");
            task.destroy();       
        }
        route.step=step+=direction;
        route.direction=direction;
        Route.update(route.id,route)
    });
    return task; 
}

exports.getPosition = async function(){
    var route =Route.getById(0)
    var position = route.itinary[route.step]
    console.log('Sending position '+ toString_Position(position))
    return position;
}

function toString_Position(position){
    return "("+position.latitude+","+position.longitude+")"
}
