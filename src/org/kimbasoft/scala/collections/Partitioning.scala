package org.kimbasoft.scala.collections

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Partitioning {

  def main(args: Array[String]) {

    class Person1(name:String, age:Int) {
      def getName = name
      def getAge = age
      override def toString: String = name + "(" + age + ")"
    }

    val people = Array(new Person1("Steffen", 40), new Person1("Jennifer", 40), new Person1("Mattes", 4))

    val (minors, adults) = people partition(_.getAge < 18)

    println("-- Minors -----------------")
    minors foreach println
    println("-- Adults -----------------")
    adults foreach println
  }
}

