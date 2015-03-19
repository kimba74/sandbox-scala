package org.kimbasoft.akka.router

import akka.actor.Actor

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class ActorSupervisor(worker_type: String, worker_num: Int) extends Actor {

  println(s"""Creating $worker_num "$worker_type" workers""")
  val workers = ((1 to worker_num) map createWorker).toVector
  println(s"""Workers: $workers""")

  def receive: Receive = {
    case _ => // Don't do anything here
  }

  private def createWorker(index: Int) = context.actorOf(ActorWorker.props(worker_type), s"worker$index")
}
