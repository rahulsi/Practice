package com.test

import org.w3c.dom.ranges.Range

object HelloScala {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(114); 
  println("Welcome to the Scala worksheet");$skip(171); 

  def lastElement[T](list: List[T]): Option[T] = {
    list match {
      case Nil => None
      case x :: Nil => Some(x)
      case x :: xs => lastElement(xs)
    }
  };System.out.println("""lastElement: [T](list: List[T])Option[T]""");$skip(36); val res$0 = 

  lastElement(List(1, 2, 3, 4, 5));System.out.println("""res0: Option[Int] = """ + $show(res$0));$skip(23); val res$1 = 
  lastElement(List(1));System.out.println("""res1: Option[Int] = """ + $show(res$1));$skip(22); val res$2 = 
  lastElement(List());System.out.println("""res2: Option[Nothing] = """ + $show(res$2));$skip(19); val res$3 = 
  lastElement(Nil);System.out.println("""res3: Option[Nothing] = """ + $show(res$3));$skip(40); val res$4 = 
  lastElement(List('a', 'b', 'c', 'd'));System.out.println("""res4: Option[Char] = """ + $show(res$4));$skip(41); val res$5 = 
  lastElement(List("abc", "def", "ghi"));System.out.println("""res5: Option[String] = """ + $show(res$5));$skip(223); 

  def penUltimateElement(list: List[Any]): Any =
    {
      list match {
        case Nil => None
        case x :: Nil => None
        case x :: y :: Nil => x
        case x :: xs => penUltimateElement(xs)
      }
    };System.out.println("""penUltimateElement: (list: List[Any])Any""");$skip(42); val res$6 = 
  penUltimateElement(List(1, 2, 3, 4, 5));System.out.println("""res6: Any = """ + $show(res$6));$skip(30); val res$7 = 
  penUltimateElement(List(1));System.out.println("""res7: Any = """ + $show(res$7));$skip(33); val res$8 = 
  penUltimateElement(List(1, 2));System.out.println("""res8: Any = """ + $show(res$8));$skip(29); val res$9 = 
  penUltimateElement(List());System.out.println("""res9: Any = """ + $show(res$9));$skip(26); val res$10 = 
  penUltimateElement(Nil);System.out.println("""res10: Any = """ + $show(res$10));$skip(47); val res$11 = 
  penUltimateElement(List('a', 'b', 'c', 'd'));System.out.println("""res11: Any = """ + $show(res$11));$skip(48); val res$12 = 
  penUltimateElement(List("abc", "def", "ghi"));System.out.println("""res12: Any = """ + $show(res$12));$skip(154); 

