package org.kimbasoft.scala.general

/**
 * Missing documentation
 *
 * @since 1.0
 */
object MethodInvocation {

  def toCollection(str: String): Seq[String] = str.split(' ')

  def main(args: Array[String]) {
    val str = "This will be a list of Strings"

    println("-- Type 1 ----------------")
    toCollection(str).foreach((s:String) => println(s))
    println("-- Type 2 ----------------")
    toCollection(str).foreach(s => println(s))
    println("-- Type 3 ----------------")
    toCollection(str).foreach(println(_))
    println("-- Type 4 ----------------")
    toCollection(str) foreach (println(_))
    println("-- Type 5 ----------------")
    toCollection(str) foreach { println(_) }

  }
}
