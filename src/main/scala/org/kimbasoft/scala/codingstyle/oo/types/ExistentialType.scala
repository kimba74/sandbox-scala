package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ExistentialType extends App {

  /**
   * Method using parameter with Existential Type to deal with Type Erasure
   * of parametrized Objects at runtime. Especially useful when working with
   * Java classes.
   */
  def double(seq: Seq[_]): Seq[Int] = seq match {
    case Nil => Nil
    case head +: tail => (toInt(head) * 2) +: double(tail)
  }

  def toInt(x: Any): Int = x match {
    case i: Int => i
    case s: String => s.toInt
    case z => throw new RuntimeException(s"Unexpected list element $z")
  }


  val seq1: Seq[Any] = Seq("1", 2, "3", 4, "5", 6)
  val seq2: Seq[Int] = double(seq1)
  println(seq2)
}
