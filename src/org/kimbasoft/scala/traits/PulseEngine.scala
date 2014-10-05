package org.kimbasoft.scala.traits

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
trait PulseEngine extends Spacecraft {
  val maxPulse: Int
  var currentPulse: Int = 0

  def speedup(): Unit = if (currentPulse < maxPulse) currentPulse += 1
}
