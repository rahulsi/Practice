package com.test

import org.w3c.dom.ranges.Range

object HelloScala {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def lastElement[T](list: List[T]): Option[T] = {
    list match {
      case Nil => None
      case x :: Nil => Some(x)
      case x :: xs => lastElement(xs)
    }
  }                                               //> lastElement: [T](list: List[T])Option[T]

  lastElement(List(1, 2, 3, 4, 5))                //> res0: Option[Int] = Some(5)
  lastElement(List(1))                            //> res1: Option[Int] = Some(1)
  lastElement(List())                             //> res2: Option[Nothing] = None
  lastElement(Nil)                                //> res3: Option[Nothing] = None
  lastElement(List('a', 'b', 'c', 'd'))           //> res4: Option[Char] = Some(d)
  lastElement(List("abc", "def", "ghi"))          //> res5: Option[String] = Some(ghi)

  def penUltimateElement(list: List[Any]): Any =
    {
      list match {
        case Nil => None
        case x :: Nil => None
        case x :: y :: Nil => x
        case x :: xs => penUltimateElement(xs)
      }
    }                                             //> penUltimateElement: (list: List[Any])Any
  penUltimateElement(List(1, 2, 3, 4, 5))         //> res6: Any = 4
  penUltimateElement(List(1))                     //> res7: Any = None
  penUltimateElement(List(1, 2))                  //> res8: Any = 1
  penUltimateElement(List())                      //> res9: Any = None
  penUltimateElement(Nil)                         //> res10: Any = None
  penUltimateElement(List('a', 'b', 'c', 'd'))    //> res11: Any = c
  penUltimateElement(List("abc", "def", "ghi"))   //> res12: Any = def

  def reverse[T](list: List[T]): List[T] =
    {
      list match {
        case Nil => Nil
        case x :: xs => reverse(xs) ++ List(x)
      }
    }                                             //> reverse: [T](list: List[T])List[T]
  reverse(List(1, 2, 3, 4, 5))                    //> res13: List[Int] = List(5, 4, 3, 2, 1)
  reverse(List(1))                                //> res14: List[Int] = List(1)
  reverse(List(1, 2))                             //> res15: List[Int] = List(2, 1)
  reverse(List())                                 //> res16: List[Nothing] = List()
  reverse(Nil)                                    //> res17: List[Nothing] = List()
  reverse(List('a', 'b', 'c', 'd'))               //> res18: List[Char] = List(d, c, b, a)
  reverse(List("abc", "def", "ghi"))              //> res19: List[String] = List(ghi, def, abc)

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
    }                                             //> compress: [T](list: List[T])List[T]

  compress(List(1, 1, 2, 2, 2, 3, 3, 1, 1))       //> res20: List[Int] = List(1, 2, 3, 1)
  compress(List(1))                               //> res21: List[Int] = List()
  compress(List())                                //> res22: List[Nothing] = List()
  compress(List('a', 'a', 'b', 'c', 'd', 'd', 'e', 'e', 'e', 'e'))
                                                  //> res23: List[Char] = List(a, b, c, d, e)

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

    }                                             //> expand: [T](n: Int, list: List[T])List[T]
  expand(3, List(1, 2, 3))                        //> res24: List[Int] = List(1, 1, 1, 2, 2, 2, 3, 3, 3)
  expand(3, List())                               //> res25: List[Nothing] = List()
  expand(3, List("rahul", "singh"))               //> res26: List[String] = List(rahul, rahul, rahul, singh, singh, singh)

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
  }                                               //> sliceN: [T](low: Int, size: Int, list: List[T])List[T]

  sliceN(2, 5, List())                            //> res27: List[Nothing] = List()
  sliceN(2, 5, List(0, 1, 2, 3, 4, 5, 6, 7, 8))   //> res28: List[Int] = List(2, 3, 4, 5, 6)
  sliceN(0, 2, List("0", "1", "2", "3", "4", "5", "6", "7", "8"))
                                                  //> res29: List[String] = List(0, 1)
  sliceN(0, 3, List("0", "1", "2", "3", "4", "5", "6", "7", "8"))
                                                  //> res30: List[String] = List(0, 1, 2)
  sliceN(1, 5, List("0", "1", "2", "3", "4", "5", "6", "7", "8"))
                                                  //> res31: List[String] = List(1, 2, 3, 4, 5)

  def slice[T](low: Int, high: Int, list: List[T]): List[T] = {

    def sliceCur(cur: Int, low: Int, high: Int, list: List[T]): List[T] = {

      list match {
        case Nil => Nil
        case x :: xs => if (cur >= low && cur <= high) x :: sliceCur(cur + 1, low, high, xs)
        else sliceCur(cur + 1, low, high, xs)
      }
    }
    sliceCur(0, low, high, list)
  }                                               //> slice: [T](low: Int, high: Int, list: List[T])List[T]

  slice(2, 5, List())                             //> res32: List[Nothing] = List()
  slice(2, 5, List(0, 1, 2, 3, 4, 5, 6, 7, 8))    //> res33: List[Int] = List(2, 3, 4, 5)
  slice(0, 8, List("0", "1", "2", "3", "4", "5", "6", "7", "8"))
                                                  //> res34: List[String] = List(0, 1, 2, 3, 4, 5, 6, 7, 8)

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

  listPrimesinRange(1, 100)                       //> res35: List[Int] = List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43,
                                                  //|  47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)

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
  }                                               //> findSum: (n: Int)Int

  findSum(0)                                      //> res36: Int = 0
  findSum(10)                                     //> res37: Int = 23
  findSum(100)                                    //> res38: Int = 2318

  def findSumFunc(n: Int, fac1: Int, fac2: Int): Int = {

    def isMultiple(x: Int, fac: Int): Boolean = {
      if (x % fac == 0) true else false
    }

    val numlist = (0 to n - 1).toList;
    println(numlist)
    numlist.filter { x => isMultiple(x, fac1) || isMultiple(x, fac2) }.sum
  }                                               //> findSumFunc: (n: Int, fac1: Int, fac2: Int)Int

  findSumFunc(0, 3, 5)                            //> List()
                                                  //| res39: Int = 0
  findSumFunc(10, 3, 5)                           //> List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                                                  //| res40: Int = 23
  findSumFunc(100, 3, 5)                          //> List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
                                                  //| 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
                                                  //|  39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57
                                                  //| , 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 7
                                                  //| 6, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 
                                                  //| 95, 96, 97, 98, 99)
                                                  //| res41: Int = 2318

  def CountWays(sizex: Int, sizey: Int): Int = {

    def count(x: Int, y: Int): Int = {

      if (x == sizex && y == sizey) 1
      else if (x == sizex) count(x, y + 1)
      else if (y == sizey) count(x + 1, y)
      else count(x + 1, y) + count(x, y + 1)
    }
    if (sizex > 0 && sizey > 0) count(0, 0)
    else 0
  }                                               //> CountWays: (sizex: Int, sizey: Int)Int

  CountWays(4, 4);                                //> res42: Int = 70

}