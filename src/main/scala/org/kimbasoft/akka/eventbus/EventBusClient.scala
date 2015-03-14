package org.kimbasoft.akka.eventbus

import akka.actor.{ActorSystem, DeadLetter}
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.eventbus.ActorBus.{ActorBusImpl, ActorEvent}
import org.kimbasoft.akka.eventbus.EventBusMessages.{BusPublication, BusPublicationRequest, BusMessage, BusRequestMessage}
import org.kimbasoft.akka.eventbus.LookupBus.{LookupBusImpl, LookupEvent}
import org.kimbasoft.akka.eventbus.ScanningBus.{ScanningBusImpl, ScanningEvent}
import org.kimbasoft.akka.eventbus.SubchannelBus.{SubchannelBusImpl, SubchannelEvent}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object EventBusClient {

  def main(args: Array[String]) {
    
    val config = ConfigFactory.load("org/kimbasoft/akka/eventbus/eventbus-config")

    val sys = ActorSystem("EventBusSystem", config)

    /* Message processing example for Event Bus with Lookup Classification */
    val actor1 = sys.actorOf(EventBusActor.props("LookupActorGreetings"), "event-actor-greeting")
    val actor2 = sys.actorOf(EventBusActor.props("LookupActorStatements"), "event-actor-statements")

    val busA = new LookupBusImpl()
    busA.subscribe(actor1, "greetings")
    busA.subscribe(actor2, "statements")

    busA.publish(LookupEvent("greetings", BusMessage("hello")))
    busA.publish(LookupEvent("statements", BusMessage("Life is good!")))
    busA.publish(LookupEvent("greetings", "good morning"))
    busA.publish(LookupEvent("statements", "Sleep is overrated"))
    busA.publish(LookupEvent("greetings", 90201))
    busA.publish(LookupEvent("statements", true))

    /* Message processing example for Event Bus with Subchannel Classification */
    val actor3 = sys.actorOf(EventBusActor.props("SubChannel-X"), "subchannel-actor-X")
    val actor4 = sys.actorOf(EventBusActor.props("SubChannel-X.Y"), "subchannel-actor-XY")

    val busB = new SubchannelBusImpl
    busB.subscribe(actor3, "x")
    busB.subscribe(actor4, "x.y")

    busB.publish(SubchannelEvent("x", BusMessage("You've got mail!")))
    busB.publish(SubchannelEvent("x.y", BusMessage("Was that the doorbell?")))
    busB.publish(SubchannelEvent("x.y.z", BusMessage("Baby it's cold outside.")))

    /* Message processing example for Event Bus with Scanning Classification */
    val actor5 = sys.actorOf(EventBusActor.props("Scanning-2"), "scanning-2")
    val actor6 = sys.actorOf(EventBusActor.props("Scanning-4"), "scanning-4")

    val busC = new ScanningBusImpl
    busC.subscribe(actor5, 2)
    busC.subscribe(actor6, 4)

    busC.publish(ScanningEvent("a", "Topic length = 1"))
    busC.publish(ScanningEvent("ab", "Topic length = 2"))
    busC.publish(ScanningEvent("abc", "Topic length = 3"))
    busC.publish(ScanningEvent("abcd", "Topic length = 4"))

    /* Message processing example for Event Bus with Actor Classification */
    val observer1 = sys.actorOf(EventBusActor.props("Observer-1"), "observer-1")
    val observer2 = sys.actorOf(EventBusActor.props("Observer-2"), "observer-2")
    val actor7 = sys.actorOf(EventBusActor.props("ActorSubscriber-1"), "actorevent-1")
    val actor8 = sys.actorOf(EventBusActor.props("ActorSubscriber-2"), "actorevent-2")

    val busD = new ActorBusImpl
    busD.subscribe(actor7, observer1)
    busD.subscribe(actor8, observer1)
    busD.subscribe(actor8, observer2)

    busD.publish(ActorEvent(observer1, BusMessage("1. Message with observer 1")))
    busD.publish(ActorEvent(observer1, BusMessage("2. Message with observer 1")))
    busD.publish(ActorEvent(observer2, BusMessage("3. Message with observer 2")))
    busD.publish(ActorEvent(observer2, BusMessage("4. Message with observer 3")))

    /* Subscribing to the Actor System's Event Stream for Dead Letters*/
    val actorP = sys.actorOf(EventBusActor.props("Producer"), "stream-producer")
    val actorC = sys.actorOf(EventBusActor.props("Consumer"), "stream-consumer")
    val actorD = sys.actorOf(EventBusActor.props("DeadLetter"), "dead-letter")

    sys.eventStream.subscribe(actorD, classOf[DeadLetter])
    sys.eventStream.subscribe(actorC, classOf[BusPublication])

    actorP ! BusRequestMessage("Hello World.")
    actorP ! BusPublicationRequest("Hello Subscribers")
  }
}
