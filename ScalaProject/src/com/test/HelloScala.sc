package com.test

import org.w3c.dom.ranges.Range

object HelloScala {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  /*
  def lastElement[T](list: List[T]): Option[T] = {
    list match {
      case Nil => None
      case x :: Nil => Some(x)
      case x :: xs => lastElement(xs)
    }
  }

  lastElement(List(1, 2, 3, 4, 5))
  lastElement(List(1))
  lastElement(List())
  lastElement(Nil)
  lastElement(List('a', 'b', 'c', 'd'))
  lastElement(List("abc", "def", "ghi"))

  def penUltimateElement(list: List[Any]): Any =
    {
      list match {
        case Nil => None
        case x :: Nil => None
        case x :: y :: Nil => x
        case x :: xs => penUltimateElement(xs)
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

  //fold
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

  def expand[T](n: Int, list: List[T]): List[T] =
    {
      def expandNum(n: Int, x: T): List[T] =
        {
          if (n == 1) List(x) else x :: expandNum(n - 1, x)
        }
      list match {
        case Nil => Nil;
        case x :: xs => expandNum(n, x) ++ expand(n, xs)
      }

    }
  expand(3, List(1, 2, 3))
  expand(3, List())
  expand(3, List("rahul", "singh"))

  //we can send less elements
  def sliceN[T](low: Int, size: Int, list: List[T]): List[T] = {

    def take(list: List[T], size: Int): List[T] =
      {
        list match {
          case Nil => Nil
          case x :: xs => {
            if (size == 0) Nil
            else if (size == 1) List(x)
            else if (size > 1) { val rest = take(xs, size - 1); if (rest == Nil) Nil else x :: rest } else Nil
          }
        }
      }
    def sliceCur(cur: Int, low: Int, size: Int, list: List[T]): List[T] = {

      list match {
        case Nil => Nil
        case x :: xs => if (cur == low) { val rest = take(xs, size - 1); if (rest == Nil || rest.size == 0) Nil else x :: rest }
        else sliceCur(cur + 1, low, size, xs)
      }
    }
    sliceCur(0, low, size, list)
  }

  sliceN(2, 5, List())
  sliceN(2, 5, List(0, 1, 2, 3, 4, 5, 6, 7, 8))
  sliceN(0, 2, List("0", "1", "2", "3", "4", "5", "6", "7", "8"))
  sliceN(0, 3, List("0", "1", "2", "3", "4", "5", "6", "7", "8"))
  sliceN(1, 5, List("0", "1", "2", "3", "4", "5", "6", "7", "8"))

  def slice[T](low: Int, high: Int, list: List[T]): List[T] = {

    def sliceCur(cur: Int, low: Int, high: Int, list: List[T]): List[T] = {

      list match {
        case Nil => Nil
        case x :: xs => if (cur >= low && cur <= high) x :: sliceCur(cur + 1, low, high, xs)
        else sliceCur(cur + 1, low, high, xs)
      }
    }
    sliceCur(0, low, high, list)
  }

  slice(2, 5, List())
  slice(2, 5, List(0, 1, 2, 3, 4, 5, 6, 7, 8))
  slice(0, 8, List("0", "1", "2", "3", "4", "5", "6", "7", "8"))

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
  }

  listPrimesinRange(1, 100)

  def findSum(n: Int): Int = {
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
  }

  findSum(0)
  findSum(10)
  findSum(100)

  def findSumFunc(n: Int, fac1: Int, fac2: Int): Int = {

    def isMultiple(x: Int, fac: Int): Boolean = {
      if (x % fac == 0) true else false
    }

    val numlist = (0 to n - 1).toList;
    println(numlist)
    numlist.filter { x => isMultiple(x, fac1) || isMultiple(x, fac2) }.sum
  }

  findSumFunc(0, 3, 5)
  findSumFunc(10, 3, 5)
  findSumFunc(100, 3, 5)

	

  def CountWays(sizex: Int, sizey: Int): Int = {

    def count(x: Int, y: Int): Int = {

      if (x == sizex && y == sizey) 1
      else if (x == sizex) count(x, y + 1)
      else if (y == sizey) count(x + 1, y)
      else count(x + 1, y) + count(x, y + 1)
    }
    if(sizex>0 && sizey>0) count(0,0)
    else 0
  }
	
		CountWays(4, 4);
*/

}