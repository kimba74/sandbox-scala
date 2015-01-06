package org.kimbasoft.scala.codingstyle.functional.implicits

import scala.language.implicitConversions

/**
 * Missing documentation
 *
 * @since 1.0
 */
object ImplicitArguments {

  /**
   * Implicit values are matched by their type. In other words, only
   * exactly one implicit value of a given type is allowed in the context
   * at a given time otherwise the the value would be ambiguous and the
   * build would fail
   */
  implicit val currentRate = 0.08F

  /**
   * Abstract base class for the implicit concrete implementations.
   * The "implicitNotFound" annotation can be used to tell the compiler what
   * error message to print should an concrete implicit implementation of
   * this class for a specific type not exist.
   */
  @scala.annotation.implicitNotFound("No implicit Rate defined for ${T}")
  abstract class Rate[T] {
    def calculate(amount: T): T
  }

  /**
   * Concrete implicit object for Int extending the abstract Rate[] class
   */
  implicit object IntRate extends Rate[Int] {
    override def calculate(amount: Int): Int = amount * 2
  }

  /**
   * Concrete implicit object for Float extending the abstract Rate[] class
   */
  implicit object FloatRate extends Rate[Float] {
    override def calculate(amount: Float): Float = amount * 3.0F
  }

  /**
   * Concrete implicit object for Double extending the abstract Rate[] class
   */
  implicit object DoubleRate extends Rate[Double] {
    override def calculate(amount: Double): Double = amount * 4.0
  }

  /**
   * Simple method with a second argument list requiring and implicit value of
   * type Float. Since there is only one implicit value of type float available
   * in the context it will be injected into that second argument list.
   */
  def calcSimple(amount: Float)(implicit rate: Float) = amount * rate

  /**
   * Parametrized method with a second argument list that requires a Rate[] object
   * for the given parameter type. If no Rate[] implementation for the given type
   * could be found within the context at compile time the build will fail and the
   * above specified error message ("implicitNotFound" annotation) will be thrown.
   *
   * This approach could be simplified using "implicitly[]" (see example)
   */
  def calcComplex[A](amount: A)(implicit rate: Rate[A]) = rate.calculate(amount)

  def main(args: Array[String]) {
    println("-- Implicit Arguments ------------------------")
    println("Calc Simple>> " + calcSimple(60000F))

    println("Calc Complex Int   >> " + calcComplex(12))
    println("Calc Complex Float >> " + calcComplex(12F))
    println("Calc Complex Double>> " + calcComplex(12.0))
  }
}
