package org.kimbasoft.specs2.contexts

import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAll

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class BeforeAllContext extends Specification with BeforeAll {

  "Example 1 must be OK" >> {
    println("executing example1")
    ok
  }
  "Example 2 must be OK" >> {
    println("executing example2")
    ok
  }

  def beforeAll(): Unit = println("Before All")
}
