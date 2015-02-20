package org.kimbasoft.scala.codingstyle.functional.implicits

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ImplicitTypeErasure {

  implicit object IntMarker

  implicit object StringMarker

  /**
   * This object defines two methods that, aside from the type parameters are
   * absolutely identical. Type parameters are mainly used to guide developers.
   * Due to Type Erasure during compilation the compiler will not allow this kind
   * of method overloading. However, Scala permits a workaround to make the methods
   * different from each other, this mechanism is called implicit evidence for
   * type erasure. To used this workaround one simply defines implicit "marker"
   * objects and appends the method with a second parameter list taking an implicit
   * argument of the marker types.
   */
  object SeqPrinter {
    def print(seq: Seq[Int])(implicit ev: IntMarker.type) = println("Integer Seq: " + seq)

    def print(seq: Seq[String])(implicit ev: StringMarker.type) = println("String Seq : " + seq)
  }

  def main(args: Array[String]) {
    println("-- Implicit Prevent Type Erasure -------------")
    SeqPrinter.print(List(1, 2, 3, 4, 5, 6))
    SeqPrinter.print(List("A", "B", "C", "D", "E", "F"))
  }
}
