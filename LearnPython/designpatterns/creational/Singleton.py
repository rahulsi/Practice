#SingleTon
# creates  a single instance for each class
from designpatterns.creational.Core import BaseTest


class Singleton():
    __instance = None
    __count = 0
    def __new__(cls):
        if not cls.__instance:
            cls.__instance = super(Singleton, cls).__new__(cls)
        return cls.__instance

    def GetCount(self):
        return self.__count

    def SetCount(self, num):
        self.__count = num

class testSingleton(BaseTest):
    def __init__(self):
        super().__init__('testSingleton')

    def test1(self):

        # print(dir(Singleton))
        obj1 :Singleton = Singleton()
        obj1.SetCount(10)
        obj2 = Singleton()
        print('before', 'obj1', obj1.GetCount(),  'obj2', obj2.GetCount())

        obj2.SetCount(5)
        print( 'after', 'obj1', obj1.GetCount(),  'obj2', obj2.GetCount())