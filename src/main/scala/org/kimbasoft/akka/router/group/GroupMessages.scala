package org.kimbasoft.akka.router.group

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object GroupMessages {

  case class GroupRequest(message: String)

  case class GroupResponse(response: Try[String])

  object Exceptions {

    case object IllegalRequestException extends RuntimeException

  }
}
