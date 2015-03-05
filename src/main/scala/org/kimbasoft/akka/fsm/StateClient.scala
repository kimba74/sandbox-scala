package org.kimbasoft.akka.fsm

import akka.actor.FSM.{UnsubscribeTransitionCallBack, SubscribeTransitionCallBack}
import akka.actor.{Props, ActorSystem}
import org.kimbasoft.akka.fsm.StateActorFSM.{Flush, Initialize, QueueMessage}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object StateClient {

  def main(args: Array[String]) {
    val sys = ActorSystem("FSMSystem")

    val fsmActor = sys.actorOf(Props[StateActorFSM], "state-actor")
    val lsnActor = sys.actorOf(Props[StateActorListener], "state-listener")

    // Call will fail because the Actor is still uninitialized in state 'Idle'
    fsmActor ! QueueMessage("Hello World!")

    /* Sending the initializer message leaving the Actor in state 'Idle' but setting
     * its internal data state from 'Uninitialized' to 'Operational' */
    fsmActor ! Initialize(lsnActor)

    /* Sending a control message to the Actor registering an external Actor as listener
     * for state transitions. This Actor will send initially a CurrentState(self, stateName)
     * message to the listener to inform it of the current state, then it will send a
     * Transition(actorRef, oldState, newState) message for every state transition performed.
     * The listener can be unregistered by sending a UnsubscribeTransitionCallBack(actorRef)
     * to the actor (see below) */
    fsmActor ! SubscribeTransitionCallBack(lsnActor)

    /* Sending BustMessages to Actor for processing. First message will set the Actor
     * into state 'Active' and keep the data state as 'Operational' */
    fsmActor ! QueueMessage("Hello")
    fsmActor ! QueueMessage("World")
    fsmActor ! QueueMessage("How")
    fsmActor ! QueueMessage("Are")
    fsmActor ! QueueMessage("You?")

    /* Sending Actor a Flush message causing the Actor to reset the data in its queue
     * and transition back to state 'Idle'. The internal data state will remain
     * 'Operational' but with an empty data queue */
    fsmActor ! Flush

    /* Sending control message to the Actor un-registering the external Actor listening
     * to state transitions */
    fsmActor ! UnsubscribeTransitionCallBack(lsnActor)
  }
}
