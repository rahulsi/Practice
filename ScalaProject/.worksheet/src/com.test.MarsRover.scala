package com.test

object MarsRover {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(80); 
  println("Welcome to the Scala worksheet")

  class Direction(dir: Char) {                    //TODO : use Enum
    def CH: Char = dir;
    def rotate(ch: Char): Direction = {
      if (ch.toUpper == 'R') {
        if (dir == 'E') new Direction('S')
        else if (dir == 'S') new Direction('W')
        else if (dir == 'W') new Direction('N')
        else new Direction('E')
      } else if (ch.toUpper == 'L') {
        if (dir == 'S') new Direction('E')
        else if (dir == 'W') new Direction('S')
        else if (dir == 'N') new Direction('W')
        else new Direction('N')
      } else
        throw error("Unknown Rotation " + ch)
    }
    override def toString() = dir.toString()
  }

  class RoverState(x: Int, y: Int, dir: Direction) {
    def X: Int = x;
    def Y: Int = y;
    def DIR: Direction = dir;

    override def toString() = x + " " + y + " " + dir.toString()
  }

  class MarsRoverNavigation(highx: Int, highy: Int) {

    def move(state: RoverState): RoverState = {
      if (state.DIR.CH == 'E' && state.Y + 1 <= highy) new RoverState(state.X, state.Y + 1, state.DIR)
      else if (state.DIR.CH == 'W' && state.Y - 1 >= 0) new RoverState(state.X, state.Y - 1, state.DIR)
      else if (state.DIR.CH == 'N' && state.X + 1 <= highx) new RoverState(state.X + 1, state.Y, state.DIR)
      else if (state.DIR.CH == 'S' && state.X - 1 >= 0) new RoverState(state.X - 1, state.Y, state.DIR)
      else
        throw error("Cannot move as specified")
    }

    def changeState(curState: RoverState, ch: Char): RoverState = {
      if (ch == 'M') {
        move(curState)
      } else {
        new RoverState(curState.X, curState.Y, curState.DIR.rotate(ch));
      }
    }

	//make curState as class member
    def playOrders(x: Int, y: Int, ch: Char, orders: String): RoverState = {
      var curState: RoverState = new RoverState(x, y, new Direction(ch))
      for (x <- orders) {
        println(curState+ " " + x)
        curState = changeState(curState, x)
      }
      curState
    }
  };$skip(2045); 
  val rover1: MarsRoverNavigation = new MarsRoverNavigation(5, 5);System.out.println("""rover1  : com.test.MarsRover.MarsRoverNavigation = """ + $show(rover1 ));$skip(55); val res$0 = 
  rover1.playOrders(1, 2, 'N', "LMLMLMLMM").toString();System.out.println("""res0: String = """ + $show(res$0));$skip(56); val res$1 = 
  rover1.playOrders(3, 3, 'E', "MMRMMRMRRM").toString();System.out.println("""res1: String = """ + $show(res$1))}
}
