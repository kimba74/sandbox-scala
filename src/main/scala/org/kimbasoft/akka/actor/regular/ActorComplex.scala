package org.kimbasoft.akka.actor.regular

import akka.actor._
import org.kimbasoft.akka.actor.regular.ActorComplex.Exceptions
import org.kimbasoft.akka.actor.regular.ActorComplex.Exceptions.{ComplexRequestException, ComplexManagementException}
import org.kimbasoft.akka.actor.regular.ActorComplex.Manager.{Stop, Start}
import org.kimbasoft.akka.actor.regular.ActorComplex.Messages.ComplexRequest
import org.kimbasoft.akka.actor.regular.ActorSimple.Messages.{SimpleRequest, SimpleResponse}

import scala.util.Try

/**
 * Missing documentation
 *
 * @since 1.0
 */
class ActorComplex(workers: Int) extends Actor {

  private var wActors = Vector.empty[ActorRef]

  val name = self.path.name

  def receive: Receive = initialize

  val initialize: Receive = {
    case Start =>
      println(s"Actor[$name]: starting up actor with $workers workers")
      wActors = ((1 to workers) map makeActor).toVector
      context become processing
    case request =>
      println(s"Actor[$name]: received unknown management request $request")
      sender ! ComplexManagementException
  }

  val processing: Receive = {
    case ComplexRequest(message: String) =>
      println(s"""Actor[$name]: forwarding message "$message" to workers""")
      wActors foreach { act => act ! SimpleRequest(message) }
    case ComplexRequest(message: Int) =>
      println(s"""Actor[$name]: keeping "$message" to myself""")
    case response: SimpleResponse =>
      println(s"""Actor[$name]: worker responded with "$response"""")
    case Stop =>
      wActors foreach { act => act ! PoisonPill }
      context become shuttingdown
    case request =>
      println(s"""Actor[$name]: received unknown request "$request"""")
      sender ! ComplexRequestException
  }

  val shuttingdown: Receive = {
    case Terminated(worker) =>
      println(s"Actor[$name]: shut down $worker")
      wActors = wActors diff Vector(worker)
      if (wActors.isEmpty) {
        println(s"Actor[$name]: all workers are stopped, stopping myself")
        context stop self
      }
    case _ =>
      sender ! Exceptions.ComplexManagementException
  }

  /**
   * Utility method creating a new child Actor and registering this actor
   * as the child's DeathWatch.
   */
  private def makeActor(id: Int): ActorRef = context.watch(context.actorOf(ActorSimple.props, s"worker-$id"))
}

object ActorComplex {

  def props(workers: Int): Props = Props(classOf[ActorComplex], workers)

  object Manager {

    case object Start

    case object Stop

  }

  object Messages {

    case class ComplexRequest(message: Any)

    case class ComplexResponse(response: Try[Any])

  }

  object Exceptions {

    case object ComplexManagementException extends RuntimeException

    case object ComplexRequestException extends RuntimeException

  }
}