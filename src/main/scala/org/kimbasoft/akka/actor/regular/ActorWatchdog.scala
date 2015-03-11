package org.kimbasoft.akka.actor.regular

import akka.actor._
import org.kimbasoft.akka.actor.regular.ActorWatchdog.Messages.{UnwatchActor, WatchActor}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class ActorWatchdog extends Actor {

  val name = self.path.name

  context.system.eventStream.subscribe(self, classOf[DeadLetter])

  def receive: Receive = {
    case Terminated(actor) =>
      println(s"Actor[$name] DeathWatch: $actor terminated!")
    case WatchActor(actor) =>
      context.watch(actor)
    case UnwatchActor(actor) =>
      context.unwatch(actor)
    case DeadLetter(message, sender, recipient) =>
      println(s"Actor[$name] DeadLetter: $message, from: $sender, to: $recipient")
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