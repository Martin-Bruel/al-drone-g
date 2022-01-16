const { engineTaskTest } = require('./component/EngineTaskTest');
const { piloteTaskTest } = require('./component/PiloteTaskTest');

async function test(){
    //await engineTaskTest();
    await piloteTaskTest();
}

test();

