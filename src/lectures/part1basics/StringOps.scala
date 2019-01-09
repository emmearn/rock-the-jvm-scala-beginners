package lectures.part1basics

object StringOps extends App {
  val str: String = "Hello, I'm learing Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toUpperCase)
  println(str.length)
  // all already present in Java


  val aNumberString = "42"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println(str.reverse)
  println(str.take(2))
  // Scala specific method

  // String interpolators.

  // S-interpolations.
  val name = "Marco"
  val age = 12
  val greeting = s"Hello, my name is $name and I'm $age years old"
  val anotherGreeting = s"Hello, my name is $name and I would be turning ${age + 1} years old."
  println(anotherGreeting)

  // F-interpolators.
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute."

  println(myth)

  val x = 1.1f
  // val anotherStr = f"$x%3d" <-- compile error
  // println(anotherStr)

  // raw-interpolator
  println(raw"This is a \n newline")
  val escaped = "This is a \n newline"
  println(raw"$escaped")
}
