from abc import abstractmethod

from designpatterns.creational.Core import BaseTest
from designpatterns.creational.Factory import Vehicle, Car, Truck


class Specification:
    @abstractmethod
    def print(self):
        pass

class USSpecification(Specification):
    def print(self):
        print("US Specification")

class EUSpecification(Specification):
    def print(self):
        print("EU Specification")


#Abstract factory creates an object by accumulating various parts seperately
# e.g. here the Specs Type and Vehicle Type
class AbstractFactory(Specification, Vehicle):

    @abstractmethod
    def print_specs(self):
        pass

    @abstractmethod
    def print_vehicle(self):
        pass

class USFactory(AbstractFactory):
    def print_specs(self):
        USSpecification().print()
    def print_vehicle(self)->Vehicle:
        Car().printVehicle()

class EUFactory(AbstractFactory):
    def print_specs(self):
        EUSpecification().print()
    def print_vehicle(self):
        Truck().printVehicle()


class testAbstractFactory(BaseTest):
    def __init__(self):
        super().__init__('testAbstractFactory')

    def test1(self):

        factory = USFactory()
        factory.print_specs()
        factory.print_vehicle()

        factory = EUFactory()
        factory.print_specs()
        factory.print_vehicle()