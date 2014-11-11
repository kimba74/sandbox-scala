package org.kimbasoft.scala.codingstyle.functional.datastructs

/**
 * Missing documentation
 *
 * @since 1.0
 */
object FoldingReducing {

  def main(args: Array[String]) {
    val list = List(1, 2, 3, 4, 5, 6)

    /* Reducing Sequence, no seed can be provided */
    println("Reducing: " + list.reduceLeft(_ + _))

    /* Folding Sequence, seed of '0' provided
     * First argument of function is the seed value, the second the value of the list item. */
    println("Folding : " + list.foldLeft(0)(_ + _))
  }
}
