package org.kimbasoft.akka.config

import akka.actor.{Props, ActorSystem}
import org.kimbasoft.akka.config.Messages.ConfigRequest

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ConfigClient {

  def main(args: Array[String]) {
    val sys = ActorSystem("ConfigSystem")

    val actor1 = sys.actorOf(Props[MailboxActor], "mailbox-actor")
    val actor2 = sys.actorOf(Props[PriorityMailboxActor], "priority-actor")
    // TODO: Configure PriorityMailbox for actor2

    actor1 ! ConfigRequest("Hello World!")
    actor2 ! ConfigRequest("Hello World!")
  }
}
