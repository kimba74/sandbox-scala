package org.kimbasoft.scala.traits

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SimpleTraitExample {

  class Button(name: String) {
    def click() {
      // Do some button clicking related stuff
    }
  }

  trait Subject {
    type Observer = { def notify(subject: Any) }
    private var observers = List[Observer]()
    def addObserver(observer: Observer) = observers ::= observer
    def notifyObservers() = observers foreach (_.notify(this))
  }

  class ObservableButton(name: String) extends Button(name) with Subject {
    override def click() = {
      super.click()
      notifyObservers()
    }
  }

  class ClickCounter {
    var counter = 0
    def notify(subject: Any) = {
      counter += 1
    }
  }

  def main(args: Array[String]) {
    val button = new ObservableButton("Okay")
    val observer = new ClickCounter()

    button.addObserver(observer)

    for (i <- 1 to 3) button.click()

    println("> " + observer.counter)
  }
}
