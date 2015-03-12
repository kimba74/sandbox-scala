package org.kimbasoft.akka.actor.regular

import akka.actor.{Actor, Props}
import org.kimbasoft.akka.actor.regular.ActorSimple.Exceptions.SimpleRequestException
import org.kimbasoft.akka.actor.regular.ActorSimple.Messages.{SimpleResponse, SimpleRequest}

import scala.util.{Failure, Success, Try}

/**
 * Missing documentation
 *
 * @since 1.0
 */
class ActorSimple extends Actor {

  val name = self.path.name

  /**
   * Method that needs to be implemented as the Actor's behavior
   * @return
   */
  def receive: Receive = {
    case SimpleRequest("deadletter") =>
      println(s"Actor[$name]: received a DeadLetter request")
      context.system.deadLetters ! SimpleResponse(Success("Request to send to DeadLetter!"))
    case SimpleRequest(message) =>
      println(s"""Actor[$name]: received message: "$message"""")
      sender ! SimpleResponse(Success(s"""Received and processed message "$message""""))
    case request =>
      println(s"""Actor[$name]: received unknown request "$request"""")
      sender ! SimpleResponse(Failure(SimpleRequestException))
  }

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    super.preStart()
    println(s"Actor[$name]: instance of ActorSimple, $self is about to start!")
  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    println(s"Actor[$name]: instance of ActorSimple $self was stopped!")
  }
}

object ActorSimple {

  /**
   * Recommended practice to encapsulate the creation of the Actor's
   * Props. This will help inexperienced users to create Props with
   * valid settings.
   */
  def props : Props = Props[ActorSimple]

  /**
   * Encapsulating the possible Actor Messages in the Actor's companion
   * object.
   */
  object Messages {

    case class SimpleRequest(message: String)

    case class SimpleResponse(response: Try[String])

  }

  /**
   * Encapsulating possible Actor processing exceptions in the Actor's
   * companion object.
   */
  object Exceptions {

    case object SimpleRequestException extends RuntimeException

  }
}
