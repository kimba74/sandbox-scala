package org.kimbasoft.specs2.scalacheck

import org.scalacheck.Gen
import org.scalacheck.Prop.{all, atLeastOne, forAll, BooleanOperators}

import scala.math._

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
  val propSqrt1 = forAll { (n: Int) => sqrt(n*n) == n }
  propSqrt1.check

  println("\n\"ForAll\" Square Root Prop (custom Gen): ")
  val genSqrt2 = Gen.choose(1,100)
  val propSqrt2 = forAll (genSqrt2){ (n: Int) => scala.math.sqrt(n*n) == n }
  propSqrt2.check

  println("\n\"ForAll\" Square Root Prop (implication): ")
  val propSqrt3 = forAll { (n: Int) => (n > 0 && n < 1000) ==> (scala.math.sqrt(n*n) == n) }
  propSqrt3.check

  println("\n\n-- Combining Properties -------------------------------")
  println("\n forAll((n: Int) => n*2 == n+n)")
  val p1 = forAll((n: Int) => n*2 == n+n)
  p1.check

  println("\n forAll((n: Int) => sqrt(n*n) == n)")
  val p2 = forAll((n: Int) => sqrt(n*n) == n)
  p2.check

  println("\n p1 && p2")
  val p3 = p1 && p2
  p3.check

  println("\n p1 || p2")
  val p4 = p1 || p2
  p4.check

  println("\n all(p1, p2)")
  val p5 = all(p1, p2)         // Same as p3
  p5.check

  println("\n atLeastOne(p1, p2)")
  val p6 = atLeastOne(p1, p2)  // Same as p4
  p6.check
}
