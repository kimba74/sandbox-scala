package org.kimbasoft.scala.general

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class Person(name: String, age: Int) {

  def getName = name

  def getAge = age

  override def toString: String = name + "(" + age + ")"
}
