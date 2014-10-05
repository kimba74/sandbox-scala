package org.kimbasoft.scala.traits

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
trait CommandoBridge extends Spacecraft {

  def engage(): Unit = for (_ <- 1 to 3) speedup()

  def speedup(): Unit

}
