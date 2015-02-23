package org.kimbasoft.akka.router.pool

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.router.Messages.{ConfRouterRequest, ProgRouterRequest}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object PoolClient {

  def main(args: Array[String]) {
    /* Reading ActorSystem configuration from embedded config file */
    val config = ConfigFactory.load("org/kimbasoft/akka/router/pool/pool-config")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    val sys = ActorSystem("PoolSystem", config)

    /* Creating an Actor as router in the previously constructed ActorSystem */
    val pool = sys.actorOf(Props[PoolRouter], "router")
    pool ! ConfRouterRequest("message 1")
    pool ! ConfRouterRequest("message 2")
    pool ! ConfRouterRequest("message 3")
    pool ! ConfRouterRequest("message 4")
    pool ! ConfRouterRequest("message 5")
    pool ! ConfRouterRequest("message 6")
    pool ! ProgRouterRequest("message A")
    pool ! ProgRouterRequest("message B")
    pool ! ProgRouterRequest("message C")
    pool ! ProgRouterRequest("message D")
    pool ! ProgRouterRequest("message E")
    pool ! ProgRouterRequest("message F")
  }
}