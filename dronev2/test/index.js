const { engineTaskTest } = require('./component/EngineTaskTest');
const { piloteTaskTest } = require('./component/PiloteTaskTest');
const { fleetRegistryTest } = require('./component/FleetRegistryTest');

async function test(){
    //await engineTaskTest();
    //await piloteTaskTest();
    await fleetRegistryTest();
}

test();

