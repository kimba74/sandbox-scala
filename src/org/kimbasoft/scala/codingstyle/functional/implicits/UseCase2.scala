package org.kimbasoft.scala.codingstyle.functional.implicits

/**
 * Missing documentation
 *
 * @since 1.0
 */
object UseCase2 {

  /**
   * Implicit class serves as wrapper for any type and provides a piping
   * method that allows to concatenate operations.
   */
  implicit class Pipeline[V](value: V) {
    def |[R](f: V => R) = f(value)
  }

  def debug(value: String) = { println("Debug: " + value); value }

  def decorate(value: String) = "<" + value + ">"

  def fillBlanks(value: String) = value.replace(' ', '_')

  def uppercase(value: String) = value.toUpperCase


  def main(args: Array[String]) {
    val v = "Hello World" | debug | decorate | debug | fillBlanks | debug | uppercase | debug
    println(v)
  }
}
