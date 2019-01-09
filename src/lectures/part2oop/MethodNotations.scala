package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean =
      movie == favoriteMovie

    def hangOutWith(person: Person): String =
      s"${this.name} is hanging out with ${person.name}"

    def +(person: Person): String =
      s"${this.name} is hanging out with ${person.name}"

    def unary_! : String = s"$name, what the heck?!"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def +(nickname: String) = new Person(s"$name ($nickname)", favoriteMovie)

    def unary_+ = new Person(name, "Inception", age + 1)

    def learns(what: String) = s"$name is learning $what"
    def learnsScala = this learns "Scala"

    def apply(n: Int): String =
      s"$name whatched the $favoriteMovie $n times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")
  // infix notation = operator notation (syntactic sugar)

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS
  // Akka actors have ! ?

  // prefix notation
  val x = -1 //unary operator
  val y = 1.unary_- //equivalent
  // unary_ prefix only works with ~ + - !
  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary isAlive) // rarely used (only without parameters)

  //apply
  println(mary.apply())
  println(mary()) // equivalent

  println((mary + "the Rockstars")())

  val maryIncresed = +mary
  println(maryIncresed.age)

  println(mary learnsScala)

  println(mary(2))
}

