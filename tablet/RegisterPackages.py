import argparse
import requests

from Command import Command


class RegisterPackages(Command):

    def __init__(self):
        self._url = 'http://localhost:8085/package/addAll'

    def description(self):
        return 'Register packages for tests'

    def getPackages2fleet(self):
        packages = [{"latitude":43.546604, "longitude":7.128822},
                    {"latitude":43.544773,"longitude": 7.131650},
                    {"latitude":43.587680, "longitude":7.071836},
                    {"latitude":43.587507, "longitude":7.072437}]
        return packages



    def getPackage3chainedfleet(self):
        packages = [{"latitude":43.637538, "longitude":6.970750}, #beta haut 1
                    {"latitude":43.617915,"longitude": 6.982132}, #omicron milieu 2
                    {"latitude":43.607434, "longitude":6.968958}] #gamma bas 3
        return packages

    def execute(self):
        try:
            packages = self.getPackage3chainedfleet()
            

            self.registerPackages(packages)
            print('> Register done.')
        except requests.exceptions.RequestException:
            print('Can\'t create connection with : ' + self._url)

    def registerPackages(self, packages):
        response = requests.post(self._url, json=packages)
        return response

    