package org.kimbasoft.scala.oo.traits.stackable

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Example {

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
