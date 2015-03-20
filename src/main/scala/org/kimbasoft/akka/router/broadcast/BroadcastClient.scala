package org.kimbasoft.akka.router.broadcast

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.router.ActorSupervisor
import org.kimbasoft.akka.router.ActorWorker.Messages.{ConfRouterRequest, ProgRouterRequest}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object BroadcastClient {

  def main(args: Array[String]) {
    /* Reading ActorSystem configuration from embedded config file */
    val config = ConfigFactory.load("org/kimbasoft/akka/router/broadcast/broadcast-config")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
   * only one of them is advisable per Software System. */
    val sys = ActorSystem("BroadcastSystem", config)

    /* Creating supervising actor for the workers */
    val supervisor = sys.actorOf(ActorSupervisor.props("Group", 3), "super")

    /* Creating Broadcast Group Router and sending messages */
    val group = sys.actorOf(Props[BroadcastRouterGroup], "router-group")
    group ! ConfRouterRequest("message 0")
    group ! ConfRouterRequest("message 1")
    group ! ConfRouterRequest("message 2")
    group ! ConfRouterRequest("message 3")
    group ! ConfRouterRequest("message 4")
    group ! ProgRouterRequest("message 5")
    group ! ProgRouterRequest("message 6")
    group ! ProgRouterRequest("message 7")
    group ! ProgRouterRequest("message 8")
    group ! ProgRouterRequest("message 9")

    /* Sleep 100ms to give first example enough time to finish */
    Thread.sleep(100)

    /* Creating Broadcast Pool Router and sending messages */
    val pool = sys.actorOf(Props[BroadcastRouterPool], "router-pool")
    pool ! ConfRouterRequest("message A")
    pool ! ConfRouterRequest("message B")
    pool ! ConfRouterRequest("message C")
    pool ! ConfRouterRequest("message D")
    pool ! ConfRouterRequest("message E")
    pool ! ProgRouterRequest("message F")
    pool ! ProgRouterRequest("message G")
    pool ! ProgRouterRequest("message H")
    pool ! ProgRouterRequest("message I")
    pool ! ProgRouterRequest("message J")
  }
}
