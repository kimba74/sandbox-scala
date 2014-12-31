package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SelfRecursiveType {

  /**
   * Trait Parent has a Self-Recursive Type in that T must be a 
   * sub-type of Parent[T].
   */
  trait Parent[T <: Parent[T]] {
    def make: T
  }

  case class Child1(name: String) extends Parent[Child1] {
    def make: Child1 = Child1(s"Child1: make: $name")
  }

  case class Child2(name: String) extends Parent[Child2] {
    def make: Child2 = Child2(s"Child2: make: $name")
  }

  def main(args: Array[String]) {
    // Creating the root objects from the Child1 and Child2 implementations
    val c1 = Child1("c1")
    val c2 = Child2("c2")
    println(s"c1 = $c1")
    println(s"c2 = $c2")

    // Creating children objects from the Child1 and Child2 root objects using the make() method
    val c11 = c1.make
    val c22 = c2.make
    println(s"c11 = $c11")
    println(s"c22 = $c22")

    // Assigning the Child1 and Child2 root objects to variables of the Parent trait type
    val p1: Parent[Child1] = c1
    val p2: Parent[Child2] = c2
    println(s"p1 = $p1")
    println(s"p2 = $p2")

    /* make() being called on Parent[T] will produce an object of
     * the concrete implementation rather than the Parent type. */
    val p11 = p1.make
    val p22 = p2.make
    println(s"p11 = $p11")
    println(s"p22 = $p22")
  }
}
