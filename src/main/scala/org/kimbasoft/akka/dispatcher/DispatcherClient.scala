package org.kimbasoft.akka.dispatcher

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.dispatcher.DispatcherActor.Messages.DispatcherRequest

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object DispatcherClient {

  def main(args: Array[String]) {
    /* Reading ActorSystem configuration from embedded config file */
    val config = ConfigFactory.load("org/kimbasoft/akka/dispatcher/dispatcher-config")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    val sys = ActorSystem("DispatcherSystem", config)

    val actorDefault = sys.actorOf(DispatcherActor.props, "standard-actor")
    actorDefault ! DispatcherRequest("default 1")
    actorDefault ! DispatcherRequest("default 2")
    actorDefault ! DispatcherRequest("default 3")

    val actorSimple = sys.actorOf(DispatcherActor.props, "simple-dispatcher-actor")
    actorSimple ! DispatcherRequest("simple 1")
    actorSimple ! DispatcherRequest("simple 2")
    actorSimple ! DispatcherRequest("simple 3")

    val actorThreadPool = sys.actorOf(DispatcherActor.props, "thread-pool-dispatcher-actor")
    actorThreadPool ! DispatcherRequest("thread pool 1")
    actorThreadPool ! DispatcherRequest("thread pool 2")
    actorThreadPool ! DispatcherRequest("thread pool 3")
  }
}
