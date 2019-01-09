package exercises

abstract class MyListGenerics[+A] {
  def head: A
  def tail: MyListGenerics[A]
  def isEmpty: Boolean
  def add[B >: A] (element: B): MyListGenerics[B]
  def printElements: String
  // polymorphic call
  override def toString= "[" + printElements + "]"
}

object EmptyListGenerics extends MyListGenerics[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListGenerics[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListGenerics[B] = new ConsGenerics[B](element, EmptyListGenerics)
  def printElements: String = ""
}

class ConsGenerics[+A] (h: A, t: MyListGenerics[A]) extends MyListGenerics[A] {
  def head: A = h
  def tail: MyListGenerics[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyListGenerics[B] = new ConsGenerics[B](element, this)
  def test = this
  override def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + tail.printElements
}

object ListTestGenerics extends App {
  val listOfIntegers : MyListGenerics[Int] = new ConsGenerics(1, new ConsGenerics(2, new ConsGenerics(3, EmptyListGenerics)))
  val listOfString: MyListGenerics[String] = new ConsGenerics("Hello", new ConsGenerics("Scala", EmptyListGenerics))

  println(listOfIntegers.toString)
  println(listOfString.toString)
}