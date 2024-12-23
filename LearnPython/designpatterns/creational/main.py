from designpatterns.creational.AbstractFactory import testAbstractFactory
from designpatterns.creational.Builder import TestBuilder
from designpatterns.creational.Factory import testFactory
from designpatterns.creational.Prototype import testPrototype
from designpatterns.creational.Singleton import testSingleton


def main():
    print("Start Main")

    testSingleton().test1()
    testFactory().test1()
    testAbstractFactory().test1()
    testPrototype().test1()
    TestBuilder().test1()
    print('End Main')

if __name__ == "__main__":
    main()