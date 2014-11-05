package org.kimbasoft.scala.oo.traits.simple

import scala.language.reflectiveCalls

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
trait Subject {
  type Observer = { def notify(subject: Any) }

  private var observers = List[Observer]()

  def addObserver(observer: Observer) = observers ::= observer

  def notifyObservers() = observers foreach (_.notify(this))
}
