package org.kimbasoft.akka.router.group

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.util.Timeout
import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.router.group.GroupMessages.GroupRequest

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

    val supervisor = sys.actorOf(Props[GroupActorSupervisor], "super")

    val router = sys.actorOf(Props[GroupActorRouter], "router")
    router ! GroupRequest("message 0")
    router ! GroupRequest("message 1")
    router ! GroupRequest("message 2")
    router ! GroupRequest("message 3")
    router ! GroupRequest("message 4")
    router ! GroupRequest("message 5")
    router ! GroupRequest("message 6")
    router ! GroupRequest("message 7")
    router ! GroupRequest("message 8")
    router ! GroupRequest("message 9")
  }
}
