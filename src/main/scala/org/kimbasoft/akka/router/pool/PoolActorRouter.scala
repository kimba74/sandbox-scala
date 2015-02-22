package org.kimbasoft.akka.router.pool

import akka.actor.{Actor, Props}
import akka.routing.FromConfig
import org.kimbasoft.akka.router.ActorWorker
import org.kimbasoft.akka.router.Messages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.Messages.{RouterRequest, RouterResponse}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class PoolActorRouter extends Actor {
  /* Creating Router Pool from configuration settings */
  val workers = context.actorOf(FromConfig.props(Props(classOf[ActorWorker], "Pool")), "workers")

  def receive: Receive = {
    case request: RouterRequest =>
      workers forward request
    case _ =>
      sender ! RouterResponse(Failure(IllegalRequestException))
  }
}
