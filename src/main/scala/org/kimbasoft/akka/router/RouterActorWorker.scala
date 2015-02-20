package org.kimbasoft.akka.router

import akka.actor.Actor
import org.kimbasoft.akka.router.RouterMessages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.RouterMessages.{RouterResponse, RouterRequest}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class RouterActorWorker extends Actor {

  def receive: Receive = {
    case RouterRequest(message) =>
      println(s"$this: $message")
    case _ =>
      sender ! RouterResponse(Failure(IllegalRequestException))
  }
}
