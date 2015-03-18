package org.kimbasoft.akka.mailbox

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.kimbasoft.akka.mailbox.MailboxActor.Messages.MailboxRequest
import org.kimbasoft.akka.mailbox.MailboxActor.Priority

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object MailboxClient {

  def main(args: Array[String]) {
    /* Reading ActorSystem configuration from embedded config file */
    val config = ConfigFactory.load("org/kimbasoft/akka/mailbox/mailbox-config")

    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    val sys = ActorSystem("MailboxSystem", config)

    /* Creating an Actor with default mailbox in the previously constructed ActorSystem */
    val actor1 = sys.actorOf(MailboxActor.props, "mailbox-actor")
    /* Creating an Actor with priority mailbox in the previously constructed ActorSystem */
    val actor2 = sys.actorOf(MailboxActor.props, "priority-actor")

    /* Sending MailboxRequest to actor with default mailbox */
    println("-- Default Mailbox -------------------------------------")
    actor1 ! MailboxRequest("Hello Low 1!", Priority.LOW)
    actor1 ! MailboxRequest("Hello Medium 1!", Priority.MEDIUM)
    actor1 ! MailboxRequest("Hello Normal 1!", Priority.NORMAL)
    actor1 ! MailboxRequest("Hello High 1!", Priority.HIGH)
    actor1 ! MailboxRequest("Hello World 1!")
    actor1 ! MailboxRequest("Hello Low 2!", Priority.LOW)
    actor1 ! MailboxRequest("Hello High 2!", Priority.HIGH)
    actor1 ! MailboxRequest("Hello World 2!")
    actor1 ! MailboxRequest("Hello Normal 2!", Priority.NORMAL)
    actor1 ! MailboxRequest("Hello Medium 2!", Priority.MEDIUM)
    actor1 ! MailboxRequest("Hello High 3!", Priority.HIGH)
    actor1 ! MailboxRequest("Hello Low 3!", Priority.LOW)
    actor1 ! MailboxRequest("Hello World 3!")
    actor1 ! MailboxRequest("Hello Medium 3!", Priority.MEDIUM)
    actor1 ! MailboxRequest("Hello Normal 3!", Priority.NORMAL)

    /* Sleep 2 seconds to allow Default Mailbox Actor to finish up */
    Thread.sleep(2000)

    /* Sending MailboxRequest to actor with PriorityMailbox implementation */
    println("-- Priority Mailbox ------------------------------------")
    actor2 ! MailboxRequest("Hello Low 1!", Priority.LOW)
    actor2 ! MailboxRequest("Hello Medium 1!", Priority.MEDIUM)
    actor2 ! MailboxRequest("Hello Normal 1!", Priority.NORMAL)
    actor2 ! MailboxRequest("Hello High 1!", Priority.HIGH)
    actor2 ! MailboxRequest("Hello World 1!")
    actor2 ! MailboxRequest("Hello Low 2!", Priority.LOW)
    actor2 ! MailboxRequest("Hello High 2!", Priority.HIGH)
    actor2 ! MailboxRequest("Hello World 2!")
    actor2 ! MailboxRequest("Hello Normal 2!", Priority.NORMAL)
    actor2 ! MailboxRequest("Hello Medium 2!", Priority.MEDIUM)
    actor2 ! MailboxRequest("Hello High 3!", Priority.HIGH)
    actor2 ! MailboxRequest("Hello Low 3!", Priority.LOW)
    actor2 ! MailboxRequest("Hello World 3!")
    actor2 ! MailboxRequest("Hello Medium 3!", Priority.MEDIUM)
    actor2 ! MailboxRequest("Hello Normal 3!", Priority.NORMAL)
  }
}
