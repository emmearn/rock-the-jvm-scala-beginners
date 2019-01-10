package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  // println(x.length)
  // this ^^ will crash with a NPE

  // 1. throwing exceptions
  // val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if(withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // code that whill get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use final only for side effects (logging something)
    println("finally")
  }

  println(potentialFail)

  // 3. How to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  // throw exception

  // OOM
  // val array = Array.ofDim(Int.MaxValue)

  // SO
  // def infinite: Int = 1 + infinite
  // val noLimit = infinite

  class OverFlowException extends RuntimeException
  class UnderFlowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by zero")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y

      if(x > 0 && y > 0 && result < 0) throw new OverFlowException
      else if(x < 0 && y < 0 && result > 0) throw new UnderFlowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y

      if(x > 0 && y < 0 && result < 0) throw new OverFlowException
      else if(x < 0 && y > 0 && result > 0) throw new UnderFlowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y

      if(x > 0 && y > 0 && result < 0) throw new OverFlowException
      else if(x < 0 && y < 0 && result < 0) throw new OverFlowException
      else if(x > 0 && y < 0 && result > 0) throw new UnderFlowException
      else if(x < 0 && y > 0 && result > 0) throw new UnderFlowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if(y == 0) throw new MathCalculationException
      else x / y
    }
  }

  // println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))
}
