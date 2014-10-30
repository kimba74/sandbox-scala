package org.kimbasoft.scala.methods

/**
 * Missing documentation
 *
 * @since 1.0
 */
object UniformAccessPrinciple {

  class Person {
    /**
     * Backing variable for property "name". In Scala names of members (types, fields, methods)
     * cannot conflict since they are, other than in Java, in the same namespace.
     * Meaning:
     * To allow Uniform Access (as seen in main method of this example) to occur methods and
     * fields must be in the same namespace.
     */
    private var fn = "Unknown"

    /**
     * Reader method for property "name"
     * @return
     */
    def name = {
      println("   Reading name (" + fn + ")")
      fn
    }

    /**
     * Writer method for property "name"
     */
    def name_=(newName: String) = {
      println("   Writing name (" + fn + " -> " + newName + ")")
      fn = newName
    }
  }

  def main(args: Array[String]) {
    // Accessing the property "name" as if it was a field member
    val person1 = new Person
    println("name1> " + person1.name)
    person1.name = "James"
    println("name1> " + person1.name)

    println("--------------------")

    // Accessing the property "name" through its Read/Write methods
    val person2 = new Person
    println("name2> " + person2.name)
    person2.name_=("Fred")
    println("name2> " + person2.name)
  }

}
