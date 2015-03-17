package org.kimbasoft.akka.circuitbreaker

import akka.actor.{Props, ActorLogging, Actor}
import akka.pattern.CircuitBreaker
import akka.pattern.pipe
import org.kimbasoft.akka.circuitbreaker.BreakerActor.Messages.{StallRequest, GoodRequest, CrashRequest}

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

  val breaker = new CircuitBreaker(context.system.scheduler, 3, 2 seconds, 10 seconds)
    .onOpen(notifyOpen)
    .onClose(notifyClose)
    .onHalfOpen(notifyHalfOpen)

  def notifyClose: Unit = log.info("Breaker: Circuit Breaker now closed")

  def notifyHalfOpen: Unit = log.info("Breaker: Circuit Breaker now half-open")

  def notifyOpen: Unit = log.info("Breaker: Circuit Breaker now open, won't close for 10 sec")

  def receive: Receive = {
    case CrashRequest =>
      log.info("Received CrashRequest...")
      // Asynchronous Call will fire and forget
      breaker.withCircuitBreaker(Future(BreakerDangerSimulator.crash)) pipeTo sender
    case GoodRequest =>
      log.info("Received GoodRequest...")
      // Asynchronous Call will fire and forget
      breaker.withCircuitBreaker(Future(BreakerDangerSimulator.good)) pipeTo sender
    case StallRequest(sleep) =>
      log.info(s"Received StallRequest [${sleep}ms]...")
      // Synchronous Call will block and wait until result or timeout
      sender ! breaker.withSyncCircuitBreaker(BreakerDangerSimulator.stall(sleep))
    case "Blocking Call" =>
      sender ! breaker.withSyncCircuitBreaker("Dangerous call")
  }
}

object BreakerActor {

  val props = Props[BreakerActor]

  object Messages {
    case object CrashRequest

    case object GoodRequest

    case class StallRequest(stall: Long)
  }
}
