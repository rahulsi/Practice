package com.test

import java.util.Stack

object InfixToPostFix {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def getOperandValue(ch: Char): Int = {
    ch match {
    	case '!' => 1
      case '*' => 5
      case '/' => 5
      case '+' => 10
      case '-' => 10
      case '&' => 15
      case '|' => 20
      case _ => 100
    }
  }                                               //> getOperandValue: (ch: Char)Int

  def hashighPrecedence(ch: Char, op: Char): Boolean = {

    if (getOperandValue(ch) - getOperandValue(op) >= 0) true
    else false

  }                                               //> hashighPrecedence: (ch: Char, op: Char)Boolean

  def isOperand(ch: Char): Boolean = {
    if (ch != '(' && ch != ')' && ch != '&' && ch != '|' && ch != '!'
      && ch != '+' && ch != '-' && ch != '*' && ch != '/') true
    else false
  }                                               //> isOperand: (ch: Char)Boolean

  def infixToPostFix(input: String): String = {

    var output: String = ""
    var stack: Stack[Char] = new Stack()
    for (ch <- input) {
      if (isOperand(ch)) output += ch
      else if (ch == '(') stack.push(ch)
      else if (ch == ')') {
        var op: Char = stack.pop()
        while (!stack.isEmpty() && op != '(') {
          output += op
          op = stack.pop()
        }
      } else if (!stack.isEmpty()) {
        var op: Char = stack.peek()
        while (!stack.isEmpty() && hashighPrecedence(ch, op)) {
          output += op
          stack.pop()
          if (!stack.isEmpty()) {
            op = stack.peek()
          }
        }
        stack.push(ch)
      } else stack.push(ch)
    }
    while (!stack.isEmpty()) output += stack.pop()
    output
  }                                               //> infixToPostFix: (input: String)String

  infixToPostFix("a+b*c-d")                       //> res0: String = abc*+d-
  infixToPostFix("a*b+c*d")                       //> res1: String = ab*cd*+
  infixToPostFix("(a+b)*(c-d)")                   //> res2: String = ab+cd-*
  infixToPostFix("a*(b+c)+d")                     //> res3: String = abc+*d+

}