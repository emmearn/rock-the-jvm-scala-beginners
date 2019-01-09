package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  // println(person.name) error
  println(person.x)
  person.greet("Daniel")
  person.greet

  val author = new Writer("Marco", "Arnone", 1990)
  val imposter = new Writer("Marco", "Arnone", 1990)
  val novel = new Novel("I want to learn Scala", 2018, author)

  println(novel.getAuthorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
}

// constructor
class Person(name: String, val age: Int = 0) {
  // body
  val  x = 2


  println( 1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet: Unit = println(s"Hi, I'm $name")

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
// class parameters are not fields

class Writer(name: String, surname: String, val year: Int) {
  def getFullName =
    s"$name $surname"
}

class Novel(name: String, year: Int, author: Writer) {
  def getAuthorAge =
    this.year - author.year

  def isWrittenBy(author: Writer) =
    this.author == author

  def copy(newYers: Int): Novel =
    new Novel(this.name, newYers, this.author)
}

class Counter(n: Int = 0) {
  def count = n
  def inc = {
    println("Incrementing")
    new Counter(n + 1) // Immutability
  }
  def dec = {
    println("Decrementing")
    new Counter(n - 1)
  }

  def inc(n: Int) : Counter = {
    // new Counter(this.n + n)
    if (n <= 0) this
    else inc.inc(n - 1)
  }
  def dec(n: Int) : Counter = {
    // new Counter(this.n - n)
    if  (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}