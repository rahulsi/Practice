from abc import abstractmethod

from designpatterns.creational.Core import BaseTest


class Computer:
    __ram:str = ''
    __cpu:str = ''
    __storage:str = ''

    def buildRam(self, ram:str) -> None:
        self.__ram=ram
    def buildCPU(self, cpu:str) -> None:
        self.__cpu= cpu
    def buildStorage(self, storage:str) -> None:
        self.__storage= storage
    def describeComputer(self):
        print(*[self.__ram,self.__cpu,self.__storage], sep=',')

class Builder:
    @abstractmethod
    def buildRam(self) -> None:
        raise NotImplementedError
    @abstractmethod
    def buildCPU(self) -> None:
        raise  NotImplementedError
    @abstractmethod
    def buildStorage(self) -> None:
        raise NotImplementedError

class GameComputerBuilder(Builder):
    __computer:Computer = Computer()
    def buildRam(self) -> None:
        self.__computer.buildRam("Gaming RAM")
    def buildCPU(self) -> None:
        self.__computer.buildCPU("Gaming CPU")
    def buildStorage(self) -> None:
        self.__computer.buildStorage("2TB")
    def getComputer(self)->Computer:
        return self.__computer

class Director:
    __builder : Builder = None
    def __init__(self, builder:Builder) -> None:
        self.__builder = builder
    def construct(self):
        self.__builder.buildRam()
        self.__builder.buildCPU()
        self.__builder.buildStorage()


class TestBuilder(BaseTest):
    def __init__(self):
        super().__init__('TestBuilder')

    def test1(self):
        builder = GameComputerBuilder()
        director = Director(builder)
        director.construct()
        computer = builder.getComputer()
        computer.describeComputer()

