package org.kimbasoft.akka.circuitbreaker

/**
 * Missing documentation
 *
 * @since 1.0
 */
object BreakerDangerSimulator {

  def crash: Unit = {
    throw SimulatorCrashException
  }

  def good: String = {
    "Request was good"
  }

  def stall(sleep: Long): String = {
    Thread.sleep(sleep)
    "Done stalling"
  }

  case object SimulatorCrashException extends RuntimeException

}
