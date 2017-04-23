package com.test

object TautologyTemp {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  trait Expression {
    def calculate(): Boolean
  }

  class BinaryExpression(expr1: Expression, operator: String, expr2: Expression) extends Expression {
    def calculate(): Boolean = {
      if (operator.equals("&")) expr1.calculate() & expr2.calculate() else if (operator.equals("|"))
        expr1.calculate() | expr2.calculate() else throw error("operator undefined error");
    }
  }

  class UrinaryExpression(expr: Expression) extends Expression {
    def calculate(): Boolean = !expr.calculate();
  }

  class TrueTerm extends Expression {
    def calculate(): Boolean = true
  }

  class FalseTerm extends Expression {
    def calculate(): Boolean = false
  }


	
  def parseString(str: String): Expression =
    {
      new BinaryExpression(new TrueTerm(), "&", new FalseTerm())
    }                                             //> parseString: (str: String)com.test.TautologyTemp.Expression

  val expr = parseString("")                      //> expr  : com.test.TautologyTemp.Expression = com.test.TautologyTemp$$anonfun$
                                                  //| main$1$BinaryExpression$1@1889f191
 
  expr.calculate()                                //> res0: Boolean = false
}