package org.kimbasoft.akka.router.group

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.router.ActorSupervisor
import org.kimbasoft.akka.router.Messages.RouterRequest

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
    val routerConf = sys.actorOf(Props[GroupActorRouterConf], "router-conf")
    routerConf ! RouterRequest("conf message 0")
    routerConf ! RouterRequest("conf message 1")
    routerConf ! RouterRequest("conf message 2")
    routerConf ! RouterRequest("conf message 3")
    routerConf ! RouterRequest("conf message 4")
    routerConf ! RouterRequest("conf message 5")
    routerConf ! RouterRequest("conf message 6")
    routerConf ! RouterRequest("conf message 7")
    routerConf ! RouterRequest("conf message 8")
    routerConf ! RouterRequest("conf message 9")

    /* Creating Group Router with programmatic provided workers */
    val routerProg = sys.actorOf(Props[GroupActorRouterProg], "router-prog")
    routerProg ! RouterRequest("prog message 0")
    routerProg ! RouterRequest("prog message 1")
    routerProg ! RouterRequest("prog message 2")
    routerProg ! RouterRequest("prog message 3")
    routerProg ! RouterRequest("prog message 4")
    routerProg ! RouterRequest("prog message 5")
    routerProg ! RouterRequest("prog message 6")
    routerProg ! RouterRequest("prog message 7")
    routerProg ! RouterRequest("prog message 8")
    routerProg ! RouterRequest("prog message 9")
  }
}
