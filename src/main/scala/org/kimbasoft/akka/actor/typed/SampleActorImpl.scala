package org.kimbasoft.akka.actor.typed

import akka.actor.ActorRef
import akka.actor.TypedActor.Receiver
import org.kimbasoft.akka.actor.typed.SampleActor.SampleActorException

import scala.concurrent.Future

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class SampleActorImpl(name: String) extends SampleActor with Receiver {

  def this() = this("default")

  def fireAndForget(value: String): Unit = {
    println(s"Actor[$name] received '$value', but who cares!")
  }

  def replyResponseNonBlocking(value: String): Future[String] = {
    println(s"Actor[$name] received '$value', sending Future() in return!")
    Future.successful( s"""Received "$value"""")
  }

  def replyResponseOptionBlocking(value: String): Option[String] = {
    println(s"Actor[$name] received '$value', sending Some() in return!")
    Some( s"""Received "$value"""")
  }

  def replyResponseBlocking(value: String): String = {
    println(s"Actor[$name] received '$value', sending String in return!")
    s"""Received "$value""""
  }

  def replyResponseException(value: String): String = {
    println(s"Actor[$name] received '$value', Throwing SampleActorException!")
    throw SampleActorException("Just throwing exception")
  }

  def onReceive(message: Any, sender: ActorRef): Unit = {
    println(s"Actor[$name] received arbitrary message '$message' from $sender ")
  }
}
