package org.kimbasoft.akka.eventbus.bustype

import akka.actor.ActorRef
import akka.event.{ActorClassification, ActorClassifier, ActorEventBus}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ActorBus {

  case class ActorEvent(ref: ActorRef, payload: Any)

  class ActorBusImpl extends ActorEventBus with ActorClassifier with ActorClassification {
    type Event = ActorEvent

    protected def classify(event: Event): Classifier = event.ref

    protected def mapSize: Int = 128
  }
}
