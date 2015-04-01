package org.kimbasoft.specs2.structure

// Specification class for a Unit Specification
import org.specs2.mutable.Specification

/**
 * Missing documentation
 *
 * @since 1.0
 */
class UnitSpec extends Specification {
  "This is my specification" >> {
    "where example 1 must be true" >> {
      1 must_== 1
    }
    "where example 2 must be true" >> {
      2 must_!= 3
    }
    "where example 3 must be true" >> {
      4 must_== 4
      5 must_!= 6
    }
  }
}
