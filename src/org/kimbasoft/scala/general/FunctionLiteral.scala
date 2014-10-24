package org.kimbasoft.scala.general

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object FunctionLiteral {

  def sum(from: Int, to: Int, base: (Int) => Int): Int = if (from > to) 0 else base(from) + sum(from + 1, to, base)

  def self(num: Int): Int = num
  def square(num: Int): Int = num * num

  def sumSelf(from: Int, to: Int): Int = sum(from, to, self)
  def sumSquare(from: Int, to: Int): Int = sum(from, to, square)

  def main(args: Array[String]) {
    // Anonymous Functions
    println("-- Simple Anonymous Function -------")
    val add = (a:Int, b:Int) => a + b
    println(add(1,2))

    // Anonymous Function Literal
    println("-- Anonymous Function Literal ------")
    println("self  : " + sum(1, 3, (n: Int) => n))
    println("square: " + sum(1, 3, (n: Int) => n * n))

    // Named Functions Literal
    println("-- Named Function Literal ----------")
    println("self  : " + sum(1, 4, self))
    println("square: " + sum(1, 4, square))

    // Prepared Functions Literal
    println("-- Prepared Function Literal -------")
    println("self  : " + sumSelf(1, 5))
    println("square: " + sumSquare(1, 5))
  }
}
