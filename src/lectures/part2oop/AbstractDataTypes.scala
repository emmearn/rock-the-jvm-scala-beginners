package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String = "wild" // no abstract
    def eat: Unit // abstract member
  }

  abstract class Potato

  class Dog extends Animal { // keyword override is optional
    override val creatureType: String = "Canine"

    override def eat: Unit = println("crunch crunch")
  }

  trait ColdBlooded

  trait Carnivore {
    def eat(animal: Animal): Unit // abstract member
    val preferredMeal = "fresh meat" // no abstract member
  }

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc" // override keyword is necessary if the abstract class has defined the member in object

    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal) : Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)

  // traits vs abstract classes
  // 1 - traits cannot have constructor parameters
  // 2 - you can only extend one class but you can extends multiple traits
  // 3 - traits describe a behavior, abstract class = "thing" (e.g. Animal class describe a animal like a dog, but Carnivore trait descrive what they do)
}
