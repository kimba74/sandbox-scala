package org.kimbasoft.akka.actor.regular

import akka.actor.{ActorSystem, PoisonPill, Props}
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

    val sys = ActorSystem("ActorDemoSystem")

    val watchdog = sys.actorOf(Props[ActorWatchdog], "watchdog")

    println("-- Simple Actor example --------------------------------")
    val simple = sys.actorOf(ActorSimple.props, "simple")
    watchdog ! WatchActor(simple)
    simple ! SimpleRequest("Fire-and-Forget")
    simple ! 90210
    simple ! PoisonPill
    Thread.sleep(500)

    println("-- Complex Actor example -------------------------------")
    val complex = sys.actorOf(ActorComplex.props("parent", 3), "parent")
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
