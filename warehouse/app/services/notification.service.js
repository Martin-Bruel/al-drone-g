async function receivedNotifications(notifications){
    for(indexNotification in notifications){
        console.log(notifications[indexNotification]);
    }
}

module.exports = {
    receivedNotifications
}