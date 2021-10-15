const { Router } = require('express')
const router = new Router()

router.post('/notification', function (req, res) {
    console.log('warehouse received a notification');
    let message=req.body.description;
    console.log('message received : ' + JSON.stringify(message))
    res.status(200).json('notification received')
})
module.exports = router