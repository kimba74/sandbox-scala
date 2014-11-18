package org.kimbasoft.scala.codingstyle.functional.implicits

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ImplicitEvidence {

  /**
   * The object "<:<" ensures that the Type parameter of the set for this class
   * is of type String. This is a simple example of how a method can assure at
   * compile time that it is invoked on valid types only.
   * String is a simplification to demonstrate the functionality of the "<:<"
   * object. A better example would probably be a Tuple (A, B).
   */
  class Processor[T](val obj: T) {
    def process(implicit ev: <:<[T, String]) = "<" + obj.replace(' ', '_') + ">"
  }

  object Processor {
    def apply[T](obj: T) = new Processor(obj)
  }

}
