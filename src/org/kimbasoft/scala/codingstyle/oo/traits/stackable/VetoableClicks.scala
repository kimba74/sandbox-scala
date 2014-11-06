package org.kimbasoft.scala.codingstyle.oo.traits.stackable

/**
 * Missing documentation
 *
 * @since 1.0
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
