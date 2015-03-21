package org.kimbasoft.akka.extension

import akka.actor.{Props, Actor}


/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class CountActor extends Actor with Counting {
  import org.kimbasoft.akka.extension.CountActor.Messages.CountAction

  override def receive: Receive = {
    case CountAction =>
      println(s"Current Counter state is ${increment()}")
    case action =>
      println(s"Unrecognized action $action")
  }
}

object CountActor {

  val props = Props[CountActor]

  object Messages {

    case object CountAction

  }
}
