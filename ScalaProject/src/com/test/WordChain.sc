package com.test
import scala.io.Source.fromFile

object WordChain {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val dictionary = fromFile("C:\\Users\\sony\\Downloads\\Rahul\\Coding\\workspace\\ScalaProject\\words.dat").getLines().
    map { x => x.trim().toLowerCase() }.toSet     //> java.io.FileNotFoundException: C:\Users\sony\Downloads\Rahul\Coding\workspac
                                                  //| e\ScalaProject\words.dat (No such file or directory)
                                                  //| 	at java.io.FileInputStream.open0(Native Method)
                                                  //| 	at java.io.FileInputStream.open(FileInputStream.java:195)
                                                  //| 	at java.io.FileInputStream.<init>(FileInputStream.java:138)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:91)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:76)
                                                  //| 	at scala.io.Source$.fromFile(Source.scala:54)
                                                  //| 	at com.test.WordChain$$anonfun$main$1.apply$mcV$sp(com.test.WordChain.sc
                                                  //| ala:7)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at com.test.WordChain$.main(com.test.WordChain.scala:4)
                                                  //| 	at com.test.WordChain.main(com
                                                  //| Output exceeds cutoff limit.
  val characterSet = 'a' to 'z';

  def changeWord(word: String, ch: Char, index: Int): String = {

    if (index == 0)
      ch + word.substring(index + 1)
    else
      word.substring(0, index) + ch + word.substring(index + 1)
  }

  def checkYield(discoveredwords: Set[String], outputdictionary: Map[String, String]): Map[String, String] = {
    for {
      (key, value) <- outputdictionary; ch <- characterSet; i <- 0 to key.length() - 1
      val chword = changeWord(key, ch, i)
      if (!discoveredwords.contains(chword) && dictionary.contains(chword))
    } yield chword -> (outputdictionary(key) + ";" + chword) //println(word +"->" + changeWord(word,ch,i))

  }

  // val output = checkYield()
  def getPath(src: String, dest: String): String = {
    var outputdictionary = Map(src -> src)
    var discoveredwords = outputdictionary.keySet
    var output = Map("test" -> "test");
    do {
      output = checkYield(discoveredwords, outputdictionary)
      discoveredwords = discoveredwords union output.keySet
      outputdictionary = output
    } while (!output.isEmpty && !output.contains(dest))
    if (output.contains(dest)) output(dest) else "<NotFound>"
  }

  getPath("cat", "dog")
  getPath("ruby", "code")
  getPath("scala", "singh")

}