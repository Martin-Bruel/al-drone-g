const { Router } = require('express');
const router = new Router();
const DroneStarterController = require('../controllers/DroneStarterController');
const FleetInfoController = require('../controllers/FleetInfoController');

router.post('/delivery/start',DroneStarterController.deliveryStart);
router.post('/followers/position',FleetInfoController.getFollowerPosition);
module.exports = router;