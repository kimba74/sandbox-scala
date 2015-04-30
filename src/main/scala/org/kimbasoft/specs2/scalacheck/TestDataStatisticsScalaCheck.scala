package org.kimbasoft.specs2.scalacheck

import org.scalacheck.Prop._

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object TestDataStatisticsScalaCheck extends App {

  def ordered(list: List[Int]) = list == list.sortWith(_ > _)

  val collector1 = forAll { list: List[Int] =>
    /* The first parameter list defines the check to be performed on the test
     * data in order to collect statistics about them. In this case classify
     * distinguishes between 'ordered' and 'unordered' lists being used as random
     * data generated for the test. The test itself is being specified in the
     * second parameter list. In this case we don't perform a real test because
     * for this example we are only interested in the test data statistics */
    classify(ordered(list), "ordered")(list == list)
  }
  collector1.check


  val collector2 = forAll { list: List[Int] =>
    /* This classify method takes three parameters. The first is the criteria,
     * the second is the value returned if the criteria is true, the third is
     * the value to be returned when the criteria is false */
    classify(list.length > 5, "large", "small")(list == list)
  }
  collector2.check


  val collector3 = forAll { list: List[Int] =>
    // Collect statistics on how many ordered lists are used for tests
    classify(ordered(list), "ordered") {
      // Collect statistics on how many small and large lists are being used for tests
      classify(list.length > 5, "large", "small") {
        list.reverse.reverse == list
      }
    }
  }
  collector3.check
}
