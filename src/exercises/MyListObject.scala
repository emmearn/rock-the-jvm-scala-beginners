package exercises

abstract class MyListCase[+A] {
  def head: A
  def tail: MyListCase[A]
  def isEmpty: Boolean
  def add[B >: A] (element: B): MyListCase[B]
  def printElements: String
  // polymorphic call
  override def toString= "[" + printElements + "]"

  // def map[B](transformer: MyTransformerObject[A, B]) : MyListCase[B]
  def map[B](transformer: A => B) : MyListCase[B]
  // def flatMap[B](transformer: MyTransformerObject[A, MyListCase[B]]): MyListCase[B]
  def flatMap[B](transformer: A => MyListCase[B]): MyListCase[B]
  // def filter(predicate: MyPredicateObject[A]): MyListCase[A]
  def filter(predicate: A => Boolean): MyListCase[A]

  // concatenation
  def ++[B >: A](list: MyListCase[B]): MyListCase[B]

  // hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyListCase[A]
  def zipWidth[B, C](list: MyListCase[B], zip: (A, B) => C): MyListCase[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object EmptyListCase extends MyListCase[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListCase[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListCase[B] = new ConsCase[B](element, EmptyListCase)
  def printElements: String = ""

  // def map[B](transformer: MyTransformerObject[Nothing, B]): MyListCase[B] = EmptyListCase
  def map[B](transformer: Nothing => B): MyListCase[B] = EmptyListCase
  // def flatMap[B](transformer: MyTransformerObject[Nothing, MyListCase[B]]): MyListCase[B] = EmptyListCase
  def flatMap[B](transformer: Nothing => MyListCase[B]): MyListCase[B] = EmptyListCase
  // def filter(predicate: MyPredicateObject[Nothing]): MyListCase[Nothing] = EmptyListCase
  def filter(predicate: Nothing => Boolean): MyListCase[Nothing] = EmptyListCase

  def ++[B >: Nothing](list: MyListCase[B]): MyListCase[B] = list

  // hofs
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = EmptyListCase
  def zipWidth[B, C](list: MyListCase[B], zip: (Nothing, B) => C): MyListCase[C] =
    if(!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else EmptyListCase

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
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

  // higher-order functions
  def map[B](transformer: A => B) : MyListCase[B] =
    new ConsCase(transformer(h), t.map(transformer))

  def flatMap[B](transformer: A => MyListCase[B]): MyListCase[B] =
    transformer(h) ++ t.flatMap(transformer)

  def filter(predicate: A => Boolean): MyListCase[A] =
    if(predicate(h)) new ConsCase(h, t.filter(predicate))
    else t.filter(predicate)

  def ++[B >: A](list: MyListCase[B]): MyListCase[B] =
    new ConsCase(h, t ++ list)

  // hofs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }
  def sort(compare: (A, A) => Int): MyListCase[A] = {
    def insert(x: A, sortedList: MyListCase[A]): MyListCase[A] = {
      if(sortedList.isEmpty) new ConsCase(x, EmptyListCase)
      else if(compare(x, sortedList.head) <= 0) new ConsCase(x, sortedList)
      else new ConsCase(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWidth[B, C](list: MyListCase[B], zip: (A, B) => C): MyListCase[C] =
    if(list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new ConsCase(zip(h, list.head), t.zipWidth(list.tail, zip))

  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)
}

/* trait MyPredicateObject[-T] {
  def test(element: T) : Boolean
}

trait MyTransformerObject[-A, B] {
  def transform(element: A) : B
} */

object ListTestGenerics3 extends App {
  val listOfIntegers : MyListCase[Int] = new ConsCase(1, new ConsCase(2, new ConsCase(3, EmptyListCase)))
  val cloneListOfIntegers : MyListCase[Int] = new ConsCase(1, new ConsCase(2, new ConsCase(3, EmptyListCase)))
  val anotherListOfIntegers : MyListCase[Int] = new ConsCase(4, new ConsCase(5, EmptyListCase))
  val listOfString: MyListCase[String] = new ConsCase("Hello", new ConsCase("Scala", EmptyListCase))

  println(listOfIntegers.toString)
  println(listOfString.toString)

  println(listOfIntegers.map(elem => elem * 2).toString)

  println(listOfIntegers.filter(elem => elem % 2 == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(elem => new ConsCase(elem + 1, EmptyListCase)).toString)

  println(listOfIntegers == cloneListOfIntegers)

  listOfIntegers.foreach(println)

  println(listOfIntegers.sort((x, y) => y - x))

  println(anotherListOfIntegers.zipWidth[String, String](listOfString, (x, y) => s"$x-$y"))

  println(listOfIntegers.fold(0)((x, y) => x + y))
}