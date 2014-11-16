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

  object SeqPrinter {
    def print(seq: Seq[Int])(implicit ev: IntMarker.type) = println("Integer Seq: " + seq)

    def print(seq: Seq[String])(implicit ev: StringMarker.type) = println("String Seq : " + seq)
  }
}
