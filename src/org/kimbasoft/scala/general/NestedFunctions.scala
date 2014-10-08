package org.kimbasoft.scala.general

/**
 * Missing documentation
 *
 * @since 1.0
 */
object NestedFunctions {

  def factorial(i: Int): Int = {
    def fact(i: Int, accumulator: Int): Int = {
      if (i <= 1)
        accumulator
      else
        fact(i - 1, i * accumulator)
    }
    fact(i, 1)
  }

  def countTo(n: Int):Unit = {
    def count(i: Int): Unit ={
      if (i <= n) {
        println(i)
        count(i + 1)
      }
    }
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
