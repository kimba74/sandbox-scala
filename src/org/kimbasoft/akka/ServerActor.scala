package org.kimbasoft.akka


import akka.actor._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Try, Failure, Success}
import scala.util.control.NonFatal

/**
 * Missing documentation
 *
 * @since 1.0
 */
class ServerActor extends Actor {

  import Messages._

  implicit val timeout = Timeout(1.seconds)

  override def supervisorStrategy: SupervisorStrategy = {
    val decider: SupervisorStrategy.Decider = {
      case WorkerActor.CrashException => SupervisorStrategy.Restart
      case NonFatal(ex) => SupervisorStrategy.Resume
    }
    OneForOneStrategy()(decider orElse super.supervisorStrategy.decider)
  }

  var workers = Vector.empty[ActorRef]

  def receive = initial

  val initial: Receive = {
    case Start(numOfWorkers) =>
      workers = ((1 to numOfWorkers) map makeWorker).toVector
      context become processRequests
  }

  val processRequests: Receive = {
    case c @ Crash(n) => workers(n % workers.size) ! c
    case DumpAll =>
      Future.fold(workers map (_ ? DumpAll))(Vector.empty[Any])(_ :+ _).onComplete(askHandler("State of the Workers"))
    case Dump(n) =>
      (workers(n % workers.size) ? DumpAll).map(Vector(_)).onComplete(askHandler(s"State of Worker $n"))
    case request: Request =>
      val index = request.key.toInt % workers.size
      workers(index) ! request
    case Response(Success(message)) => printResult(message)
    case Response(Failure(ex)) => printResult(s"Error! $ex")
  }

  def askHandler(prefix: String): PartialFunction[Try[Any],Unit] = {
    case Success(suc) => suc match {
      case vect: Vector[_] =>
        printResult(s"$prefix:\n")
        vect foreach {
          case Response(Success(message)) => printResult(s"$message")
          case Response(Failure(ex)) => printResult(s"ERROR! Success received wrapping $ex")
        }
      case _ => printResult(s"BUG! Expected a vector, got $suc")
    }
    case Failure(ex) => printResult(s"ERROR! $ex")
  }

  protected def printResult(message: String) = {
    println(s"<< $message")
  }

  protected def makeWorker(i: Int) = context.actorOf(Props[WorkerActor], s"worker-$i")
}

object ServerActor {
  def make(system: ActorSystem): ActorRef = system.actorOf(Props[ServerActor], "server")
}
