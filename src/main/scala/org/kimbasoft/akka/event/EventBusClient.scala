package org.kimbasoft.akka.event

import akka.actor.ActorSystem
import org.kimbasoft.akka.event.EventBusMessages.BusMessage
import org.kimbasoft.akka.event.LookupBus.{LookupEvent, LookupBusImpl}
import org.kimbasoft.akka.event.ScanningBus.{ScanningEvent, ScanningBusImpl}
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
  }
}
