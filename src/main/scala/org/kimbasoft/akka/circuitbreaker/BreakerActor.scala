package org.kimbasoft.akka.circuitbreaker

import akka.actor.{ActorLogging, Actor}
import akka.pattern.CircuitBreaker
import akka.pattern.pipe

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class BreakerActor extends Actor with ActorLogging {
  import context.dispatcher

  val breaker = new CircuitBreaker(context.system.scheduler, 5, 10 seconds, 1 minute).onOpen(notifyMe)

  def notifyMe: Unit = log.info("Circuit Breaker now open, won't close for 1 min")

  def receive: Receive = {
    case "Message" =>
      breaker.withCircuitBreaker(Future("Dangerous call")) pipeTo sender
    case "Blocking Call" =>
      sender ! breaker.withSyncCircuitBreaker("Dangerous call")
  }
}
