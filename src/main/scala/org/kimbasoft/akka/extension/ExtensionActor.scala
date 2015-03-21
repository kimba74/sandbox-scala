package org.kimbasoft.akka.extension

import akka.actor.{Props, Actor}
import org.kimbasoft.akka.extension.ExtensionActor.Messages.{ManualAction, ConfigAction}


/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class ExtensionActor extends Actor with Counting {
  import org.kimbasoft.akka.extension.ExtensionActor.Messages.CountAction

  val name = self.path.name

  // Accessing ConfigExtension registered with this ActorSystem
  val configExt = ConfigExtension(context.system)
  // Accessing ManualExtension registered with this ActorSystem
  val manualExt = ManualExtension(context.system)

  override def receive: Receive = {
    case ConfigAction =>
      println(s"  $name - ConfigAction: ${configExt.configMsg}")
    case CountAction =>
      println(s"  $name - CountAction : Current Counter state is ${increment()}")
    case ManualAction =>
      println(s"  $name - ManualAction: ${manualExt.manualMsg}")
    case action =>
      println(s"  !! Unrecognized action $action")
  }
}

object ExtensionActor {

  val props = Props[ExtensionActor]

  object Messages {

    case object ConfigAction

    case object CountAction

    case object ManualAction

  }
}
