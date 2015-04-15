package org.kimbasoft.specs2.scalacheck

import org.scalacheck.Gen
import org.scalacheck.Prop.{forAll, BooleanOperators}

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
  val propSqrt1 = forAll { (n: Int) => scala.math.sqrt(n*n) == n }
  propSqrt1.check

  println("\n\"ForAll\" Square Root Prop (custom Gen): ")
  val genSqrt2 = Gen.choose(1,100)
  val propSqrt2 = forAll (genSqrt2){ (n: Int) => scala.math.sqrt(n*n) == n }
  propSqrt2.check

  println("\n\"ForAll\" Square Root Prop (implication): ")
  val propSqrt3 = forAll { (n: Int) => (n > 0 && n < 1000) ==> (scala.math.sqrt(n*n) == n) }
  propSqrt3.check
}
