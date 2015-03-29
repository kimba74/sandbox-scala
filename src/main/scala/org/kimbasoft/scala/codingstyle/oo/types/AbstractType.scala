package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object AbstractType extends App {

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

  /**
   * Concrete implementation of the abstract class MyTest. This class defines
   * the abstract type 'T' declared in the superclass to be of type String.
   * The interesting aspect here is, that the implementation of the field
   * 'source' can be done within the constructor of the concrete class.
   */
  class StringType(val source: String) extends MyType {
    type T = String
    def read: String = source.toString
  }

  /**
   * Another concrete implementation of the abstract class MyTest. This concrete
   * class defines the abstract type 'T' to be Int. As the 'StringType'
   * implementation it defines the field 'source' in the constructor.
   */
  class IntegerType(val source: Int) extends MyType {
    type T = Int
    def read: String = source.toString
  }


  val str: MyType = new StringType("Hello")
  val int: MyType = new IntegerType(123)

  println(s"Str> ${str.read}")
  println(s"Int> ${int.read}")
}
