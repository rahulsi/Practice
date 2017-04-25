package com.test

object MarsRoverImproved {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(88); 
  println("Welcome to the Scala worksheet")

  trait Direction {
    def rotateLeft(): Direction
    def rotateRight(): Direction
  }

  object East extends Direction {
    def rotateLeft(): Direction = North
    def rotateRight(): Direction = South
    override def toString() = "East"
  }
  object West extends Direction {
    def rotateLeft(): Direction = South
    def rotateRight(): Direction = North
    override def toString() = "West"
  }
  object North extends Direction {
    def rotateLeft(): Direction = West
    def rotateRight(): Direction = East
    override def toString() = "North"
  }
  object South extends Direction {
    def rotateLeft(): Direction = East
    def rotateRight(): Direction = West
    override def toString() = "South"
  }

  class RoverState(x: Int, y: Int, dir: Direction) {
    def X: Int = x;
    def Y: Int = y;
    def DIR: Direction = dir;

    override def toString() = x + " " + y + " " + dir.toString()
  }

  class MarsRoverNavigation(highx: Int, highy: Int) {

    def move(state: RoverState): RoverState = {
      state.DIR match {
        case East => new RoverState(state.X, state.Y + 1, state.DIR)
        case West => new RoverState(state.X, state.Y - 1, state.DIR)
        case North => new RoverState(state.X + 1, state.Y, state.DIR)
        case South => new RoverState(state.X - 1, state.Y, state.DIR)
      }
    }
    def changeState(curState: RoverState, ch: Char): RoverState = {
      if (ch == 'M') {
        move(curState)
      } else if (ch == 'L') {
        new RoverState(curState.X, curState.Y, curState.DIR.rotateLeft());
      } else if (ch == 'R') {
        new RoverState(curState.X, curState.Y, curState.DIR.rotateRight());
      } else {
        curState
      }
    }

    def getDirection(ch: Char): Direction =
      {
        ch match {
          case 'E' => East
          case 'W' => West
          case 'N' => North
          case 'S' => South
        }

      }
    //make curState as class member
    def playOrders(x: Int, y: Int, ch: Char, orders: String): RoverState = {

      val direction = getDirection(ch)
      var curState: RoverState = new RoverState(x, y, direction)
      for (x <- orders) {
        println(curState + " " + x)
        curState = changeState(curState, x)
      }
      curState
    }
  };$skip(2324); 
  val rover1: MarsRoverNavigation = new MarsRoverNavigation(5, 5);System.out.println("""rover1  : com.test.MarsRoverImproved.MarsRoverNavigation = """ + $show(rover1 ));$skip(55); val res$0 = 
  rover1.playOrders(1, 2, 'N', "LMLMLMLMM").toString();System.out.println("""res0: String = """ + $show(res$0));$skip(56); val res$1 = 
  rover1.playOrders(3, 3, 'E', "MMRMMRMRRM").toString();System.out.println("""res1: String = """ + $show(res$1))}

}
