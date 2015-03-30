package org.kimbasoft.scala.codingstyle.oo.traits

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object TraitSimple extends App {

  /**
   * Extension Trait defining and implementing the basic functionality
   * that allows external observers to be notified when a button is
   * clicked.
   */
  trait Subject {
    // Define structural type Observer
    type Observer = { def notify(subject: Any) }
    // Create immutable private list to hold the registered observers
    private var observers = List[Observer]()
    // Declare method to add an observer
    def addObserver(observer: Observer) = observers ::= observer
    // Declare method to notify all registered observers
    def notifyObservers() = observers foreach (_.notify(this))
  }

  /**
   * Button Base Class defining the simple click() method.
   */
  class Button(val label: String) {
    def click() {
      // Do some Button specific logic
    }
  }

  /**
   * Simple Observer that implements the structural type defined in the
   * Subject Trait. This particular observer counts the amount of clicks
   * a button receives.
   */
  class ClickCountObserver {
    var counter = 0

    def notify(subject: Any) = {
      counter += 1
    }
  }

  /**
   * Specialized Button that extends the standard Button and adds the Subject
   * Trait. This allows this Button implementation to be observable.
   */
  class ButtonObservable(label: String) extends Button(label) with Subject {
    override def click(): Unit = {
      super.click()
      notifyObservers()
    }
  }


  // Example with special ButtonObservable Button.
  val button1 = new ButtonObservable("Okay")
  val observer1 = new ClickCountObserver
  button1.addObserver(observer1)
  for (i <- 1 to 3) button1.click()
  println("button1 clicks> " + observer1.counter)

  // Example with a regular Button instance implementing theSubject Trait on-the-fly
  val button2 = new Button("Cancel") with Subject {
    override def click() = {
      super.click()
      notifyObservers()
    }
  }
  val observer2 = new ClickCountObserver
  button2.addObserver(observer2)
  for (i <- 1 to 5) button2.click()
  println("button2 clicks> " + observer2.counter)
}
