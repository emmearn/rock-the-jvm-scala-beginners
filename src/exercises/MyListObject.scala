package exercises

abstract class MyListCase[+A] {
  def head: A
  def tail: MyListCase[A]
  def isEmpty: Boolean
  def add[B >: A] (element: B): MyListCase[B]
  def printElements: String
  // polymorphic call
  override def toString= "[" + printElements + "]"

  def map[B](transformer: MyTransformerObject[A, B]) : MyListCase[B]
  def flatMap[B](transformer: MyTransformerObject[A, MyListCase[B]]): MyListCase[B]
  def filter(predicate: MyPredicateObject[A]): MyListCase[A]

  // concatenation
  def ++[B >: A](list: MyListCase[B]): MyListCase[B]
}

case object EmptyListCase extends MyListCase[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListCase[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListCase[B] = new ConsCase[B](element, EmptyListCase)
  def printElements: String = ""

  def map[B](transformer: MyTransformerObject[Nothing, B]) : MyListCase[B] = EmptyListCase
  def flatMap[B](transformer: MyTransformerObject[Nothing, MyListCase[B]]): MyListCase[B] = EmptyListCase
  def filter(predicate: MyPredicateObject[Nothing]): MyListCase[Nothing] = EmptyListCase

  def ++[B >: Nothing](list: MyListCase[B]): MyListCase[B] = list
}

case class ConsCase[+A] (h: A, t: MyListCase[A]) extends MyListCase[A] {
  def head: A = h
  def tail: MyListCase[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyListCase[B] = new ConsCase[B](element, this)
  def test = this
  override def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + tail.printElements

  def map[B](transformer: MyTransformerObject[A, B]) : MyListCase[B] =
    new ConsCase(transformer.transform(h), t.map(transformer))

  def flatMap[B](transformer: MyTransformerObject[A, MyListCase[B]]): MyListCase[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  def filter(predicate: MyPredicateObject[A]): MyListCase[A] =
    if(predicate.test(h)) new ConsCase(h, t.filter(predicate))
    else t.filter(predicate)

  def ++[B >: A](list: MyListCase[B]): MyListCase[B] =
    new ConsCase(h, t ++ list)
}

trait MyPredicateObject[-T] {
  def test(element: T) : Boolean
}

trait MyTransformerObject[-A, B] {
  def transform(element: A) : B
}

object ListTestGenerics3 extends App {
  val listOfIntegers : MyListCase[Int] = new ConsCase(1, new ConsCase(2, new ConsCase(3, EmptyListCase)))
  val cloneListOfIntegers : MyListCase[Int] = new ConsCase(1, new ConsCase(2, new ConsCase(3, EmptyListCase)))
  val anotherListOfIntegers : MyListCase[Int] = new ConsCase(4, new ConsCase(5, EmptyListCase))
  val listOfString: MyListCase[String] = new ConsCase("Hello", new ConsCase("Scala", EmptyListCase))

  println(listOfIntegers.toString)
  println(listOfString.toString)

  println(listOfIntegers.map(new MyTransformerObject[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicateObject[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new MyTransformerObject[Int, MyListCase[Int]] {
    override def transform(element: Int): MyListCase[Int] =
      new ConsCase[Int](element, new ConsCase[Int](element + 1, EmptyListCase))
  }).toString)

  println(listOfIntegers == cloneListOfIntegers)
}