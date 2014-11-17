package org.kimbasoft.scala.codingstyle.functional.implicits

import ImplicitArguments._
import ImplicitConversions._
import ImplicitEvidence._
import ImplicitTypeErasure._

/**
 * Missing documentation
 *
 * @since 1.0
 */
object Implicit {

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

    println("-- Implicit Evidence -------------------------")
    println(Processor("Hello World").process)
    // println(Processor(125000).process)    // Will not compile because no "evidence" that Int is String

    println("-- Implicit Prevent Type Erasure -------------")
    SeqPrinter.print(List(1, 2, 3, 4, 5, 6))
    SeqPrinter.print(List("A", "B", "C", "D", "E", "F"))

  }

}
