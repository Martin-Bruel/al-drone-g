from behave import *
import requests
from time import sleep

def getAllocations():
    url = 'http://localhost:8085/truck/allocation'
    response = requests.get(url)
    return response.json()
    

def launchDrone(droneId, deliveryIds):
    url = 'http://localhost:8085/start/drone/' + str(droneId) +'/package/' + str(deliveryIds)
    return requests.post(url)

def initTest(number):
    try:
        for i in range(number):
            url = 'http://localhost:8085/package/add'
            requests.post(url, json = {'latitude':number, 'longitude':number})
        sleep(15)
    except requests.exceptions.ConnectionError:
        sleep(5)
        initTest(number)


@given("Un conducteur, 3 drones, {number:n} colis et sa tablette")
def step_impl(context, number):
    initTest(number)

allocations = []
@when("le conducteur demande les assignations")
def step_impl(context):
    global allocations
    allocations = getAllocations()

@when("Le conducteur lance le drone avec son colis")
def step_impl(context):
    global res
    global allocations
    droneId = allocations[0]["droneId"]
    deliveryId = allocations[0]["deliveryIds"][0]
    res = launchDrone(droneId, deliveryId)
    sleep(5)



@then("Le drone part effectuer sa livraison")
def step_impl(context):
    global res
    assert(res.status_code == 200)
    sleep(15)


@then("Il y a {number:n} assignations")
def step_impl(context, number):
    global allocations
    print(allocations)
    assert(len(allocations)==number)
