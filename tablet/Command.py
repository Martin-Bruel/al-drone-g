from abc import ABCMeta, abstractmethod

class Command(metaclass=ABCMeta):

    @staticmethod
    @abstractmethod
    def execute():
        ...
        
    @staticmethod
    @abstractmethod
    def description():
        ...