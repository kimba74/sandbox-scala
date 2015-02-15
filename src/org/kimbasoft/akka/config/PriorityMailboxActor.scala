package org.kimbasoft.akka.config

import akka.actor.Actor
import org.kimbasoft.akka.config.Messages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.config.Messages.{ConfigResponse, ConfigRequest}

import scala.util.{Failure, Success}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class PriorityMailboxActor extends Actor {

  def receive: Receive = {
    case ConfigRequest(message) =>
      println(s"Priority: $message")
      sender ! ConfigResponse(Success(s""""$message" - priority processed!"""))
    case _ =>
      sender ! ConfigResponse(Failure(IllegalRequestException))
  }
}
