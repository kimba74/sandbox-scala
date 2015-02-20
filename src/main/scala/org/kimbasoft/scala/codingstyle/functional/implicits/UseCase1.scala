package org.kimbasoft.scala.codingstyle.functional.implicits

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object UseCase1 {

  class Person(val name: String, val age: Int, val height: Double)

  implicit class PersonWrapper(person: Person) {
    def get[T](implicit f: Person => T) = f(person)
  }

  implicit val ageAccess: Person => Int = (p: Person) => p.age

  implicit val heightAccess: Person => Double = (p: Person) => p.height

  implicit val nameAccess: Person => String = (p: Person) => p.name

  def main(args: Array[String]) {
    println("-- Accessing Person1 -----")
    val person1 = new Person("John Doe", 31, 6.2)

    val age: Int = person1.get
    val height: Double = person1.get
    val name: String = person1.get

    println("Name  : " + name)
    println("Age   : " + age)
    println("Height: " + height)

    println("-- Accessing Person2 -----")
    val person2 = new Person("Jane Doe", 28, 5.6)

    println("Name  : " + person2.get[String])
    println("Age   : " + person2.get[Int])
    println("Height: " + person2.get[Double])
  }
}
