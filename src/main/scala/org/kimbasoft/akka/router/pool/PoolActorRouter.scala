package org.kimbasoft.akka.router.pool

import akka.actor.{Actor, Props}
import akka.routing.FromConfig
import org.kimbasoft.akka.router.pool.PoolMessages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.pool.PoolMessages.{PoolResponse, PoolRequest}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class PoolActorRouter extends Actor {
  /* Creating Router Pool from configuration settings */
  val workers = context.actorOf(FromConfig.props(Props[PoolActorWorker]), "workers")

  def receive: Receive = {
    case request: PoolRequest =>
      workers forward request
    case _ =>
      sender ! PoolResponse(Failure(IllegalRequestException))
  }
}
