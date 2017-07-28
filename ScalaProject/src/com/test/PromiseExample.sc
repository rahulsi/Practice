package com.test

import scala.concurrent.Promise
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object PromiseExample {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  case class LameExcuse(msg: String) extends Exception(msg)

  case class TaxCut(reduction: Int)
  val taxcut = Promise[TaxCut]()                  //> taxcut  : scala.concurrent.Promise[com.test.PromiseExample.TaxCut] = List()
  val taxcut2: Promise[TaxCut] = Promise()        //> taxcut2  : scala.concurrent.Promise[com.test.PromiseExample.TaxCut] = List()
                                                  //| 
  val taxcutF: Future[TaxCut] = taxcut.future     //> taxcutF  : scala.concurrent.Future[com.test.PromiseExample.TaxCut] = List()
  taxcut.success(TaxCut(20))                      //> res0: com.test.PromiseExample.taxcut.type = Success(TaxCut(20))

  def redeemCampaignPledge(): Future[TaxCut] = {
    val p = Promise[TaxCut]()
    Future {
      println("Starting the new legislative period.")
      Thread.sleep(2000)
      //p.success(TaxCut(20))
      p.failure(LameExcuse("global economy crisis"))
      println("We reduced the taxes! You must reelect us!!!!1111")
    }
    p.future
  }                                               //> redeemCampaignPledge: ()scala.concurrent.Future[com.test.PromiseExample.TaxC
                                                  //| ut]
  val taxCutF: Future[TaxCut] = redeemCampaignPledge()
                                                  //> taxCutF  : scala.concurrent.Future[com.test.PromiseExample.TaxCut] = List()
  println("Now that they're elected, let's see if they remember their promises...")
                                                  //> Now that they're elected, let's see if they remember their promises...


  taxCutF.onComplete {
    case Success(TaxCut(reduction)) => {
      println(s"A miracle! They really cut our taxes by percentage points!")
      //throw (new Exception("sucess"))
    }
    case Failure(ex) =>
      {
        println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
        //throw (new Exception("failed"))
      }
  }


  taxCutF.isCompleted                             //> res1: Boolean = false
  Await.ready(taxCutF, Duration.Inf)              //> Starting the new legislative period.
                                                  //| res2: com.test.PromiseExample.taxCutF.type = Failure(com.test.PromiseExampl
                                                  //| e$$anonfun$main$1$LameExcuse$3: global economy crisis)
  taxCutF.isCompleted                             //> We reduced the taxes! You must reelect us!!!!1111
                                                  //| res3: Boolean = true
  //Thread.sleep(1000)
 
}