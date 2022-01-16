const { flyTo } = require('../../app/interfaces/EngineActuator');
const { setCurrentPosition } = require('../../app/interfaces/PositionModifier');
const { Position } = require('../../app/model/Position');

async function engineTaskTest(){

    console.log('> EngineTask Test')
    setCurrentPosition(new Position(4,8));
    await flyTo(new Position(0,0));
}

module.exports = {
    engineTaskTest
}
