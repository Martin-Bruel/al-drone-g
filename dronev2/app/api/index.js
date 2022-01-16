const { Router } = require('express');
const router = new Router();
const DroneStarterController = require('../controllers/DroneStarterController');

router.post('/delivery/start',DroneStarterController.deliveryStart);
module.exports = router;