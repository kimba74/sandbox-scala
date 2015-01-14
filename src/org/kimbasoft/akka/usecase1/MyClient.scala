package org.kimbasoft.akka.usecase1

import akka.actor.{Props, ActorSystem}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object MyClient {

  def main(args: Array[String]) {
    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    val sys = ActorSystem("My Actors")

    /* Creating an Actor of type 'MyActor' in the previously created Actor System.
     * Top-Level Actors will be created via the actorOf() method of the Actor System. */
    val mainActor = sys.actorOf(Props[MyActor], "MainActor")
  }

}
