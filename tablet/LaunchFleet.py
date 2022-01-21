import argparse
import requests

from Command import Command


class LaunchFleet(Command):

    def __init__(self):
        self._url = 'http://localhost:8085/start/fleet/'

    def description(self):
        return 'Launch specified fleet with given delivery'

    def execute(self):
        try:
            dronesId = input('> '+'Enter droneId for each drone of the fleet'+' : ')
            data = self.launchFleet(dronesId.split())
            print('> ','Starting fleet with drones :', ",".join(dronesId.split()))
        except requests.exceptions.RequestException:
            print('Can\'t create connection with : ' + self._url)

    def launchFleet(self, dronesId):
        response = requests.post(self._url, json=dronesId)
        return response

if __name__ == "__main__":
    LaunchFleet = LaunchFleet()
    parser = argparse.ArgumentParser(description=LaunchFleet.description())
    parser.add_argument('-d', '--dronesId', help='droneId for each drone of the fleet', required=True)
    args = parser.parse_args()
    res = LaunchFleet.launchFleet(args.droneId, args.deliveryIds)
    print(res)
