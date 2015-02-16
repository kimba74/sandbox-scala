package org.kimbasoft.akka.supervision

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SupervisionMessages {

  case class SupervisionRequest(factor: Int, depth: Int, message: String)

  case class SupervisionResponse(response: Try[String])

  object Exceptions {

    object IllegalRequestException extends RuntimeException

    object IllegalFactorException extends RuntimeException

    object IllegalDepthException extends RuntimeException
    
  }
}
