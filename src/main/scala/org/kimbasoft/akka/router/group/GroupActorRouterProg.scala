package org.kimbasoft.akka.router.group

import akka.actor.Actor
import akka.routing.RoundRobinGroup
import org.kimbasoft.akka.router.Messages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.Messages.{RouterRequest, RouterResponse}


import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class GroupActorRouterProg extends Actor {
  /* Creating Router Group from configuration settings */
  val paths = Vector("/user/super/worker1", "/user/super/worker2", "/user/super/worker3")
  val workers = context.actorOf(RoundRobinGroup(paths).props(), "workers")

  def receive: Receive = {
    case request: RouterRequest =>
      workers forward request
    case _ =>
      sender ! RouterResponse(Failure(IllegalRequestException))
  }
}
