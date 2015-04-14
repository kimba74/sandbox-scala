package org.kimbasoft.specs2.scalacheck

import org.scalacheck.Prop._

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object PropertiesScalaCheck extends App {

  println("\"ForAll\" Concatenate List Prop: ")
  val propConcat = forAll { (l1: List[Int], l2: List[Int]) => l1.size + l2.size == (l1 ::: l2).size }
  propConcat.check

  println("\n\"ForAll\" Square Root Prop: ")
  val propSqrt = forAll { (n: Int) => scala.math.sqrt(n*n) == n }
  propSqrt.check
}
