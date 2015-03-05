package org.kimbasoft.akka.fsm

import akka.actor.{ActorRef, FSM}
import org.kimbasoft.akka.fsm.StateActorFSM._

import scala.collection.immutable
import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class StateActorFSM extends FSM[State, Data] {

  startWith(Idle, Uninitialized)

  /**
   * This block registers a StateFunction for the state 'Idle'
   * thereby declaring the possible transitions within this state.
   * A StateFunction is declared as PartialFunction[Event, State]
   * meaning it will pass an Event in and requires a State to be returned.
   * The Event is defined as Event[Data] and holds the actual received
   * message as well as the current Actor state. FSM provides a convenience
   * unapply() method that allows the match block to match Event like
   * Event(message, state_data). The 'when()' block is stackable meaning
   * it can be called multiple times without overwriting what has already
   * been registered.
   */
  when(Idle) {
    case Event(Initialize(actor), Uninitialized) =>
      log.info(s"""State: Setting target to $actor [$stateName, $stateData]""")
      stay using Operational(actor, Vector.empty[String])
  }

  /**
   * This block registers a StateFunction for the state 'Active'
   * and it also sets a timeout for the state. If no message is
   * received withing this timeout window the StateFunction will
   * receive a 'FSM.StateTimeout' message.
   */
  when(Active, 2 seconds) {
    case Event(Flush | StateTimeout, r: Operational) =>
      log.info(s"""State: Received Flush/StateTimeout! [$stateName -> Idle, $stateData]""")
      goto(Idle) using r.copy(queue = Vector.empty[String])
  }

  /**
   * This block registers a StateFunction for all messages that
   * were not handled by one of the state bound blocks. Use Cases
   * for this block include error handling for unknown message or
   * handling of messages that are common across all states.
   * NOTE: Other then the when() handlers, the whenUnhandled() handler
   *       is not stackable, meaning it cannot be called more than one
   *       time. If it does get called more than once the last call will
   *       overwrite the handler's behavior
   */
  whenUnhandled {
    case Event(QueueMessage(msg), r @ Operational(_, queue)) =>
      log.info(s"""State: Adding "$msg" to queue! [$stateName -> Active, $stateData]""")
      goto(Active) using r.copy(queue = queue :+ msg) // Will work even if already in state Active
    case Event(event, data) =>
      log.warning(s"""Unknown event $event received! [$stateName, $data]""")
      stay
  }

  /**
   * This block registers a TransitionHandler, which allows actions to be executed
   * on transitions between states. A TransitionHandler is a callback handler that will
   * be called every time a state handler triggers a state transition. The TransitionHandler
   * is defined as a PartialFunction[(State, State), Unit] meaning it will send a state tuple
   * into the match block and expects no return value. FSM provides a convenience '->' method
   * to decompose the state tuple, which also helps reminding the developer from which to which
   * state the transition is taking place.
   * NOTE: Same as the whenUnhandled() handler registration, onTransition() is not stackable
   *       and can therefore only be called once. Every subsequent invocation will overwrite
   *       the already configured handler.
   */
  onTransition {
    case Active -> Idle =>
      stateData match {
        case Operational(target, queue) =>
          log.info(s"""Transition: Sending collected messages $queue to Actor $target""")
          target ! Batch(queue)
      }
  }
}

object StateActorFSM {
  // Declaring the possible FSM states
  sealed trait State
  case object Idle extends State
  case object Active extends State

  // Declaring the internal FSM data state
  sealed trait Data
  case object Uninitialized extends Data
  case class Operational(target: ActorRef, queue: immutable.Seq[String]) extends Data

  // Declaring incoming events
  case class Initialize(target: ActorRef)
  case class QueueMessage(message: String)
  case object Flush

  // Declaring outgoing events
  case class Batch(messages: immutable.Seq[String])
}
