package org.kimbasoft.akka.usecase1

import akka.actor.Actor

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MySplitActor extends Actor {

  override def receive: Receive = {
    case _ => "Do Nothing"
  }

}


