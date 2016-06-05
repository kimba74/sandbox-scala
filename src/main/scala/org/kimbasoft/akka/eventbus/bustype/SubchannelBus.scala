package org.kimbasoft.akka.eventbus.bustype

import akka.actor.ActorRef
import akka.event.{EventBus, SubchannelClassification}
import akka.util.Subclassification

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SubchannelBus {

  case class SubchannelEvent(channel: String, payload: Any)

  class SubclassClassification extends Subclassification[String] {
    def isEqual(x: String, y: String): Boolean = {
      x == y
    }

    def isSubclass(x: String, y: String): Boolean = {
      x startsWith y
    }
  }

  class SubchannelBusImpl extends EventBus with SubchannelClassification {
    type Event = SubchannelEvent
    type Classifier = String
    type Subscriber = ActorRef

    protected def classify(event: Event): Classifier = event.channel

    protected def publish(event: Event, subscriber: Subscriber): Unit = subscriber ! event.payload

    protected implicit def subclassification: Subclassification[Classifier] = new SubclassClassification
  }
}
