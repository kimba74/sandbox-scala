package org.kimbasoft.specs2.matcher

import org.specs2.Specification
import org.specs2.specification.core.SpecStructure

import scala.language.postfixOps

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class StringMatchers extends Specification {
  def is: SpecStructure = s2"""
  Some String Matchers:
       ex1: "Hello" must not be empty          $ex1
       ex2: "Hello" must be matching "Hello"   $ex2
       ex3: "Hello" must have length 5         $ex3
       ex4: "Hello" must contain "ll"          $ex4
  """

  val string = "Hello"

  val ex1 = string must not be empty
  val ex2 = string must be matching "Hello"
  val ex3 = string must have length 5
  val ex4 = string must contain ("ll")
}
