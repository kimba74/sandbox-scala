package org.kimbasoft.scala.injection

/**
 * Missing documentation
 *
 * @since 1.0
 */
object Helper {

  def forEach(strings: Array[String], f: String => Unit): Unit = for (str <- strings) f(str)

  def main(args:Array[String]): Unit ={
    val strings = Array("This","is","a","text","as","array")
    forEach(strings, s => println(s"element> $s"))
  }
}
