package org.kimbasoft.akka.router

import akka.actor.{Props, Actor}
import akka.routing.FromConfig
import org.kimbasoft.akka.router.RouterMessages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.RouterMessages.{RouterRequest, RouterResponse}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class RouterActor extends Actor {
  /* Creating Router Pool from configuration settings */
  val workers = context.actorOf(FromConfig.props(Props[RouterActorWorker]), "worker")

  def receive: Receive = {
    case r @ RouterRequest(message) =>
      workers forward r
    case _ =>
      sender ! RouterResponse(Failure(IllegalRequestException))
  }
}
