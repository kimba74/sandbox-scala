package org.kimbasoft.akka.actor.regular

import akka.actor.{Terminated, ActorRef, Props, Actor}
import org.kimbasoft.akka.actor.regular.ActorWatchdog.Messages.{UnwatchActor, WatchActor}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class ActorWatchdog extends Actor {

  def receive: Receive = {
    case Terminated(actor) =>
      println(s"Death Watch: $actor terminated!")
    case WatchActor(actor) =>
      context.watch(actor)
    case UnwatchActor(actor) =>
      context.unwatch(actor)
    case _ =>
  }
}

object ActorWatchdog {

  def props() = Props[ActorWatchdog]

  object Messages {

    case class WatchActor(actor: ActorRef)

    case class UnwatchActor(actor: ActorRef)

  }
}