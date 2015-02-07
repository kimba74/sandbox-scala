package org.kimbasoft.akka.usecase1

import akka.actor.SupervisorStrategy.Stop
import akka.actor.{OneForOneStrategy, SupervisorStrategy, Props, Actor}
import org.kimbasoft.akka.usecase1.MyActorMessages.{SplitResponse, SplitRequest}
import org.kimbasoft.akka.usecase1.MySplitActor.InvalidRequestException

import scala.util.{Success, Failure}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MySplitActor extends Actor {

  var count = 1

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case InvalidRequestException => Stop
  }

  override def receive: Receive = {
    case SplitRequest(depth) =>
      if (depth > 0) {
        context.actorOf(Props[MySplitActor], s"child$count-1") ! SplitRequest(depth - 1)
        context.actorOf(Props[MySplitActor], s"child$count-2") ! SplitRequest(depth - 1)
        count += 1
      }
      else
        sender ! SplitResponse(Success("Finished"))
    case SplitResponse =>
    case _ =>
      sender ! SplitResponse(Failure(InvalidRequestException))
  }

}

object MySplitActor {
  case object InvalidRequestException extends RuntimeException
}
