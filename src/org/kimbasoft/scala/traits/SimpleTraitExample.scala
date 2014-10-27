package org.kimbasoft.scala.traits

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SimpleTraitExample {

  class Button(val name: String) {
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
    val button1 = new ObservableButton("Okay")
    val observer1 = new ClickCounter()

    button1.addObserver(observer1)

    for (i <- 1 to 3) button1.click()

    println("> " + observer1.counter)


    val button2 = new Button("Cancel") with Subject {
      override def click(): Unit = {
        super.click()
        notifyObservers()
      }
    }
    val observer2 = new ClickCounter()
    button2.addObserver(observer2)

    for (i <- 1 to 5) button2.click()

    println("> " + observer2.counter)


  }
}
