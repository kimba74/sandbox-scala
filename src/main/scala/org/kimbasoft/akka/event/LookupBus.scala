package org.kimbasoft.akka.event

import akka.actor.ActorRef
import akka.event.{LookupClassification, EventBus}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object LookupBus {

  case class LookupEvent(topic: String, payload: Any)

  class LookupBusImpl extends EventBus with LookupClassification {
    type Event = LookupEvent
    type Classifier = String
    type Subscriber = ActorRef

    protected def classify(event: Event): Classifier = event.topic

    protected def mapSize(): Int = 128

    protected def publish(event: Event, subscriber: Subscriber): Unit = subscriber ! event.payload

    protected def compareSubscribers(a: Subscriber, b: Subscriber): Int = a compareTo b
  }

}
