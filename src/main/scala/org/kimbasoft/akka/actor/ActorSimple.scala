package org.kimbasoft.akka.actor

import akka.actor.{Props, Actor}
import org.kimbasoft.akka.actor.ActorSimple.Exceptions.SimpleRequestException
import org.kimbasoft.akka.actor.ActorSimple.Messages.{SimpleResponse, SimpleRequest}

import scala.util.{Failure, Success, Try}

/**
 * Missing documentation
 *
 * @since 1.0
 */
class ActorSimple extends Actor {

  /**
   * Method that needs to be implemented as the Actor's behavior
   * @return
   */
  def receive: Receive = {
    case SimpleRequest(message) =>
      println(s"""ActorSimple: received message: "$message"""")
      sender ! SimpleResponse(Success(s"""Received and processed message "$message""""))
    case request =>
      println(s"""ActorSimple: received unknown request "$request"""")
      sender ! SimpleResponse(Failure(SimpleRequestException))
  }

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    super.preStart()
    println(s"ActorSimple $self is about to start!")
  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    println(s"ActorSimple $self was stopped!")
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
