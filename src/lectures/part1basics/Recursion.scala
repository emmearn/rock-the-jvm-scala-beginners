package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int =
    if(n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need a factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }

  println(factorial(10))
  //println(factorial(5000))

  def anotherFactorial(n: BigInt): BigInt = {
    def factorialHelper(x: BigInt, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator)
    }

    factorialHelper(n, 1)
  }

  /*
    anotherFactorial(10) = factHelper(10, 1)
    = factHelper(9, 10 * 1)
    = factHelper(8, 9 * 10 * 1)
    = factHelper(7, 8 * 9 * 10 * 1)
    = ...
    = factHelper(2, 3 * 4 * ... * 10 * 1)
    = factHelper(1, 2 * 3 * 4 * ... * 10 * 1)
    = 2 * 3 * 4 * ... * 10 * 1
   */

  println(anotherFactorial(5000))

  // WHEN YOU NEED LOOPS, USE TAIL RECURSION.

  @tailrec
  def concatenateWithTailRec(aString: String, n: Int): String =
    if (n == 1) aString
    else concatenateWithTailRec(aString.replace("_", "__"), n-1)

  println(concatenateWithTailRec("u_u", 5))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeWithTailRec(t: Int, accumulator: Boolean): Boolean =
      if(!accumulator) false
      else if(t <= 1) true
      else isPrimeWithTailRec(t - 1, n % t != 0 && accumulator)

    isPrimeWithTailRec(n / 2, true)
  }

  println(isPrime(8))
  println(isPrime(37))
  println(isPrime(50000000))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciTailRec(i: Int, last: Int, nextToLast: Int) : Int =
      if(i >= n) last
      else fibonacciTailRec(i + 1, last + nextToLast, last)

    if(n <= 2) 1
    else fibonacciTailRec(2, 1, 1)
  }

  println(fibonacci(8))
  println(fibonacci(110))
}
