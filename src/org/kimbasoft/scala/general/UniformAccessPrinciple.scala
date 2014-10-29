package org.kimbasoft.scala.general

/**
 * Missing documentation
 *
 * @since 1.0
 */
object UniformAccessPrinciple {

  class Person {
    private var fn = "Unknown"

    def name = {
      println("   Reading name (" + fn + ")")
      fn
    }

    def name_=(newName: String) = {
      println("   Writing name (" + fn + " -> " + newName + ")")
      fn = newName
    }
  }

  def main(args: Array[String]) {
    val person1 = new Person
    println("name1> " + person1.name)
    person1.name = "James"
    println("name1> " + person1.name)

    println("--------------------")

    val person2 = new Person
    println("name2> " + person2.name)
    person2.name_=("Fred")
    println("name2> " + person2.name)
  }

}
