package com.test

import scala.io.Source.fromFile
import sun.security.util.Length
import scala.collection.mutable.HashSet

object WordChainImproved {
  println("Welcome to the Scala worksheet")

  val dictionary = fromFile("/home/indix/Search/Practice/ScalaProject/words.dat").getLines().
    map { x => x.trim().toLowerCase() }.toSet.filter { x => x.length() == 3 }

  def editDistance(src: String, srclen: Int, dest: String, destlen: Int): Int = {

    lazy val x = editDistance(src, srclen - 1, dest, destlen);
    lazy val y = editDistance(src, srclen, dest, destlen - 1);
    lazy val z = editDistance(src, srclen - 1, dest, destlen - 1);
    if (srclen < 0 && destlen < 0) 0
    else if (srclen < 0) destlen + 1
    else if (destlen < 0) srclen + 1
    else
      Math.min(Math.min(1 + x,
        1 + y),
        if (src.charAt(srclen) == dest.charAt(destlen)) z
        else 1 + z)
  }

  val wordsPair = for {
    srcword <- dictionary; destword <- dictionary
    if (!srcword.equals(destword) && Math.abs(srcword.length() - destword.length()) == 0 && 1 == editDistance(srcword, srcword.length() - 1, destword, destword.length() - 1))
  } yield srcword -> destword

  val wordsPath = wordsPair.groupBy(p => p._1).toMap
  //  val words = wordsPath.getOrElse("aa", Set(None))

  def checkYield(dest: String, discoveredwords: Set[String], outputdictionary: Map[String, String]): String = {

    if (outputdictionary.isEmpty || outputdictionary.contains(dest)) outputdictionary.getOrElse(dest, "<NotFound>")
    else {
      val output = for {
        (key, value) <- outputdictionary; (word1, chword) <- wordsPath.getOrElse(key, new HashSet[(String, String)])

        if (!discoveredwords.contains(chword) && dictionary.contains(chword))
      } yield chword -> (outputdictionary(key) + ";" + chword) //println(word +"->" + changeWord(word,ch,i))
      val newdiscoveredwords = discoveredwords union output.keySet
      checkYield(dest, newdiscoveredwords, output)
    }
  }

  // val output = checkYield()
  def getPath(src: String, dest: String): String = {
    var outputdictionary = Map(src -> src)
    var discoveredwords = outputdictionary.keySet
    checkYield(dest, discoveredwords, outputdictionary)
  }

  getPath("cat", "dog")
  getPath("ruby", "code")
  //getPath("scala", "singh")

}