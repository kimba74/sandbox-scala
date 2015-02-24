package org.kimbasoft.akka.dsl

import scala.util.Try

/**
 * Missing documentation
 *
 * @since 1.0
 */
object Messages {

  case class DSLRequest(message: Any)

  case class DSLResponse(response: Try[Any])

  object Exceptions {

    case object IllegalDSLRequestException extends RuntimeException

  }
}
