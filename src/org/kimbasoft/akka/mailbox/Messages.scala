package org.kimbasoft.akka.mailbox

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Messages {

  case class ConfigRequest(message: String)

  case class ConfigResponse(response: Try[String])

  object Exceptions {

    object IllegalRequestException extends RuntimeException

  }
}
