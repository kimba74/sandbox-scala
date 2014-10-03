package org.kimbasoft.scala.general

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Loops {

  def main(args: Array[String]) {
    // Regular For loop (from 1 - 9)
    println("-- For Loop ------------------------")
    for (i <- 1 to 9)
    println(s"$i.> Iteration")

    // Foreach loop over collection
    println("-- For-Each Loop -------------------")
    val str = Array[String]("This", "is", "a", "String", "array")
    for (s <- str)
      println(s"item> $s")
  }
}
