package org.kimbasoft.scala.methods

/**
 * Missing documentation
 *
 * @since 1.0
 */
object NestedFunctions {

  /**
   * Function with a nested recursive function. The nested function will use the same
   * parameter name as its enclosing function to demonstrate "shadowing" of parameters
   * and variables. (parameter and variables in nested functions that have the same name
   * as parameter and variables of the enclosing function will take priority (shadow) over
   * the outer ones without influencing their value)
   */
  def factorial(i: Int): Int = {
    /* Nested function uses same parameter name ('i') as enclosing function.
     * the nested functions parameter will "shadow" the outer parameter */
    def fact(i: Int, accumulator: Int): Int = {
      if (i <= 1)
        accumulator
      else
        fact(i - 1, i * accumulator) // Recursive call of nested function to itself
    }
    /* Initial call to the nested, recursive function */
    fact(i, 1)
  }

  /**
   * Function with a nested recursive function. The nested function will make use of the
   * parameter passed into the enclosing function to demonstrate the scoping of parameters
   * and variables. (parameter and variables in the outer function are in the scope of nested
   * function but not vice versa)
   */
  def countTo(n: Int):Unit = {
    /* Nested function will make use of 'n' since it is in scope */
    def count(i: Int): Unit ={
      if (i <= n) {
        println(i)
        count(i + 1) // Recursive call of nested function to itself
      }
    }
    /* Initial call to the nested, recursive function */
    count(1)
  }

  def main(args: Array[String]) {
    println("-- Factorial -------------")
    println(factorial(0))
    println(factorial(1))
    println(factorial(2))
    println(factorial(3))
    println(factorial(4))
    println(factorial(5))
    println("-- Count To --------------")
    countTo(6)
  }
}
