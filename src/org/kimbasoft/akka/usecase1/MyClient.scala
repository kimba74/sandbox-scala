package org.kimbasoft.akka.usecase1

import akka.actor._
import org.kimbasoft.akka.usecase1.MyActorMessages.{ProcessSummation, ProcessFactorial}

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

    /* Creating some sample data for the Actor to process and then passing the content
     * as payload of a processing instruction message to the Actor. */
    // Creating the sample data
    val seq1 = Seq(1, 2, 3, 4, 5, 6)
    val seq2 = Seq(4, 5, 6, 7, 8, 9)

    // Sending the sample data as payload of a message to the Actor
    mainActor ! ProcessFactorial(seq1)
    mainActor ! ProcessSummation(seq2)
  }

}
