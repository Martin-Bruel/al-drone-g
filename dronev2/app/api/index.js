const { Router } = require('express');
const router = new Router();
const DroneStarterController = require('../controllers/DroneStarterController');
const FleetInfoController = require('../controllers/FleetInfoController');
const DeliveryController = require('../controllers/DeliveryController')
const UtilsController = require('../controllers/UtilsController');

router.post('/delivery/start',DroneStarterController.deliveryStart);
router.post('/status/followers',DeliveryController.saveFollowerStatusCode);
router.post('/position/followers',FleetInfoController.receiveFollowerPosition);
router.post('/connection/stop',UtilsController.disconnection);
router.post('/connection/start',UtilsController.connection);
module.exports = router;