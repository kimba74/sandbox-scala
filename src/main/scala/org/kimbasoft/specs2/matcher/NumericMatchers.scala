package org.kimbasoft.specs2.matcher

import org.specs2.Specification
import org.specs2.specification.core.SpecStructure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class NumericMatchers extends Specification {
  def is: SpecStructure = s2"""
  Some Numeric Matchers:
       ex1: 6 must be equalTo 6         $ex1
       ex2: 6 must be between (4, 8)    $ex2
       ex3: 6 must be ~(5 +/- 1)        $ex3
       ex4: 6 must beCloseTo(7, 1)      $ex4
       ex5: 6 must beGreaterThan (3)    $ex5
  """

  val number = 6

  val ex1 = number must be equalTo 6
  val ex2 = number must be between (4, 8)
  val ex3 = number must be ~(5 +/- 1)
  val ex4 = number must beCloseTo(7, 1)
  val ex5 = number must beGreaterThan (3)
}
