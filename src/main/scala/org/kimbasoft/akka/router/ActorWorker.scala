package org.kimbasoft.akka.router

import akka.actor.{Actor, Props}
import org.kimbasoft.akka.router.ActorWorker.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.ActorWorker.Messages.{RouterRequest, RouterResponse}

import scala.util.{Failure, Try}

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

object ActorWorker {

  def props(worker_type: String) = Props(classOf[ActorWorker], worker_type)

  object Messages {
    case class ConfRouterRequest(message: String)

    case class ProgRouterRequest(message: String)

    case class RouterRequest(message: String)

    case class RouterResponse(response: Try[String])
  }

  object Exceptions {
    case object IllegalRequestException extends RuntimeException
  }
}
