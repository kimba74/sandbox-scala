package org.kimbasoft.scala.codingstyle.oo.traits.stackable

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class Button(val label: String) extends Clickable {
  override def click() = {
    println("-- Button(" + label + "): Doing Button specific stuff")
  }
}
