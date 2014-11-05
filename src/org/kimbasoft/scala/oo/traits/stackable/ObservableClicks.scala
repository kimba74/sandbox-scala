package org.kimbasoft.scala.oo.traits.stackable

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
trait ObservableClicks extends Clickable with Subject {
  abstract override def click() = {
    super.click()
    println("-- ObservableClicks: Now notifying Observers")
    notifyObservers()
  }
}
