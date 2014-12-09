package org.kimbasoft.scala.codingstyle.functional.implicits

/**
 * Missing documentation
 *
 * @since 1.0
 */
object Implicitly {

  abstract class Decorator[T] {
    def decorate(obj: T): String
  }

  implicit object StringDecorator extends Decorator[String] {
    def decorate(obj: String): String = "$" + obj
  }

  implicit object IntDecorator extends Decorator[Int] {
    def decorate(obj: Int): String = "#" + obj
  }

  class Printer {
    /**
     * Use of implicit argument
     */
    def print1[T](obj: T)(implicit decorator: Decorator[T]): Unit = {
      println(decorator.decorate(obj))
    }

    /**
     * Use of implicitly() via context binding
     */
    def print2[T : Decorator](obj: T) = {
      println(implicitly[Decorator[T]].decorate(obj))
    }
  }

  def main(args: Array[String]) {
    val strPrinter = new Printer
    println("-- Implicit Argument ---------------")
    strPrinter.print1("Hello")
    strPrinter.print1(123)
    println("-- Implicitly() --------------------")
    strPrinter.print2("Good-bye")
    strPrinter.print2(321)
  }
}
