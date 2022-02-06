import argparse
import requests

from Command import Command


class RegisterPackages(Command):

    def __init__(self):
        self._url = 'http://localhost:8085/package/addAll'

    def description(self):
        return 'Register packages for tests'

    def execute(self):
        try:
            packages = [
                {
                    "latitude":43.587817, 
                    "longitude":7.072201
                },
                {
                    "latitude":43.622352,
                    "longitude":7.060880
                },
                {
                    "latitude":43.622224,
                    "longitude":7.056913
                }
            ]

            self.registerPackages(packages)
            print('> Register done.')
        except requests.exceptions.RequestException:
            print('Can\'t create connection with : ' + self._url)

    def registerPackages(self, packages):
        response = requests.post(self._url, json=packages)
        return response
