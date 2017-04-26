package com.test

import java.util.Stack

object InfixToPostFix {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(109); 
  println("Welcome to the Scala worksheet");$skip(230); 

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
  };System.out.println("""getOperandValue: (ch: Char)Int""");$skip(140); 

  def hashighPrecedence(ch: Char, op: Char): Boolean = {

    if (getOperandValue(ch) - getOperandValue(op) >= 0) true
    else false

  };System.out.println("""hashighPrecedence: (ch: Char, op: Char)Boolean""");$skip(193); 

  def isOperand(ch: Char): Boolean = {
    if (ch != '(' && ch != ')' && ch != '&' && ch != '|' && ch != '!'
      && ch != '+' && ch != '-' && ch != '*' && ch != '/') true
    else false
  };System.out.println("""isOperand: (ch: Char)Boolean""");$skip(784); 

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
  };System.out.println("""infixToPostFix: (input: String)String""");$skip(29); val res$0 = 

  infixToPostFix("a+b*c-d");System.out.println("""res0: String = """ + $show(res$0));$skip(28); val res$1 = 
  infixToPostFix("a*b+c*d");System.out.println("""res1: String = """ + $show(res$1));$skip(32); val res$2 = 
  infixToPostFix("(a+b)*(c-d)");System.out.println("""res2: String = """ + $show(res$2));$skip(30); val res$3 = 
  infixToPostFix("a*(b+c)+d");System.out.println("""res3: String = """ + $show(res$3))}

}
