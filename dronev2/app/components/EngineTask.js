const PositionModifier = require('../interfaces/PositionModifier');
const PositionProvider = require('../interfaces/PositionProvider');
const { getConfiguration } = require('../configuration/config');
const TimeUtil = require('../utils/TimeUtil')
const { Position } = require('../model/Position')

async function flyTo(position){

    return new Promise((res, rej) => {
        let speed = getConfiguration().info.speed;
        let startPos = PositionProvider.getCurrentPosition();
        let targetPos = position;
        let currentPos = startPos;

        let duringTime = startPos.distance(targetPos) / speed;
        let startTime = TimeUtil.getCurrentTime();
        let endTime = startTime + duringTime;

        console.log('Drone flying from ' + startPos.toString() + ' to ' + targetPos.toString() + ' in ' + Math.round(duringTime) + ' seconds');

        let id = setInterval(() => {
            currentPos = calculPositionProjection(startPos, targetPos, startTime, endTime, speed);
            PositionModifier.setCurrentPosition(currentPos);
            PositionModifier.updatePositionDrone(getConfiguration().info.id,currentPos,TimeUtil.getCurrentTime())
            if(currentPos.equals(targetPos)){
                clearInterval(id);
                res();
            }
        }, 25)
    })
}

function calculPositionProjection(startPosition, targetPosition, startTime, endTime, speed) {

    T = TimeUtil.getCurrentTime();
    if(endTime - T < 0) T = endTime;
    
    var dist = startPosition.distance(targetPosition);

    Xa = startPosition.latitude
    Ya = startPosition.longitude

    Xc = targetPosition.latitude
    Yc = targetPosition.longitude

    Tstart = startTime
    Tend = dist / speed + Tstart

    Xv = (speed / dist) * (Xc - Xa)
    Yv = (speed / dist) * (Yc - Ya)

    Xb = Xv * (T - Tstart) + Xa
    Yb = Yv * (T - Tstart) + Ya

    return new Position(Xb, Yb);
}

module.exports = {
    flyTo
}