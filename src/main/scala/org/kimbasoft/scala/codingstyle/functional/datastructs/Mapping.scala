package org.kimbasoft.scala.codingstyle.functional.datastructs

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Mapping {

  def main(args: Array[String]) {

    // Create a map of values consisting each of a key and a corresponding value
    val valuesMap = Map("A" -> "Alpha", "B" -> "Beta", "G" -> "Gamma", "D" -> "Delta", "E" -> "Epsilon")
    println("Map Values: " + valuesMap)

    /* Use the map() function on the value map to create a new map containing the
     * original key and the length of the value */
    val lengthMap1 = valuesMap.map(kv => (kv._1, kv._2.length))
    println("Map Length (1): " + lengthMap1)

    /* Same as above but use pattern matching to decompose the tuple into its key
     * and value part for easier usage */
    val lengthMap2 = valuesMap.map{ case (k, v) => (k, v.length) }
    println("Map Length (2): " + lengthMap2)

    /* Create a list of Int values */
    val valuesList = List(1, 2, 3, 4, 5, 6)
    println("List Values  : " + valuesList)

    /* Use the map() function on the list of Int values to create a new list of
     * Strings with each of the original values wrapped in between < and > */
    val modifyList = valuesList.map("<" + _ + ">")
    println("List Modified: " + modifyList)
  }
}
