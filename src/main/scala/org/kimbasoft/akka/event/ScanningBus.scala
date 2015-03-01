package org.kimbasoft.akka.event

import akka.actor.ActorRef
import akka.event.{ScanningClassification, EventBus}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ScanningBus {

  case class ScanningEvent(topic: String, payload: Any)

  class ScanningBusImpl extends EventBus with ScanningClassification {
    type Event = ScanningEvent
    type Classifier = Int
    type Subscriber = ActorRef

    protected def compareClassifiers(a: Classifier, b: Classifier): Int =
      if (a < b) -1 else if (a == b) 0 else 1

    protected def matches(classifier: Classifier, event: Event): Boolean =
      event.topic.length <= classifier

    protected def publish(event: Event, subscriber: Subscriber): Unit =
      subscriber ! event.payload

    protected def compareSubscribers(a: Subscriber, b: Subscriber): Int =
      a compareTo b
  }
}
