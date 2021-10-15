const { Router } = require('express')
var cron = require('node-cron');
const router = new Router()
const { Route } = require('../models')
const TruckService= require('../services/truck.service')
const {getConfiguration}= require('../config')
var droneId=getConfiguration().info.id;
var task ={}
router.post('/delivery/start', async function (req, res) {
    let steps=req.body.steps;
    Route.clean()
    Route.create({id:0,step:0,itinary:steps})
    console.log('Drone reiceve flight plan : ' + JSON.stringify(steps))
    TruckService.sendDeliveryState(droneId,1);
    task = cron.schedule('* * * * *', async () =>  {
        var route =Route.getById(0)
        var step = route.step;
        console.log("I'm in position "+route.itinary[step]);
        if(step+1==route.itinary.length){
            await TruckService.sendDeliveryState(droneId,3);
            Route.clean();
            task.destroy();       
        }
        route.step=step+=1;
        Route.update(route.id,route)
    });  
    res.status(200).json({started:true,message:'Drone started'})
})
router.get('/position', function (req, res) {
    var route =Route.getById(0)
    var position = route.itinary[route.step]
    console.log('Sending position '+ position)
    res.status(200).json(position)
})
module.exports = router