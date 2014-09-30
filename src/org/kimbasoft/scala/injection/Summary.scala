package org.kimbasoft.scala.injection

/**
 * Missing documentation
 *
 * @since 1.0
 */
object Summary {

  private def sum(f: Int => Int, a: Int, b: Int): Int = if (a > b) 0 else f(a) + sum(f, a + 1, b)

  private def id(x: Int): Int = x
  private def square(x: Int): Int = x * x

  def sumInts(a:Int, b:Int): Int = sum(id, a, b)
  def sumSquares(a:Int, b:Int): Int = sum(square, a, b)

  def main(args:Array[String]) {
    println(s"Sum Integers 1-4 = ${sumInts(1, 4)}")
    println(s"Sum Squares  1-4 = ${sumSquares(1, 4)}")
  }
}
