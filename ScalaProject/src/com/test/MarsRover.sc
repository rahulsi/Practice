package com.test

object MarsRover {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  class Direction(dir: Char) {
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

    def playOrders(x: Int, y: Int, ch: Char, orders: String): RoverState = {
      var curState: RoverState = new RoverState(x, y, new Direction(ch))
      for (x <- orders) {
        println(curState+ " " + x)
        curState = changeState(curState, x)
      }
      curState
    }
  }
  val rover1: MarsRoverNavigation = new MarsRoverNavigation(5, 5)
                                                  //> rover1  : com.test.MarsRover.MarsRoverNavigation = com.test.MarsRover$$anon
                                                  //| fun$main$1$MarsRoverNavigation$1@7f63425a
  rover1.playOrders(1, 2, 'N', "LMLMLMLMM").toString()
                                                  //> 1 2 N L
                                                  //| 1 2 W M
                                                  //| 1 1 W L
                                                  //| 1 1 S M
                                                  //| 0 1 S L
                                                  //| 0 1 E M
                                                  //| 0 2 E L
                                                  //| 0 2 N M
                                                  //| 1 2 N M
                                                  //| res0: String = 2 2 N
  rover1.playOrders(3, 3, 'E', "MMRMMRMRRM").toString()
                                                  //> 3 3 E M
                                                  //| 3 4 E M
                                                  //| 3 5 E R
                                                  //| 3 5 S M
                                                  //| 2 5 S M
                                                  //| 1 5 S R
                                                  //| 1 5 W M
                                                  //| 1 4 W R
                                                  //| 1 4 N R
                                                  //| 1 4 E M
                                                  //| res1: String = 1 5 E
}