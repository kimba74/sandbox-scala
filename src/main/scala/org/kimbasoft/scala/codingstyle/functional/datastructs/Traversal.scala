package org.kimbasoft.scala.codingstyle.functional.datastructs

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Traversal {

  //List, Set, Map (Tuple)

  def main(args: Array[String]) {

    // Traversing List
    println("-- List Traversal ------------------")
    val list = List(1, 2, 3, 4, 5)
    list foreach println

    // Traversing Set
    println("-- Set Traversal -------------------")
    val set = Set('A', 'B', 'C', 'D', 'E')
    set foreach println

    // Traversing Map
    println("-- Map Traversal -------------------")
    val map = Map(1 -> "Alpha", 2 -> "Beta", 3 -> "Gamma", 4 -> "Delta", 5 -> "Epsilon")
    map foreach println
  }
}
