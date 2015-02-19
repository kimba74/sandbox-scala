package org.kimbasoft.akka.dispatcher

import akka.actor.Actor
import org.kimbasoft.akka.dispatcher.DispatcherMessages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.dispatcher.DispatcherMessages.{DispatcherResponse, DispatcherRequest}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class DispatcherActor extends Actor {

  def receive: Receive = {
    case DispatcherRequest(message) =>
      Thread.sleep(1500)
      println(s"$this: $message")
    case _ =>
      sender ! DispatcherResponse(Failure(IllegalRequestException))
  }
}
