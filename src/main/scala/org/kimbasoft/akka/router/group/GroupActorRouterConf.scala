package org.kimbasoft.akka.router.group

import akka.actor.Actor
import akka.routing.FromConfig
import org.kimbasoft.akka.router.group.GroupMessages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.group.GroupMessages.{GroupRequest, GroupResponse}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class GroupActorRouterConf extends Actor {
  /* Creating Router Group from configuration settings */
  val workers = context.actorOf(FromConfig.props(), "workers")

  def receive: Receive = {
    case request: GroupRequest =>
      workers forward request
    case _ =>
      sender ! GroupResponse(Failure(IllegalRequestException))
  }
}
