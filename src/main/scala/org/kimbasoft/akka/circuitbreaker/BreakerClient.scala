package org.kimbasoft.akka.circuitbreaker

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.circuitbreaker.BreakerActor.Messages.{StallRequest, GoodRequest, CrashRequest}

/**
 * Missing documentation
 *
 * @since 1.0
 */
object BreakerClient {

  def main(args: Array[String]) {
    /* Reading ActorSystem configuration from embedded config file */
    val config = ConfigFactory.load("org/kimbasoft/akka/circuitbreaker/breaker-config")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    val sys = ActorSystem("BreakerSystem", config)

    /* Create Actor with Circuit Breaker inside */
    val actor = sys.actorOf(BreakerActor.props, "breaker-actor")

    /* Have the call crash 3 times */
    actor ! CrashRequest
    actor ! CrashRequest
    actor ! CrashRequest

    /* While the Circuit Breaker is open for 10sec sleep 15sec
     * to ensure the reset timeout has passed and the Breaker goes into half-open */
    Thread.sleep(15000)

    /* Send an initial good request to bring the Circuit Breaker back from half-open to
     * closed mode. The toggle between good and bad request to demonstrate that a response
     * to a good request resets the Breaker's Fail Counter.*/
    actor ! GoodRequest
    actor ! CrashRequest
    actor ! GoodRequest
    actor ! CrashRequest
    actor ! GoodRequest
    actor ! CrashRequest

    /* Stall 3 times by having the call take longer than the Circuit Breaker's timeout value.
     * This demonstrates that timed out calls are counted as failed attempts and trip Breaker. */
    actor ! StallRequest(3000)
    actor ! StallRequest(3000)
    actor ! StallRequest(3000)
  }
}
