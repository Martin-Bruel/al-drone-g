const { Position } = require('../model/Position')

let position = new Position(0,0);

function getCurrentPosition(){
    return position
}

function setCurrentPosition(newPosition){
    position = newPosition;
}

module.exports = {
    getCurrentPosition,
    setCurrentPosition
}
