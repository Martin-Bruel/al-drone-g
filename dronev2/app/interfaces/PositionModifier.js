const { setCurrentPosition } = require('../components/DroneLocator');
const { updatePositionDrone } = require('../components/FleetRegistry');
const { updatePositionFleet } = require('../components/FleetRegistry');

module.exports = {
    setCurrentPosition,
    updatePositionDrone,
    updatePositionFleet
}