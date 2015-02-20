package org.kimbasoft.scala.helper

/**
 * Missing documentation
 *
 * @since 1.0
 */
object PackagePerson {

  def apply(name: String, age: Int) = new PackagePerson(name, age)
}

class PackagePerson(name: String, age: Int) {

  override def toString: String = name + " is " + age + " years old"
}