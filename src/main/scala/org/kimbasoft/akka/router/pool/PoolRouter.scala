package org.kimbasoft.akka.router.pool

import akka.actor.Actor
import akka.routing.{Broadcast, FromConfig, RoundRobinPool}
import org.kimbasoft.akka.router.ActorWorker
import org.kimbasoft.akka.router.ActorWorker.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.ActorWorker.Messages.{ConfRouterRequest, ProgRouterRequest, RouterRequest, RouterResponse}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class PoolRouter extends Actor {

  /* Creating Router Pool from configuration file */
  val workersConf = context.actorOf(FromConfig.props(ActorWorker.props("Pool")), "worker-conf")

  /* Creating Router Pool from programmatic configuration */
  val workersProg = context.actorOf(RoundRobinPool(3).props(ActorWorker.props("Pool")), "worker-prog")

  def receive: Receive = {
    case ConfRouterRequest(message) =>
      workersConf forward RouterRequest(s"conf $message")
    case ProgRouterRequest(message) =>
      workersProg forward RouterRequest(s"prog $message")
    case Broadcast(request) =>
      request match {
        case ConfRouterRequest(message) =>
          workersConf forward Broadcast(RouterRequest(s"conf broadcast $message"))
        case ProgRouterRequest(message) =>
          workersProg forward Broadcast(RouterRequest(s"prog broadcast $message"))
        case _ =>
          sender ! RouterResponse(Failure(IllegalRequestException))
      }
    case _ =>
      sender ! RouterResponse(Failure(IllegalRequestException))
  }
}
