package org.kimbasoft.akka.router.broadcast

import akka.actor.Actor
import akka.routing.{BroadcastGroup, FromConfig}
import org.kimbasoft.akka.router.Messages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.Messages.{ConfRouterRequest, ProgRouterRequest, RouterRequest, RouterResponse}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class BroadcastRouterGroup extends Actor {

  /* Creating Router Group from configuration file */
  val workersConf = context.actorOf(FromConfig.props(), "worker-conf")

  /* Creating Router Group from programmatic configuration */
  val paths = Vector("/user/super/worker1", "/user/super/worker2", "/user/super/worker3")
  val workersProg = context.actorOf(BroadcastGroup(paths).props(), "worker-prog")
  
  def receive: Receive = {
    case ConfRouterRequest(message) =>
      workersConf forward RouterRequest(s"conf $message")
    case ProgRouterRequest(message) =>
      workersProg forward RouterRequest(s"prog $message")
    case _ =>
      sender ! RouterResponse(Failure(IllegalRequestException))
  }
}
