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
    override def log(message: String): Unit = println(s"log: $message")
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
    val logger: MyService#Log = new ConsoleLogger // Type projection on concrete implementations using the # symbol

    // Singleton Type
    val myS1 = new MyService
    val myS2 = new MyService

    val l11: myS1.logger.type = myS1.logger
//  val l12: myS1.logger.type = myS2.logger  // Will not compile because the Singleton Type differs
  }
}
