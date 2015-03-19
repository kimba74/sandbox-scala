package org.kimbasoft.akka.router.broadcast

import akka.actor.Actor
import akka.routing.{BroadcastPool, FromConfig}
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
class BroadcastRouterPool extends Actor {

  /* Creating Router Pool from configuration file */
  val workersConf = context.actorOf(FromConfig.props(ActorWorker.props("Pool")), "worker-conf")

  /* Creating Router Pool from programmatic configuration */
  val workersProg = context.actorOf(BroadcastPool(3).props(ActorWorker.props("Pool")), "worker-prog")

  def receive: Receive = {
    case ConfRouterRequest(message) =>
      workersConf forward RouterRequest(s"conf $message")
    case ProgRouterRequest(message) =>
      workersProg forward RouterRequest(s"prog $message")
    case _ =>
      sender ! RouterResponse(Failure(IllegalRequestException))
  }
}
