package org.kimbasoft.scala.oo.methods

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object MethodBinding {

  def main(args: Array[String]) {
    /* Scala allows for methods that only take one or no parameter at all to be
     * written like an expression by dropping the '.' and the '()'. For functions
     * with only one parameter this notation style is called 'infix'
     * Example (infix):
     *    Invocation         number.add(25)
     *    Can be written as  number add 25
     *
     * Functions with no parameter at all can be handled similarly. In this case
     * the notation style is called 'postfix'
     * Example (postfix):
     *    Invocation         "Hello World".length()
     *    Can be written as  "Hello World" length
     *
     * Depending on the function's name this infix notation changes from the regular
     * Left-to-Right interpretation to a Right-to-Left one. This happens when a
     * function name ends with a ':'.
     */
    val list = List('b', 'c', 'd')

    /* The following two examples show a regular function invocation and its normal
     * Left-to-Right infix notation */
    println(list.++(List('1', '2'))) // Regular method invocation
    println(list ++ List('1', '2'))  // Left-to-Right 'infix' notation

    /* The next two examples show an invocation of a function with a name ending in ':'
     * and its Right-to-Left infix notation */
    println(list.::('a')) // Regular method invocation
    println('a' :: list)  // Right-to-Left 'infix' notation

    val mt = new MyTest

    'a' %: mt
    mt % 'b'

    'b' prepend_: mt
    mt append 'c'
  }

  class MyTest {

    def %: (char: Char): String = "Hello World"

    def % (char: Char): String = "Some String"
    
    def prepend_: (char: Char): String = "Hello World"

    def append (char: Char): String = "Hello World"
  }
}
