package org.kimbasoft.akka.usecase1

import akka.actor.{Props, Actor}
import akka.pattern.ask

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MyActor extends Actor {

  def receive: Receive = {
    /* Creating an Actor of type 'MyActor' as sub-actor of this supervising Actor.
     * Child-Level Actors will be created via the actorOf() method of the ActorContext
     * found within the supervising Actor via the 'context' variable. */
    case "Start" => context.actorOf(Props[MyActor], "Child")
    case _ =>
  }

}
