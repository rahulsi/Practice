package com.test

import scala.concurrent.future
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Random
import scala.concurrent.Await

object FuturePromiseExample {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  type CoffeeBeans = String
  type GroundCoffee = String
  case class Water(temperature: Int)
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    println("start grinding...")
    Thread.sleep(Random.nextInt(2000))
    if (beans == "baked beans") throw GrindingException("are you joking?")
    println("finished grinding...")
    s"ground coffee of $beans"
  }                                               //> grind: (beans: com.test.FuturePromiseExample.CoffeeBeans)scala.concurrent.F
                                                  //| uture[com.test.FuturePromiseExample.GroundCoffee]

  def heatWater(water: Water): Future[Water] = Future {
    println("heating the water now")
    Thread.sleep(Random.nextInt(2000))
    println("hot, it's hot!")
    water.copy(temperature = 85)
  }                                               //> heatWater: (water: com.test.FuturePromiseExample.Water)scala.concurrent.Fut
                                                  //| ure[com.test.FuturePromiseExample.Water]

  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    println("milk frothing system engaged!")
    Thread.sleep(Random.nextInt(2000))
    println("shutting down milk frothing system")
    s"frothed $milk"
  }                                               //> frothMilk: (milk: com.test.FuturePromiseExample.Milk)scala.concurrent.Futur
                                                  //| e[com.test.FuturePromiseExample.FrothedMilk]

  def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
    println("happy brewing :)")
    Thread.sleep(Random.nextInt(2000))
    println("it's brewed!")
    "espresso"
  }                                               //> brew: (coffee: com.test.FuturePromiseExample.GroundCoffee, heatedWater: com
                                                  //| .test.FuturePromiseExample.Water)scala.concurrent.Future[com.test.FuturePro
                                                  //| miseExample.Espresso]

  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino =
    {
      val x = s"cappuccino $espresso $frothedMilk"
      println(x)
      x
    }                                             //> combine: (espresso: com.test.FuturePromiseExample.Espresso, frothedMilk: co
                                                  //| m.test.FuturePromiseExample.FrothedMilk)com.test.FuturePromiseExample.Cappu
                                                  //| ccino

  def prepareCappuccino(): Future[Cappuccino] = {
    val groundCoffee = grind("arabica beans")
    val heatedWater = heatWater(Water(20))
    val frothedMilk = frothMilk("milk")
    for {
      ground <- groundCoffee
      water <- heatedWater
      foam <- frothedMilk
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)
  }                                               //> prepareCappuccino: ()scala.concurrent.Future[com.test.FuturePromiseExample.
                                                  //| Cappuccino]

  val x = prepareCappuccino()                     //> heating the water now
                                                  //| x  : scala.concurrent.Future[com.test.FuturePromiseExample.Cappuccino] = Li
                                                  //| st()
  println("not completed status = " + x.isCompleted)
                                                  //> not completed status = false

  Await.ready(x, Duration.Inf)                    //> milk frothing system engaged!
                                                  //| start grinding...
                                                  //| hot, it's hot!
                                                  //| finished grinding...
                                                  //| shutting down milk frothing system
                                                  //| happy brewing :)
                                                  //| it's brewed!
                                                  //| cappuccino espresso frothed milk
                                                  //| res0: com.test.FuturePromiseExample.x.type = Success(cappuccino espresso fr
                                                  //| othed milk)
  println("completed status = " + x.isCompleted)  //> completed status = true

}