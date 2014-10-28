package org.kimbasoft.scala.traits.simple

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class ButtonObservable(label: String) extends Button(label) with Subject {

  override def click(): Unit = {
    super.click()
    notifyObservers()
  }

}
