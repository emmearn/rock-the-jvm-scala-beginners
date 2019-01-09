package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x = 42
  println(x)

  // VALS ARE IMMUTABLE

  // COMPILER can infer types

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = true
  val aChar : Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong : Long = 3412324314L
  val aFloat : Float = 2.0f
  val aDouble : Double = 3.14

  // variables
  var aVariable: Int = 4

  aVariable = 5
}
