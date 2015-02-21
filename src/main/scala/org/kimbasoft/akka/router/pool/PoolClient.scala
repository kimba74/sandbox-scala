package org.kimbasoft.akka.router.pool

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.router.pool.PoolMessages.PoolRequest

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
    val router = sys.actorOf(Props[PoolActorRouter], "router")

    router ! PoolRequest("message 0")
    router ! PoolRequest("message 1")
    router ! PoolRequest("message 2")
    router ! PoolRequest("message 3")
    router ! PoolRequest("message 4")
    router ! PoolRequest("message 5")
    router ! PoolRequest("message 6")
    router ! PoolRequest("message 7")
    router ! PoolRequest("message 8")
    router ! PoolRequest("message 9")
  }
}