package org.kimbasoft.akka.usecase1

import akka.actor._

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object MyClient {

  def main(args: Array[String]) {
    /* Configuring Supervisor Strategy for top-level (user) actors */
    System.setProperty("akka.actor.guardian-supervisor-strategy","org.kimbasoft.akka.usecase1.MyStrategyConfigurator")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    //  val conf = ConfigFactory.load() // Loading Configuration from config file
    val sys = ActorSystem("MyActors")

    /* Creating an Actor of type 'MyActor' in the previously created Actor System.
     * Top-Level Actors will be created via the actorOf() method of the Actor System. */
    val mainActor = sys.actorOf(Props[MyActor], "MainActor")
  }

}
