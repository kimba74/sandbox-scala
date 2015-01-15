package org.kimbasoft.akka.usecase1

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object MyActorMessages {
  // Main Trait for all Request type Messages
  sealed trait Request

  // Response Message
  case class Response(result: Try[String])

  // Actor Control Messages
  case class Start()
}
