# Factore Method - has a factory class to create objects
from abc import abstractmethod

from designpatterns.creational.Core import BaseTest


class Vehicle:
    @abstractmethod
    def printVehicle(self):
        pass

class Car(Vehicle):
    def printVehicle(self):
        print('i am a car')

class Truck(Vehicle):
    def printVehicle(self):
        print('i am a truck')

class VehicleFactory:
    __vehicle_type = 0
    def __init__(self, vehicleType:int):
        self.__vehicle_type = vehicleType

    def geVehicle(self):
        if self.__vehicle_type == 1:
            return Car()
        elif self.__vehicle_type == 2:
            return Truck()
        else:
            print('invalid vehicle type')


class testFactory(BaseTest):
    def __init__(self):
        super().__init__('testFactory')

    def test1(self):

        factory:VehicleFactory = VehicleFactory(1)
        factory.geVehicle().printVehicle()

        factory = VehicleFactory(2)
        factory.geVehicle().printVehicle()