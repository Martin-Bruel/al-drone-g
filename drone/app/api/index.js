const { Router } = require('express')
const TimeController= require('../controllers/time.controller')
const router = new Router()
router.get('/', TimeController.getTime)
router.post('/tick', TimeController.update)
module.exports = router