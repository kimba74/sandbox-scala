package org.kimbasoft.scala.codingstyle.functional.dataoperations

/**
 * Missing documentation
 *
 * @since 1.0
 */
object FoldingReducing {

  def main(args: Array[String]) {
    val list = List(1, 2, 3, 4, 5, 6)

    /* Reducing Sequence, no seed can be provided */
    println("Reducing Left : " + list.reduceLeft((a1, a2) => a1 + a2))
    println("Reducing Right: " + list.reduceRight((a1, a2) => a1 + a2))

    /* The left folding function has the following abstract method signature:
     *
     *    def foldLeft[B](z: B)(op: (B,A) => B): B
     *
     * This function will apply function 'op' to all elements of the list starting with the
     * outer left element and working its way to the right. For the first call the initial
     * seed value 'z' will be passed to the function as first argument. For all subsequent
     * calls the return value of the previous function call will be passed as first argument.
     *
     * NOTE: This function is tail-recursive and therefore more optimal for large list than
     *       its foldRight cousin.
     * NOTE: The foldLeft function uses currying (multiple argument lists)
     */
    val foldL1 = list.foldLeft(List[String]()) { (lst, v) => "<" + v + ">" :: lst }
    val foldL2 = (List[String]() /: list) { (lst, v) => "<" + v + ">" :: lst } // equivalent to foldL1
    println("Folding Left 1 : " + foldL1)
    println("Folding Left 2 : " + foldL2)

    /* The right folding function has the following abstract method signature:
     *
     *    def foldRight[B](z: B)(op: (A,B) => B): B
     *
     * This function will apply function 'op' to all elements of the list starting with the
     * outer right element and working its way to the left. For the first call the initial
     * seed value 'z' will be passed to the function as first argument. For all subsequent
     * calls the return value of the previous function call will be passed as first argument.
     *
     * NOTE: The argument order of the required function is exactly the opposite as the one
     *       of its foldLeft cousin.
     * NOTE: The foldRight function uses currying (multiple argument lists)
     */
    val foldR1 = list.foldRight(List[String]()) { (v, lst) => "<" + v + ">" :: lst }
    val foldR2 = (list :\ List[String]()) { (v, lst) => "<" + v + ">" :: lst } // equivalent to foldR1
    println("Folding Right 1: " + foldR1)
    println("Folding Right 2: " + foldR2)
  }
}
