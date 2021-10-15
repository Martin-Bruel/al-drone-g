const { Router } = require('express')
const router = new Router()

router.post('/delivery/start', function (req, res) {
    let steps=req.body.steps;
    Itinary=steps;
    console.log('Drone reiceve flight plan : ' + JSON.stringify(steps))
    res.status(200).json('drone started')
})
router.get('/position', function (req, res) {
    console.log('Sending position')
    res.status(200).json({
        latitude:"00000",
        longitude:"111111"
    })
})
module.exports = router