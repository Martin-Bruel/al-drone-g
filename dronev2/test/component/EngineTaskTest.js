const EngineActuator = require('../../app/interfaces/EngineActuator');
const PositionModifier = require('../../app/interfaces/PositionModifier');
const { Position } = require('../../app/model/Position');

async function engineTaskTest(){

    console.log('> EngineTask Test')
    PositionModifier.setCurrentPosition(new Position(4,8));
    await EngineActuator.flyTo(new Position(0,0));
}

module.exports = {
    engineTaskTest
}
