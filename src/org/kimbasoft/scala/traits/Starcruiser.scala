package org.kimbasoft.scala.traits

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class Starcruiser extends Spacecraft with CommandoBridge with PulseEngine {

  override val maxPulse: Int = 200

}
