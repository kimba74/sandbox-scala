package org.kimbasoft.specs2.scalacheck

import org.scalacheck.Gen

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object GeneratorsScalaCheck extends App {

  val tupleGen = for {
    n <- Gen.choose(10, 20)
    m <- Gen.choose(2*n, 500)
  } yield (n, m)

  val stringGen = Gen.oneOf("a", "b", "c", "d")

  val smallEvenIntegerGen = Gen.choose(0,200) suchThat (_ % 2 == 0)

  println("Tuple Gen : " + tupleGen.sample)
  println("String Gen: " + stringGen.sample)
  println("Small Even Int Gen: " + smallEvenIntegerGen.sample)
}
