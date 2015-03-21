package org.kimbasoft.akka.extension

import akka.actor.{ExtendedActorSystem, Extension, ExtensionId, ExtensionIdProvider}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ManualExtension extends ExtensionId[ManualExtensionImpl] with ExtensionIdProvider {

  override def createExtension(system: ExtendedActorSystem): ManualExtensionImpl = {
    println("  ! Created ManualExtension")
    new ManualExtensionImpl
  }

  override def lookup(): ExtensionId[_ <: Extension] = {
    println("  ? Looking up ManualExtension")
    ManualExtension
  }

}

class ManualExtensionImpl extends Extension {

  val manualMsg = "Hello, Manual World!"

}