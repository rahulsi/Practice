package com.test
import scala.io.Source.fromFile

object WordChain {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val dictionary = fromFile("C:\\Users\\sony\\Downloads\\Rahul\\Coding\\workspace\\ScalaProject\\words.dat").getLines().
    map { x => x.trim().toLowerCase() }.toSet     //> dictionary  : scala.collection.immutable.Set[String] = Set(mells, koel, stum
                                                  //| , chary, motts, firn, misfire, gaslit, buns, serious, furzy, brink, basils, 
                                                  //| cruors, acronym, jalapic, comply, marrer, ebb, breaks, kinged, mafioso, pero
                                                  //| xid, shirred, sneezed, seeder, leer, viremia, boxers, ordines, inkhorn, unke
                                                  //| mpt, sectors, obelus, gunwale, raxes, hourly, tripe, mity, dorsers, otic, re
                                                  //| date, finnier, alarums, rissole, haugh, ragis, alsike, derided, bricked, cad
                                                  //| ent, papayan, damson, croci, lover, ribby, mackle, snazzy, tokened, cadged, 
                                                  //| refries, proband, miasmic, awardee, pasture, fobs, speaker, unplait, dapples
                                                  //| , rosily, lion, drudges, acta, didact, welshes, extolls, unfelt, citron, rat
                                                  //| e, waul, ervils, mikado, pepper, fantom, derry, mole, areola, burgle, orphre
                                                  //| y, assert, seesaws, muggers, lights, maturer, spokes, rage, kris, mise, osmi
                                                  //| ous, nicols, nixies, luxates, clough, musers, cognize, gowan, primes, isonom
                                                  //| y, gigues, aerugos, over
                                                  //| Output exceeds cutoff limit.
  val characterSet = 'a' to 'z';                  //> characterSet  : scala.collection.immutable.NumericRange.Inclusive[Char] = Nu
                                                  //| mericRange(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v,
                                                  //|  w, x, y, z)

  def changeWord(word: String, ch: Char, index: Int): String = {

    if (index == 0)
      ch + word.substring(index + 1)
    else
      word.substring(0, index) + ch + word.substring(index + 1)
  }                                               //> changeWord: (word: String, ch: Char, index: Int)String

  def checkYield(discoveredwords: Set[String], outputdictionary: Map[String, String]): Map[String, String] = {
    for {
      (key, value) <- outputdictionary; ch <- characterSet; i <- 0 to key.length() - 1
      val chword = changeWord(key, ch, i)
      if (!discoveredwords.contains(chword) && dictionary.contains(chword))
    } yield chword -> (outputdictionary(key) + ";" + chword) //println(word +"->" + changeWord(word,ch,i))

  }                                               //> checkYield: (discoveredwords: Set[String], outputdictionary: Map[String,Stri
                                                  //| ng])Map[String,String]

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
  }                                               //> getPath: (src: String, dest: String)String

  getPath("cat", "dog")                           //> res0: String = cat;cot;dot;dog
  getPath("ruby", "code")                         //> res1: String = ruby;rube;robe;rode;code
  getPath("scala", "singh")                       //> res2: String = <NotFound>

}