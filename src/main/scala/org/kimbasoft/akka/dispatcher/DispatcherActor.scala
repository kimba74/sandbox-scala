package org.kimbasoft.akka.dispatcher

import akka.actor.{Actor, Props}
import org.kimbasoft.akka.dispatcher.DispatcherActor.Exceptions.IllegalRequestException
import org.kimbasoft.akka.dispatcher.DispatcherActor.Messages.{DispatcherResponse, DispatcherRequest}

import scala.util.{Failure, Try}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class DispatcherActor extends Actor {

  val dispatcher = context.dispatcher

  val name = self.path.name

  def receive: Receive = {
    case DispatcherRequest(message) =>
      Thread.sleep(1500)
      println(s"$name [$dispatcher]: $message")
    case _ =>
      sender ! DispatcherResponse(Failure(IllegalRequestException))
  }
}

object DispatcherActor {

  val props = Props[DispatcherActor]

  object Exceptions {
    case object IllegalRequestException extends RuntimeException
  }

  object Messages {
    case class DispatcherRequest(message: String)
    case class DispatcherResponse(response: Try[String])
  }
}
