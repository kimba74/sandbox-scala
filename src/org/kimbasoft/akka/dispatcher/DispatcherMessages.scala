package org.kimbasoft.akka.dispatcher

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object DispatcherMessages {

  case class DispatcherRequest(message: String)

  case class DispatcherResponse(response: Try[String])

  object Exceptions {

    case object IllegalRequestException extends RuntimeException

  }
}
