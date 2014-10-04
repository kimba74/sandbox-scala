package org.kimbasoft.scala.general

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Partitioning {

  def main(args: Array[String]) {
    val people = Array[Person](new Person("Steffen", 40), new Person("Jennifer", 40), new Person("Mattes", 4))

    val (minors, adults) = people partition(_.getAge < 18)

    println("-- Minors -----------------")
    for (m <- minors) println(m)
    println("-- Adults -----------------")
    for (a <- adults) println(a)
  }
}
