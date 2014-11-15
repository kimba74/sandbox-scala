package org.kimbasoft.scala.codingstyle.functional.implicits

// Needs to be imported for the implicit method otherwise compiler issues a warning
import scala.language.implicitConversions

/**
 * Missing documentation
 *
 * @since 1.0
 */
object ImplicitConversions {

  class MyOtherStringWrapper(str: String) {
    def fillBlanks(filler: Char) = str.replace(' ', filler)
  }

  implicit def createWrapper(str: String): MyOtherStringWrapper = new MyOtherStringWrapper(str)

  /**
   * Implicit classes introduced in Scala 2.10
   */
  implicit class MyStringWrapper(str: String) {
    def decorate(head: Char, tail: Char) = head + str + tail
  }

}
