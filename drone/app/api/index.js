const { Router } = require('express')
const router = new Router()
router.post('/delivery/start', function (req, res) {
    console.log('Drone reiceve flight plan : ' + JSON.stringify(req.body.steps))
    res.status(200).json('drone started')
})
module.exports = router