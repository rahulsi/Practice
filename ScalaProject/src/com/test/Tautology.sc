package com.test

object Tautology {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  trait Expression {
    def calculate(): Expression
  }

  class BinaryOrExpression(expr1: Expression, expr2: Expression) extends Expression {
    def calculate(): Expression = {
      val exp1 = expr1.calculate()
      val exp2 = expr2.calculate()
      if(exp1.isInstanceOf[TrueTerm]) new TrueTerm()
      else if(exp1.isInstanceOf[FalseTerm]) exp2
      exp2
    }

  }

  /*
  class UrinaryExpression(expr: Expression) extends Expression {
    def calculate(): Expression = {
      val exp = expr.calculate();
      if(exp.isInstanceOf[TrueTerm]) new FalseTerm()
      else if(exp.isInstanceOf[FalseTerm]) new TrueTerm()
      else
      {
       	val bin = exp.asInstanceOf[BinaryExpression]
       	new BinaryExpression(bin.e
      }
    }
  }
*/
  class TrueTerm extends Expression {
    def calculate(): Expression = this
  }

  class FalseTerm extends Expression {
    def calculate(): FalseTerm = this
  }

}