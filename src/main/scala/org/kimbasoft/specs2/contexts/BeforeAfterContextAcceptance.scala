package org.kimbasoft.specs2.contexts

import org.specs2.Specification
import org.specs2.specification.{AfterEach, BeforeEach}
import org.specs2.specification.core.SpecStructure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class BeforeAfterContextAcceptance extends Specification with BeforeEach with AfterEach {
  
  def is: SpecStructure = s2"""
  This is a Before and After Context example:
       ex1: Hello must endWith (lo)      $ex1
       ex2: 25 must be between (10, 50)  $ex2
  """

  protected def before: Any = println("Before each")

  protected def after: Any = println("After each")

  val ex1 = "Hello" must endWith ("lo")
  val ex2 = 25 must be between (10, 50)
}
