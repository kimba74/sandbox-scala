package org.kimbasoft.scala.codingstyle.functional.implicits

import ImplicitArguments._
import ImplicitConversions._

/**
 * Missing documentation
 *
 * @since 1.0
 */
object ImplicitsExample {

  def calcSimple(amount: Float)(implicit rate: Float) = amount * rate

  def calcComplex[A](amount: A)(implicit rate: Rate[A]) = rate.calculate(amount)

  def main(args: Array[String]) {
    println("-- Implicit Arguments ------------------------")
    println("Calc Simple>> " + calcSimple(60000F))

    println("Calc Complex Int   >> " + calcComplex(12))
    println("Calc Complex Float >> " + calcComplex(12F))
    println("Calc Complex Double>> " + calcComplex(12.0))

    println("-- Implicit Conversions ----------------------")
    println("Hello Implicit Class".decorate('<', '>'))
    println("Hello Implicit Method".fillBlanks('_'))
  }

}
