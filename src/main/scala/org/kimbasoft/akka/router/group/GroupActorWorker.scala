package org.kimbasoft.akka.router.group

import akka.actor.Actor
import org.kimbasoft.akka.router.group.GroupMessages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.group.GroupMessages.{GroupResponse, GroupRequest}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class GroupActorWorker extends Actor {

  def receive: Receive = {
    case GroupRequest(message) =>
      println(s"Group Worker ($this): $message")
    case _ =>
      sender ! GroupResponse(Failure(IllegalRequestException))
  }
}
