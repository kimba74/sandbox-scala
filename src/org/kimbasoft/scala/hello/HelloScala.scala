package org.kimbasoft.scala.hello

/**
 * Missing documentation
 *
 * @since 1.0
 */
class HelloScala(name: String) {

  def this() = this("stranger")

  def getName = name

  override def toString = s"Hello $name, this is the HelloScala class"

}
