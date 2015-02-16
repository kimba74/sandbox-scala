package org.kimbasoft.akka.mailbox

import akka.actor.Actor
import org.kimbasoft.akka.mailbox.Messages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.mailbox.Messages.{ConfigResponse, ConfigRequest}

import scala.util.{Success, Failure}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MailboxActor extends Actor {

  def receive: Receive = {
    // Processing of recognized message
    case ConfigRequest(messages) =>
      println(s"Mailbox: $messages")
      sender ! ConfigResponse(Success(s""""$messages" - processed!"""))
    // Handling of all unrecognized messages
    case _ =>
      sender ! ConfigResponse(Failure(IllegalRequestException))
  }
}
