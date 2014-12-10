package org.kimbasoft.scala.codingstyle.functional.implicits

import org.kimbasoft.scala.codingstyle.functional.implicits.ImplicitTypeErasure.SeqPrinter

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ImplicitEvidence {

  /**
   * The object "<:<" ensures that the Type parameter set for this class is of
   * type String. This is a simple example of how a method can assure at compile
   * time that it is invoked on valid types only. String is a simplification to
   * demonstrate the functionality of the "<:<" object. A better example would
   * probably be a Tuple (A, B).
   */
  class Processor[T](val obj: T) {
    /**
     * One way of writing it: <:<[T, String]
     */
    def process1(implicit ev: <:<[T, String]) = "<" + obj.replace(' ', '_') + ">"

    /**
     * Another way of writing it: T <:< String
     */
    def process2(implicit ev: T <:< String) = ">" + obj.replace(' ', '+') + "<"
  }

  object Processor {
    def apply[T](obj: T) = new Processor(obj)
  }

  def main(args: Array[String]) {
    println("-- Implicit Evidence -------------------------")
    println(Processor("Hello World").process1)
    println(Processor("Hello World").process2)
    // println(Processor(125000).process)    // Will not compile because no "evidence" that Int is String
  }
}
