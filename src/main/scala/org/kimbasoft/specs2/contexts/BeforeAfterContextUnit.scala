package org.kimbasoft.specs2.contexts

import org.specs2.mutable.Specification
import org.specs2.specification.{AfterEach, BeforeEach}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class BeforeAfterContextUnit extends Specification with BeforeEach with AfterEach {

  "This is a Before and After Context example:" >> {
    "ex1: Hello must endWith (lo)" >> {
      "Hello" must endWith ("lo")
    }
    "ex2: 25 must be between (10, 50)" >> {
      25 must be between (10, 50)
    }
  }

  protected def before: Any = println("Before each")

  protected def after: Any = println("After each")
}
