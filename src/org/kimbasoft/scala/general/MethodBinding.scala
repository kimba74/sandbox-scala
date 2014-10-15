package org.kimbasoft.scala.general

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object MethodBinding {

  def main(args: Array[String]) {
    var list = List('b', 'c', 'd')
    println(list)

    // Right binding
    list = 'a' :: list
    println(list)

  }
}
