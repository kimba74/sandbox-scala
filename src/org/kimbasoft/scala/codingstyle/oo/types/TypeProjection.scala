package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object TypeProjection {

  trait Logger {
    def log(message: String): Unit
  }

  class ConsoleLogger extends Logger {
    def log(message: String): Unit = println(s"log: $message")
  }

  trait Service {
    type Log <: Logger
    val logger: Log
  }

  class MyService extends Service {
    type Log = ConsoleLogger
    val logger: Log = new ConsoleLogger
  }

  def main(args: Array[String]) {
    // Type Projection (accessed via #)

    /* Trying to project type Log from trait Service will not compile since the
     * type is defined abstract and not yet defined. */
//  val logger1: Service#Log = new ConsoleLogger

    /* Projecting type Log from a concrete implementation of trait Service. This
     * will work since the concrete implementation MyService defines the type to
     * be ConsoleLogger, a sub-type of trait Logger. */
    val logger2: MyService#Log = new ConsoleLogger

    // Singleton Type
    val myS1 = new MyService
    val myS2 = new MyService

    val l11: myS1.logger.type = myS1.logger
//  val l12: myS1.logger.type = myS2.logger  // Will not compile because the Singleton Type differs
  }
}
