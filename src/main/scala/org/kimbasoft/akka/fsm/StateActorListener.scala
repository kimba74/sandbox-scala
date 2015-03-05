package org.kimbasoft.akka.fsm

import akka.actor.FSM.{Transition, CurrentState}
import akka.actor.{ActorLogging, Actor}
import org.kimbasoft.akka.fsm.StateActorFSM.Batch

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class StateActorListener extends Actor with ActorLogging {

  def receive: Receive = {
    case Batch(queue) =>
      log.info(s"Received Batch message with payload $queue")
    case CurrentState(actor, state) =>
      log.info(s"Subscribed to $actor in state '$state'")
    case Transition(actor, oldState, newState) =>
      log.info(s"$actor changed states '$oldState' -> '$newState'  ")
    case event =>
      log.warning(s"Received unknown event $event")
  }
}
