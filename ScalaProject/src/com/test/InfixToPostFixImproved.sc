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
      case _ => Operator(';', 100)  //Not operator
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

  infixToPostFix("a+b*c-d")                       //> res0: String = abc*+d-
  infixToPostFix("a*b+c*d")                       //> res1: String = ab*cd*+
  infixToPostFix("(a+b)*(c-d)")                   //> res2: String = ab+cd-*
  infixToPostFix("a*(b+c)+d")                     //> res3: String = abc+*d+

}