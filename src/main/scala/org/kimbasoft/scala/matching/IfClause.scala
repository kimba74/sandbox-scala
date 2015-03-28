package org.kimbasoft.scala.matching

import scala.util.Random

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object IfClause extends App {
  // Regular If-Statement
  val num1 = Random.nextInt(10)
  if (num1 <= 5)
    println(s"$num1 <= 5")
  else
    println(s"$num1 > 5")

  // Assignment of If-Statement result
  val num2 = Random.nextInt(10)
  val result = if (num2 <= 5) s"$num2 <= 5" else s"$num2 > 5"
  println(result)
}
