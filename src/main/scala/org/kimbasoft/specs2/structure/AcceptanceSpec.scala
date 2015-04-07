package org.kimbasoft.specs2.structure

// Specification class for an Acceptance Specification
import org.specs2.Specification
import org.specs2.specification.core.SpecStructure

/**
 * The Acceptance style specification extends 'org.specs2.Specification' and operates
 * in the Functional Expectation mode. This means the final result of a block is
 * always returned by the last test in the block.
 *
 * Example:
 *        def exp = {
 *           1 must be equalTo 4
 *           3 must beGreaterThan 1
 *        }
 *
 * Even though the first statement in the block is 'false' the block will return 'true'
 * since the last statement returns true. This behavior can either be changed by chaining
 * the two statements via 'and' (thereby making them one statement) or by changing the
 * Specifications default Functional Expectation behavior to the Thrown Expectation behavior.
 * Latter can be achieved by also implementing the 'org.specs2.matcher.ThrownExpectations'
 * trait.
 */
class AcceptanceSpec extends Specification {
  def is: SpecStructure = s2"""
  This is my Specification:
     where example 1 must be true    $e1
     where example 2 must be true    $e2
     where example 3 must be true    $e3
                                     """
  def e1 = 1 must_== 1
  def e2 = 2 must_!= 3
  def e3 = (4 must_== 4) and (5 must_!= 6)  //Chaining of Expectations in an Acceptance Specification
}
