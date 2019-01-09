package exercises

abstract class MyListGenerics2[+A] {
  def head: A
  def tail: MyListGenerics2[A]
  def isEmpty: Boolean
  def add[B >: A] (element: B): MyListGenerics2[B]
  def printElements: String
  // polymorphic call
  override def toString= "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]) : MyListGenerics2[B]
}

object EmptyListGenerics2 extends MyListGenerics2[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListGenerics2[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListGenerics2[B] = new ConsGenerics2[B](element, EmptyListGenerics2)
  def printElements: String = ""
}

class ConsGenerics2[+A] (h: A, t: MyListGenerics2[A]) extends MyListGenerics2[A] {
  def head: A = h
  def tail: MyListGenerics2[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyListGenerics2[B] = new ConsGenerics2[B](element, this)
  def test = this
  override def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + tail.printElements
}

trait MyPredicate[-T] {
  def test(element: T) : Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A) : {}
}

object ListTestGenerics2 extends App {
  val listOfIntegers : MyListGenerics2[Int] = new ConsGenerics2(1, new ConsGenerics2(2, new ConsGenerics2(3, EmptyListGenerics2)))
  val listOfString: MyListGenerics2[String] = new ConsGenerics2("Hello", new ConsGenerics2("Scala", EmptyListGenerics2))

  println(listOfIntegers.toString)
  println(listOfString.toString)
}