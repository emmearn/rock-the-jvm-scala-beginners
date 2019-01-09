package lectures.part2oop

object Generics extends App {

  //it's works also with the traits
  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }

  class myMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CoVariantList[+A]

  val animal: Animal = new Cat
  val animalList: CoVariantList[Animal] = new CoVariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION => we return a list of Animals

  // 2. no = INVARIANCE
  class InvariantList[A]
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class ContravarianceList[-A]
  val contravariantList: ContravarianceList[Cat] = new ContravarianceList[Animal]

  //bounded types
  class Cage[A <: Animal](animal: A) // subclasses of Animal
  val cage = new Cage(new Dog())
  val cage2 = new Cage(new Animal())

  class Car
  // val newCage = new Cage(new Car)

  class Cage2[A >: Animal](animal: A) // superclasses of Animal
}
