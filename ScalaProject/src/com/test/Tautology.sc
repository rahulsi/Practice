package com.test

import java.util.Stack

object Tautology {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  trait Expression {
    def calculate(valMap: Map[String, Boolean]): Boolean
  }

  class BinaryOrExpression(expr1: Expression, expr2: Expression) extends Expression {
    def calculate(valMap: Map[String, Boolean]): Boolean = {
      expr1.calculate(valMap) || expr2.calculate(valMap)
    }
    override def toString(): String = "(" + expr1.toString() + "|" + expr2.toString() + ")"
  }

  class BinaryAndExpression(expr1: Expression, expr2: Expression) extends Expression {
    def calculate(valMap: Map[String, Boolean]): Boolean = {
      expr1.calculate(valMap) && expr2.calculate(valMap) // use logical so that it is efficient
    }
    override def toString(): String = "(" + expr1.toString() + "&" + expr2.toString() + ")"
  }

  class UrinaryExpression(expr: Expression) extends Expression {
    def calculate(valMap: Map[String, Boolean]): Boolean = {
      !expr.calculate(valMap)
    }
    override def toString(): String = "!" + expr.toString()
  }

  class VariableTerm(str: String) extends Expression {
    def calculate(valMap: Map[String, Boolean]): Boolean = {
      //println(this+" -> "+valMap.getOrElse(str, false))
      valMap.getOrElse(str, false)
    }
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

  def isTautology(exp: Expression, eleSet: Set[String]): Boolean = {

    for (x <- 0 to Math.pow(2, eleSet.size).toInt - 1) {
      var valMap: Map[String, Boolean] = Map()
      var value: Boolean = false
      var count = 0
      for (ele <- eleSet) {
        value = if ((x & 1 << count) == 0) false else true
        //println(ele + " " + value)
        valMap += (ele -> value)
        count += 1
      }
      //println(valMap)
      //println(exp + " " + exp.calculate(valMap))
      if (exp.calculate(valMap) == false) return false
    }
    return true
  }                                               //> isTautology: (exp: com.test.Tautology.Expression, eleSet: Set[String])Boole
                                                  //| an

  //TODO : avoid multiple return
  val input = Array("(a|((b|c)|(!d)))", "(!a | (a & a))", "(!a | (b & !a))", "(!a | a)", "((a & (!b | b)) | (!a & (!b | b)))")
                                                  //> input  : Array[String] = Array((a|((b|c)|(!d))), (!a | (a & a)), (!a | (b &
                                                  //|  !a)), (!a | a), ((a & (!b | b)) | (!a & (!b | b))))

  for (spacestr <- input) {
    val str = spacestr.replaceAll(" ", "");
    val exp = parseString(str, 0, str.length() - 1) //TODO function name should be more appropriate
    val eleSet = str.split(Array('(', ')', '&', '|', '!')).filter { x => x.trim().length() > 0 }.toSet
    println("output=" + str + " " + isTautology(exp, eleSet))
  }                                               //> output=(a|((b|c)|(!d))) false
                                                  //| output=(!a|(a&a)) true
                                                  //| output=(!a|(b&!a)) false
                                                  //| output=(!a|a) true
                                                  //| output=((a&(!b|b))|(!a&(!b|b))) true
  //assume

}