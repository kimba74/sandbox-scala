package org.kimbasoft.akka.router.pool

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object PoolMessages {

  case class PoolRequest(message: String)

  case class PoolResponse(response: Try[String])

  object Exceptions {

    case object IllegalRequestException extends RuntimeException

  }
}
