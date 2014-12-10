package org.kimbasoft.scala.codingstyle.functional.implicits

// Needs to be imported for the implicit method otherwise compiler issues a warning
import scala.language.implicitConversions

/**
 * Missing documentation
 *
 * @since 1.0
 */
object ImplicitConversions {

  /**
   * Implicit Conversion before Scala 2.10
   * An implicit method is being used as a factory method creating
   * an instance of the wrapper class
   */
  implicit def createWrapper(str: String): MyOtherStringWrapper = new MyOtherStringWrapper(str)

  class MyOtherStringWrapper(str: String) {
    def fillBlanks(filler: Char) = str.replace(' ', filler)
  }


  /**
   * Implicit classes introduced in Scala 2.10
   */
  implicit class MyStringWrapper(str: String) {
    def decorate(head: Char, tail: Char) = head + str + tail
  }


  def main(args: Array[String]) {
    println("-- Implicit Conversions ----------------------")
    println("Hello Implicit Class".decorate('<', '>'))
    println("Hello Implicit Method".fillBlanks('_'))
  }
}
