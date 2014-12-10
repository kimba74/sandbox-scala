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


  def calcSimple(amount: Float)(implicit rate: Float) = amount * rate

  def calcComplex[A](amount: A)(implicit rate: Rate[A]) = rate.calculate(amount)

  def main(args: Array[String]) {
    println("-- Implicit Arguments ------------------------")
    println("Calc Simple>> " + calcSimple(60000F))

    println("Calc Complex Int   >> " + calcComplex(12))
    println("Calc Complex Float >> " + calcComplex(12F))
    println("Calc Complex Double>> " + calcComplex(12.0))
  }
}
