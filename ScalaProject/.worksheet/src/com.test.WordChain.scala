package com.test
import scala.io.Source.fromFile

object WordChain {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(113); 
  println("Welcome to the Scala worksheet");$skip(142); 

  val dictionary = fromFile("/home/indix/Search/Practice/ScalaProject/words.dat").getLines().
    map { x => x.trim().toLowerCase() }.toSet;System.out.println("""dictionary  : scala.collection.immutable.Set[String] = """ + $show(dictionary ));$skip(33); 
  val characterSet = 'a' to 'z';System.out.println("""characterSet  : scala.collection.immutable.NumericRange.Inclusive[Char] = """ + $show(characterSet ));$skip(203); ;

  def changeWord(word: String, ch: Char, index: Int): String = {

    if (index == 0)
      ch + word.substring(index + 1)
    else
      word.substring(0, index) + ch + word.substring(index + 1)
  };System.out.println("""changeWord: (word: String, ch: Char, index: Int)String""");$skip(441); 

  def checkYield(discoveredwords: Set[String], outputdictionary: Map[String, String]): Map[String, String] = {
    for {
      (key, value) <- outputdictionary; ch <- characterSet; i <- 0 to key.length() - 1
      val chword = changeWord(key, ch, i)
      if (!discoveredwords.contains(chword) && dictionary.contains(chword))
    } yield chword -> (outputdictionary(key) + ";" + chword) //println(word +"->" + changeWord(word,ch,i))

  };System.out.println("""checkYield: (discoveredwords: Set[String], outputdictionary: Map[String,String])Map[String,String]""");$skip(503); 

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
  };System.out.println("""getPath: (src: String, dest: String)String""");$skip(26); val res$0 = 

  getPath("cat", "dog");System.out.println("""res0: String = """ + $show(res$0));$skip(26); val res$1 = 
  getPath("ruby", "code");System.out.println("""res1: String = """ + $show(res$1));$skip(28); val res$2 = 
  getPath("scala", "singh");System.out.println("""res2: String = """ + $show(res$2))}

}
