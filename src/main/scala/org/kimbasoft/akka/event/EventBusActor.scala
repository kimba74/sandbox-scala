package org.kimbasoft.akka.event

import akka.actor.{Props, Actor}
import org.kimbasoft.akka.event.EventBusMessages.BusMessage

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
    case event: String =>
      println(s"""$name: received String event "$event"""")
    case event =>
      println(s"""$name: received unrecognized event "$event"""")
  }
}

object EventBusActor {

  def props(name: String): Props = Props(classOf[EventBusActor], name)

}
