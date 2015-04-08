package org.kimbasoft.specs2.structure

// Specification class for a Unit Specification
import org.specs2.mutable.Specification

/**
 * The Unit style specification extends 'org.specs2.mutable.Specification' and operates
 * in the Thrown Expectation mode. This means the first failure encountered in a block
 * of expectations throws an exception, which will be interpreted as the block's final result.
 *
 * Example:
 *        def exp = {
 *           1 must be equalTo 4
 *           3 must beGreaterThan 1
 *        }
 *
 * The first statement in the block is 'false', the block will thrown an exception, and the
 * the last statement of the block will never be evaluated since the result has already been
 * given. This behavior can be altered by changing the Specification's default Thrown Expectation
 * to the Functional Expectation behavior. The behavior change can be achieved by also implementing
 * the 'org.specs2.matcher.NoThrownExpectations' trait.
 */
class UnitSpec extends Specification {   // with NoThrownExpectations
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
