package com.test

import java.util.Stack

object Tautology {
  println("WelcometotheScalaworksheet")           //> WelcometotheScalaworksheet

  trait Expression {
    def calculate(valMap: Map[String, Boolean]): Boolean
  }

  class BinaryOrExpression(expr1: Expression, expr2: Expression) extends Expression {
    def calculate(valMap: Map[String, Boolean]): Boolean = {
      expr1.calculate(valMap) | expr2.calculate(valMap)
    }
    override def toString(): String = "(" + expr1.toString() + "|" + expr2.toString() + ")"
  }

  class BinaryAndExpression(expr1: Expression, expr2: Expression) extends Expression {
    def calculate(valMap: Map[String, Boolean]): Boolean = {
      expr1.calculate(valMap) & expr2.calculate(valMap)
    }
    override def toString(): String = "(" + expr1.toString() + "&" + expr2.toString() + ")"
  }

  class UrinaryExpression(expr: Expression) extends Expression {
    def calculate(valMap: Map[String, Boolean]): Boolean = {
      if (expr.calculate(valMap) == true) false
      else true
    }
    override def toString(): String = "!" + expr.toString()
  }

  class VariableTerm(str: String) extends Expression {
    def calculate(valMap: Map[String, Boolean]): Boolean = valMap.getOrElse(str, false)
    override def toString(): String = str
  }

  /*
classTrueTermextendsExpression{
defcalculate(valMap:Map[String,Boolean]):Boolean=true
}

classFalseTermextendsExpression{
defcalculate(valMap:Map[String,Boolean]):Boolean=false
}
*/

  //assumptions :
  // 1. no spaces
  // 2. single character variable
  def parseString(expr: String, start: Int, end: Int): Expression = {
    val stack = new Stack[String];
    var i = start;
    var exp: Expression = null;
    var negfound = false;
    if (expr.charAt(i) == '!') {
      negfound = true;
      i += 1
    }
    if (expr.charAt(i) == '(') {
      i += 1
      var expr_start = i;
      stack.push("(")
      while (i <= end) {
        if (expr.charAt(i) == '(')
          stack.push("(")
        else if (expr.charAt(i) == ')') {
          stack.pop()
          if (stack.isEmpty()) {
            exp = parseString(expr, expr_start, i - 1)
            if (negfound == true)
              exp = new UrinaryExpression(exp);
            if (i + 1 > end) {
              return exp
            } else if (expr.charAt(i + 1) == '&') {
              exp = new BinaryAndExpression(exp, parseString(expr, i + 2, end))
              return exp
            } else if (expr.charAt(i + 1) == '|') {
              exp = new BinaryOrExpression(exp, parseString(expr, i + 2, end))
              return exp
            } else {
              throw new Exception("Cannot not parse the expression at" + i + 1)
            }
          }
        }
        i += 1
      }
    } else {
      exp = new VariableTerm(expr.charAt(i).toString())
      if (negfound == true)
        exp = new UrinaryExpression(exp);
      if (i + 1 > end) {
        return exp
      } else if (expr.charAt(i + 1) == '&') {
        exp = new BinaryAndExpression(exp, parseString(expr, i + 2, end))
        return exp
      } else if (expr.charAt(i + 1) == '|') {
        exp = new BinaryOrExpression(exp, parseString(expr, i + 2, end))
        return exp
      } else {
        throw new Exception("Cannot not parse the expression at" + i + 1)
      }
      return exp
    }
    return exp
  }                                               //> parseString: (expr: String, start: Int, end: Int)com.test.Tautology.Express
                                                  //| ion
  val str = "a|(b|!c)"                            //> str  : String = a|(b|!c)
  val exp = parseString(str, 0, str.length() - 1) //> exp  : com.test.Tautology.Expression = (a|(b|!c))

}