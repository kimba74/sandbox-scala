package org.kimbasoft.scala.codingstyle.functional.implicits

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ImplicitEvidence {

  class Processor[T](val obj: T) {
    def process(implicit ev: <:<[T, String]) = "<" + obj.replace(' ', '_') + ">"
  }

  object Processor {
    def apply[T](obj: T) = new Processor(obj)
  }

}
