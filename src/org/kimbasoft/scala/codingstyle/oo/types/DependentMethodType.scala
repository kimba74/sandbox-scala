package org.kimbasoft.scala.codingstyle.oo.types

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object DependentMethodType {

  case class LocalResponse(statusCode: Int)
  case class RemoteResponse(message: String)

  sealed trait Computation {
    type Response
    val work: Future[Response]
  }

  case class LocalComputation(work: Future[LocalResponse]) extends Computation {
    type Response = LocalResponse
  }

  case class RemoteComputation(work: Future[RemoteResponse]) extends Computation {
    type Response = RemoteResponse
  }

  object Service {
    /**
     * Return type of this method is dependent on a type declared in the object
     * provided as method argument, hence the method is type depending.
     */
    def handle(task: Computation): task.Response = {
      val duration = Duration(2, SECONDS)
      Await.result(task.work, duration)
    }
  }

  def main(args: Array[String]) {
    println(Service.handle(LocalComputation(Future(LocalResponse(42)))))
    println(Service.handle(RemoteComputation(Future(RemoteResponse("hello world")))))
  }

}
