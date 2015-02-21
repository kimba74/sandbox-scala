package org.kimbasoft.akka.router.pool

import akka.actor.Actor
import org.kimbasoft.akka.router.pool.PoolMessages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.router.pool.PoolMessages.{PoolResponse, PoolRequest}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class PoolActorWorker extends Actor {

  def receive: Receive = {
    case PoolRequest(message) =>
      println(s"Pool Worker ($this): $message")
    case _ =>
      sender ! PoolResponse(Failure(IllegalRequestException))
  }
}
