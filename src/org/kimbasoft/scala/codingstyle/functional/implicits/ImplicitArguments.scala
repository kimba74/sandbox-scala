package org.kimbasoft.scala.codingstyle.functional.implicits

import scala.language.implicitConversions

/**
 * Missing documentation
 *
 * @since 1.0
 */
object ImplicitArguments {

  implicit val currentRate = 0.08F

  @scala.annotation.implicitNotFound("No implicit Rate defined for ${T}")
  abstract class Rate[T] {
    def calculate(amount: T): T
  }

  implicit object IntRate extends Rate[Int] {
    override def calculate(amount: Int): Int = amount * 2
  }

  implicit object FloatRate extends Rate[Float] {
    override def calculate(amount: Float): Float = amount * 3.0F
  }

  implicit object DoubleRate extends Rate[Double] {
    override def calculate(amount: Double): Double = amount * 4.0
  }

}
