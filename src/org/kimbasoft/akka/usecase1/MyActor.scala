package org.kimbasoft.akka.usecase1

import akka.actor.SupervisorStrategy.{Stop, Restart, Resume}
import akka.actor._

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MyActor extends Actor {

  /**
   * Method that defines the Supervisor Strategy for all Child Actors.
   * The Supervisor Strategy defines how to handle crashes of the
   * supervised Actor.
   */
  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    /* Exception Objects can only be referenced directly (since there can only be one) */
    case MyActor.ProcessingException => Resume
    case MyActor.GeneralException => Restart
    /* Exception Classes can be mapped to placeholders since there can be multiple instances */
    case _ : MyActor.TestException => Stop
  }

  /**
   * Method that defines the behavior of the Actor when receiving a message.
   * This method can be changed at runtime using the 'become()' method on
   * the ActorContext (use 'context' variable of Actor).
   */
  def receive: Receive = {
    /* Creating an Actor of type 'MyActor' as sub-actor of this supervising Actor.
     * Child-Level Actors will be created via the actorOf() method of the ActorContext
     * found within the supervising Actor via the 'context' variable. */
    case "Start" => context.actorOf(Props[MyActor], "Child")
    case _ =>
  }
}

object MyActor {
  // Declaring Singleton Exception Objects (meant for generic exception flagging)
  case object ProcessingException extends RuntimeException
  case object GeneralException extends RuntimeException
  // Declaring Regular Exception Objects (meant for situation specific flagging)
  case class TestException(message: String) extends RuntimeException(message)
}