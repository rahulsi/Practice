package com.test

import scala.io.Source.fromFile
import sun.security.util.Length
import scala.collection.mutable.HashSet

object WordChainImproved {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(193); 
  println("Welcome to the Scala worksheet");$skip(173); 

  val dictionary = fromFile("/home/indix/Search/Practice/ScalaProject/words.dat").getLines().
    map { x => x.trim().toLowerCase() }.toSet.filter { x => x.length() == 3 };System.out.println("""dictionary  : scala.collection.immutable.Set[String] = """ + $show(dictionary ));$skip(526); 

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
  };System.out.println("""editDistance: (src: String, srclen: Int, dest: String, destlen: Int)Int""");$skip(280); 

  val wordsPair = for {
    srcword <- dictionary; destword <- dictionary
    if (!srcword.equals(destword) && Math.abs(srcword.length() - destword.length()) == 0 && 1 == editDistance(srcword, srcword.length() - 1, destword, destword.length() - 1))
  } yield srcword -> destword;System.out.println("""wordsPair  : scala.collection.immutable.Set[(String, String)] = """ + $show(wordsPair ));$skip(54); 

  val wordsPath = wordsPair.groupBy(p => p._1).toMap;System.out.println("""wordsPath  : scala.collection.immutable.Map[String,scala.collection.immutable.Set[(String, String)]] = """ + $show(wordsPath ));$skip(785); 
  //  val words = wordsPath.getOrElse("aa", Set(None))

  def BFS(dest: String, discoveredwords: Set[String], currentLevel: Map[String, String]): String = {

    if (currentLevel.isEmpty || currentLevel.contains(dest)) currentLevel.getOrElse(dest, "<NotFound>") // base condition
    else {
      val nextLevel = for {
        (key, value) <- currentLevel; (word1, chword) <- wordsPath.getOrElse(key, new HashSet[(String, String)])  //visit this level

        if (!discoveredwords.contains(chword) && dictionary.contains(chword))
      } yield chword -> (currentLevel(key) + ";" + chword) //println(word +"->" + changeWord(word,ch,i))
      val newdiscoveredwords = discoveredwords union nextLevel.keySet
      BFS(dest, newdiscoveredwords, nextLevel) 		//go the next level
    }
  };System.out.println("""BFS: (dest: String, discoveredwords: Set[String], currentLevel: Map[String,String])String""");$skip(219); 

  // val output = checkYield()
  def getPath(src: String, dest: String): String = {
    var currentLevel = Map(src -> src)
    var discoveredwords = currentLevel.keySet
    BFS(dest, discoveredwords, currentLevel)
  };System.out.println("""getPath: (src: String, dest: String)String""");$skip(25); val res$0 = 

  getPath("cat", "dog");System.out.println("""res0: String = """ + $show(res$0));$skip(26); val res$1 = 
  getPath("ruby", "code");System.out.println("""res1: String = """ + $show(res$1))}
  //getPath("scala", "singh")

}
