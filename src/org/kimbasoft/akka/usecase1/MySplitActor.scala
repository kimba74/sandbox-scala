package org.kimbasoft.akka.usecase1

import akka.actor.SupervisorStrategy.Stop
import akka.actor._
import org.kimbasoft.akka.usecase1.MyActorMessages.{SplitResponse, SplitRequest}
import org.kimbasoft.akka.usecase1.MySplitActor.InvalidRequestException

import scala.util.{Success, Failure}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MySplitActor(name: String) extends Actor {

  var count = 1

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case InvalidRequestException => Stop
  }

  override def receive: Receive = {
    case SplitRequest(depth, message) =>
      if(depth > 0) {
        println(s">> $name: $message")
        context.actorOf(Props(classOf[MySplitActor], s"$name.$count"), s"$name.$count") ! SplitRequest(depth - 1, message)
        count += 1
        context.actorOf(Props(classOf[MySplitActor], s"$name.$count"), s"$name.$count") ! SplitRequest(depth - 1, message)
        count += 1
      }
      else {
        println(s"== $name: $message")
      }
    case res @ SplitResponse(result) =>
      println(s"<< $name: $result")
      sender ! res
    case unknown =>
      println(s"Oops! [$unknown]")
  }

}

object MySplitActor {
  case object InvalidRequestException extends RuntimeException
}
