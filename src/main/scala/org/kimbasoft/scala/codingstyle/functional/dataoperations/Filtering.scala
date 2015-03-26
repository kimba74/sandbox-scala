package org.kimbasoft.scala.codingstyle.functional.dataoperations

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Filtering {

  def main(args: Array[String]) {

    val valueList = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    println("All Values : " + valueList)

    println("-- Dropping Values -----------------")
    println("Last 5 Values        : " + valueList.drop(5))
    println("First 5 Values       : " + valueList.dropRight(5))
    println("Drop while value <= 3: " + valueList.dropWhile(_ <= 3))

    println("-- Exist Values --------------------")
    println("Does number with sqr 25 exist? " + valueList.exists(num => num * num == 25))
    println("Does number with sqr 13 exist? " + valueList.exists(num => num * num == 13))

    println("-- Filtering Values ----------------")
    println("Even Values: " + valueList.filter(_ % 2 == 0))
    println("Odd Values : " + valueList.filter(_ % 2 > 0))

    println("-- Find Values ---------------------")
    println("Find value > 4: " + valueList.find(_ > 4))
    println("Find value < 0: " + valueList.find(_ < 0))

    println("-- For All Values ------------------")
    println("All values less 10 : " + valueList.forall(_ < 10))
    println("All values are even: " + valueList.forall(_ % 2 == 0))

    println("-- Index of Values ------------------")
    println("Index of '5' : " + valueList.indexOf(5) )
    println("Index of '10': " + valueList.indexOf(10))

    println("-- Partition Values ----------------")
    val (multiple, rest) = valueList.partition(_ % 3 == 0)
    println("Multiple of 3 : " + multiple)
    println("Rest of values: " + rest)

  }
}
