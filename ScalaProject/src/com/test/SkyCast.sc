package com.test
import scala.math.min

object SkyCast {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  trait ChannelBrowsing {
    def navigate(start: Int, dest: Int, blocked: Set[Int]): Int
  }

  class UpDownBrowsing(min: Int, max: Int) extends ChannelBrowsing {

    def computeSteps(start: Int, dest: Int, blocked: Set[Int]): Int =
      {
        scala.math.abs(dest - start) - blocked.filter { x => (x >= start && x <= dest) || (x >= dest && x <= start) }.size
      }
    def navigate(start: Int, dest: Int, blocked: Set[Int]): Int = {
      List(computeSteps(start, dest, blocked),
        computeSteps(min, start, blocked) + computeSteps(min, dest, blocked),
        computeSteps(max, start, blocked) + computeSteps(max, dest, blocked)).min
    }
  }
  class RandomBrowsing(min: Int, max: Int) extends ChannelBrowsing {

    def count(n: Int): Int = {
      if (n / 10 == 0) 1
      else
        1 + count(n / 10)
    }

    def navigate(start: Int, dest: Int, blocked: Set[Int]): Int = {
      count(dest);
    }

  }

  def countRemoteClicks(min: Int, max: Int, list: List[Int], blocked: Set[Int]): Int = {
    var prev = min
    var cur = min
    var sum = 0
    val updownbrowser = new UpDownBrowsing(min, max)
    val randombrowser = new RandomBrowsing(min, max)
    for (x <- list) {
      val list = List(updownbrowser.navigate(cur, x, blocked), 1 + updownbrowser.navigate(prev, x, blocked),
        randombrowser.navigate(cur, x, blocked), 1 + randombrowser.navigate(prev, x, blocked))
      println(prev + " " + cur + " " + list)
      sum += list.min
      prev = cur
      cur = x
    }
    sum
  }                                               //> countRemoteClicks: (min: Int, max: Int, list: List[Int], blocked: Set[Int])
                                                  //| Int

  countRemoteClicks(1, 20, List(15, 14, 17, 1, 17), Set(8, 18))
                                                  //> 1 1 List(13, 14, 2, 3)
                                                  //| 1 15 List(1, 13, 2, 3)
                                                  //| 15 14 List(3, 3, 2, 3)
                                                  //| 14 17 List(15, 13, 1, 2)
                                                  //| 17 1 List(15, 1, 2, 3)
                                                  //| res0: Int = 7
  countRemoteClicks(103, 108, List(105, 106, 107, 103, 105), Set(104))
                                                  //> 103 103 List(1, 2, 3, 4)
                                                  //| 103 105 List(1, 3, 3, 4)
                                                  //| 105 106 List(1, 3, 3, 4)
                                                  //| 106 107 List(3, 3, 3, 4)
                                                  //| 107 103 List(1, 3, 3, 4)
                                                  //| res1: Int = 7
  countRemoteClicks(1, 100, List(10,13,13,100,99,98,77,81), Set(78, 79, 80, 3))
                                                  //> 1 1 List(8, 9, 2, 3)
                                                  //| 1 10 List(3, 12, 2, 3)
                                                  //| 10 13 List(0, 4, 2, 3)
                                                  //| 13 13 List(84, 85, 3, 4)
                                                  //| 13 100 List(1, 84, 2, 3)
                                                  //| 100 99 List(1, 3, 2, 3)
                                                  //| 99 98 List(18, 20, 2, 3)
                                                  //| 98 77 List(1, 18, 2, 3)
                                                  //| res2: Int = 12
  countRemoteClicks(1, 200, List(1, 100, 1, 101), Set())
                                                  //> 1 1 List(0, 1, 1, 2)
                                                  //| 1 1 List(99, 100, 3, 4)
                                                  //| 1 100 List(99, 1, 1, 2)
                                                  //| 100 1 List(100, 2, 3, 4)
                                                  //| res3: Int = 6

}