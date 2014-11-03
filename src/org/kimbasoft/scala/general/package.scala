package org.kimbasoft.scala

/**
 * Missing documentation
 *
 * @since 1.0
 */
package object general {

  // Specifying an out-of-package Class and Companion Object to appear as package local
  type PackagePerson = org.kimbasoft.scala.helper.PackagePerson
  val PackagePerson = org.kimbasoft.scala.helper.PackagePerson

  // Exposing a package level method
  def sqr(x: Int) = x * x
}
