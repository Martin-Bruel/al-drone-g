import requests
from behave import *
from time import sleep

DOCKER = True
LIST_DRONES = [8087, 8088, 8089] if DOCKER else [8087]

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
            url = 'http://localhost:' + str(d) + '/drone-api/position'
            response = requests.get(url)
            res.append(response.status_code)
        return res
    else:
        url = 'http://localhost:' + str(LIST_DRONES[0]) + '/drone-api/position'
        response = requests.get(url)
        return response


def launchDrone(droneId, deliveryIds):
    url = 'http://localhost:8085/start/drone/' + str(droneId)
    return requests.post(url, json=deliveryIds)

def launchFleet(dronesIds):
    url = 'http://localhost:8085/start/fleet/'
    return requests.post(url, json=dronesIds)

def disconnectDrones():
    for k in LIST_DRONES:
        url = 'http://localhost:' + str(k) + '/drone-api/connection/stop'
        requests.post(url)

def disconnectOnlyDronesFollowers():
    for k in LIST_DRONES:
        url = 'http://localhost:' + str(k) + '/drone-api/connection/stop'
        requests.post(url,json={'onlyFollowers':True})
        
def reconnectDrone():
    for k in LIST_DRONES:
        url = 'http://localhost:' + str(k) + '/drone-api/connection/start'
        requests.post(url)

def initTest(number):
    try:
        url = 'http://localhost:8085/package/add'
        for i in range(number):
            requests.post(url, json = {'latitude':43.637226+i*0.01, 'longitude':7.095738+i*0.01})
        sleep(15)
    except requests.exceptions.ConnectionError:
        sleep(5)
        initTest(number)

def initTest2(number):
    try:
        url = 'http://localhost:8085/package/add'
        for i in range(number):
            requests.post(url, json = {'latitude':43.607226, 'longitude':7.065738})
        sleep(15)
    except requests.exceptions.ConnectionError:
        sleep(5)
        initTest(number)


@given("un conducteur, 3 drones, {number:n} colis et sa tablette")
def step_impl(context, number):
    initTest(number)

@given("un conducteur, 1 flotte de 3 drones, {number:n} colis avec la même adresse et la tablette")
def step_impl(context, number):
    initTest2(number)

@given("un conducteur, 1 flotte de 3 drones, {number:n} colis et la tablette")
def step_impl(context, number):
    initTest(number)

def getAllAllocations():
    global fleetAllocations
    fleetAllocations = getAllocations()
    allocations = []
    for fleetAllocation in fleetAllocations:
        for alloc in fleetAllocation['allocations']:
            allocations.append(alloc)
    return allocations

allocations = []
@when("le conducteur demande les assignations")
def step_impl(context):
    global allocations
    allocations = getAllAllocations()

@when("le conducteur demande les assignations pour une flotte")
def step_impl(context):
    global allocations
    allocations = getAllocations()

@when("le conducteur lance le drone avec son colis")
def step_impl(context):
    global res, allocations, deliveryId, droneId
    droneId = allocations[0]["droneId"]
    deliveryId = allocations[0]["deliveryIds"][0]
    res = launchDrone(droneId, [deliveryId])
    sleep(5)

@when("le conducteur lance la flotte avec les colis")
def step_impl(context):
    global res, dronesIds
    dronesIds = list(map(lambda x: x["droneId"], [k["allocations"] for k in allocations][0]))
    res = launchFleet(dronesIds)
    sleep(5)

@then("le drone part effectuer sa livraison")
def step_impl(context):
    global res
    assert(res.status_code == 200)
    sleep(16)

@then("la flotte part effectuer sa livraison")
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
    sleep(10)

@then("il y a {number:n} assignations pour la flotte")
def step_impl(context, number):
    global allocations
    if not DOCKER and len(LIST_DRONES)==1:
        assert(len(allocations[0]['allocations'])==number-2)
    else:
        assert(len(allocations[0]['allocations'])==number)
    sleep(10)

@then("le colis est livré")
def step_impl(context):
    global deliveryId
    sleep(10)
    package = getPackage(deliveryId)
    assert(package["deliveryStatus"] == 'DELIVERED')

@then("les colis sont livrés")
def step_impl(context):
    global allocations
    sleep(10)
    for id in list(map(lambda x: x["deliveryIds"], [k["allocations"] for k in allocations][0])):
        print("\n")
        print("deliveryId =" +str(id[0]))
        package = getPackage(id[0])
        print("\n")
        print("package =" +str(package))
        print("\n")
        print("\n")
        assert(package["deliveryStatus"] == 'DELIVERED')

@then("le camion perd la connexion avec le drone")
def step_impl(context):
    disconnectDrones()
    sleep(1)

@then("le drone n'est pas localisable")
def step_impl(context):
    res = getDronePosition()
    if DOCKER:
        assert(500 in res)
    else: assert(res.status_code == 500)


@when("le camion retrouve la connexion avec le drone")
def step_impl(context):
    reconnectDrone()
    sleep(2)
