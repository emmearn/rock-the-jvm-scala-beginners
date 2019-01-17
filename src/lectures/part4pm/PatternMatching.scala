package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  val random = new Random()
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else" // _ = WILDCARD
  }

  println(x)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)
  val bob = new Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }

  println(greeting)

  /*
    1. cases are matched in order
    2. what if no cases match? MatchError exception
    3. what's the type of PM expression? unified type of all the types in all the cases
    4. PM works really well with case classes
   */

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the some $someBreed breed")
  }

  // match everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  // WHY ???
  val isEvenCond = if(x % 2 == 0) true else false
  val isEventNormal = x % 2 == 0

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case Prod(e1, e2) => {
      def maybeShowParenteses(exp: Expr) = exp match {
        case Prod(_, _) | Number(_) => show(exp)
        case _ => s"(${show(exp)})"
      }

      s"${maybeShowParenteses(e1)} * ${maybeShowParenteses(e2)}"
    }
  }

  println(show(Sum(Number(2), Number(3)))) // => 2 + 3
  println(show(Sum(Sum(Number(2), Number(3)), Number(4)))) // => 2 + 3 + 4
  println(show(Prod(Sum(Number(2), Number(1)), Number(3)))) // => (2 + 1) * 3
  println(show(Prod(Sum(Number(2), Number(1)), Sum(Number(3), Number(4))))) // => (2 + 1) * (3
  println(show(Sum(Prod(Number(2), Number(1)), Number(3)))) // => 2 + 3 + 4
}
