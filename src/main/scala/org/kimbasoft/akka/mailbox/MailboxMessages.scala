package org.kimbasoft.akka.mailbox

import org.kimbasoft.akka.mailbox.MailboxMessages.Priority.PriorityType

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object MailboxMessages {

  case class MailboxRequest(message: String, priority: PriorityType = Priority.NORMAL)

  case class MailboxResponse(response: Try[String])

  object Exceptions {

    object IllegalRequestException extends RuntimeException

  }

  object Priority extends Enumeration {

    type PriorityType = Value

    val LOW, NORMAL, MEDIUM, HIGH = Value

  }
}
