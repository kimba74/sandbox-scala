package org.kimbasoft.scala.general

/**
 * Missing documentation
 *
 * @since 1.0
 */
object Tuples {

  /**
   * Defining a Tuple as return type is simply done by setting the functions
   * return type to a Tuple definition, e.g. (Int, String, Double, Boolean)
   */
  def createTuple(int:Int, dbl:Double, str:String): (Int, Double, String) = (int, dbl, str)

  /**
   * Passing a Tuple to a function as parameter is as simple as setting it
   * as return type for a function. We just have to define a function parameter
   * and set its type tu a Tuple definition, e.g. (String, Int, Int, Boolean)
   */
  def printTuple(tuple: (Int, Double, String)) = println(tuple)

  def main(args: Array[String]) {
    // Create Tuple manually with declared types and order
    val tuple1: (Int, Double, String) = (2, 5.3, "The Text")

    // Create Tuple manually with type inference and unknown order
    val tuple2 = (6.4, "Something", 9)

    // Assigning Tuple to constant from function return value
    val tuple3 = createTuple(10, 37.5, "Another Text")

    // Decomposing Tuple and assigning it to individual constants
    val (int, dbl, str) = createTuple(18, 1.83, "A word")

    // Passing Tuple as parameter to a function
    printTuple(2, 3.1, "La la la")

    // Defining Tuple2 instances in different ways
    val tuple4a = (12, "Ah")
    val tuple4b = 73 -> "Hmm"
    val tuple4c = Pair(88, "OK")
    val tuple4d = Tuple2(42, "That's it")
  }
}
