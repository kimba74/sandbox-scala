package org.kimbasoft.akka.actor

import akka.actor.ActorSystem
import org.kimbasoft.akka.actor.ActorComplex.Manager.{Start, Stop}
import org.kimbasoft.akka.actor.ActorComplex.Messages.ComplexRequest
import org.kimbasoft.akka.actor.ActorSimple.Messages.SimpleRequest

/**
 * Missing documentation
 *
 * @since 1.0
 */
object Client {

  def main(args: Array[String]) {

    val sys = ActorSystem("ActorDemoSystem")

    println("-- Simple Actor example --------------------------------")
    val simple = sys.actorOf(ActorSimple.props, "simple")
    simple ! SimpleRequest("Fire-and-Forget")
    simple ! 90210
    Thread.sleep(500)

    println("-- Complex Actor example -------------------------------")
    val complex = sys.actorOf(ActorComplex.props("parent", 3), "parent")
    complex ! Start
    complex ! ComplexRequest("Complex Request")
    complex ! ComplexRequest(90210)
    Thread.sleep(500)
    complex ! Stop
    Thread.sleep(500)

    println("-- Shutting down ActorSystem ---------------------------")
    sys.shutdown
    println("Finished!")
  }
}
