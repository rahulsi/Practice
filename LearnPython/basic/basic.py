# print 'hello world'
#
# the_world_is_flat=True
#
# if the_world_is_flat:
#     print "Be careful, don't fall"
# else:
#     print "Jump As you like"
#
# a=2**3
# print a
#
# x=2+2
# print x
#
# y=x/2
# print y
#
# z=x/2.0
# print z
#
#
# sample_string='hello how are you'
#
# print sample_string
# print sample_string[6:]
# print sample_string[6:4]
# print sample_string[4:6]
# print sample_string[-7:]
# print len(sample_string)
# print '''
# hi
# how are
# you
# '''
#
# print '''
# hi \
# how are \
# you \
# '''
#
# prefix='Hi!!'
# sufix = 'Rahul'
#
#
# print prefix + sample_string+ '...' +sufix
# print prefix + sample_string+ '...' +sufix*2
# print (prefix + sample_string+ '...' +sufix)*2


## Lists Examples
# squares = [1, 4, 9, 16, 25]
# print squares
# squares_till_10 = squares + [36, 49, 64, 81, 100]
# print squares_till_10
#
# cubes = [1, 8, 27, 65, 125]  # something's wrong here
# print cubes
#
# cubes[3]=64
# print cubes
#
# cubes.append(216)
# cubes.append(7**3)
# print cubes
#
#
# letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g']
# print letters
# print letters[1:4]
# letters[1:4]=['B','C','D']
# print letters
#
# complex_data = [letters,cubes]
# print complex_data
# print complex_data[0]
# print complex_data[0][2]
# print complex_data[1]
# print complex_data[1][2]


# a,b=1,0
# while b<100:
#     print a
#     a,b=a+b,a
#
# a,b=1,0
# while b<100:
#     print a,
#     a,b=a+b,a
#
# x=int(raw_input("\nEnter a number :"))
#
# if x < 0 :
#     print 'Negetive'
# elif x==0 :
#     print 'Zero'
# else:
#     print 'Positive'

# words = ['cat', 'window', 'defenestrate']
# for w in words:
#     print w,  len(w)

## causes infinite loop
# for w in words:
#     print words
#     if len(w)>6:
#         words.insert(0,w)


# for w in words[:]:
#     if len(w)>6:
#         words.insert(0,w)
#     print words
#
#
# x = range(4,100,4)
# print x
#
# a = ['Mary', 'had', 'a', 'little', 'lamb']
# for i in range(len(a)):
#     print i, a[i]
#
#
# for n in range(2, 10):
#     for x in range(2, n/2):
#         if n % x == 0:
#             print n, 'equals', x, '*', n / x
#             break
#     else:
#         print n, 'is a prime number'
#
# print
#
# for num in range(2, 10):
#     if num % 2 == 0:
#         print "Found an even number", num
#         continue
#     print "Found a number", num

# while True:
#     pass

# class MyEmptyClass:
#     pass
#
# def initlog(*args):
#     pass
#
#
# def fib(n):
#     a,b=1,0
#     result = []
#     for i in range(1,n+1):
#         result.append(a)
#         a,b=a+b,a
#     return result
#
# y=fib(10)
# print y
#
# f=fib
#
# x=f(5)
# print x


# i = 5
#
# def f(arg=i):
#     print arg
#
# i = 6
# f()
#
#
# def f(a, L=[]):
#     L.append(a)
#     return L
#
# print f(1)
# print f(2)
# print f(3)
#
#
#
#
# def f(a, L=None):
#     if L is None:
#         L =  []
#     L.append(a)
#     return L
#
# print f(1)
# print f(2)
# print f(3)
#
#
# def parrot(voltage, state='a stiff', action='voom', type='Norwegian Blue'):
#     print "-- This parrot wouldn't", action,
#     print "if you put", voltage, "volts through it."
#     print "-- Lovely plumage, the", type
#     print "-- It's", state, "!"
#
# parrot(1000)  # 1 positional argument
# parrot(voltage=1000)  # 1 keyword argument
# parrot(voltage=1000000, action='VOOOOOM')  # 2 keyword arguments
# parrot(action='VOOOOOM', voltage=1000000)  # 2 keyword arguments
# parrot('a million', 'bereft of life', 'jump')  # 3 positional arguments
# parrot('a thousand', state='pushing up the daisies')  # 1 positional, 1 keyword
#
# #parrot()                     # required argument missing
# #parrot(voltage=5.0, 'dead')  # non-keyword argument after a keyword argument
# #parrot(110, voltage=220)     # duplicate value for the same argument
# #parrot(actor='John Cleese')  # unknown keyword argument
#
import os
print os.getcwd()
os.chdir("/tmp")
print os.getcwd()