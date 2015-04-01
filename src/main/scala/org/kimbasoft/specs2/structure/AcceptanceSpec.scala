package org.kimbasoft.specs2.structure

// Specification class for an Acceptance Specification
import org.specs2.Specification
import org.specs2.specification.core.SpecStructure

/**
 * Missing documentation
 *
 * @since 1.0
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
