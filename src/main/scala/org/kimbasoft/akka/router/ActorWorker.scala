package org.kimbasoft.akka.router

import akka.actor.Actor
import org.kimbasoft.akka.router.Messages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.Messages.{RouterResponse, RouterRequest}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class ActorWorker(worker_type: String) extends Actor {

  def receive: Receive = {
    case RouterRequest(message) =>
      println(s"$worker_type Worker[$this]: $message")
    case _ =>
      sender ! RouterResponse(Failure(IllegalRequestException))
  }
}
