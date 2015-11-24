package org.kimbasoft.akka.router.group

import akka.actor.Actor
import akka.routing.{Broadcast, FromConfig, RoundRobinGroup}
import org.kimbasoft.akka.router.ActorWorker.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.ActorWorker.Messages.{ConfRouterRequest, ProgRouterRequest, RouterRequest, RouterResponse}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class GroupRouter extends Actor {

  /* Creating Router Group from configuration settings */
  val workersConf = context.actorOf(FromConfig.props(), "worker-conf")

  /* Creating Router Group from programmatic configuration */
  val paths = Vector("/user/super/worker1", "/user/super/worker2", "/user/super/worker3")
  val workersProg = context.actorOf(RoundRobinGroup(paths).props(), "worker-prog")

  def receive: Receive = {
    case ConfRouterRequest(message) =>
      workersConf forward RouterRequest(s"conf $message")
    case ProgRouterRequest(message) =>
      workersProg forward RouterRequest(s"prog $message")
    case Broadcast(request) =>
      request match {
        case ConfRouterRequest(message) =>
          workersConf forward Broadcast(RouterRequest(s"conf broadcast $message"))
        case ProgRouterRequest(message) =>
          workersProg forward Broadcast(RouterRequest(s"prog broadcast $message"))
        case _=>
          sender ! RouterResponse(Failure(IllegalRequestException))
      }
    case _ =>
      sender ! RouterResponse(Failure(IllegalRequestException))
  }
}
