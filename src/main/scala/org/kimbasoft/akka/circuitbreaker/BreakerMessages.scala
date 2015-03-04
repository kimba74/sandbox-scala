package org.kimbasoft.akka.circuitbreaker

/**
 * Missing documentation
 *
 * @since 1.0
 */
object BreakerMessages {

  case object CrashRequest

  case object GoodRequest

  case class StallRequest(stall: Long)

}
