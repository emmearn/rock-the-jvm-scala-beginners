package lectures.part3fp

object MapFlatMapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list)

  println(list.head)
  println(list.tail)

  // map
  println(list.map(x => x + 1))
  println(list.map(x => s"$x is a number"))

  // filter
  println(list.filter(x => x % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  // iterations
  val combinations = numbers.filter(x => x % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(color => s"$c$n-$color")))
  println(combinations)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield(s"$c$n-$color")

  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }
}
