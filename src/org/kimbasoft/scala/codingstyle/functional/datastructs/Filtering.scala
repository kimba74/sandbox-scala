package org.kimbasoft.scala.codingstyle.functional.datastructs

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
    println("Drop while value <= 3: " + valueList.dropWhile(num => num <= 3))

    println("-- Exist Values --------------------")
    println("Does number with sqr 25 exist? " + valueList.exists(num => num * num == 25))
    println("Does number with sqr 13 exist? " + valueList.exists(num => num * num == 13))

    println("-- Filtering Values ----------------")
    println("Even Values: " + valueList.filter(num => (num % 2) == 0))
    println("Odd Values : " + valueList.filter(num => (num % 2) > 0))

    println("-- Find Values ---------------------")
    println("Find value > 4: " + valueList.find(num => num > 4))
    println("Find value < 0: " + valueList.find(num => num < 0))

    println("-- For All Values ------------------")
    println("All values less 10 : " + valueList.forall(num => num < 10))
    println("All values are even: " + valueList.forall(num => num % 2 == 0))

    println("-- Partition Values ----------------")
    val (multiple, rest) = valueList.partition(num => num % 3 == 0)
    println("Multiple of 3 : " + multiple)
    println("Rest of values: " + rest)

  }
}
