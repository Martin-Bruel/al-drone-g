var cron = require('node-cron');
const { Route } = require('../models')
const TruckService= require('../services/truck.service')
const {getConfiguration}=require('../config')
const droneId= getConfiguration().info.id;
let task=[]

exports.startDelivery= async function(itinary){
    Route.clean()
    Route.create({id:0,step:0,itinary:itinary})
    console.log('Drone reiceve flight plan : ' + JSON.stringify(itinary))
    TruckService.sendDeliveryState(droneId,1);
    startTask() 
}

function startTask(){
    console.log("Starting delivery")
    task=cron.schedule('*/5 * * * * *', async () =>  {
        var route =Route.getById(0)
        var step = route.step;
        var itinary=route.itinary;
        console.log("I'm in position "+toString_Position(itinary[step]));
        if(step+1==itinary.length){
            await TruckService.sendDeliveryState(droneId,3);
            Route.clean();
            console.log("Arriving to truck");
            task.destroy();       
        }
        route.step=step+=1;
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
    return "("+position.Latitude+","+position.longitude+")"
}