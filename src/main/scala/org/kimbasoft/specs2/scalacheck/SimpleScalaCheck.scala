package org.kimbasoft.specs2.scalacheck

import org.specs2.specification.core.SpecStructure
import org.specs2.{Specification, ScalaCheck}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class SimpleScalaCheck extends Specification with ScalaCheck {
  def is: SpecStructure = s2"""
    Boolean    : addition and multiplication are related ${ prop { (a: Int) => a + a == 2 * a } }
    MatchResult: addition and multiplication are related ${ prop { (a: Int) => a + a must_== 2 * a } }
    Prop       : addition and multiplication are related ${ prop { (a: Int) => (a > 0) ==> (a + a must_== 2 * a) } }
  """
}
