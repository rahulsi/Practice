package com.test

import java.util.Stack

object InfixToPostFixImproved {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  case class Operator(ch: Char, rank: Int) {
    override def toString: String = ch.toString()
  }

  def getOperator(ch: Char): Operator = {
    ch match {
      case '!' => Operator('!', 1)
      case '*' => Operator('*', 5)
      case '/' => Operator('/', 5)
      case '+' => Operator('+', 10)
      case '-' => Operator('-', 10)
      case '&' => Operator('&', 15)
      case '|' => Operator('|', 20)
      case '(' => Operator('(', 90)
      case ')' => Operator(')', 90)
      case _ => Operator(';', 100) //Not operator
    }
  }                                               //> getOperator: (ch: Char)com.test.InfixToPostFixImproved.Operator

  def hashighPrecedence(ch: Operator, op: Operator): Boolean = {

    if (ch.rank - op.rank >= 0) true
    else false

  }                                               //> hashighPrecedence: (ch: com.test.InfixToPostFixImproved.Operator, op: com.te
                                                  //| st.InfixToPostFixImproved.Operator)Boolean

  def isOperand(ch: Char): Boolean = {
    if (getOperator(ch).ch == ';') true
    else false
  }                                               //> isOperand: (ch: Char)Boolean

  def infixToPostFix(input: String): String = {

    var output: String = ""
    var stack: Stack[Operator] = new Stack()
    for (ch <- input) {
      if (isOperand(ch)) output += ch
      else if (ch == '(') stack.push(getOperator(ch))
      else if (ch == ')') {
        var op: Operator = stack.pop()
        while (!stack.isEmpty() && op.ch != '(') {
          output += op
          op = stack.pop()
        }
      } else if (!stack.isEmpty()) {
        var op: Operator = stack.peek()
        while (!stack.isEmpty() && hashighPrecedence(getOperator(ch), op)) {
          output += op
          stack.pop()
          if (!stack.isEmpty()) {
            op = stack.peek()
          }
        }
        stack.push(getOperator(ch))
      } else stack.push(getOperator(ch))
    }
    while (!stack.isEmpty()) output += stack.pop()
    output
  }                                               //> infixToPostFix: (input: String)String

  def evalPostFix(input: String, map: Map[String, Boolean]): Boolean = {
    var stack: Stack[Boolean] = new Stack()
    for (ch <- input) {
      if (ch == '!') { val v1 = stack.pop(); stack.push(!v1); /* println(ch + " -> !" + v1 + " = " + (!v1)) */ }
      else if (ch == '|') { val v1 = stack.pop(); val v2 = stack.pop(); stack.push(v1 || v2); /* println(ch + " -> " + v1 + "|" + v2 + "=" + (v1 || v2))*/ }
      else if (ch == '&') { val v1 = stack.pop(); val v2 = stack.pop(); stack.push(v1 && v2); /*println(ch + " -> " + v1 + "&" + v2 + "=" + (v1 && v2)) */ }
      else {
        //println(ch + "->" + map.getOrElse(ch.toString(), false))
        stack.push(map.getOrElse(ch.toString(), false))
      }
    }
    val output = stack.pop()
    if (stack.isEmpty()) output
    else { println(stack); throw (new Exception("faied to evaluate  expression :" + input)) }
  }                                               //> evalPostFix: (input: String, map: Map[String,Boolean])Boolean
  //infixToPostFix("a+b*c-d")
  //infixToPostFix("a*b+c*d")
  //infixToPostFix("(a+b)*(c-d)")
  //infixToPostFix("a*(b+c)+d")

  //infixToPostFix("(!a | (a & a))".replaceAll("\\s+", "").trim())
  //infixToPostFix("(!a | (b & !a))".replaceAll("\\s+", "").trim())
  //infixToPostFix("(!a | a)".replaceAll("\\s+", "").trim())
  //infixToPostFix("((a & (!b | b)) | (!a & (!b | b)))".replaceAll("\\s+", "").trim())

  def generateValueMap(x: Int, eleSet: Set[String]): Map[String, Boolean] = {

    var valMap: Map[String, Boolean] = Map()
    var value: Boolean = false
    var count = 0
    for (ele <- eleSet) {
      value = if ((x & 1 << count) == 0) false else true
      valMap += (ele -> value)
      count += 1
    }
    valMap
  }                                               //> generateValueMap: (x: Int, eleSet: Set[String])Map[String,Boolean]

  def isTautology(input: String, eleSet: Set[String], n: Int): Boolean = {
    if (n == -1) true
    else if (evalPostFix(input, generateValueMap(n, eleSet)) == false) false
    else isTautology(input, eleSet, n - 1)
  }                                               //> isTautology: (input: String, eleSet: Set[String], n: Int)Boolean

  def isTautologyImpr(input: String): Boolean = {
    //    println("input=" + input)
    val str = input.replaceAll(" ", "");
    //    println("infix=" + str)
    val exp = infixToPostFix(str)
    //    println("postfix=" + exp)
    val eleSet = str.split(Array('(', ')', '&', '|', '!')).filter { x => x.trim().length() > 0 }.toSet
    val n = Math.pow(2, eleSet.size).toInt - 1;

    isTautology(exp, eleSet, n)
  }                                               //> isTautologyImpr: (input: String)Boolean

  val input = Array("(a|((b|c)|(!d)))", "(!a | (a & a))", "(!a | (b & !a))", "(!a | a)", "((a & (!b | b)) | (!a & (!b | b)))")
                                                  //> input  : Array[String] = Array((a|((b|c)|(!d))), (!a | (a & a)), (!a | (b &
                                                  //|  !a)), (!a | a), ((a & (!b | b)) | (!a & (!b | b))))
  //val input = Array("(a|!a)")
  for (spacestr <- input) {
    println(spacestr + "=" + isTautologyImpr(spacestr))
                                                  //> (a|((b|c)|(!d)))=false
                                                  //| (!a | (a & a))=true
                                                  //| (!a | (b & !a))=false
                                                  //| (!a | a)=true
                                                  //| ((a & (!b | b)) | (!a & (!b | b)))=true
  }
}