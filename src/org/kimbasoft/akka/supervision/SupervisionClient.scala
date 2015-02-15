package org.kimbasoft.akka.supervision

import akka.actor.{Props, ActorSystem}
import org.kimbasoft.akka.supervision.Messages.SupervisionRequest

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SupervisionClient {

  def main(args: Array[String]) {
    /* Configuring Supervisor Strategy for top-level (user) actors */
    System.setProperty("akka.actor.guardian-supervisor-strategy","org.kimbasoft.akka.supervision.SupervisionStrategyConfig")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    //  val conf = ConfigFactory.load() // Loading Configuration from config file
    val sys = ActorSystem("SupervisionSystem")

    /* Creating an Actor of type 'MyActor' in the previously created Actor System.
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
