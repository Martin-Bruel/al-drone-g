import requests
from behave import *
from time import sleep
from launchDelivery import *

class DeliveryStatusCodeClass:
    def __init__(self):
        self.PENDING=0;
        self.DELIVERED=1;
        self.LOST=2;
        self.BEING_DELIVERED=3;

DeliveryStatusCode = DeliveryStatusCodeClass()

TRUCK_PORT= "8085"
DOCKER = True
LIST_DRONES = [8087, 8088, 8089] if DOCKER else [8087]

def getDeliveryStatus(id):
    url = 'http://localhost:' + TRUCK_PORT + '/truck-api/delivery/' + id+'/status'
    requests.post(url)

@then("les colis sont en livraison")
def step_impl(context):
    for allocation in allocations:
        for deliveryId in allocation['deliveryIds']:
            status = getDeliveryStatus(deliveryId)
            assert(DeliveryStatusCode.BEING_DELIVERED==status)

@when("les drones arrivent à leur addresse de livraison")
def step_impl(context):
    sleep(2)

@when("le drone follower perd la connexion avec le camion")
def step_impl(context):
    disconnectOnlyDronesFollowers()
    sleep(5)

@then("le drone leader envoi au camion le status de l'autre drone")
def step_impl(context):
    for allocation in allocations:
        for deliveryId in allocation['deliveryIds']:
            status = getDeliveryStatus(deliveryId)
            assert(DeliveryStatusCode.DELIVERED==status)
    reconnectDrone()
