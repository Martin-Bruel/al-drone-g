const DroneModifier = require('../../app/interfaces/DroneModifier');
const DroneFinder = require('../../app/interfaces/DroneFinder');
const { Fleet } = require('../../app/model/Fleet');

async function fleetRegistryTest(){
    let fleet = new Fleet([
        {
            id:13,
            ip:'localhost:3000',
            position:{
                'lat':0,
                'lon':0
            }
        }
    ])
    DroneModifier.registerFleet(fleet, 13);
    console.log(DroneFinder.findLeader().position.toString());
}

module.exports = {
    fleetRegistryTest
}