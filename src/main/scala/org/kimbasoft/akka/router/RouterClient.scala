package org.kimbasoft.akka.router

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.router.RouterMessages.RouterRequest

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object RouterClient {

  def main(args: Array[String]) {
    /* Reading ActorSystem configuration from embedded config file */
    val config = ConfigFactory.load("org/kimbasoft/akka/router/router-config")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
   * only one of them is advisable per Software System. */
    val sys = ActorSystem("RouterSystem", config)

    /* Creating an Actor with default mailbox in the previously constructed ActorSystem */
    val actor1 = sys.actorOf(Props[RouterActor], "router")

    actor1 ! RouterRequest("message 0")
    actor1 ! RouterRequest("message 1")
    actor1 ! RouterRequest("message 2")
    actor1 ! RouterRequest("message 3")
    actor1 ! RouterRequest("message 4")
    actor1 ! RouterRequest("message 5")
    actor1 ! RouterRequest("message 6")
    actor1 ! RouterRequest("message 7")
    actor1 ! RouterRequest("message 8")
    actor1 ! RouterRequest("message 9")
  }
}