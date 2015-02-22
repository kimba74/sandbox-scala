package org.kimbasoft.akka.router.group

import akka.actor.{Actor, Props}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class GroupActorSupervisor extends Actor {
  var w1 = context.actorOf(Props[GroupActorWorker], name = s"worker1")
  var w2 = context.actorOf(Props[GroupActorWorker], name = s"worker2")
  var w3 = context.actorOf(Props[GroupActorWorker], name = s"worker3")

  def receive: Receive = {
    case _ =>
  }
}
