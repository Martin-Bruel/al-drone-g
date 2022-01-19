const PositionProvider = require('../interfaces/PositionProvider');
const { getConfiguration } = require('../configuration/config');
const TruckService = require('../services/TruckService')
const TimeUtils = require('../utils/TimeUtil')

async function startSendingPositions(lastPosition) {

    let id = setInterval(() => {
        let currentPosition = PositionProvider.getCurrentPosition();
        if(currentPosition.equals(lastPosition)){
            clearInterval(id);
        }

        let currentTime = TimeUtils.getCurrentTime();
        let idDrone = getConfiguration().info.id;

        TruckService.sendPositionDrone(idDrone, currentPosition, currentTime);

    }, 5000)
}


module.exports = {
    startSendingPositions
}