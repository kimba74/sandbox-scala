package org.kimbasoft.akka.router.pool

import akka.actor.{Actor, Props}
import akka.routing.{FromConfig, RoundRobinPool}
import org.kimbasoft.akka.router.ActorWorker
import org.kimbasoft.akka.router.Messages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.Messages.{ConfRouterRequest, ProgRouterRequest, RouterRequest, RouterResponse}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class PoolRouter extends Actor {

  /* Creating Router Pool from configuration file */
  val workersConf = context.actorOf(FromConfig.props(Props(classOf[ActorWorker], "Pool")), "worker-conf")

  /* Creating Router Pool from programmatic configuration */
  val workersProg = context.actorOf(RoundRobinPool(3).props(Props(classOf[ActorWorker],"Pool")), "worker-prog")

  def receive: Receive = {
    case ConfRouterRequest(message) =>
      workersConf forward RouterRequest(s"conf $message")
    case ProgRouterRequest(message) =>
      workersProg forward RouterRequest(s"prog $message")
    case _ =>
      sender ! RouterResponse(Failure(IllegalRequestException))
  }
}
