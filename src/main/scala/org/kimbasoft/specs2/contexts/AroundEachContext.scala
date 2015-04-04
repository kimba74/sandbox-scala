package org.kimbasoft.specs2.contexts

import org.specs2.execute.{Result, AsResult}
import org.specs2.mutable.Specification
import org.specs2.specification.AroundEach

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class AroundEachContext extends Specification with AroundEach {

  protected def around[R: AsResult](r: => R): Result = {
    println("Around each before")
    try AsResult(r)
    finally println("Around each after")
  }

  "Example 1 must be OK" >> {
    println("executing example1")
    ok
  }

  "Example 2 must be OK" >> {
    println("executing example2")
    ok
  }
}
