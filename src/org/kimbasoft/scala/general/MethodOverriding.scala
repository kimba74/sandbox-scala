package org.kimbasoft.scala.general

/**
 * Missing documentation
 *
 * @since 1.0
 */
object MethodOverriding {

  abstract class BaseClass {
    def name = "Unknown"
    def age = 0
  }

  class DerivedClass extends BaseClass {
    override val name = "John" //Overriding method w/o parameter works only with 'val' not with 'var'
    override val age = 36
  }

  def main(args: Array[String]) {
    val test1 = new DerivedClass
    println("name> " + test1.name)
    println("age > " + test1.age)
  }

}
