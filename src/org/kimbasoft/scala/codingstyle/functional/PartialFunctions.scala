package org.kimbasoft.scala.codingstyle.functional

/**
 * Missing documentation
 *
 * @since 1.0
 */
object PartialFunctions {

  def concat(s1: String, s2: String): String = s1 + "-" + s2

  def main(args: Array[String]) {

    // define the function as value but leave the arguments open
    val c1 = concat _
    println(c1("Krause", "Steffen"))

    // define the function as value with partially providing the arguments
    val c2 = concat("Krause", _:String)
    println(c2("Jennifer"))

    //--- Utilizing Trait PartialFunction ---
    /* Composing a new function 'tester' out of the two partial functions 'truthier' and 'fallback'.
     * The first PartialFunction (truthier) implements only a part of a pattern matching clause, the
     * other part is being implemented by the second PartialFunction (fallback). */
    val truthier: PartialFunction[Boolean, String] = { case true => "truthful" }
    val fallback: PartialFunction[Boolean, String] = { case x => "sketchy" }
    val tester = truthier orElse fallback

    println(tester(1 == 1))
    println(tester(2 + 2 == 5))
  }
}
