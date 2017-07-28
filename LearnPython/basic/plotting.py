from cProfile import label

import matplotlib.pyplot as plt
import math
import numpy as np
import random
from random import randint
from sklearn import svm
from sklearn import linear_model

def f(x): 
  return (x-0.02)*(x-0.05)+5

def df(x):
  return 2*x-0.07

def gradient_descent(curx,alpha,precision,iterations):
  i=0
  output=list()
  slopes=list()
  slope = df(curx)
  output.append(curx)
  slopes.append(slope)
  while i<iterations:
    prevx=curx
    slope = df(prevx)
    curx+=-1*alpha*slope
    output.append(curx)
    slopes.append(slope)
    step_size=abs(curx-prevx)
    if step_size<precision:
      break
    i+=1
  return (output,slopes)

def test_gradient_descent():
    curx=-10
    alpha=0.0001
    precision=0.000001
    iterations=10000
    (min_at,slopes)=gradient_descent(curx,alpha,precision,iterations)
    print min_at
    print slopes
    print len(min_at)
    print len(slopes)
    print "Min Occurs at=",min_at[-1]
    print "Min func val=",f(min_at[-1])

    x = np.linspace(min(min_at),max(min_at),len(min_at))
    fx= map(lambda i: f(i), x)
    y = min_at
    plt.figure(1)
    plt.plot(x,min_at,'g')
    plt.plot(x,slopes,'b')

    plt.figure(2)
    plt.plot(x,fx,'r')
    plt.show()

def generate_linear_seperable_data(f1, lowx, highx, count, maxdeviation, positive, impurities):
    output=[]
    while count>0:
        x=randint(lowx,highx)
        y=f1(x)
        if randint(0,100)>impurities and positive:
            y+=randint(0,maxdeviation)
        elif randint(0,100)>impurities and not positive:
            y-=randint(0,maxdeviation)
        elif positive:
            y -= randint(0, maxdeviation)
        elif not positive:
            y += randint(0, maxdeviation)
        output.append((x,y,int(positive)))
        count-=1
    return output

def test_generate_linear_seperable_data():

    random.seed(1001)
    f1 = lambda x: (x-10)*(x-95)*(x-90)
    lowx = 0
    highx = 100
    count = 50
    maxdeviation = int(0.10 * f1(highx))
    positive = True
    impurities = 10

    x = np.linspace(lowx,highx,100)
    # print x
    y = map(lambda i:f1(i),x )
    plt.plot(x,y,'y')

    avg_x = sum(y) / len(y)
    y_avg = map(lambda a: avg_x, x)
    plt.plot(x, y_avg, 'y')

    # output = generate_linear_seperable_data(f1, lowx, highx, count, maxdeviation, positive, impurities)
    # out_x = map(lambda ((a, b, c)): a, output)
    # out_y = map(lambda ((a, b, c)): b, output)
    # plt.plot(out_x,out_y,'b^')

    output1 = generate_linear_seperable_data(f1, lowx, highx, count, maxdeviation, not positive, impurities)
    out_x1 = map(lambda ((a, b, c)): a, output1)
    # print out_x1
    out_y1 = map(lambda ((a, b, c)): b, output1)
    plt.plot(out_x1, out_y1, 'y^')

    clf = svm.SVR(kernel="rbf",degree=4,gamma=0.01,C=25000.0,verbose=True,max_iter=1500)
    clf.fit(map(lambda (a,b,c):[a],output1),map(lambda (a,b,c):b,output1))
    y_predict = map(lambda a:clf.predict(a),x)
    plt.plot(x, y_predict, 'b')

    clf = linear_model.LinearRegression()
    clf.fit(map(lambda (a, b, c): [a], output1), map(lambda (a, b, c): b, output1))
    y_predict = map(lambda a: clf.predict(a), x)
    plt.plot(x, y_predict, 'c')

    plt.show()

def main():
    test_generate_linear_seperable_data()




if __name__=="__main__":
    main()
