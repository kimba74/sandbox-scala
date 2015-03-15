package org.kimbasoft.akka.supervision

import akka.actor.SupervisorStrategy.{Resume, Stop}
import akka.actor.{Actor, OneForOneStrategy, Props, SupervisorStrategy}
import org.kimbasoft.akka.supervision.SupervisorActor.Exceptions.{IllegalDepthException, IllegalFactorException, IllegalRequestException}
import org.kimbasoft.akka.supervision.SupervisorActor.Messages.SupervisionRequest

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class SupervisorActor extends Actor {

  var counter = 1

  val name = self.path.name

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case IllegalRequestException =>
      println(s"Child[$name]: IllegalRequestException -> Stopping Actor")
      Stop
    case IllegalDepthException =>
      println(s"Child[$name]: IllegalDepthException -> Resuming Actor")
      Resume
    case IllegalFactorException =>
      println(s"Child[$name]: IllegalFactorException -> Resuming Actor")
      Resume
  }

  def receive: Receive = {
    // Process good requests
    case SupervisionRequest(factor, depth, message) if factor > 0 && depth >= 0 =>
      // Requested depth is larger than 0
      if (depth > 0) {
        println(s">> $name: $message")
        for (c <- 1 to factor) {
          val actName = s"$name.$counter"
          val actRefs = context.actorOf(SupervisorActor.props, actName)
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
        throw IllegalFactorException
      }
      else if (depth < 0) {
        println(s"!! $name: Illegal depth $depth")
        throw IllegalDepthException
      }
      else {
        println(s"!! $name: Illegal Request")
        throw IllegalRequestException
      }
    case _ =>
      throw IllegalRequestException
  }
}

object SupervisorActor {

  val props = Props[SupervisorActor]

  object Messages {
    case class SupervisionRequest(factor: Int, depth: Int, message: String)

    case class SupervisionResponse(response: Try[String])
  }

  object Exceptions {
    object IllegalRequestException extends RuntimeException

    object IllegalFactorException extends RuntimeException

    object IllegalDepthException extends RuntimeException
  }
}
