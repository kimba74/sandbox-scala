package org.kimbasoft.scala.collections

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Partitioning {

  def main(args: Array[String]) {
    val people = Array[Person](new Person("Steffen", 40), new Person("Jennifer", 40), new Person("Mattes", 4))

    val (minors, adults) = people partition(_.getAge < 18)

    println("-- Minors -----------------")
    minors foreach println
    println("-- Adults -----------------")
    adults foreach println
  }
}

class Person(name: String, age: Int) {

  def getName = name

  def getAge = age

  override def toString: String = name + "(" + age + ")"
}
