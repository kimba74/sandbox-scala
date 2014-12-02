package org.kimbasoft.scala.concurrency

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
 * Missing documentation
 *
 * @since 1.0
 */
object FutureSimple {

  def main(args: Array[String]) {
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
