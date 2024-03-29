const { Router } = require('express');
const router = new Router();
const Controller = require('../controller');
const { Drone } = require('../model/Drone');
const { Position } = require('../model/Position');

router.post('/update/drone/position/:fromId/:toId', (req, res) => {
    let visibleDroneIds = Controller.calculInvisibleDrones(parseInt(req.params.fromId), parseInt(req.params.toId), new Position(req.body.latitude, req.body.longitude));
    res.send(visibleDroneIds);
});

router.post('/add/drone', (req, res) => {
    let drone = Controller.addDrone(new Drone(req.body.id, req.body.name, req.body.status, new Position(req.body.position.latitude, req.body.position.longitude), req.body.connectionInterface));
    res.send(drone);
})

router.post('/update/drone/status/:droneId', (req, res) => {
    let drone = Controller.updateStatus(parseInt(req.params.droneId), req.body.status);
    res.send(drone);
})

router.get('/info', (req,res) => {
    let info = Controller.getInfo();
    res.send(info);
})

module.exports = router;