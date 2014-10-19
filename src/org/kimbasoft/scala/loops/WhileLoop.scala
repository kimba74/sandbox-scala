package org.kimbasoft.scala.loops

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object WhileLoop {

  def main(args: Array[String]) {

    // Regular While Loop (nothing special)
    println("-- While Loop ----------------------")
    var count = 0
    while(count < 10) {
      count += 1
      println(s"> $count ")
    }

    // Regular Do-While Loop (nothing special)
    println("-- Do-While Loop -------------------")
    count = 0
    do {
      count += 1
      println(s"> $count")
    } while(count < 10)
  }
}
