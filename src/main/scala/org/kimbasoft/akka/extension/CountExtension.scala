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

  /**
   * Will be called when the Extension is being called first (instantiated)
   */
  override def createExtension(system: ExtendedActorSystem): CountExtensionImpl = {
    println("  ! Created CountExtension")
    new CountExtensionImpl
  }

  /**
   * Will be called when an Extension is being looked up and returns the factory
   * to create an instance of the Extension and register it with the ActorSystem.
   */
  override def lookup(): ExtensionId[_ <: Extension] = {
    println("  ? Looking up CountExtension")
    CountExtension
  }

}

class CountExtensionImpl extends Extension {

  private val counter = new AtomicLong(0)

  def increment() = counter.incrementAndGet()

}

trait Counting { self: Actor =>

  def increment() = CountExtension(context.system).increment()

}