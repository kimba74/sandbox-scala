package org.kimbasoft.akka.scheduler

import akka.actor.{Props, Actor}
import org.kimbasoft.akka.scheduler.WorkerActor.Messages.Tick

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class WorkerActor extends Actor {

  def receive: Receive = {
    case Tick(message) =>
      println(s"Worker: $message [${System.currentTimeMillis()}]")
    case _ =>
      println(s"Worker: Whut?")
  }
}

object WorkerActor {

  val props = Props[WorkerActor]

  object Messages {
    case class Tick(message: String)
  }
}
