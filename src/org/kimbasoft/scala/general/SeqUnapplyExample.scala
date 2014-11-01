package org.kimbasoft.scala.general

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SeqUnapplyExample {

  def main(args: Array[String]) {
    // Companion Object apply() factory method
    val seqUn = SeqUnapply(1, 2, 3, 4, 5)

    // Companion Object unapplySeq() in pattern matching
    seqUn match {
      case s @ SeqUnapply(a, b, _*) => print("Has at least two items (" + a + ", " + b + ") length = " + s.length)
      case _ => println("Has less than two items")
    }
  }
}
