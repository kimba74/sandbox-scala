package org.kimbasoft.scala.codingstyle.oo.traits.stackable

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class ClickCountObserver {
  var count = 0

  def notify(subject: Any) = {
    count += 1
  }
}