  def reverse[T](list: List[T]): List[T] =
    {
      list match {
        case Nil => Nil
        case x :: xs => reverse(xs) ++ List(x)
      }
    };System.out.println("""reverse: [T](list: List[T])List[T]""");$skip(31); val res$13 = 
  reverse(List(1, 2, 3, 4, 5));System.out.println("""res13: List[Int] = """ + $show(res$13));$skip(19); val res$14 = 
  reverse(List(1));System.out.println("""res14: List[Int] = """ + $show(res$14));$skip(22); val res$15 = 
  reverse(List(1, 2));System.out.println("""res15: List[Int] = """ + $show(res$15));$skip(18); val res$16 = 
  reverse(List());System.out.println("""res16: List[Nothing] = """ + $show(res$16));$skip(15); val res$17 = 
  reverse(Nil);System.out.println("""res17: List[Nothing] = """ + $show(res$17));$skip(36); val res$18 = 
  reverse(List('a', 'b', 'c', 'd'));System.out.println("""res18: List[Char] = """ + $show(res$18));$skip(37); val res$19 = 
  reverse(List("abc", "def", "ghi"));System.out.println("""res19: List[String] = """ + $show(res$19));$skip(420); 

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
    };System.out.println("""compress: [T](list: List[T])List[T]""");$skip(45); val res$20 = 

  compress(List(1, 1, 2, 2, 2, 3, 3, 1, 1));System.out.println("""res20: List[Int] = """ + $show(res$20));$skip(20); val res$21 = 
  compress(List(1));System.out.println("""res21: List[Int] = """ + $show(res$21));$skip(19); val res$22 = 
  compress(List());System.out.println("""res22: List[Nothing] = """ + $show(res$22));$skip(67); val res$23 = 
  compress(List('a', 'a', 'b', 'c', 'd', 'd', 'e', 'e', 'e', 'e'));System.out.println("""res23: List[Char] = """ + $show(res$23));$skip(298); 

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

    };System.out.println("""expand: [T](n: Int, list: List[T])List[T]""");$skip(27); val res$24 = 
  expand(3, List(1, 2, 3));System.out.println("""res24: List[Int] = """ + $show(res$24));$skip(20); val res$25 = 
  expand(3, List());System.out.println("""res25: List[Nothing] = """ + $show(res$25));$skip(36); val res$26 = 
  expand(3, List("rahul", "singh"));System.out.println("""res26: List[String] = """ + $show(res$26));$skip(787); 

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
  };System.out.println("""sliceN: [T](low: Int, size: Int, list: List[T])List[T]""");$skip(24); val res$27 = 

  sliceN(2, 5, List());System.out.println("""res27: List[Nothing] = """ + $show(res$27));$skip(48); val res$28 = 
  sliceN(2, 5, List(0, 1, 2, 3, 4, 5, 6, 7, 8));System.out.println("""res28: List[Int] = """ + $show(res$28));$skip(66); val res$29 = 
  sliceN(0, 2, List("0", "1", "2", "3", "4", "5", "6", "7", "8"));System.out.println("""res29: List[String] = """ + $show(res$29));$skip(66); val res$30 = 
  sliceN(0, 3, List("0", "1", "2", "3", "4", "5", "6", "7", "8"));System.out.println("""res30: List[String] = """ + $show(res$30));$skip(66); val res$31 = 
  sliceN(1, 5, List("0", "1", "2", "3", "4", "5", "6", "7", "8"));System.out.println("""res31: List[String] = """ + $show(res$31));$skip(376); 

  def slice[T](low: Int, high: Int, list: List[T]): List[T] = {

    def sliceCur(cur: Int, low: Int, high: Int, list: List[T]): List[T] = {

      list match {
        case Nil => Nil
        case x :: xs => if (cur >= low && cur <= high) x :: sliceCur(cur + 1, low, high, xs)
        else sliceCur(cur + 1, low, high, xs)
      }
    }
    sliceCur(0, low, high, list)
  };System.out.println("""slice: [T](low: Int, high: Int, list: List[T])List[T]""");$skip(23); val res$32 = 

  slice(2, 5, List());System.out.println("""res32: List[Nothing] = """ + $show(res$32));$skip(47); val res$33 = 
  slice(2, 5, List(0, 1, 2, 3, 4, 5, 6, 7, 8));System.out.println("""res33: List[Int] = """ + $show(res$33));$skip(65); val res$34 = 
  slice(0, 8, List("0", "1", "2", "3", "4", "5", "6", "7", "8"));System.out.println("""res34: List[String] = """ + $show(res$34));$skip(505); 

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
  };System.out.println("""listPrimesinRange: (low: Int, high: Int)List[Int]""");$skip(29); val res$35 = 

  listPrimesinRange(1, 100);System.out.println("""res35: List[Int] = """ + $show(res$35));$skip(271); 

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
  };System.out.println("""findSum: (n: Int)Int""");$skip(14); val res$36 = 

  findSum(0);System.out.println("""res36: Int = """ + $show(res$36));$skip(14); val res$37 = 
  findSum(10);System.out.println("""res37: Int = """ + $show(res$37));$skip(15); val res$38 = 
  findSum(100);System.out.println("""res38: Int = """ + $show(res$38));$skip(295); 

  def findSumFunc(n: Int, fac1: Int, fac2: Int): Int = {

    def isMultiple(x: Int, fac: Int): Boolean = {
      if (x % fac == 0) true else false
    }

    val numlist = (0 to n - 1).toList;
    println(numlist)
    numlist.filter { x => isMultiple(x, fac1) || isMultiple(x, fac2) }.sum
  };System.out.println("""findSumFunc: (n: Int, fac1: Int, fac2: Int)Int""");$skip(24); val res$39 = 

  findSumFunc(0, 3, 5);System.out.println("""res39: Int = """ + $show(res$39));$skip(24); val res$40 = 
  findSumFunc(10, 3, 5);System.out.println("""res40: Int = """ + $show(res$40));$skip(25); val res$41 = 
  findSumFunc(100, 3, 5);System.out.println("""res41: Int = """ + $show(res$41))}

}
