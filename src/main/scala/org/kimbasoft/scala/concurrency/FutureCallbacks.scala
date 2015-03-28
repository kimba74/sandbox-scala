package org.kimbasoft.scala.concurrency

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

/**
 * Missing documentation
 *
 * @since 1.0
 */
object FutureCallbacks extends App {
  // Exception object required by Future.failed()
  case class OddBall(i: Int) extends RuntimeException(s"odd $i received!")

  println("-- Future Callbacks Example ----------------")
  // Create callback function for onComplete() method
  val doComplete: PartialFunction[Try[String], Unit] = {
    case s@Success(_) => println(s)
    case f@Failure(_) => println(f)
  }
  // Create Seq of Future objects
  val futures = (0 to 9) map {
    case i if i % 2 == 0 => Future.successful(i.toString)
    case i => Future.failed(OddBall(i))
  }
  // Adding callback function 'doComplete' for completed Futures
  futures map (_ onComplete doComplete)
  // Giving Future objects time to complete and invoke callback
  Thread.sleep(1000)
}
