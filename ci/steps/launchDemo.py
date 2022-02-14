import requests
from behave import *
from time import sleep

LIST_DRONES = [8087, 8088, 8089, 8090, 8091]

def initTest(number):
    try:
        url = 'http://localhost:8085/package/add'
        requests.post(url, json = {'latitude':43.64316, 'longitude':7.09493})
        requests.post(url, json = {'latitude':43.64009, 'longitude':7.10250})
        requests.post(url, json = {'latitude':43.64927, 'longitude':7.04393})
        requests.post(url, json = {'latitude':43.65045, 'longitude':7.05411})
        requests.post(url, json = {'latitude':43.64119, 'longitude':7.04112})
        sleep(15)
    except requests.exceptions.ConnectionError:
        sleep(5)
        initTest(number)


@given("un conducteur, 1 flotte de 5 drones, {number:n} colis")
def step_impl(context, number):
    initTest(number)

# @then("le camion perd la connexion avec le drone")
# def step_impl(context):
#     disconnectDrones()
#     sleep(1)
#
# @then("la drone n'est pas localisable")
# def step_impl(context):
#     res = getDroneConnexionStatus()
#     if DOCKER:
#         assert(False in res)
#     else: assert(not res)


# @when("le camion retrouve la connexion avec le drone")
# def step_impl(context):
#     reconnectDrone()
#     sleep(2)
