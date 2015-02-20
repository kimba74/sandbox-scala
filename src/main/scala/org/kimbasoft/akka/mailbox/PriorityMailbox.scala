package org.kimbasoft.akka.mailbox

import akka.actor.ActorSystem
import akka.dispatch.{PriorityGenerator, UnboundedPriorityMailbox}
import com.typesafe.config.Config
import org.kimbasoft.akka.mailbox.MailboxMessages.MailboxRequest
import org.kimbasoft.akka.mailbox.MailboxMessages.Priority._

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class PriorityMailbox(settings: ActorSystem.Settings, config: Config) extends UnboundedPriorityMailbox(PriorityGenerator {
  case MailboxRequest(_, priority) =>
    priority match {
      case HIGH => 0
      case MEDIUM => 1
      case NORMAL => 2
      case LOW => 3
    }
  case _ => 3
}) {
  println("* PriorityMailbox instantiated!")
}

