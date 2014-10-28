package org.kimbasoft.scala.traits.simple

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class ClickCountObserver {
  var counter = 0

  def notify(subject: Any) = {
    counter += 1
  }
}
