package org.kimbasoft.akka.supervision

import akka.actor.SupervisorStrategy.Resume
import akka.actor.{Actor, OneForOneStrategy, Props, SupervisorStrategy}
import org.kimbasoft.akka.supervision.Messages.Exceptions.{IllegalFactorException, IllegalDepthException, IllegalSupervisionException}
import org.kimbasoft.akka.supervision.Messages.{SupervisionRequest, SupervisionResponse}

import scala.util.Failure

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class SupervisorActor(name: String) extends Actor {

  var counter = 1

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case IllegalSupervisionException => Resume
  }

  def receive: Receive = {
    // Process good requests
    case SupervisionRequest(factor, depth, message) if factor > 0 && depth >= 0 =>
      // Requested depth is larger than 0
      if (depth > 0) {
        println(s">> $name: $message")
        for (c <- 1 to factor) {
          val actName = s"$name.$counter"
          val actRefs = context.actorOf(Props(classOf[SupervisorActor], actName), actName)
          actRefs ! SupervisionRequest(factor, depth - 1, message)
          counter += 1
        }
      }
      // Requested depth has been reached
      else {
        println(s"== $name: $message")
      }
    // Handle requests with bad parameters
    case SupervisionRequest(factor, depth, _) =>
      if (factor < 1) {
        println(s"!! $name: Illegal factor $factor")
        sender ! SupervisionResponse(Failure(IllegalFactorException))
      }
      else if (depth < 0) {
        println(s"!! $name: Illegal depth $depth")
        sender ! SupervisionResponse(Failure(IllegalDepthException))
      }
      else {
        println(s"!! $name: Illegal Request")
        sender ! SupervisionResponse(Failure(IllegalSupervisionException))
      }
    case _ =>
      sender ! SupervisionResponse(Failure(IllegalSupervisionException))
  }
}
