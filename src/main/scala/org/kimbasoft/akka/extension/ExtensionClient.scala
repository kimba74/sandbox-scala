package org.kimbasoft.akka.extension

import akka.actor.ActorSystem
import org.kimbasoft.akka.extension.CountActor.Messages.CountAction

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ExtensionClient {

  def main(args: Array[String]) {
    println("-- Creating ActorSystem ")
    val sys = ActorSystem("ExtensionSystem")

    /* Registering the CountExtension with the ActorSystem.
     * This step is not required but it makes sure the extension is registered
     * before the actors are created and utilize it. If not registered with the
     * ActorSystem the extension is registered upon first usage.*/
    sys.registerExtension(CountExtension)

    println("-- Creating CountActor ")
    val actor = sys.actorOf(CountActor.props, "counter")

    println("-- Sending messages to Actor")
    actor ! CountAction
    actor ! CountAction
    actor ! CountAction
    actor ! CountAction
    actor ! CountAction

  }
}

