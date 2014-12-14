package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object AbstractType {

  /**
   * Abstract class using an abstract type rather than being parametrized.
   * The value 'source' cannot be set via a constructor since type 'T' is
   * not yet initialized when constructor is being parsed.
   */
  abstract class MyType {
    type T
    val source: T
    def read: String
  }

  class StringType(val source: String) extends MyType {
    type T = String
    def read: String = source.toString
  }

  class IntegerType(val source: Int) extends MyType {
    type T = Int
    def read: String = source.toString
  }

  def main(args: Array[String]) {
    val str: MyType = new StringType("Hello")
    val int: MyType = new IntegerType(123)

    println(s"Str> ${str.read}")
    println(s"Int> ${int.read}")
  }
}
