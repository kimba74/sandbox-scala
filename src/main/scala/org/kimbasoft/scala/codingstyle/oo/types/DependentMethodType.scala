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
object DependentMethodType extends App {

  case class LocalResponse(statusCode: Int)
  case class RemoteResponse(message: String)

  /**
   * This sealed trait defines an abstract type called 'Response'. This type will
   * then be used as parameter for the Future object returned by the 'work' method.
   * It will also be used as "dependent" return value for the 'handle' method later
   * on declared in the Service object thus allowing for dynamically defining the
   * return type of the 'handle' method via the concrete implementation of this
   * sealed trait.
   */
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


  println(Service.handle(LocalComputation(Future(LocalResponse(42)))))
  println(Service.handle(RemoteComputation(Future(RemoteResponse("hello world")))))
}
