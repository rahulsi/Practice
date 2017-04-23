package com.test

object TautologyTemp {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(85); 
  println("Welcome to the Scala worksheet")

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
  };$skip(806); 


	
  def parseString(str: String): Expression =
    {
      new BinaryExpression(new TrueTerm(), "&", new FalseTerm())
    };System.out.println("""parseString: (str: String)com.test.TautologyTemp.Expression""");$skip(31); 

  val expr = parseString("");System.out.println("""expr  : com.test.TautologyTemp.Expression = """ + $show(expr ));$skip(22); val res$0 = 
 
  expr.calculate();System.out.println("""res0: Boolean = """ + $show(res$0))}
}
