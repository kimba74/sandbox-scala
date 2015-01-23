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
  case class ProcessFactorial(nums: List[Int]) extends Request
  case class ProcessSummation(nums: List[Int]) extends Request

  // Response Message
  case class Response(result: Try[Int])

  // Actor Control Messages
  case class Start()
}
