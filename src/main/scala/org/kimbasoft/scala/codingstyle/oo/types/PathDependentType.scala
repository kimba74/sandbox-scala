package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object PathDependentType {

  trait X {
    def setXX(value: String) = {}
  }

  class C1 {
    private var x = "empty"
    def setX1(value: String) = this.x = value    // In Scala 'this' is shorthand for C1.this
    def setX2(value: String) = C1.this.x = value // The usage of C1.this is equivalent to the shorthand above
  }

  class C2 extends C1

  class C3 extends C2 with X {
    def setX3(value: String) = super.setX1(value)
    def setX4(value: String) = C3.super.setX1(value)
    def setX5(value: String) = C3.super[C2].setX1(value)
    def setX6(value: String) = C3.super[X].setXX(value)
//    def setX7(value: String) = C3.super[C1].setX2(value)   // Won't compile because grandparents can't be referenced
//    def setX8(value: String) = C3.super.super.setX2(value) // Won't compile because super calls can't be chained
  }
}
