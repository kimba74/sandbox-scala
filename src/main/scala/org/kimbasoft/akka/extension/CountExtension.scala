package org.kimbasoft.akka.extension

import java.util.concurrent.atomic.AtomicLong

import akka.actor._

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object CountExtension extends ExtensionId[CountExtensionImpl] with ExtensionIdProvider {

  override def createExtension(system: ExtendedActorSystem): CountExtensionImpl = {
    println(".. Created CountExtension")
    new CountExtensionImpl
  }

  override def lookup(): ExtensionId[_ <: Extension] = CountExtension

}

class CountExtensionImpl extends Extension {

  private val counter = new AtomicLong(0)

  def increment() = counter.incrementAndGet()

}

trait Counting { self: Actor =>

  def increment() = CountExtension(context.system).increment()

}