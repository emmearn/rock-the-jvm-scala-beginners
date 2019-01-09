package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  class Animal {
    val creatureType = "wild"

    def eat = println("nomnom")
    private def eatPrivate = println("nomnom")
    protected def eatProtected = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eatProtected
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.eat
  //cat.eat(2)
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
//    override val creatureType: String = "domestic"

    override def eat = {
      super.eat
      println("crunch, crunch")
    }
  }

  val dog = new Dog("domestic")
  dog.eat
  println(dog.creatureType)

  // type sostitution
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overRIDING vs overLOADING

  // super

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on class
  // 3 - seal the class (by the keywords "sealed" = extends classes in THIS file only, prevent the extensions in other file
}
