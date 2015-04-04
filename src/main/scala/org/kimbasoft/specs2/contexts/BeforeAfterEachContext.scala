package org.kimbasoft.specs2.contexts

import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAfterEach

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class BeforeAfterEachContext extends Specification with BeforeAfterEach {

  "Example 1 must be OK" >> {
    println("executing example1")
    ok
  }
  "Example 2 must be OK" >> {
    println("executing example2")
    ok
  }

  protected def before: Any = println("Before each")

  protected def after: Any = println("After each")
}
