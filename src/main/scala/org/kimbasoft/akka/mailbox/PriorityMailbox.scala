package org.kimbasoft.akka.mailbox

import akka.actor.ActorSystem
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import com.typesafe.config.Config
import org.kimbasoft.akka.mailbox.MailboxActor.Messages.MailboxRequest
import org.kimbasoft.akka.mailbox.MailboxActor.Priority

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class PriorityMailbox(settings: ActorSystem.Settings, config: Config) extends UnboundedPriorityMailbox(PriorityGenerator {
  case MailboxRequest(_, priority) =>
    priority match {
      case Priority.HIGH => 0
      case Priority.MEDIUM => 1
      case Priority.NORMAL => 2
      case Priority.LOW => 3
    }
  case _ => 3
}) {
  println("* PriorityMailbox instantiated!")
}

