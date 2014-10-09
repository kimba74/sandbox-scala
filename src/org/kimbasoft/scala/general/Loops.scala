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

    // Empty for loop (?)
    println("-- Empty For Loop ------------------")
    for (_ <- 1 to 9)
      println("empty> iteration")

    // Foreach loop over collection
    println("-- For-Each Loop -------------------")
    val str = Array[String]("This", "is", "a", "String", "array")
    for (s <- str)
      println(s"item> $s")

    // Simplified foreach loop over same collection. Suitable for singe statement bodied loops.
    str.foreach(printf("item> %s%n", _))
  }
}
