package org.kimbasoft.specs2.matcher

import org.specs2.Specification
import org.specs2.specification.core.SpecStructure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class TraversableMatchers extends Specification {
  def is: SpecStructure = s2"""
  Some Traversable Matchers:
       ex1: list must not be empty                          $ex1
       ex2: list must have size 9                           $ex2
       ex3: list must contain (5)                           $ex3
       ex4: list must contain(beGreaterThan(5)).exactly(4)  $ex4
  """

  val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

  val ex1 = list must not be empty
  val ex2 = list must have size 9
  // Check each element individually
  val ex3 = list must contain (5)
  val ex4 = list must contain(beGreaterThan(5)).exactly(4) // must contain exactly 4 numbers greater than 5
}
