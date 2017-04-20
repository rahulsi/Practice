package com.test

import org.w3c.dom.ranges.Range

object HelloScala {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet


  def lastElement(list: List[Any]): Any = //TODO: brackets should succeed =
    {
      list match {
        case Nil => None
        case x :: Nil => x
        case x :: xs => lastElement(xs)
      }
    }

  lastElement(List(1, 2, 3, 4, 5))
  lastElement(List(1))
  lastElement(List())
  lastElement(Nil)
  lastElement(List('a', 'b', 'c', 'd'))
  lastElement(List("abc", "def", "ghi"))

  def penultimateElement(list: List[Any]): Any =
    {
      list match {
        case Nil => None
        case x :: Nil => None
        case x :: y :: Nil => x
        case x :: xs => penUltimateElement(xs)  //TODO : penultimate
      }
    }
  penUltimateElement(List(1, 2, 3, 4, 5))
  penUltimateElement(List(1))
  penUltimateElement(List(1, 2))
  penUltimateElement(List())
  penUltimateElement(Nil)
  penUltimateElement(List('a', 'b', 'c', 'd'))
  penUltimateElement(List("abc", "def", "ghi"))

  def reverse[T](list: List[T]): List[T] =
    {
      list match {
        case Nil => Nil
        case x :: xs => reverse(xs) ++ List(x)
      }
    }
  reverse(List(1, 2, 3, 4, 5))
  reverse(List(1))
  reverse(List(1, 2))
  reverse(List())
  reverse(Nil)
  reverse(List('a', 'b', 'c', 'd'))
  reverse(List("abc", "def", "ghi"))


  def compress[T](list: List[T]): List[T] =
    {
      def compress[T](x: T, xs: List[T]): List[T] =
        {
          xs match {
            case Nil => Nil;
            case xx :: Nil => List(xx)
            case xx :: xxs => if (x == xx) compress(xx, xxs) else x :: compress(xx, xxs)
          }
        }
      list match {
        case Nil => Nil;
        case x :: xs => compress(x, xs)
      }
    }

  compress(List(1, 1, 2, 2, 2, 3, 3, 1, 1))
  compress(List(1))
  compress(List())
  compress(List('a', 'a', 'b', 'c', 'd', 'd', 'e', 'e', 'e', 'e'))


  def expand[T](n: Int, list: List[T]): List[Any] =
    {
      def expandNum(n: Int, x: T): List[T] =
        {
          if (n == 1) List(x) else x :: expandNum(n - 1, x)  // TODO : check if with return None we can use List[T] in return type
        }
      list match {
        case Nil => Nil;
        case x :: xs => expandNum(n, x) ++ expand(n, xs)
      }

    }
  expand(3, List(1, 2, 3))
  expand(3, List())
  expand(3, List("rahul","singh"))


  def slice[T](low: Int, high: Int, list: List[T]): List[T] = {

    def sliceCur(cur: Int, low: Int, high: Int, list: List[T]): List[T] = {

      list match {
        case Nil => Nil
        case x :: xs => if (cur >= low && cur <= high) x :: sliceCur(cur + 1, low, high, xs)
        else  sliceCur(cur + 1, low, high, xs)
      }
    }
    sliceCur(0, low, high, list)
  }
	slice(2,5,List())                                                 // TODO: second parameter should be length
	slice(2,5,List(0,1,2,3,4,5,6,7,8))
	slice(0,8,List("0","1","2","3","4","5","6","7","8"))


  def listPrimesinRange(low: Int, high: Int): List[Int] = {

    val numlist = (2 to high).toList;

    def remove(x: Int, xs: List[Int]): List[Int] = {
      xs match {
        case Nil => Nil
        case xx :: xxs => if (xx % x == 0) remove(x, xxs) else xx :: remove(x, xxs)
      }
    }

    def removelist(list: List[Int]): List[Int] = {
      list match {
        case Nil => Nil
        case x :: xs => x :: removelist(remove(x, xs))
      }
    }
    removelist(numlist).filter { _ >= low }
  }                                               //> listPrimesinRange: (low: Int, high: Int)List[Int]

  listPrimesinRange(1, 100)                       //> res0: List[Int] = List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 
                                                  //| 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)

  def findSum(n: Int, three, five): Int = {       //TODO : re write this using functions
    def isMultiple3()
    //TODO : use filter
    def findSumRes(n: Int): Int = {
      n match {
        case 0 => 0
        case n => if (n % 3 == 0 || n % 5 == 0) n + findSumRes(n - 1) else findSumRes(n - 1)
      }
    }
    if (n > 0)
      findSumRes(n - 1)
    else
      0
  }                                               //> findSum: (n: Int)Int

  findSum(0)                                      //> res1: Int = 0
  findSum(10)                                     //> res2: Int = 23
  findSum(100)                                    //> res3: Int = 2318
}