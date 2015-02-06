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
  sealed trait ProcessRequest
  case class ProcessFactorial(nums: Seq[Int]) extends ProcessRequest
  case class ProcessSummation(nums: Seq[Int]) extends ProcessRequest

  // Response Message
  case class ProcessResponse(result: Try[(Int,Seq[Int])])

  // Request Object for MySplitActor
  case class SplitRequest(depth: Int)

  case class SplitResponse(result: Try[String])
}
