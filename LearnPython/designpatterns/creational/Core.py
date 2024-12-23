
class BaseTest:
    __name = 'undefined'

    def __init__(self):
        pass

    def __init__(self, name:str):
        self.__name = name
        print("start running :", self.__name)

    def __del__(self):
        print("end running :", self.__name, '\n\n')