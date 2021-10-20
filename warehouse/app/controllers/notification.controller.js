const notificationService = require('../services/notification.service');

async function receivedNotifications(req, res) {
    try {
        let bodyNotif = req.body;
        console.log("ce que contient le body : "+bodyNotif);
        notificationService.receivedNotifications(bodyNotif);
        res.status(200).json('notification received')
    } catch (error) {
        console.log(error)
        res.status(500)
    }
}

module.exports = {
    receivedNotifications
}