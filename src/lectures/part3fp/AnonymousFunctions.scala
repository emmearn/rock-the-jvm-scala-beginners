package lectures.part3fp

object AnonymousFunctions extends App {
  /* val doubler = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  } */

  // anonymous functions (LAMBA)
  // val doubler = (x: Int) => x * 2
  val doubler: Int => Int = x => x * 2

  // multiple params in a lamba
  val adder: (Int, Int) => Int = (a: Int, b:Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curly braces with lambas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  /*
    1. MyList: replace all FunctionX calls with lambdas
    2. Rewrite the "special" adder as an anonymous function
   */

  val superAdder: Int => (Int => Int) = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val specialAdder = (x: Int) => (y: Int) => x + y

  println(superAdder(3)(4))
  println(specialAdder(3)(4))
}
