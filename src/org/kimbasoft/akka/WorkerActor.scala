package org.kimbasoft.akka

import akka.actor.Actor

import scala.util.{Success, Try}

/**
 * Missing documentation
 *
 * @since 1.0
 */
class WorkerActor extends Actor {
  import org.kimbasoft.akka.Messages._

  private val datastore = collection.mutable.Map.empty[Long, String]

  def receive: Receive = {
    case Create(key, value) =>
      datastore += key -> value
      sender ! Response(Success(s"$key -> $value added"))
    case Read(key) =>
      sender ! Response(Try(s"${datastore(key)} found for key = $key"))
    case Update(key, value) =>
      datastore += key -> value
      sender ! Response(Success(s"$key -> $value updated"))
    case Delete(key) =>
      datastore -= key
      sender ! Response(Success(s"$key deleted"))
    case Crash(_) => throw WorkerActor.CrashException
    case DumpAll =>
      sender ! Response(Success(s"${self.path}: datastore = $datastore"))
  }
}

object WorkerActor {
  case object CrashException extends RuntimeException("Crash!")
}