package com.test

import java.util.Stack

object InfixToPostFixImproved {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(117); 
  println("Welcome to the Scala worksheet")

  case class Operator(ch: Char, rank: Int) {
    override def toString: String = ch.toString()
  };$skip(540); 

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
      case _ => Operator(';', 100)  //Not operator
    }
  };System.out.println("""getOperator: (ch: Char)com.test.InfixToPostFixImproved.Operator""");$skip(124); 

  def hashighPrecedence(ch: Operator, op: Operator): Boolean = {

    if (ch.rank - op.rank >= 0) true
    else false

  };System.out.println("""hashighPrecedence: (ch: com.test.InfixToPostFixImproved.Operator, op: com.test.InfixToPostFixImproved.Operator)Boolean""");$skip(99); 

  def isOperand(ch: Char): Boolean = {
    if (getOperator(ch).ch == ';') true
    else false
  };System.out.println("""isOperand: (ch: Char)Boolean""");$skip(851); 

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
  };System.out.println("""infixToPostFix: (input: String)String""");$skip(29); val res$0 = 

  infixToPostFix("a+b*c-d");System.out.println("""res0: String = """ + $show(res$0));$skip(28); val res$1 = 
  infixToPostFix("a*b+c*d");System.out.println("""res1: String = """ + $show(res$1));$skip(32); val res$2 = 
  infixToPostFix("(a+b)*(c-d)");System.out.println("""res2: String = """ + $show(res$2));$skip(30); val res$3 = 
  infixToPostFix("a*(b+c)+d");System.out.println("""res3: String = """ + $show(res$3))}

}
