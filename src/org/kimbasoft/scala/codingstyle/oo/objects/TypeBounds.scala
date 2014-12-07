package org.kimbasoft.scala.codingstyle.oo.objects

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object TypeBounds {

  class CSuper {
    def msuper = println("CSuper")
  }

  class C extends CSuper {
    def m = println("C")
  }

  class CSub extends C {
    def msub = println("CSub")
  }

  class Boundaries[A] {
    def process[B<:A](param: B) = println("Received: " + param)

  }


  def main(args: Array[String]) {
    val csup = new CSuper
    val c = new C
    val csub = new CSub

    val bounds: Boundaries[C] = new Boundaries[C]

    /* Will Work:
     * */
    bounds.process(csub)

    /* Will Work:
     * */
    bounds.process(c)

    /* Won't Work:
     * */
//  bounds.process(csup)
  }
}
