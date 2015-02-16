package org.kimbasoft.akka.mailbox

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object MailboxMessages {

  case class MailboxRequest(message: String)

  case class MailboxResponse(response: Try[String])

  object Exceptions {

    object IllegalRequestException extends RuntimeException

  }
}
