package org.kimbasoft.scala.codingstyle.functional.dataoperations

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Partitioning {

  case class Person(name:String, age:Int)

  def main(args: Array[String]) {
    val people = Array(Person("Bob", 40), Person("Edith", 53), Person("John", 4), Person("Megan", 17))

    val (minors, adults) = people partition(_.age < 18)

    println("-- Minors -----------------")
    minors foreach println
    println("-- Adults -----------------")
    adults foreach println
  }
}

