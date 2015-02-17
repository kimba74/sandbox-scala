package org.kimbasoft.akka.mailbox

import akka.actor.Actor
import org.kimbasoft.akka.mailbox.MailboxMessages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.mailbox.MailboxMessages.{MailboxRequest, MailboxResponse}

import scala.util.{Failure, Success}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class PriorityMailboxActor extends Actor {

  def receive: Receive = {
    // Processing of recognized message
    case MailboxRequest(message, priority) =>
      println(s"""Priority: received message "$message" [$priority]""")
      sender ! MailboxResponse(Success(s""""$message" - priority processed!"""))
    // Handling of all unrecognized messages
    case message =>
      println(s"""Priority: Unknown message "$message"""")
      sender ! MailboxResponse(Failure(IllegalRequestException))
  }
}
