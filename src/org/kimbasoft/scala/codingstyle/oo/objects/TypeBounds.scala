package org.kimbasoft.scala.codingstyle.oo.objects

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object TypeBounds {

  class CSuper
  class C extends CSuper
  class CSub extends C


  class Boundaries[P1] {
    /**
     *
     */
    def upperBound[A<:P1](param: A) = println("Upper Bound: " + param.getClass)

    /**
     *
     */
    def lowerBound[B>:P1](param: B): B = { println("Lower Bound: " + param.getClass); param }
  }


  def main(args: Array[String]) {
    val csup = new CSuper
    val c = new C
    val csub = new CSub

    val bounds: Boundaries[C] = new Boundaries[C]

    println("-- Upper Bounds ------------------------------")

    /* Will Work:
     * */
    bounds.upperBound(csub)

    /* Will Work:
     * */
    bounds.upperBound(c)

    /* Won't Work:
     * */
//  bounds.upperBound(csup)

    println("-- Lower Bounds ------------------------------")

    /* Will Work:
     * */
    val boundCSup: CSuper = bounds.lowerBound(new CSuper)

    /* Will Work:
     * */
    val boundC: C = bounds.lowerBound(new C)

    /* Won't Work:
     * */
//  val boundCSub: CSub = bounds.lowerBound(new CSub)
  }
}
