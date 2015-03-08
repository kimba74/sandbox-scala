package org.kimbasoft.akka.actor.typed

import akka.actor.{ActorSystem, TypedActor, TypedProps}
import akka.routing.RoundRobinGroup

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Random

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SampleClient {

  def main(args: Array[String]) {
    /* Creating an Actor System. These Actor Systems are very heavy weight therefore
     * only one of them is advisable per Software System. */
    val sys = ActorSystem("TypedActorSystem")

    /* Creating a TypedActor extension for the ActorSystem */
    val ext = TypedActor(sys)

    /* Creating TypedActor and sending predefined messages via methods */
    val actor: SampleActor = ext.typedActorOf(TypedProps[SampleActorImpl], "typed-actor")
    actor.fireAndForget("Hello World!")
    println("Received: " + Await.result(actor.replyResponseNonBlocking("Anyone There?"), 2 seconds))
    println("Received: " + actor.replyResponseOptionBlocking("Hi there!"))
    println("Received: " + actor.replyResponseBlocking("How are you?"))
//    println("Received: " + actor.replyResponseException("Hey, still there?"))

    /* Looking up the ActorRef for TypedActor and sending arbitrary messages */
    val actorRef = ext.getActorRefFor(actor)
    actorRef ! "Hey, can you handle this?"  // Unknown message to TypedActor (will be processed by Receiver trait)

    /* Creating RoundRobin router for TypedActors */
    def createTypedActor(): SampleActor =
      ext.typedActorOf(TypedProps(classOf[SampleActor], new SampleActorImpl(s"actor-${Random.nextInt(500)}")))

    val routees: List[SampleActor] = List.fill(5){ createTypedActor() }
    val routeePaths = routees map{ r => ext.getActorRefFor(r).path.toStringWithoutAddress }

    val router = sys.actorOf(RoundRobinGroup(routeePaths).props, "typed-router")

    val typedRouter: SampleActor = ext.typedActorOf(TypedProps[SampleActorImpl], actorRef = router)

    typedRouter.fireAndForget("Hello")
    typedRouter.fireAndForget("World")
    typedRouter.fireAndForget("How")
    typedRouter.fireAndForget("Are")
    typedRouter.fireAndForget("You")
    typedRouter.fireAndForget("Today?")

  }


}
