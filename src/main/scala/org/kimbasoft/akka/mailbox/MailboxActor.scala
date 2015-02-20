package org.kimbasoft.akka.mailbox

import akka.actor.Actor
import org.kimbasoft.akka.mailbox.MailboxMessages.Exceptions.IllegalRequestException
import org.kimbasoft.akka.mailbox.MailboxMessages.{MailboxResponse, MailboxRequest}

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
    case MailboxRequest(message, priority) =>
      println(s"""Mailbox: received message "$message" [$priority]""")
      sender ! MailboxResponse(Success(s""""$message" - processed!"""))
    // Handling of all unrecognized messages
    case message =>
      println(s"""Mailbox: Unknown message "$message"""")
      sender ! MailboxResponse(Failure(IllegalRequestException))
  }
}
