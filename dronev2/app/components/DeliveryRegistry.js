const { Drone } = require('../model/Drone');
const { Fleet } = require('../model/Fleet');
const { Registry } = require('../registries/registry');

let notificationRegistry = new Registry('notification');

function findAll(){
    return notificationRegistry.get();
}

function save(deliveryStatusCode,deliveryId,droneId){
    notificationRegistry.create({deliveryStatusCode:deliveryStatusCode,deliveryId:deliveryId,droneId:droneId})
}

function reset(){
    notificationRegistry.clean();
}

module.exports = {
    findAll,
    save,
    reset
}