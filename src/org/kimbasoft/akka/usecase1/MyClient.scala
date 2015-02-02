package org.kimbasoft.akka.usecase1

import akka.actor._
import scala.concurrent.duration._
import akka.pattern._
import akka.util.Timeout
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
    val mainActor = sys.actorOf(Props[MyActor], "MyActor1")

    /* Creating some sample data for the Actor to process and then passing the content
     * as payload of a processing instruction message to the Actor. */
    // Creating the sample data
    val seq1 = Seq(1, 2, 3, 4, 5, 6)
    val seq2 = Seq(4, 5, 6, 7, 8, 9)

    /* Sending the sample data as payload of a message to the Actor fire-and-forget.
     * Since the client is not an Actor itself it cannot receive the response message
     * and the response will go to the dead-letter queue ('tell' pattern) */
    mainActor ! ProcessFactorial(seq1)
    mainActor ! ProcessSummation(seq1)

    /* Sending the sample data as payload of a message to the Actor and requesting a
     * Future object so client can retrieve result later. ('ask' pattern) */
    implicit val timeout = Timeout(1.seconds)
    val f1 = mainActor ? ProcessFactorial(seq2)
    val f2 = mainActor ? ProcessSummation(seq2)
    // Process Future objects results
    do {
      if (f1.isCompleted)
        println(s"Value: ${f1.value}")
      if (f2.isCompleted)
        println(s"Value: ${f2.value}")
    } while (!f1.isCompleted && !f2.isCompleted)
  }

}
