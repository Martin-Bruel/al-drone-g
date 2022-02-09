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
                    "latitude":43.572720, 
                    "longitude":7.114953
                },
                {
                    "latitude":43.573556,
                    "longitude":7.123023
                }
            ]

            self.registerPackages(packages)
            print('> Register done.')
        except requests.exceptions.RequestException:
            print('Can\'t create connection with : ' + self._url)

    def registerPackages(self, packages):
        response = requests.post(self._url, json=packages)
        return response
