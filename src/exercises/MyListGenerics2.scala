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
  def flatMap[B](transformer: MyTransformer[A, MyListGenerics2[B]]): MyListGenerics2[B]
  def filter(predicate: MyPredicate[A]): MyListGenerics2[A]

  // concatenation
  def ++[B >: A](list: MyListGenerics2[B]): MyListGenerics2[B]
}

object EmptyListGenerics2 extends MyListGenerics2[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListGenerics2[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListGenerics2[B] = new ConsGenerics2[B](element, EmptyListGenerics2)
  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]) : MyListGenerics2[B] = EmptyListGenerics2
  def flatMap[B](transformer: MyTransformer[Nothing, MyListGenerics2[B]]): MyListGenerics2[B] = EmptyListGenerics2
  def filter(predicate: MyPredicate[Nothing]): MyListGenerics2[Nothing] = EmptyListGenerics2

  def ++[B >: Nothing](list: MyListGenerics2[B]): MyListGenerics2[B] = list
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

  def map[B](transformer: MyTransformer[A, B]) : MyListGenerics2[B] =
    new ConsGenerics2(transformer.transform(h), t.map(transformer))

  def flatMap[B](transformer: MyTransformer[A, MyListGenerics2[B]]): MyListGenerics2[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  def filter(predicate: MyPredicate[A]): MyListGenerics2[A] =
    if(predicate.test(h)) new ConsGenerics2(h, t.filter(predicate))
    else t.filter(predicate)

  def ++[B >: A](list: MyListGenerics2[B]): MyListGenerics2[B] =
    new ConsGenerics2(h, t ++ list)
}

trait MyPredicate[-T] {
  def test(element: T) : Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A) : B
}

object ListTestGenerics2 extends App {
  val listOfIntegers : MyListGenerics2[Int] = new ConsGenerics2(1, new ConsGenerics2(2, new ConsGenerics2(3, EmptyListGenerics2)))
  val anotherListOfIntegers : MyListGenerics2[Int] = new ConsGenerics2(4, new ConsGenerics2(5, EmptyListGenerics2))
  val listOfString: MyListGenerics2[String] = new ConsGenerics2("Hello", new ConsGenerics2("Scala", EmptyListGenerics2))

  println(listOfIntegers.toString)
  println(listOfString.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyListGenerics2[Int]] {
    override def transform(element: Int): MyListGenerics2[Int] =
      new ConsGenerics2[Int](element, new ConsGenerics2[Int](element + 1, EmptyListGenerics2))
  }).toString)
}