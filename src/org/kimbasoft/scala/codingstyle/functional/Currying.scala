package org.kimbasoft.scala.codingstyle.functional

/**
 * Currying, named after mathematician Haskell Curry, describes the
 * practice of chaining functions. In Scala currying takes a function
 * definition that declares multiple argument lists and transforms it
 * into a chain of functions that each take a single argument.
 *
 * Currying has proven especially useful during the composition of
 * partially applied functions.
 */
object Currying {

  def concat1(s1: String)(s2: String) = s1 +  s2

  def concat2(s1: String) = (s2: String) => s1 + s2

  /**
   * Currying example of a function with three parameter lists, each
   * containing a single parameter.
   */
  def concatLong1(s1: String)(s2: String)(s3: String) = s1 + s2 + s3

  /**
   * Same as concatLong1() but different notation. This form concatenates
   * multiple functions:
   *
   *    f(String) = ((String) => ((String) => ...))
   */
  def concatLong2(s1: String) = (s2: String) => (s3: String) => s1 + s2 +s3

  def concatNormal(s1: String, s2: String) = s1 + s2

  def multiplier(i: Int)(factor: Int) = i * factor

  /**
   * Use Case of a curried function. This function takes a Boolean condition
   * and a function. It will loop tail recursively and execute the higher
   * function until the condition returns 'false'.
   * See "CallBy" example for explanation of the 'condition' expression.
   */
  def myWhile(condition: => Boolean)(f: => Unit): Unit = {
    if (condition) {
      f
      myWhile(condition)(f)
    }
  }

  def main(args: Array[String]) {
    // Normal invocation of curried functions
    println(concat1("foo")("bar"))
    println(concat2("sna")("fu"))
    println(concatLong1("Li")("La")("Long"))
    println(concatLong2("Bla")("fa")("sl"))

    /* Invocation of short currying function with two argument lists.
     * The trailing underscore indicates to the compiler that the remaining
     * arguments (in this case one) need to be provided by the caller
     * of the partial function. */
    val foo = concat1("foo")_
    println(foo("bear"))

    /* Invocation of short currying function with two argument lists.
     * This case does not require the trailing underscore since it has
     * been defined with a function chaining rather than the shorthand
     * form of multiple argument lists */
    val out = concat2("out")
    println(out("standing"))

    /* Invocation of long currying function with three or more argument lists
     * The trailing underscore indicates to the compiler that the remaining
     * arguments need to be provided by the caller of the partial function. */
    val long1_1 = concatLong1("abc") _
    val long1_2 = concatLong1("abc") ("def") _
    println(long1_1("DEF")("123"))
    println(long1_2("123"))

    /* Invocation of short currying function with two argument lists.
     * This case does not require the trailing underscore since it has
     * been defined with a function chaining rather than the shorthand
     * form of multiple argument lists */
    val long2_1 = concatLong2("123")
    val long2_2 = concatLong2("123")("456")
    println(long2_1("ABC")("DEF"))
    println(long2_2("ABC"))

    /* Sometimes, when trying to create partial function from a regular function
     * definition it would be helpful, if the regular function was curried rather
     * than providing a lengthy argument list. Scala provides the possibility of
     * turning a function defined with a regular argument list into a curried
     * version by invoking the .curried method on the function object itself.
     *
     * NOTE: Don't forget, in Scala functions are objects, too! */
    val curryCat3 = (concatNormal _).curried
    val un = curryCat3("un")
    println(un("successful"))

    // Use-Case of partial implementation of curried function
    val byFive = multiplier(5) _
    val byTen  = multiplier(10) _
    println("5 * 2 = " + byFive(2))
    println("10 * 2 = " + byTen(2))

    /* The function declared in the second parameter list can also be written/implemented
     * in a code block {...} making the function call look more like a regular
     * loop call. */
    var count = 0
    myWhile(count < 5){
      println(count + ". iteration")
      count += 1
    }
  }
}
