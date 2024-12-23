from abc import abstractmethod

from designpatterns.creational.Core import BaseTest


class Shape:
    @abstractmethod
    def draw(self):
        raise NotImplementedError

    @abstractmethod
    def clone(self):
        raise NotImplementedError


class Rectangle(Shape):
    __length : int = 0
    __width : int = 0
    def __init__(self, length: int, width: int):
        self.__length = length
        self.__width = width
    def draw(self):
        print("Rectangle length is {} and width is {}".format(self.__length, self.__width))
    def clone(self):
        return Rectangle(self.__length, self.__width)

class Circle(Shape):
    __radius : int = 0
    def __init__(self, radius: int):
        self.__radius = radius
    def clone(self):
        return Circle(self.__radius)
    def draw(self):
        print("Circle with radius {} ".format(self.__radius))

class ShapePrototypeFactory:
    def __init__(self, prototype: Shape):
        self.prototype = prototype
    def create(self):
        return self.prototype.clone()

class testPrototype(BaseTest):
    def __init__(self):
        super().__init__('testPrototype')

    def test1(self):
        prototype:Shape = Rectangle(10, 5)
        factory = ShapePrototypeFactory(prototype)
        s1 = factory.create()
        s1.draw()
        s2 = factory.create()
        s2.draw()




