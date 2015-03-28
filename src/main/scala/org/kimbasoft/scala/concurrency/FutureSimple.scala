package org.kimbasoft.scala.concurrency

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
 * Missing documentation
 *
 * @since 1.0
 */
object FutureSimple extends App {
  println("-- Simple Future Example -------------------")
  print("Future: ")
  /* Creating a list of individual futures by running map() on a specified
   * Range. We then implement a new Future object by passing the current
   * number in, printing, and returning it. */
  val futures = (0 to 9) map {
    i => Future {
      val s = i.toString
      print(s)
      s
    }
  }
  /* Collapsing all the 10 individual Futures into one so we can check,
   * when all of them are done. The collapsed future will only be done
   * when each individual future is done. */
  val future = Future.reduce(futures)((s1, s2) => s1 + s2)
  val result = Await.result(future, Duration.Inf)
  println("\nResult: " + result)
}
