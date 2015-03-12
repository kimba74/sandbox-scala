package org.kimbasoft.akka.actor.regular

import akka.actor.{ActorSystem, PoisonPill, Props}
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.actor.regular.ActorComplex.Manager.{Start, Stop}
import org.kimbasoft.akka.actor.regular.ActorComplex.Messages.ComplexRequest
import org.kimbasoft.akka.actor.regular.ActorSimple.Messages.SimpleRequest
import org.kimbasoft.akka.actor.regular.ActorWatchdog.Messages.{UnwatchActor, WatchActor}

/**
 * Missing documentation
 *
 * @since 1.0
 */
object Client {

  def main(args: Array[String]) {
    /* Reading ActorSystem configuration from embedded config file */
    val config = ConfigFactory.load("org/kimbasoft/akka/actor/regular/actor-config")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    val sys = ActorSystem("ActorDemoSystem", config)

    /* Creating a watchdog actor that will register itself to observe the
     * DeadLetter and offers the capability to register another actor to the
     * watchdog's DeathWatch. Latter is accomplished via control messages
     * WatchActor(ActorRef) and UnwatchActor(ActorRef). */
    val watchdog = sys.actorOf(Props[ActorWatchdog], "watchdog")

    println("-- Simple Actor example --------------------------------")
    val simple = sys.actorOf(ActorSimple.props, "simple")
    watchdog ! WatchActor(simple)
    simple ! SimpleRequest("Fire-and-Forget")
    simple ! 90210
    simple ! SimpleRequest("deadletter")
    simple ! PoisonPill
    Thread.sleep(500)

    println("-- Complex Actor example -------------------------------")
    val complex = sys.actorOf(ActorComplex.props(3), "parent")
    watchdog ! WatchActor(complex)
    complex ! Start
    complex ! ComplexRequest("Complex Request")
    complex ! ComplexRequest(90210)
    Thread.sleep(500)
    complex ! Stop
    Thread.sleep(500)

    println("-- Shutting down ActorSystem ---------------------------")
    watchdog ! UnwatchActor(simple)
    watchdog ! UnwatchActor(complex)
    sys.shutdown
    println("Finished!")
  }
}
