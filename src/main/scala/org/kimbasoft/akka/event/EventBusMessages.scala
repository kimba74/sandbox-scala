package org.kimbasoft.akka.event

import scala.util.Try

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object EventBusMessages {

  case class BusMessage(message: String)

  case class BusPublication(message: String)

  case class BusPublicationRequest(message: String)

  case class BusRequestMessage(message: String)

  case class BusResponseMessage(response: Try[String])

  object Exceptions {

    case object IllegalBusRequest extends RuntimeException

  }
}
