package org.kimbasoft.akka.router.group

import akka.actor.{ActorSystem, Props}
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

    /* Creating supervising actor for the workers */
    val supervisor = sys.actorOf(Props[GroupActorSupervisor], "super")

    /* Creating Group Router with configuration provided workers */
    val routerConf = sys.actorOf(Props[GroupActorRouterConf], "router-conf")
    routerConf ! GroupRequest("conf message 0")
    routerConf ! GroupRequest("conf message 1")
    routerConf ! GroupRequest("conf message 2")
    routerConf ! GroupRequest("conf message 3")
    routerConf ! GroupRequest("conf message 4")
    routerConf ! GroupRequest("conf message 5")
    routerConf ! GroupRequest("conf message 6")
    routerConf ! GroupRequest("conf message 7")
    routerConf ! GroupRequest("conf message 8")
    routerConf ! GroupRequest("conf message 9")

    /* Creating Group Router with programmatic provided workers */
    val routerProg = sys.actorOf(Props[GroupActorRouterProg], "router-prog")
    routerProg ! GroupRequest("prog message 0")
    routerProg ! GroupRequest("prog message 1")
    routerProg ! GroupRequest("prog message 2")
    routerProg ! GroupRequest("prog message 3")
    routerProg ! GroupRequest("prog message 4")
    routerProg ! GroupRequest("prog message 5")
    routerProg ! GroupRequest("prog message 6")
    routerProg ! GroupRequest("prog message 7")
    routerProg ! GroupRequest("prog message 8")
    routerProg ! GroupRequest("prog message 9")
  }
}
