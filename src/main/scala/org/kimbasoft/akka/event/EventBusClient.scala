package org.kimbasoft.akka.event

import akka.actor.ActorSystem
import org.kimbasoft.akka.event.EventBusMessages.BusMessage
import org.kimbasoft.akka.event.LookupBus.{LookupEvent, LookupBusImpl}
import org.kimbasoft.akka.event.SubchannelBus.{SubchannelEvent, SubchannelBusImpl}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object EventBusClient {

  def main(args: Array[String]) {

    val sys = ActorSystem("EventBusSystem")

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

    val actor3 = sys.actorOf(EventBusActor.props("SubChannel-X"), "subchannel-actor-X")
    val actor4 = sys.actorOf(EventBusActor.props("SubChannel-X.Y"), "subchannel-actor-XY")

    val busB = new SubchannelBusImpl
    busB.subscribe(actor3, "x")
    busB.subscribe(actor4, "x.y")
    busB.publish(SubchannelEvent("x", BusMessage("You've got mail!")))
    busB.publish(SubchannelEvent("x.y", BusMessage("Was that the doorbell?")))
    busB.publish(SubchannelEvent("x.y.z", BusMessage("Baby it's cold outside.")))
  }
}
