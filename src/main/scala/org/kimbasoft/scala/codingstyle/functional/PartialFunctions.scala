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

    //--- Using multiple PartialFunction Trait implementations ---
    val pf1: PartialFunction[Any,String] = { case i: Int     => s"object $i is an Int" }
    val pf2: PartialFunction[Any,String] = { case d: Double  => s"object $d is a Double" }
    val pf3: PartialFunction[Any,String] = { case b: Boolean => s"object $b is a Boolean" }
    val pf4: PartialFunction[Any,String] = { case s: String  => s"""object "$s" is a String""" }
    val pf5: PartialFunction[Any,String] = { case x => s"""object "$x" of unknown type""" }
    val pf = pf1 orElse pf2 orElse pf3 orElse pf4 orElse pf5

    println(pf("Hello World"))
    println(pf(16))
    println(pf(true))
    println(pf(34.8))
    println(pf(21.9F))
  }
}
