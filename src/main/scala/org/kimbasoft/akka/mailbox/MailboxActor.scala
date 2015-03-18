package org.kimbasoft.akka.mailbox

import akka.actor.{Actor, Props}
import org.kimbasoft.akka.mailbox.MailboxActor.Messages.MailboxRequest

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MailboxActor extends Actor {

  val name = self.path.name

  def receive: Receive = {
    // Processing of recognized message
    case MailboxRequest(message, priority) =>
      println(s"""$name: received message "$message" [$priority]""")
      Thread.sleep(10) // Sleeping for 10ms to prevent side-effects in this example
    // Handling of all unrecognized messages
    case message =>
      println(s"""$name: Unknown message "$message"""")
  }
}

object MailboxActor {

  val props = Props[MailboxActor]

  object Messages {
    import Priority._
    case class MailboxRequest(message: String, priority: PriorityType = Priority.NORMAL)
  }

  object Exceptions {
    object IllegalRequestException extends RuntimeException
  }

  object Priority extends Enumeration {
    type PriorityType = Value
    val LOW, NORMAL, MEDIUM, HIGH = Value
  }
}
