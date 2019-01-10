package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming}
// import playground._ // everything

// import playground.Cinderella

import java.util.Date
import java.sql.{ Date => SqlDate }

object PackagingAdnImports extends App {

  // package members are accessible my their simple name
  val writer = new Writer("Daniel", "RockeTheJVM", 2018)

  // import the package
  // val princess = new playground.Cinderella // fully qualified name
  val princess = new Princess

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHTS)
  val prince = new PrinceCharming

  // 1. use FQ names
  val date = new Date
  val sqlDate = new java.sql.Date(2018, 5, 4)

  // 2. use aliasing
  val sqlDate2 = new SqlDate(2018, 5, 4)

  // default import
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
