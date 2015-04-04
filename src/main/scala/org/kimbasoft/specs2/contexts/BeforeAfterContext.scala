package org.kimbasoft.specs2.contexts

import org.specs2.mutable.Specification
import org.specs2.specification.{AfterEach, BeforeEach}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class BeforeAfterContext extends Specification with BeforeEach with AfterEach {

  "This is a Before and After Context example:" >> {
    "Example 1 must be OK" >> {
      println("example1"); ok
    }
    "Example 2 must be OK" >> {
      println("example2"); ok
    }
  }

  protected def before: Any = println("Before each")

  protected def after: Any = println("After each")
}
