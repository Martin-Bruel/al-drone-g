from behave import *
import requests
from time import sleep

DOCKER = True
LIST_DRONES = [8087, 8088, 8089] if DOCKER else [8084]

def getAllocations():
    url = 'http://localhost:8085/truck/allocation'
    response = requests.get(url)
    return response.json()

def getPackage(packageId):
    url = 'http://localhost:8085/packages/' + str(packageId)
    response = requests.get(url)
    return response.json()

def getDronePosition():
    if DOCKER :
        res = []
        for d in LIST_DRONES:
            url = 'http://localhost:' + str(LIST_DRONES[0]) + '/drone-api/position'
            response = requests.get(url)
            res.append(response.status_code)
        return res
    else:
        url = 'http://localhost:' + str(LIST_DRONES[0]) + '/drone-api/position'
        response = requests.get(url)
        return response


def launchDrone(droneId, deliveryIds):
    url = 'http://localhost:8085/start/drone/' + str(droneId) +'/package/' + str(deliveryIds)
    return requests.post(url)

def disconnectDrones():
    for k in LIST_DRONES:
        url = 'http://localhost:' + str(k) + '/drone-api/connection/stop'
        requests.post(url)

def reconnectDrone():
    for k in LIST_DRONES:
        url = 'http://localhost:' + str(k) + '/drone-api/connection/restart'
        requests.post(url)

def initTest(number):
    try:
        url = 'http://localhost:8085/package/add'
        for i in range(number):
            requests.post(url, json = {'latitude':5+number, 'longitude':5+number})
        sleep(15)
    except requests.exceptions.ConnectionError:
        sleep(5)
        initTest(number)


@given("un conducteur, 3 drones, {number:n} colis et sa tablette")
def step_impl(context, number):
    initTest(number)

allocations = []
@when("le conducteur demande les assignations")
def step_impl(context):
    global allocations
    allocations = getAllocations()

@when("le conducteur lance le drone avec son colis")
def step_impl(context):
    global res
    global allocations
    global deliveryId
    global droneId
    droneId = allocations[0]["droneId"]
    deliveryId = allocations[0]["deliveryIds"][0]
    res = launchDrone(droneId, deliveryId)
    sleep(5)



@then("le drone part effectuer sa livraison")
def step_impl(context):
    global res
    assert(res.status_code == 200)
    sleep(16)


@then("il y a {number:n} assignations")
def step_impl(context, number):
    global allocations
    if not DOCKER and len(LIST_DRONES)==1:
        assert(len(allocations)==number-2)
    else:
        assert(len(allocations)==number)


@then("le colis est livré")
def step_impl(context):
    global deliveryId
    package = getPackage(deliveryId)
    assert(package["deliveryStatus"] == 'DELIVERED')


@then("le drone se déconnecte du camion")
def step_impl(context):
    disconnectDrones()
    sleep(2)

@then("le drone n'est pas localisable")
def step_impl(context):
    res = getDronePosition()
    if DOCKER:
        assert(500 in res)
    else: assert(res.status_code == 500)


@when("le drone se reconnecte au camion")
def step_impl(context):
    reconnectDrone()
    sleep(2)

@then("le drone est localisable")
def step_impl(context):
    global droneId
    res = getDronePosition()
    if DOCKER:
        assert(not 500 in res)
    else: assert(res.status_code == 200)
