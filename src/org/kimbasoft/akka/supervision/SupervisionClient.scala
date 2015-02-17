package org.kimbasoft.akka.supervision

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.supervision.SupervisionMessages.SupervisionRequest

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SupervisionClient {

  def main(args: Array[String]) {
    /* Reading ActorSystem configuration from embedded config file */
    val config = ConfigFactory.load("org/kimbasoft/akka/supervision/supervision-config")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    val sys = ActorSystem("SupervisionSystem", config)

    /* Creating an Actor of type 'SupervisorActor' in the previously created Actor System.
     * Top-Level Actors will be created via the actorOf() method of the Actor System. */
    val actor = sys.actorOf(Props(classOf[SupervisorActor], "root"), "root")

    // Successful Request
    actor ! SupervisionRequest(2, 3, "Hello World")

    // Failing Request ('factor' too small)
    actor ! SupervisionRequest(0, 3, "Hi There")

    // Failing Request ('depth' negative)
    actor ! SupervisionRequest(2, -5, "Hi There")
  }
}
