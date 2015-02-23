package org.kimbasoft.akka.router.group

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.router.ActorSupervisor
import org.kimbasoft.akka.router.Messages.{ConfRouterRequest, ProgRouterRequest}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object GroupClient {

  def main(args: Array[String]) {
    /* Reading ActorSystem configuration from embedded config file */
    val config = ConfigFactory.load("org/kimbasoft/akka/router/group/group-config")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    val sys = ActorSystem("GroupSystem", config)

    /* Creating supervising actor for the workers */
    val supervisor = sys.actorOf(Props(classOf[ActorSupervisor], "Group", 3), "super")

    /* Creating Group Router with configuration provided workers */
    val group = sys.actorOf(Props[GroupRouter], "router")
    group ! ConfRouterRequest("message 1")
    group ! ConfRouterRequest("message 2")
    group ! ConfRouterRequest("message 3")
    group ! ConfRouterRequest("message 4")
    group ! ConfRouterRequest("message 5")
    group ! ConfRouterRequest("message 6")
    group ! ProgRouterRequest("message A")
    group ! ProgRouterRequest("message B")
    group ! ProgRouterRequest("message C")
    group ! ProgRouterRequest("message D")
    group ! ProgRouterRequest("message E")
    group ! ProgRouterRequest("message F")
  }
}
