package org.kimbasoft.akka.extension

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.extension.ExtensionActor.Messages.{ManualAction, ConfigAction, CountAction}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ExtensionClient {

  def main(args: Array[String]) {
    val config = ConfigFactory.load("org/kimbasoft/akka/extension/extension-config")
    
    println("-- Creating ActorSystem ")
    val sys = ActorSystem("ExtensionSystem", config)

    /* Registering the ManualExtension with the ActorSystem.
     * This step is not required but it makes sure the extension is registered
     * before the actors are created and utilize it. If not registered with the
     * ActorSystem the extension is registered upon first usage.*/
    sys.registerExtension(ManualExtension)

    println("-- Extension Status ")
    println("  ? ConfigExtension present : " + sys.hasExtension(ConfigExtension))
    println("  ? CountExtension present  : " + sys.hasExtension(CountExtension))
    println("  ? ManualExtension present : " + sys.hasExtension(ManualExtension))

    println("-- Creating CountActor ")
    val actor = sys.actorOf(ExtensionActor.props, "ext-actor")

    println("-- Sending messages to Actor")
    actor ! ConfigAction
    actor ! ManualAction
    actor ! CountAction
    actor ! CountAction
    actor ! CountAction
  }
}

