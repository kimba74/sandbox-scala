package org.kimbasoft.akka.extension

import akka.actor.{ExtendedActorSystem, Extension, ExtensionId, ExtensionIdProvider}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ConfigExtension extends ExtensionId[ConfigExtensionImpl] with ExtensionIdProvider {

  override def createExtension(system: ExtendedActorSystem): ConfigExtensionImpl = {
    println("  ! Created ConfigExtension")
    new ConfigExtensionImpl
  }

  override def lookup(): ExtensionId[_ <: Extension] = {
    println("  ? Looking up ConfigExtension")
    ConfigExtension
  }

}

class ConfigExtensionImpl extends Extension {

  val configMsg = "Hello, Config World!"

}