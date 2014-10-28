package org.kimbasoft.scala.traits.simple

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Example {

  def main(args: Array[String]) {
    val button1 = new ButtonObservable("Okay")
    val observer1 = new ClickCountObserver
    button1.addObserver(observer1)
    for(i <- 1 to 3) button1.click()
    println("button1 clicks> " + observer1.counter)

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

}
