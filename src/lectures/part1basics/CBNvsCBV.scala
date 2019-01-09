package lectures.part1basics

object CBNvsCBV extends App {
  def calledByValue(x: Long): Unit = {
    println(s"By value: ${x}") // 105517451080032L
    println(s"By value: ${x}") // 105517451080032L
  }

  def calledByName(x: => Long): Unit = {
    println(s"By name: ${x}") // passed by name wich means System.nanoTime()
    println(s"By name: ${x}") // passed by name wich means System.nanoTime()
  }

  calledByValue(System.nanoTime()) // calledByValue(105517451080032L)
  calledByName(System.nanoTime())

  def infinite: Int = 1 + infinite
  def printFirst(x: Int, y: => Int) = println(x)

  // printFirst(infinite, 34)
  printFirst(34, infinite)
}
