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
     * In order to get a suitable Decorator object for type T this method
     * adds a second argument list with an implicit argument for the Decorator.
     * Since the decorator object is directly injected into a named variable
     * in can be used directly. However, the use of a second argument list
     * for this case can be a bit ugly.
     */
    def print1[T](obj: T)(implicit decorator: Decorator[T]): Unit = {
      println(decorator.decorate(obj))
    }

    /**
     * To avoid the ugliness of a second argument list this method performs a
     * Context Binding on type T to Decorator. In other words, it prevents types
     * from being used as T for which no Decorator object exists in the current
     * context. Since this Context Binding removes the second argument it is
     * not possible to access the decorator object directly anymore. This is where
     * the 'implicitly[]' method comes into play. It will be parametrized with the
     * object that type T is bound to, look it up and return its instance thus
     * allowing for the invocation of the object's methods.
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
