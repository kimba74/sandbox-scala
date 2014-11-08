package org.kimbasoft.scala.codingstyle.functional.datastructs

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Mapping {

  def main(args: Array[String]) {
    val valuesMap = Map("A" -> "Alpha", "B" -> "Beta", "G" -> "Gamma", "D" -> "Delta", "E" -> "Epsilon")
    println("Values: " + valuesMap)
    val lengthMap = valuesMap.map(kv => (kv._1, kv._2.length))
    println("Length: " + lengthMap)
  }
}
