package org.kimbasoft.scala.codingstyle.oo.traits

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object TraitStackable {

  /**
   * Functional Trait defining and implementing the "observable"
   * behavior of a Button.
   */
  trait Subject {
    type Observer = { def notify(subject: Any) }

    private var observers = List[Observer]()

    def addObserver(observer: Observer) = observers ::= observer

    def notifyObservers() = observers foreach (_.notify(this))
  }

  /**
   * Functional Class implementing the Observer type defined by the
   * Trait Subject.
   */
  class ClickCountObserver {
    var count = 0

    def notify(subject: Any) = {
      count += 1
    }
  }

  /**
   * Base Trait of the Stackable Traits Pattern
   */
  trait Clickable {
    def click()
  }

  /**
   * Core implementation Class of the Stackable Trait Pattern
   */
  class Button(val label: String) extends Clickable {
    override def click() = {
      println("-- Button(" + label + "): Doing Button specific stuff")
    }
  }

  /**
   * Stackable Trait
   */
  trait ObservableClicks extends Clickable with Subject {
    abstract override def click() = {
      super.click()
      println("-- ObservableClicks: Now notifying Observers")
      notifyObservers()
    }
  }

  /**
   * Stackable Trait
   */
  trait VetoableClicks extends Clickable {
    val maxAllowed = 3
    private var count = 0

    abstract override def click() = {
      if (count < maxAllowed) {
        count += 1
        super.click()
        println("-- VetoableClicks: Permitted Click")
      }
      else {
        println("!! Maximum of " + maxAllowed + " clicks reached !!")
      }
    }
  }

  def main(args: Array[String]) {
    val button1 = new Button("OK") with ObservableClicks
    val observer1 = new ClickCountObserver
    button1.addObserver(observer1)
    for(i <- 1 to 4) button1.click()
    println("button1 clicks> " + observer1.count)

    val button2 = new Button("Cancel") with ObservableClicks with VetoableClicks
    val observer2 = new ClickCountObserver
    button2.addObserver(observer2)
    for(i <- 1 to 5) button2.click()
    println("button2 clicks> " + observer2.count)
  }
}
