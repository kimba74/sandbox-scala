package org.kimbasoft.specs2.scalacheck

import org.scalacheck.Gen

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object GeneratorsScalaCheck extends App {

  /* Generating random tuples with (semi)random values */
  val tupleGen = for {
    n <- Gen.choose(10, 20)
    m <- Gen.choose(2*n, 500)
  } yield (n, m)

  println("Tuple Gen : " + tupleGen.sample)

  /* Generating a random String by selecting one of a provided list */
  val stringGen = Gen.oneOf("a", "b", "c", "d")

  println("String Gen: " + stringGen.sample)

  /* Generating a small even Integer values by setting a range then
   * filtering the values based in even and odd */
  val evenIntGen = Gen.choose(0,200) suchThat (_ % 2 == 0)

  println("Small Even Int Gen: " + evenIntGen.sample)

  /* Generating a list with random length from provided list */
  val selectionGen = Gen.someOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

  println("Random List length Gen: " + selectionGen.sample)

  /* Generating a list of random length with only the provided elements */
  val intListGen = Gen.containerOf[List,Int](Gen.oneOf(1, 3, 5))

  println("Integer List Gen: " + intListGen.sample)
}
