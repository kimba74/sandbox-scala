package org.kimbasoft.scala.methods

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object FunctionLiteral {

  /**
   * Example method that takes two integers and a Function Literal as parameter.
   * The Function Literal is further defined to take one Int parameter and return
   * an Int. The format of defining a Function Literal is:
   *
   *    name: ([parameter,...]) => ReturnType
   */
  def sum(from: Int, to: Int, base: (Int) => Int): Int = if (from > to) 0 else base(from) + sum(from + 1, to, base)

  /**
   * Two simple methods that take one Int parameter and return an Int value.
   * The same method signature as defined by the Function Literal in the first
   * example method.
   */
  def self(num: Int): Int = num
  def square(num: Int): Int = num * num

  /**
   * Two simple 'facade' methods that take two Int parameter and pass them on to
   * the first method together with one of the previous two methods as Function Literal.
   */
  def sumSelf(from: Int, to: Int): Int = sum(from, to, self)
  def sumSquare(from: Int, to: Int): Int = sum(from, to, square)

  def main(args: Array[String]) {
    /* A Function can be as easily created as shown below. This example is called
     * an Anonymous Function, a function without name is defined and it's return value is
     * assigned to the variable 'add'. This way the function can be used like a variable
     * itself. An Anonymous Function is defined the following way:
     *
     *     ([name:parameter,...]) => implementation
     *
     * In case of a longer implementation that wraps over multiple lines the implementation
     * must be enclosed by curly parenthesis {...}
     */
    println("-- Simple Anonymous Function -------")
    val add = (a:Int, b:Int) => a + b
    println(add(1,2))

    /* The following example demonstrates an Anonymous Function to be used as Function Literal
     * of the 'sum' function. The anonymous function is being defined within the function call
     * following the same pattern:
     *
     *     ([name:parameter,...]) => implementation
     */
    println("-- Anonymous Function Literal ------")
    println("self  : " + sum(1, 3, (n: Int) => n))
    println("square: " + sum(1, 3, (n: Int) => n * n))

    /* The next example calls the previously defined 'sum' with one of the other functions
     * defined as a Function Literal. Since the 'sum' function defines the signature of the
     * Function Literal only the function name has to be provided. In case of function
     * overloading Scala automatically determines which function matches the definition of
     * the called method and picks it.
     */
    println("-- Named Function Literal ----------")
    println("self  : " + sum(1, 4, self))
    println("square: " + sum(1, 4, square))

    /* The last example simply calls the prepared methods that each call the 'sum' function
     * with one of the pre-defined functions as Function Literal.
     */
    println("-- Prepared Function Literal -------")
    println("self  : " + sumSelf(1, 5))
    println("square: " + sumSquare(1, 5))
  }
}
