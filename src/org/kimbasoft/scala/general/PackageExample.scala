package org.kimbasoft.scala.general

/**
 * Missing documentation
 *
 * @since 1.0
 */
object PackageExample {

  def main(args: Array[String]) {
    // Using the PackagePerson Class and Object as if it was in local package
    val p1 = PackagePerson("John", 34)
    println(p1)

    // Invoking a method declared at package level
    val x = 5
    println(x + "^2 = " + sqr(5))
  }
}
