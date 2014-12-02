package org.kimbasoft.scala.concurrency

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Random, Failure, Success, Try}

/**
 * Missing documentation
 *
 * @since 1.0
 */
object FutureExample {

  object SimpleFuture {
    def run {
      println("-- Simple Future Example -------------------")
      print("Future: ")
      val futures = (0 to 9) map {
        i => Future {
          val s = i.toString
          print(s)
          s
        }
      }
      val future = Future.reduce(futures)((s1, s2) => s1 + s2)
      val result = Await.result(future, Duration.Inf)
      println("\nResult: " + result)
    }
  }

  object CallbackFuture {
    // Exception object required by Future.failed()
    case class OddBall(i: Int) extends RuntimeException(s"odd $i received!")

    def run {
      println("-- Future Callbacks Example ----------------")
      // Create callback function for onComplete() method
      val doComplete: PartialFunction[Try[String],Unit] = {
        case s @ Success(_) => println(s)
        case f @ Failure(_) => println(f)
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
  }

  case class OhNo(s: String) extends RuntimeException(s)

  def main(args: Array[String]) {
    SimpleFuture.run
    CallbackFuture.run
  }
}
