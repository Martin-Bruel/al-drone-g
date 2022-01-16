const { setCurrentPosition } = require('../interfaces/PositionModifier');
const { getCurrentPosition } = require('../interfaces/PositionProvider');
const { getConfiguration } = require('../configuration/config');
const { Position } = require('../model/Position')

async function flyTo(position){

    return new Promise((res, rej) => {
        let speed = getConfiguration().info.speed;
        let startPos = getCurrentPosition();
        let targetPos = position;
        let currentPos = startPos;

        let duringTime = startPos.distance(targetPos) / speed;
        let startTime = new Date().getTime() / 1000;
        let endTime = startTime + duringTime;

        console.log('Drone fly from ' + startPos + ' to ' + targetPos + ' in ' + Math.round(duringTime) + ' seconds');

        let id = setInterval(() => {
            currentPos = calculPositionProjection(startPos, targetPos, startTime, endTime, speed);
            console.log(currentPos.toString());
            setCurrentPosition(currentPos);
            if(currentPos.equals(targetPos)){
                clearInterval(id);
                res();
            }
        }, 500)
    })
}

function calculPositionProjection(startPosition, targetPosition, startTime, endTime, speed) {

    T = new Date().getTime() / 1000;
    if(endTime - T < 0) T = endTime;
    
    var dist = startPosition.distance(targetPosition);

    Xa = startPosition.lat
    Ya = startPosition.lon

    Xc = targetPosition.lat
    Yc = targetPosition.lon

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