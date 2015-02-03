package org.kimbasoft.akka.usecase1

import akka.actor.SupervisorStrategy.{Restart, Stop}
import akka.actor._
import org.kimbasoft.akka.usecase1.MyActor.ProcessingException
import org.kimbasoft.akka.usecase1.MyActorMessages.{ProcessFactorial, ProcessSummation, Response}

import scala.util.{Failure, Success}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MyActor extends Actor {

  /**
   * Method that defines the Supervisor Strategy for all Child Actors.
   * The Supervisor Strategy defines how to handle crashes of the
   * supervised Actor. Possible strategies are:
   *    Resume   - Resumes Message processing for the failed Actor
   *    Restart  - Discards the old Actor instance and replaces it with a new, then resumes message processing.
   *    Stop     - Stops the failed Actor
   *    Escalate - Escalates the problem to the next higher supervisor
   */
  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    /* Exception Objects can only be referenced directly (since there can only be one) */
    case MyActor.ProcessingException => Stop
    case MyActor.GeneralException => Restart
    /* Exception Classes can be mapped to placeholders since there can be multiple instances */
    case _ : MyActor.TestException => Stop
  }

  /**
   * Method that defines the behavior of the Actor when receiving a message.
   * This method can be changed at runtime using the 'become()' method on
   * the ActorContext (use 'context' variable of Actor).
   */
  def receive: Receive = {
    /* Creating an Actor of type 'MyActor' as sub-actor of this supervising Actor.
     * Child-Level Actors will be created via the actorOf() method of the ActorContext
     * found within the supervising Actor via the 'context' variable. */
    case ProcessFactorial(nums) =>
      //TODO: Process factorial of int list
      sender ! Response(Success(factorial(nums), nums))
      // If list size is bigger than 3, split in half and hand to new MyActor instances
    case ProcessSummation(nums) =>
      //TODO: Process summation of int list
      sender ! Response(Success(summation(nums), nums))
      // If list size is bigger than 3, split in half and hand to new MyActor instances
    case _ => sender ! Response(Failure(ProcessingException))
  }

  private def factorial(nums: Seq[Int]): Int = {
    def fact(value: Int, nums: Seq[Int]): Int = nums match {
      case head :: tail => fact(value * head, tail)
      case _ => value
    }
    fact(1,nums)
  }

  private def summation(nums: Seq[Int]): Int = {
    def sum(value: Int, nums: Seq[Int]): Int = nums match {
      case head :: tail => sum(value + head, tail)
      case _ => value
    }
    sum(1, nums)
  }
}

object MyActor {
  // Declaring Singleton Exception Objects (meant for generic exception flagging)
  case object ProcessingException extends RuntimeException
  case object GeneralException extends RuntimeException
  // Declaring Regular Exception Objects (meant for situation specific flagging)
  case class TestException(message: String) extends RuntimeException(message)
}