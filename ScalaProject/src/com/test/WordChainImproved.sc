package com.test

import scala.io.Source.fromFile
import sun.security.util.Length
import scala.collection.mutable.HashSet

object WordChainImproved {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val dictionary = fromFile("/home/indix/Search/Practice/ScalaProject/words.dat").getLines().
    map { x => x.trim().toLowerCase() }.toSet.filter { x => x.length() == 3 }
                                                  //> dictionary  : scala.collection.immutable.Set[String] = Set(ebb, ons, ers, me
                                                  //| w, eye, dos, mut, ohm, cog, del, fub, rub, wow, beg, aas, bow, fet, rod, rue
                                                  //| , eau, lam, fib, pas, ors, mag, oar, des, for, haj, gyp, raj, fez, dot, awl,
                                                  //|  ser, vat, ugh, gad, art, fiz, sae, jew, hoe, tao, dye, sop, poh, sal, due, 
                                                  //| ama, any, poi, nub, tui, win, mac, deb, cox, bye, azo, dex, rid, bur, aff, y
                                                  //| ip, tit, kos, hex, mir, gox, sei, lug, ais, bus, lum, wok, dol, bum, flu, la
                                                  //| y, off, cad, peg, fry, rad, are, vex, mon, zax, bat, guy, pis, kep, sip, qua
                                                  //| , gul, fid, his, why, god, sly, keg, rum, tie, too, yom, mun, bay, nae, dam,
                                                  //|  noh, nut, udo, hem, jow, dei, sot, dui, yow, yod, bit, ems, bod, irk, try, 
                                                  //| opt, boa, got, lax, ram, lin, dad, dit, tun, elm, jaw, mob, cow, pun, bog, s
                                                  //| oy, zip, fag, bag, mil, mod, goo, ret, sit, ana, mor, icy, hag, con, odd, ox
                                                  //| y, sel, thy, kue, yes, fir, rye, gel, fon, abo, but, nip, dup, yak, lab, els
                                                  //| , map, cam, sim, dee, ka
                                                  //| Output exceeds cutoff limit.

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
  }                                               //> editDistance: (src: String, srclen: Int, dest: String, destlen: Int)Int

  val wordsPair = for {
    srcword <- dictionary; destword <- dictionary
    if (!srcword.equals(destword) && Math.abs(srcword.length() - destword.length()) == 0 && 1 == editDistance(srcword, srcword.length() - 1, destword, destword.length() - 1))
  } yield srcword -> destword                     //> wordsPair  : scala.collection.immutable.Set[(String, String)] = Set((hot,co
                                                  //| t), (tit,nit), (ape,are), (gip,gib), (mut,mun), (sal,pal), (hen,het), (wax,
                                                  //| was), (wha,who), (tae,tye), (lee,lye), (get,yet), (cam,ram), (pit,pig), (ai
                                                  //| d,bid), (tab,tat), (sac,sic), (fib,bib), (nee,dee), (pun,dun), (cot,coy), (
                                                  //| day,dry), (sos,sob), (ran,tan), (wae,wab), (hay,day), (tow,taw), (boa,zoa),
                                                  //|  (rot,rat), (nod,sod), (tae,tap), (rec,rev), (daw,dak), (mew,mel), (jin,pin
                                                  //| ), (rin,rig), (pip,lip), (top,toy), (pal,pol), (gox,vox), (rig,gig), (gal,a
                                                  //| al), (mob,rob), (sag,sae), (ane,age), (goy,gay), (bag,fag), (pud,pun), (nib
                                                  //| ,sib), (nun,sun), (mad,cad), (sop,sol), (chi,phi), (vas,vaw), (ben,bun), (g
                                                  //| ay,gan), (tho,tao), (vat,mat), (yet,jet), (die,dib), (nob,lob), (sim,mim), 
                                                  //| (doe,voe), (wit,uit), (waw,wad), (raw,jaw), (mar,lar), (cox,fox), (dui,due)
                                                  //| , (six,sex), (yak,yap), (zee,gee), (ran,rax), (gor,gob), (din,die), (boo,bo
                                                  //| p), (til,tis), (fad,wad
                                                  //| Output exceeds cutoff limit.

  val wordsPath = wordsPair.groupBy(p => p._1).toMap
                                                  //> wordsPath  : scala.collection.immutable.Map[String,scala.collection.immutab
                                                  //| le.Set[(String, String)]] = Map(ons -> Set((ons,ins), (ons,oms), (ons,ens),
                                                  //|  (ons,ohs), (ons,one), (ons,uns), (ons,ods), (ons,oes), (ons,ops), (ons,ors
                                                  //| )), ers -> Set((ers,efs), (ers,els), (ers,ors), (ers,ars), (ers,erg), (ers,
                                                  //| err), (ers,era), (ers,ere), (ers,ess), (ers,ems), (ers,ern), (ers,ens)), me
                                                  //| w -> Set((mew,mel), (mew,pew), (mew,met), (mew,jew), (mew,men), (mew,tew), 
                                                  //| (mew,maw), (mew,few), (mew,hew), (mew,mow), (mew,yew), (mew,new), (mew,dew)
                                                  //| , (mew,mem), (mew,sew)), eye -> Set((eye,tye), (eye,rye), (eye,eke), (eye,l
                                                  //| ye), (eye,dye), (eye,eme), (eye,pye), (eye,ewe), (eye,wye), (eye,aye), (eye
                                                  //| ,bye), (eye,ere), (eye,eve)), dos -> Set((dos,dow), (dos,nos), (dos,sos), (
                                                  //| dos,cos), (dos,kos), (dos,don), (dos,doe), (dos,doc), (dos,dom), (dos,dol),
                                                  //|  (dos,des), (dos,dor), (dos,wos), (dos,dog), (dos,bos), (dos,dot)), mut -> 
                                                  //| Set((mut,mun), (mut,cut
                                                  //| Output exceeds cutoff limit.
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
  }                                               //> BFS: (dest: String, discoveredwords: Set[String], currentLevel: Map[String,
                                                  //| String])String

  // val output = checkYield()
  def getPath(src: String, dest: String): String = {
    var currentLevel = Map(src -> src)
    var discoveredwords = currentLevel.keySet
    BFS(dest, discoveredwords, currentLevel)
  }                                               //> getPath: (src: String, dest: String)String

  getPath("cat", "dog")                           //> res0: String = cat;cot;dot;dog
  getPath("ruby", "code")                         //> res1: String = <NotFound>
  //getPath("scala", "singh")

}