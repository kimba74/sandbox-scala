package org.kimbasoft.akka.actor.dsl

// Import of ActorDSL and all its members is required
import akka.actor.ActorDSL._
import akka.actor.ActorSystem
import org.kimbasoft.akka.actor.dsl.Messages.Exceptions.IllegalDSLRequestException
import org.kimbasoft.akka.actor.dsl.Messages.{DSLResponse, DSLRequest}

import scala.util.Failure

/**
 * Missing documentation
 *
 * @since 1.0
 */
object DSLClient {

  def main(args: Array[String]) {

    val sys = ActorSystem("DSLSystem")

    val parent = actor(sys, "parent")(new Act {
      // Define a child the DSL way for this actor
      val child = actor("child")(new Act {
        become {
          case DSLRequest(message: String) =>
            println(s"""DSL Child Actor: "$message" """)
          case _ =>
            sender ! DSLResponse(Failure(IllegalDSLRequestException))
        }
      })
      // Define the behavior of this actor
      become {
        case DSLRequest(message: String) =>
          println(s"""DSL Parent Actor: "$message" """)
        case DSLRequest(request: DSLRequest) =>
          child forward request
        case _ =>
          sender ! DSLResponse(Failure(IllegalDSLRequestException))
      }
    })

    parent ! DSLRequest("Hello World") // Will be processed by the parent actor
    parent ! DSLRequest(DSLRequest("Hello Child")) // Will be processed by the child actor
    parent ! DSLRequest(12345) // Will be rejected by the parent actor
    parent ! DSLRequest(DSLRequest(67890)) // Will be rejected by the parent actor
  }
}
