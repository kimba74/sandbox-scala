package org.kimbasoft.akka.event

import akka.actor.{DeadLetter, Props, Actor}
import org.kimbasoft.akka.event.EventBusMessages._

import scala.util.{Failure, Success}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class EventBusActor(name: String) extends Actor {

  def receive: Receive = {
    case BusMessage(message) =>
      println(s"""$name: received BusMessage with payload "$message"""")
    case BusRequestMessage(message) =>
      println(s"""$name: received BusRequestMessage with payload "$message", responding""")
      sender ! BusResponseMessage(Success(s"""Processed message "$message""""))
    case BusPublication(message) =>
      println(s"""$name: received BusPublication with payload "$message"""")
    case BusPublicationRequest(message) =>
      println(s"""$name: received BusPublicationRequest with payload "$message", publishing""")
      context.system.eventStream.publish(BusPublication(message))
    case DeadLetter(message @ BusResponseMessage(payload), sender, recipient) =>
      payload match {
        case Success(response) =>
          println(s"""$name: received message "$response" via DeadLetter[$sender]""")
        case Failure(response) =>
          println(s"""$name: received failure "$response" via DeadLetter""")
      }
    case event: String =>
      println(s"""$name: received String event "$event"""")
    case event =>
      println(s"""$name: received unrecognized event "$event"""")
  }
}

object EventBusActor {

  def props(name: String): Props = Props(classOf[EventBusActor], name)

}
