package org.kimbasoft.scala.codingstyle.functional

/**
 * Missing documentation
 *
 * @since 1.0
 */
object RecursiveLoops {

  /**
   * Imperative loop
   *
   * This is the standard straight forward, iterative approach. It creates
   * a finite loop that iterates over the task n times and calculates the
   * final value. However, an iterative approach violates the functional
   * approach in so far as it requires a mutable variable (e.g. result)
   * that holds the calculated value and later returns it. To better comply
   * with the functional paradigm of immutable values a different approach
   * needs to be taken, this approach is recursion.
   */
  def factorialI(i: BigInt): BigInt = {
    var result = BigInt(1)
    for (c <- 1 to i.intValue())
      result *= c
    result
  }

  /**
   * Recursive loop
   *
   * The recursive loop will call itself until the exit criteria is
   * reached. Then it will "backwards" calculate the value by using
   * the return value of its own invocation of itself. In other words,
   * the function yields at the invocation of itself, then, when this
   * invocation returns it calculates the value and returns it.
   *
   * Recursive call for Factorial 5:
   *
   *     5 x (4 x (3 x (2 x (1))))
   *
   * This approach can be very memory intense especially with great
   * recursion depths. A better alternative is the Tail-Recursion.
   */
  def factorialR(i: BigInt): BigInt = i match {
    case _ if i == 1 => i
    case _ => i * factorialR(i - 1) // The result value is calculated AFTER the last recursive call to itself
  }

  /**
   * Tail-Recursive loop
   *
   * A Tail-Recursive loop will, other than its recursive brother, not
   * wait for the last function call to return before calculating the value.
   * It will rather calculate the value first and pass it as accumulator
   * parameter to itself thus keeping the impact on the memory to a minimum.
   *
   * Tail-Recursive call for Factorial 5:
   *
   *     ((((1 x 5) x 4) x 3) x 2)
   *
   * Because of the calculate-first-call-last the Tail-Recursion is especially
   * useful with loops that are expected to have a high recursion depth.
   * This function encapsulates a Tail-Recursive function and exposes a
   * simplified abstraction to the user. This approach is recommended when
   * trying to prevent the user from passing invalid values to the accumulator.
   */
  def factorialT(i: BigInt): BigInt = {
    def fact(i: BigInt, accu: BigInt): BigInt = i match {
      case _ if i == 1 => accu
      case _ => fact(i - 1, accu * i) // The result value is calculated BEFORE the recursive call to itself
    }
    fact(i, 1)
  }

  def main(args: Array[String]) {
    println("-- Imperative Loop -----------------")
    for (i <- 1 to 10) println(i +": " + factorialI(i))
    println("-- Recursive Loop ------------------")
    for (i <- 1 to 10) println(i +": " + factorialR(i))
    println("-- Tail Recursive Loop -------------")
    for (i <- 1 to 10) println(i +": " + factorialT(i))
  }
}
