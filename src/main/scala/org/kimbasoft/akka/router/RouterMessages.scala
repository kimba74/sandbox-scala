package org.kimbasoft.akka.router

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object RouterMessages {

  case class RouterRequest(message: String)

  case class RouterResponse(response: Try[String])

  object Exceptions {

    case object IllegalRequestException extends RuntimeException

  }
}
